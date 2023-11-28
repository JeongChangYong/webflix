package webflix.service.memberRegist;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import webflix.command.MemberCommand;
import webflix.domain.MemberDTO;
import webflix.mapper.UserMapper;
import webflix.service.EmailSendService;

@Service
public class UserWriteService {
	@Autowired
	PasswordEncoder passwordEncoder;
	@Autowired
	UserMapper userMapper;
	@Autowired
	EmailSendService emailSendService;
	public void execute(MemberCommand memberCommand , Model model) {
		MemberDTO dto = new MemberDTO();
		
		dto.setMemId(memberCommand.getMemId());
		dto.setMemPw(passwordEncoder.encode(memberCommand.getMemPw()));
		dto.setMemName(memberCommand.getMemName());
		dto.setMemAddr(memberCommand.getMemAddr());
		dto.setMemAddrDetail(memberCommand.getMemAddrDetail());
		dto.setMemPost(memberCommand.getMemPost());
		dto.setMemPhone(memberCommand.getMemPhone());
		dto.setMemEmail(memberCommand.getMemEmail());
		dto.setMemJumin(memberCommand.getMemJumin());
		
		int  i =userMapper.userInsert(dto);
		model.addAttribute("userName", dto.getMemName());
		model.addAttribute("userEmail", dto.getMemEmail());
		
		if(i >=1) {
			//메일링
			String html = "<html><body>"
					+ "		정창용님 회원 가입을 축하합니다. <br />"
					+ " 가입을 완료하시려면 <a href='http://localhost:8080/userConfirm?chk="+dto.getMemEmail()+"'>여기</a>"
					+ "를 눌러주세요.";
			String subject = "환영 인사입니다.";
		 	String fromEmail = "hiland00@gmail.com"; //관리자 이메일
		 	String toEmail = dto.getMemEmail();
		 	
		 	emailSendService.mailsend(html, subject, fromEmail, toEmail);
		 	
		 	
		}
		
	}
}
