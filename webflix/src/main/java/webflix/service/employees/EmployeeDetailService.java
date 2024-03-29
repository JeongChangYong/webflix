package webflix.service.employees;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import webflix.domain.EmployeeDTO;
import webflix.mapper.EmployeeMapper;

@Service
public class EmployeeDetailService {
	@Autowired
	EmployeeMapper employeeMapper ;
	public void execute(String empNum, Model model) {
		EmployeeDTO vo = employeeMapper.employeeOneSelect(empNum);
		model.addAttribute("employeeCommand", vo);
	}
	
}




