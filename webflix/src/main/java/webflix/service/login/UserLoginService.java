package webflix.service.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import webflix.command.LoginCommand;
import webflix.domain.AuthInfoDTO;
import webflix.mapper.UserMapper;

@Service
public class UserLoginService {
	@Autowired
	UserMapper userMapper;
	@Autowired
	PasswordEncoder passwordEncoder;

	public void execute(LoginCommand loginCommand, HttpSession session, BindingResult result, HttpServletResponse response) {

		String userId = loginCommand.getUserId();
		String userPw = loginCommand.getUserPw();

		AuthInfoDTO dto = userMapper.loginSelect(userId);
		if (userId != "" && userId !=null) {
			if (dto != null) {

				if (dto.getUserEmailCheck() == null) {
					System.out.println("이메일 인증이 필요할 때");
					result.rejectValue("userId", "loginCommand.userId", "이메일 인증이 필요합니다.");

				}
				if (passwordEncoder.matches(userPw, dto.getUserPw())) {
					System.out.println("비밀번호가 일치합니다.");
					session.setAttribute("auth", dto);
					if(loginCommand.getIdStore() != null && loginCommand.getIdStore()) {
						//쿠키생성
						Cookie cookie = new Cookie("idStore", loginCommand.getUserId());
						
						//저장경로
						cookie.setPath("/");
						
						//수명주기
						cookie.setMaxAge(60*60*24*30);
						
						//사용자에게 쿠키 전송
						response.addCookie(cookie);
					}else {
						//쿠키생성
						Cookie cookie = new Cookie("idStore", loginCommand.getUserId());
						
						//저장경로
						cookie.setPath("/");
						
						//수명주기 - 쿠키 삭제
						cookie.setMaxAge(0);
						
						//사용자에게 쿠키 전송
						response.addCookie(cookie);
						
					}
					if(loginCommand.getAutoLogin() != null && loginCommand.getAutoLogin()) {
						//쿠키생성
						Cookie cookie = new Cookie("autoLogin", loginCommand.getUserId());
						
						//저장경로
						cookie.setPath("/");
						
						//수명주기
						cookie.setMaxAge(60*60*24*30);
						
						//사용자에게 쿠키 전송
						response.addCookie(cookie);
					}
					
					
				} else {
					System.out.println("비밀번호 불일치");
					result.rejectValue("userPw", "loginCommand.userPw", "비밀번호가 불일치합니다.");

					
				}

			} else {
				// 아이디가 존재하지 않는다
				result.rejectValue("userId", "loginCommand.userId", "아이디가 존재하지 않습니다.");

			}
		}
	}
}
