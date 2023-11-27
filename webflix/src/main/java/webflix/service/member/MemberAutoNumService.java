package webflix.service.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import webflix.command.MemberCommand;
import webflix.domain.MemberDTO;
import webflix.mapper.MemberMapper;

@Service
public class MemberAutoNumService {
	@Autowired
	MemberMapper memberMapper;
	public void execute(Model model) {
		
		String memberNum = memberMapper.memberAutoNum();
		
		MemberCommand dto = new MemberCommand();
		dto.setMemNum(memberNum);
		model.addAttribute("memberCommand", dto);
	}
}
