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
import webflix.command.EmployeeCommand;
import webflix.service.employeeMyPage.EmployeeDropService;
import webflix.service.employeeMyPage.EmployeeInfoService;
import webflix.service.employeeMyPage.EmployeeInfoUpdateService;
import webflix.service.employeeMyPage.EmployeePassConfirmService;
import webflix.service.employeeMyPage.EmployeePwModifyService;

@Controller
public class EmployeeMyPageController {
	@Autowired
	EmployeeInfoService employeeInfoService;
	@Autowired
	EmployeePwModifyService employeePwModifyService;
	@Autowired
	EmployeePassConfirmService employeePassConfirmService;
	@Autowired
	EmployeeDropService employeeDropService;
	@Autowired
	EmployeeInfoUpdateService employeeInfoUpdateService;
	@RequestMapping("empMyPage")
	public String empMyPage(HttpSession session, Model model) {
		employeeInfoService.execute(session, model);
		return "thymeleaf/worker/myInfo";
	}
	@GetMapping("employeePwModify")
	public String employeePwModify() {
		
		return "thymeleaf/worker/myPwCon";
	}
	@PostMapping("employeePwModify")
	public String employeePwModify(@RequestParam("empPw")String empPw, Model model, HttpSession session) {
		
		return employeePwModifyService.execute(session, model, empPw);
	}
	@PostMapping("empPwUpdate")
	public @ResponseBody boolean empPwUpdate(@RequestParam("oldPw")String oldPw, @RequestParam("newPw") String newPw,HttpSession session) {
		
		return employeePassConfirmService.execute(newPw, oldPw, session);
		
	}@GetMapping("employeeDrop")
	public String employeeDrop() {
		
		
		return "thymeleaf/worker/employeeDrop";
		
	}@PostMapping("employeeDropOk")
	public String employeeDrop(@RequestParam("empPw")String empPw, Model model, HttpSession session) {
		int i  = employeeDropService.execute(empPw, session, model);
		if(i ==1) {
			
			session.invalidate();
			return "redirect:/";
		}else {
			
			return "thymeleaf/worker/employeeDrop";
		}
	}
	@GetMapping("employeeUpdate")
	public String employeeUpdate(HttpSession session, Model model) {
		employeeInfoService.execute(session, model);
		return "thymeleaf/worker/myModify";
	}
	@PostMapping("employeeUpdate")
	public String employeeUpdate(@Validated EmployeeCommand employeeCommand, BindingResult result, HttpSession session) {
		
		employeeInfoUpdateService.execute(employeeCommand, session, result);
		if(result.hasErrors()) {
			return "thymeleaf/worker/myModify";
			
		}else {
			
			return "redirect:empMyPage";
		}
	}
}
