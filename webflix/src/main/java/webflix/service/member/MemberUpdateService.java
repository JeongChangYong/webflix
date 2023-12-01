package webflix.service.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import webflix.command.MemberCommand;
import webflix.domain.MemberDTO;
import webflix.mapper.MemberMapper;

@Service
public class MemberUpdateService {
	@Autowired
	MemberMapper memberMapper;
	public void execute(MemberCommand memberCommand) {
		
		MemberDTO dto = new MemberDTO();
		
		dto.setMemNum(memberCommand.getMemNum());
		dto.setMemAddr(memberCommand.getMemAddr());
		dto.setMemAddrDetail(memberCommand.getMemAddrDetail());
		dto.setMemEmail(memberCommand.getMemEmail());
		dto.setMemEventWin(memberCommand.getMemEventWin());
		dto.setMemId(memberCommand.getMemId());
		dto.setMemJumin(memberCommand.getMemJumin());
		dto.setMemName(memberCommand.getMemName());
		dto.setMemPhone(memberCommand.getMemPhone());
		dto.setMemPoint(memberCommand.getMemPoint());
		dto.setMemPost(memberCommand.getMemPost());
		dto.setMemRegist(memberCommand.getMemRegist());
		dto.setPaymentStatus(memberCommand.getPaymentStatus());
		memberMapper.memberUpdate(dto);
		
	}
}
