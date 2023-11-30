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
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.servlet.http.HttpSession;
import webflix.command.MemberCommand;
import webflix.service.memberMyPage.MemberDropService;
import webflix.service.memberMyPage.MemberInfoService;
import webflix.service.memberMyPage.MemberInfoUpdateService;
import webflix.service.memberMyPage.MemberPwModifyService;
import webflix.service.memberMyPage.MyPassConfirmService;

@Controller
public class MemberMyPageController {
	@Autowired
	MemberInfoService memberInfoService;
	@Autowired
	MemberPwModifyService memberPwModifyService;
	@Autowired
	MyPassConfirmService myPassConfirmService;
	@Autowired
	MemberDropService memberDropService;
	@Autowired
	MemberInfoUpdateService memberInfoUpdateService;
	@RequestMapping("myDetail")
	public String myDetail(HttpSession session, Model model) {
		memberInfoService.execute(session, model);
		return "thymeleaf/memberShip/myInfo";
	}
	@GetMapping("userPwModify")
	public String userPwModify() {
		
		return "thymeleaf/memberShip/myPwCon";
	}
	@PostMapping("memberPwModify")
	public String memberPwModify(@RequestParam("memPw")String memPw, Model model, HttpSession session) {
		
	
		return memberPwModifyService.execute(session, model, memPw);
		
	}
	@PostMapping("myPwUpdate")
	public @ResponseBody boolean myPwUpdate(@RequestParam("oldPw")String oldPw, @RequestParam("newPw") String newPw,
								HttpSession session) {
		
		return myPassConfirmService.execute(newPw, oldPw, session);
	}
	@GetMapping("memberDrop")
	public String memberDrop() {
		
		return "thymeleaf/memberShip/memberDrop";
	}
	@PostMapping("memberDropOk")
	public String memberDrop(@RequestParam("memPw")String memPw, Model model, HttpSession session) {
		int i = memberDropService.execute(memPw, session, model);
		if(i ==1) {
			
			return "thymeleaf/memberShip/memberDrop";
		}else {
			
			return "thymeleaf/memberShip/memberDrop";
		}
	}
	@GetMapping("memberUpdate")
	public String memberUpdate(HttpSession session, Model model) {
		memberInfoService.execute(session, model);
		return "thymeleaf/memberShip/myModify";
	}
	@PostMapping("memberUpdate")
	public String memberUpdate(@Validated MemberCommand memberCommand, BindingResult result, HttpSession session) {
		memberInfoUpdateService.execute(memberCommand, session, result);
		if(result.hasErrors()) {
			return "thymeleaf/memberShip/myModify";
			
		}
		
		return "redirect:myDetail";
	}
}
