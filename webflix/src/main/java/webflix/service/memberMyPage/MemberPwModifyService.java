package webflix.service.memberMyPage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import jakarta.servlet.http.HttpSession;
import webflix.domain.AuthInfoDTO;

@Service
public class MemberPwModifyService {
	@Autowired
	PasswordEncoder passwordEncoder;
	public String execute(HttpSession session, Model model, String memPw) {
		
		AuthInfoDTO auth = (AuthInfoDTO)session.getAttribute("auth");
		if(passwordEncoder.matches(memPw, auth.getUserPw())) {
			return "thymeleaf/memberShip/myNewPw";
			
		}else {
			model.addAttribute("errPw", "비밀번호가 틀렸습니다.");
			 return "thymeleaf/memberShip/myPwCon";
			
		}
		
		
		
	}
}
