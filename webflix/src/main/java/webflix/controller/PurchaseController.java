package webflix.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import webflix.service.purchase.WebPurchaseService;


@Controller
public class PurchaseController {
	@Autowired
	WebPurchaseService webPurchaseService;
	@GetMapping("webPurchase")
	public String webPay(String purchaseNum,
			HttpSession session,Model model) {
		
		return "thymeleaf/purchase/webPay";
	}
	@PostMapping("INIstdpay_pc_return")
	public String payReturn(HttpServletRequest request, HttpSession session, Model model) {
		//iniPayReturnService.execute(request, session,model);
		return "thymeleaf/purchase/buyfinished";
	}
	@GetMapping("close")
	public String close() {
		
		return "redirect:/";
	}
	
	
	
}
