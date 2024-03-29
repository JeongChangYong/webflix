package webflix.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import webflix.command.EmployeeCommand;
import webflix.service.employees.EmployeeAutoNumService;
import webflix.service.employees.EmployeeDeleteService;
import webflix.service.employees.EmployeeDetailService;
import webflix.service.employees.EmployeeInsertService;
import webflix.service.employees.EmployeeListService;
import webflix.service.employees.EmployeeUpdateService;
import webflix.service.employees.EmployeesDeleteService;

@Controller

public class EmployeeController {
	@Autowired
	EmployeeListService employeeListService;
	@Autowired
	EmployeeUpdateService employeeUpdateService;
	@RequestMapping("employeeList")
	public String empList(
			@RequestParam(value="page", required = false, defaultValue = "1" ) int page,
			@RequestParam(value="searchWord" , required = false) String searchWord,
			Model model) {
		employeeListService.execute(searchWord, page, model);
		return "thymeleaf/employee/employeeList";
	}

	@Autowired
	EmployeeAutoNumService employeeAutoNumService;

	@RequestMapping(value = "empRegist", method = RequestMethod.GET)
	public String form(EmployeeCommand employeeCommand, Model model) {
		employeeAutoNumService.execute(employeeCommand, model);
		return "thymeleaf/employee/employeeForm";
	}

	@Autowired
	EmployeeInsertService employeeInsertService;

	@RequestMapping(value = "empRegist", method = RequestMethod.POST)
	public String from(@Validated EmployeeCommand employeeCommand, BindingResult result) {
		if (result.hasErrors()) {
			return "thymeleaf/employee/employeeForm";
		}
		if (!employeeCommand.isEmpPwEqualsEmpPwCon()) {
			System.out.println("비밀번호 확인이 다릅니다.");
			return "thymeleaf/employee/employeeForm";
		}
		employeeInsertService.execute(employeeCommand);
		return "redirect:employeeList";
	}

	@Autowired
	EmployeeDetailService employeeDetailService;

	@RequestMapping("employeeDetail")
	public String employeeDetail(@RequestParam(value = "empNum") String empNum, Model model) {
		employeeDetailService.execute(empNum, model);
		return "thymeleaf/employee/empDetail";
	}

	@RequestMapping(value = "empModify", method = RequestMethod.GET)
	public String employeeUpdate(@RequestParam(value = "empNum") String empNum, Model model) {

		employeeDetailService.execute(empNum, model);
		return "thymeleaf/employee/employeeUpdate";
	}

	

	@RequestMapping(value = "empModify", method = RequestMethod.POST)
	public String employeeUpdate(@Validated EmployeeCommand employeeCommand, BindingResult result) {
		if (result.hasErrors()) {
			System.out.println("에러발생");
			return "thymeleaf/employee/employeeUpdate";
		}
		employeeUpdateService.execute(employeeCommand);
		return "redirect:employeeDetail?empNum=" + employeeCommand.getEmpNum();
	}

	@Autowired
	EmployeeDeleteService employeeDeleteService;
	@RequestMapping("empDelete")
	public String employeeDelete(@RequestParam(value = "empNum") String empNum) {
		employeeDeleteService.execute(empNum);
		return "redirect:employeeList";
	}
	
	@Autowired
	EmployeesDeleteService employeesDeleteService;
	@RequestMapping("empsDelete")
	public String membersDelete(
			@RequestParam(value="empsDel") String empsDel []) {
		employeesDeleteService.execute(empsDel);
		return "redirect:employeeList";
	}
}