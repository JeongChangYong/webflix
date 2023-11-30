package webflix.service.memberMyPage;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import jakarta.servlet.http.HttpSession;
import webflix.domain.AuthInfoDTO;
import webflix.mapper.MemberMyMapper;

@Service
public class MemberDropService {
	@Autowired
	MemberMyMapper memberMyMapper;
	@Autowired
	PasswordEncoder passwordEncoder;
	public int execute(String memPw, HttpSession session, Model model) {
		AuthInfoDTO auth = (AuthInfoDTO)session.getAttribute("auth");
		if(passwordEncoder.matches(memPw, auth.getUserPw())) {
			
			memberMyMapper.memberDrop(auth.getUserId());
			return 1;
		}else {
			model.addAttribute("errPw", "비밀번호가 틀렸습니다.");
			return 0;
		}
	}
}
