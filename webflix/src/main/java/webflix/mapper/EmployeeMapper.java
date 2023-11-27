package webflix.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import webflix.domain.EmployeeDTO;
import webflix.domain.StartEndPageDTO;


@Mapper
public interface EmployeeMapper {
	public Integer employeeInsert(EmployeeDTO dto);
	public String autoNum();
	public List<EmployeeDTO> employeeAllSelect(StartEndPageDTO dto);
	public EmployeeDTO employeeOneSelect(String empNum);
	public Integer employeeUpdate(EmployeeDTO dto);
	public Integer employeeDelete(String empNum);
	public Integer employeesDelete(@Param("empsDel") String empsDel[]);
	public Integer employeeCount(String searchWord);
	public String getEmpNum(String empId);
}




























