package webflix.service.purchase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpSession;
import webflix.domain.AuthInfoDTO;
import webflix.domain.MemberDTO;
import webflix.mapper.MemberMyMapper;
import webflix.mapper.PurchaseMapper;

@Service
public class PaymentCheckService {
	@Autowired
	MemberMyMapper memberMyMapper;
	@Autowired
	PurchaseMapper purchaseMapper;
	public String execute(HttpSession session) {
		
		AuthInfoDTO auth = (AuthInfoDTO)session.getAttribute("auth");
		MemberDTO memDTO = memberMyMapper.memberInfo(auth.getUserId());
		
		Integer i = purchaseMapper.paymentSelect(memDTO.getMemNum());
		if(i == null) {
			
			return "thymeleaf/purchase/webPay";
		}
		else {
			
			
			return "redirect:detailView";
		}
		
		
	}
}
