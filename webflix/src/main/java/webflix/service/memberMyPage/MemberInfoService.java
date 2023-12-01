package webflix.service.memberMyPage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import jakarta.servlet.http.HttpSession;
import webflix.command.MemberCommand;
import webflix.domain.AuthInfoDTO;
import webflix.domain.MemberDTO;
import webflix.mapper.MemberMyMapper;

@Service
public class MemberInfoService {
	@Autowired
	MemberMyMapper memberMyMapper;
	public void execute(HttpSession session, Model model) {
		AuthInfoDTO authInfo = (AuthInfoDTO)session.getAttribute("auth");
		String memberId = authInfo.getUserId();
		MemberDTO dto = memberMyMapper.memberInfo(memberId);
		MemberCommand memberCommand = new  MemberCommand();
		memberCommand.setMemAddr(dto.getMemAddr());
		memberCommand.setMemAddrDetail(dto.getMemAddrDetail());
		memberCommand.setMemEmail(dto.getMemEmail());
		memberCommand.setMemEventWin(dto.getMemEventWin());
		memberCommand.setMemId(dto.getMemId());
		memberCommand.setMemJumin(dto.getMemJumin());
		memberCommand.setMemName(dto.getMemName());
		memberCommand.setMemNum(dto.getMemNum());
		memberCommand.setMemPhone(dto.getMemPhone());
		memberCommand.setMemPoint(dto.getMemPoint());
		memberCommand.setMemPost(dto.getMemPost());
		memberCommand.setMemPw(dto.getMemPw());
		memberCommand.setMemRegist(dto.getMemRegist());
		memberCommand.setPaymentStatus(dto.getPaymentStatus());
		model.addAttribute("memberCommand", memberCommand);
		
	}
}
