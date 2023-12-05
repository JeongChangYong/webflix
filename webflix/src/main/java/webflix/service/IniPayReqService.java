package webflix.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.inicis.std.util.SignatureUtil;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import webflix.domain.AuthInfoDTO;

@Service
public class IniPayReqService {
	public void execute(
			HttpSession session,Model model, String price, HttpServletResponse response) throws Exception {
		
	AuthInfoDTO auth = (AuthInfoDTO)session.getAttribute("auth");	
	
	String mid					= "INIpayTest";		                    // 상점아이디					
	String signKey			    = "SU5JTElURV9UUklQTEVERVNfS0VZU1RS";	// 웹 결제 signkey
	
	String mKey = SignatureUtil.hash(signKey, "SHA-256");

	String timestamp			= SignatureUtil.getTimestamp();			// util에 의해서 자동생성
	String orderNumber			= mid+"_"+SignatureUtil.getTimestamp();	// 가맹점 주문번호(가맹점에서 직접 설정)

	Map<String, String> signParam = new HashMap<String, String>();

	signParam.put("oid", orderNumber);
	signParam.put("price", price);
	signParam.put("timestamp", timestamp);

	String signature = SignatureUtil.makeSignature(signParam);
	model.addAttribute("timestamp", timestamp);
	model.addAttribute("mid", mid);
	model.addAttribute("signature", signature);
	model.addAttribute("orderNumber", orderNumber);
	model.addAttribute("mKey", mKey);
	model.addAttribute("price", price);

	
	Cookie cookie = new Cookie("userId", auth.getUserId());
	
	//저장경로
	cookie.setPath("/");
	
	//수명주기
	cookie.setMaxAge(60*60*24);
	
	//사용자에게 쿠키 전송
	response.addCookie(cookie);
	
	
	}
}
