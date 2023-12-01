package webflix.service.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import webflix.command.MemberCommand;
import webflix.domain.MemberDTO;
import webflix.mapper.MemberMapper;

@Service
public class MemberInsertService {
	@Autowired
	MemberMapper memberMapper;
	@Autowired
	PasswordEncoder passwordEncoder;
	public void execute(MemberCommand memberCommand) {
		
		MemberDTO dto = new MemberDTO();
		
		dto.setMemAddr(memberCommand.getMemAddr());
		dto.setMemAddrDetail(memberCommand.getMemAddrDetail());
		dto.setMemEmail(memberCommand.getMemEmail());
		dto.setMemId(memberCommand.getMemId());
		dto.setMemJumin(memberCommand.getMemJumin());
		dto.setMemName(memberCommand.getMemName());
		dto.setMemNum(memberCommand.getMemNum());
		dto.setMemPhone(memberCommand.getMemPhone());
		dto.setMemPost(memberCommand.getMemPost());
		dto.setMemPw(passwordEncoder.encode(memberCommand.getMemPw()));
		dto.setMemRegist(memberCommand.getMemRegist());
		dto.setPaymentStatus(memberCommand.getPaymentStatus());
		memberMapper.memberInsert(dto);
		
	}
}
