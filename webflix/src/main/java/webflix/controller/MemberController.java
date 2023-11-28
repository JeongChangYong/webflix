package webflix.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import webflix.command.MemberCommand;
import webflix.service.member.MemberAutoNumService;
import webflix.service.member.MemberDetailService;
import webflix.service.member.MemberInsertService;
import webflix.service.member.MemberListService;
import webflix.service.member.MemberUpdateService;

@Controller
public class MemberController {
	@Autowired
	MemberAutoNumService memberAutoNumService;
	@Autowired
	MemberInsertService memberInsertService;
	@Autowired
	MemberListService memberListService;
	@Autowired
	MemberDetailService memberDetailService;
	@Autowired
	MemberUpdateService memberUpdateService;
	@RequestMapping("memberList")
	public String memberList(Model model, @RequestParam(value="searchWord", required = false)String searchWord,
			 @RequestParam(value="page" , required = false , defaultValue = "1") int page) {
		memberListService.execute(model, searchWord, page);
		return "thymeleaf/member/memberList";
	}
	@GetMapping("memberRegist")
	public String memberRegist(MemberCommand memberCommand, Model model) {
		memberAutoNumService.execute(model);
		return "thymeleaf/member/memberForm";
	}
	@PostMapping("memberRegist")
	public String memberRegist(@Validated MemberCommand memberCommand, BindingResult result) {
		if (result.hasErrors()) {
			return "thymeleaf/member/memberForm";
		}
		if (!memberCommand.isMemberPwEqualsMemberPwCon()) {
			System.out.println("비밀번호 확인이 다릅니다.");
			return "thymeleaf/member/memberForm";
		}else {
			memberInsertService.execute(memberCommand);
			return "redirect:memberList";
			
		}
		
	}
	@RequestMapping("memberDetail")
	public String memberDetail(@RequestParam("memberNum")String memberNum, Model model) {
		
		memberDetailService.execute(memberNum, model);
		return "thymeleaf/member/memberDetail";
	}
	@GetMapping("memberUpdate")
	public String memberUpdate(@RequestParam("memberNum")String memberNum, Model model) {
		
		memberDetailService.execute(memberNum, model);
		return "thymeleaf/member/memberModify";
	}
	@PostMapping("memberModify")
	public String memberModify(MemberCommand memberCommand) {
		
		memberUpdateService.execute(memberCommand);
		
		return "redirect:memberDetail?memberNum="+memberCommand.getMemNum();
	}
	
}
