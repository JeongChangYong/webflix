package webflix.service.purchase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import jakarta.servlet.http.HttpSession;
import webflix.command.PurchaseCommand;
import webflix.domain.AuthInfoDTO;
import webflix.domain.MemberDTO;
import webflix.domain.PurchaseDTO;
import webflix.mapper.MemberMyMapper;
import webflix.mapper.PurchaseMapper;

@Service
public class WebPurchaseService {
	@Autowired
	MemberMyMapper memberMyMapper;
	@Autowired
	PurchaseMapper purchaseMapper;
	public String execute(PurchaseCommand purchaseCommand, HttpSession session, Model model) {
		AuthInfoDTO auth = (AuthInfoDTO)session.getAttribute("auth");
		MemberDTO memDTO = memberMyMapper.memberInfo(auth.getUserId());
		String purchaseNum = purchaseMapper.selectNum();
		
		PurchaseDTO dto = new PurchaseDTO();
		
		dto.setOrderNum(purchaseNum);
		dto.setMemNum(memDTO.getMemNum());
		dto.setOrderPrice(purchaseCommand.getOrderPrice());
		dto.setPurchaseStatus("결제대기중");
		
		purchaseMapper.purchaseInsert(dto);
		
		return purchaseNum;
		
	}
}
