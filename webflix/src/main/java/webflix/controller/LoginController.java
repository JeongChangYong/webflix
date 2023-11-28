package webflix.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import webflix.command.LoginCommand;
import webflix.service.login.IdCheckService;
import webflix.service.login.UserLoginService;

@Controller
public class LoginController {
	@Autowired
	IdCheckService idCheckService;
	@Autowired
	UserLoginService userLoginService;
	@PostMapping("userIdCheck")
	public @ResponseBody String userIdCheck(@RequestParam(value="userId")String userId) {
		String resultId = idCheckService.execute(userId);
		
		if(resultId == null) {
			
			return "사용가능한 아이디입니다.";
			
		}else {
			return "사용중인 아이디입니다.";
			
		}
		
		
	}
	@GetMapping("login")
	public String login(LoginCommand loginCommand) {
		
		return "thymeleaf/login";
	}
	
	@PostMapping("login")
	public String login(@Validated LoginCommand loginCommand, BindingResult result , HttpSession session ,HttpServletResponse response ) {
		
		userLoginService.execute(loginCommand, session, result , response);
		if(result.hasErrors()) {
			
			return "thymeleaf/login";
		}
		
		return "redirect:/";
		
	}
	@GetMapping("logout")
	public String logout(HttpSession session, HttpServletResponse response) {
		
		//쿠키생성
		Cookie cookie = new Cookie("autoLogin", "");
				
		//저장경로
		cookie.setPath("/");
				
		//수명주기
		cookie.setMaxAge(0);
				
		//사용자에게 쿠키 전송
		response.addCookie(cookie);
		
		session.invalidate();
		
		return "redirect:/";
	}
	
	
	
	
}
