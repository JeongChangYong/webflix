package webflix.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import webflix.service.EmailCheckService;
import webflix.service.UserEmailCheckService;

@RestController //html코드는 못넘김

public class CheckRestController {
	@Autowired
	EmailCheckService emailCheckService;
	@Autowired
	UserEmailCheckService userEmailCheckService;
	@RequestMapping(value = "checkRest/userEmailCheck" , method = RequestMethod.POST)
	public String userEmailCheck(@RequestParam(value="userEmail")String userEmail) {
		
		String resultEmail  = emailCheckService.execute(userEmail);
		
		if(resultEmail == null) {
			
			return "사용가능한 이메일입니다.";
			
		}else {
			return "사용중인 이메일입니다.";
			
		}
		
		
	}
	@RequestMapping("userConfirm")
	public String userConfirm(@RequestParam(value="chk")String chk) {
		int i =userEmailCheckService.execute(chk);
		if(i ==0 ) {
			
			return "이미 인증되었습니다.";
		}else 
		return "인증되었습니다.";
	}
}
