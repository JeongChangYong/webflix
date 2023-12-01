package webflix.service.employeeMyPage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import jakarta.servlet.http.HttpSession;
import webflix.domain.AuthInfoDTO;
import webflix.mapper.EmployeeMyMapper;

@Service
public class EmployeeDropService {
	@Autowired
	PasswordEncoder passwordEncoder;
	@Autowired
	EmployeeMyMapper employeeMyMapper;
	public int execute(String empPw, HttpSession session, Model model) {
		AuthInfoDTO auth = (AuthInfoDTO)session.getAttribute("auth");
		if(passwordEncoder.matches(empPw, auth.getUserPw())) {
			
			employeeMyMapper.employeeDrop(auth.getUserId());
			return 1;
		}else {
			
			model.addAttribute("errPw", "비밀번호가 틀렸습니다.");
			return 0;
		}
		
	}
}
