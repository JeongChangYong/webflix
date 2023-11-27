package webflix.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import webflix.command.MemberCommand;

@Controller
@RequestMapping("member")
public class MemberController {
	@RequestMapping("memberList")
	public String memberList() {
		
		return "thymeleaf/member/memberList";
	}
	@GetMapping("memberRegist")
	public String memberRegist(MemberCommand memberCommand, Model model) {
		
		return "thymeleaf/member/memberForm";
	}
	@PostMapping("memberRegist")
	public String memberRegist(MemberCommand memberCommand, BindingResult result) {
		if (result.hasErrors()) {
			return "thymeleaf/member/memberForm";
		}
		if (!memberCommand.isMemberPwEqualsMemberPwCon()) {
			System.out.println("비밀번호 확인이 다릅니다.");
			return "thymeleaf/member/memberForm";
		}
		return "redirect:memberList";
		
	}
	
}
