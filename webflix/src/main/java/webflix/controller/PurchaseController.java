package webflix.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import webflix.service.IniPayReqService;
import webflix.service.purchase.IniPayReturnService;
import webflix.service.purchase.WebPurchaseService;


@Controller
public class PurchaseController {
	@Autowired
	WebPurchaseService webPurchaseService;
	@Autowired
	IniPayReqService iniPayReqService;
	@Autowired
	IniPayReturnService iniPayReturnService;
	@GetMapping("webPurchase")
	public String webPay() {
		return "thymeleaf/purchase/webPay";
	}
	@PostMapping("webPurchase")
	public String webPay(
			@RequestParam("price") String price,
			HttpSession session,Model model, HttpServletResponse response) {
		try {
			iniPayReqService.execute(session, model, price, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "thymeleaf/purchase/payment";
	}
	@PostMapping("INIstdpay_pc_return")
	public String payReturn(HttpServletRequest request, HttpSession session, Model model, HttpServletResponse response) {
		iniPayReturnService.execute(request, session,model,  response);
		return "thymeleaf/purchase/buyfinished";
	}
	@GetMapping("close")
	public String close(HttpServletResponse response) {
		
		return "thymeleaf/purchase/close";
	}
	
	
	
}
