package webflix.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import webflix.command.MemberCommand;
import webflix.service.memberRegist.UserWriteService;

@Controller
public class MemberRegisterController {
	@Autowired
	UserWriteService userWriteService;
	@ModelAttribute
	public MemberCommand memberCommand() {
		return new MemberCommand();
		
	}
	@GetMapping("userWrite")
	public String userWrite() {
		
		return "thymeleaf/memberRegist/userForm";
	}
	@PostMapping("userRegist")
	public String userRegist(@Validated MemberCommand memberCommand, BindingResult result , Model model) {
		if(result.hasErrors()) {
			
			return "thymeleaf/memberRegist/userForm";
		}
		if(!memberCommand.isMemberPwEqualsMemberPwCon()) {
			result.rejectValue("memberPwCon", "memberCommand.memberPwCon", "비밀번호 확인이 틀렸습니다.");
			return "thymeleaf/memberRegist/userForm";
			
		}
		userWriteService.execute(memberCommand, model);
		return "thymeleaf/memberRegist/welcome";
	}
}
