package webflix.service.employees;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import webflix.command.EmployeeCommand;
import webflix.mapper.EmployeeMapper;
@Service
public class EmployeeAutoNumService {
	@Autowired
	EmployeeMapper employeeMapper;
	public void execute(EmployeeCommand employeeCommand, Model model) {
		String empNum = employeeMapper.autoNum(); 
		employeeCommand.setEmpNum(empNum);
		model.addAttribute("employeeCommand", employeeCommand);
	}
}