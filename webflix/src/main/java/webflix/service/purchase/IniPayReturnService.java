package webflix.service.purchase;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.inicis.std.util.HttpUtil;
import com.inicis.std.util.ParseUtil;
import com.inicis.std.util.SignatureUtil;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import webflix.command.LoginCommand;
import webflix.domain.AuthInfoDTO;
import webflix.domain.MemberDTO;
import webflix.domain.PaymentDTO;
import webflix.mapper.MemberMapper;
import webflix.mapper.MemberMyMapper;
import webflix.mapper.PurchaseMapper;
import webflix.mapper.UserMapper;

@Service
public class IniPayReturnService {
	@Autowired
	PurchaseMapper purchaseMapper;
	@Autowired
	MemberMapper memberMapper;
	@Autowired
	UserMapper userMapper;
	@Autowired
	MemberMyMapper memberMyMapper;
	
	public void execute(HttpServletRequest request, HttpSession session, Model model, HttpServletResponse response) {
		
		Map<String, String> resultMap = new HashMap<String, String>();

		try{

			//#############################
			// 인증결과 파라미터 일괄 수신
			//#############################
			request.setCharacterEncoding("UTF-8");

			Map<String,String> paramMap = new Hashtable<String,String>();

			Enumeration elems = request.getParameterNames();

			String temp = "";

			while(elems.hasMoreElements())
			{
				temp = (String) elems.nextElement();
				paramMap.put(temp, request.getParameter(temp));
			}
			
			System.out.println("paramMap : "+ paramMap.toString());
			
			
			if("0000".equals(paramMap.get("resultCode"))){

				System.out.println("####인증성공/승인요청####");

				//############################################
				// 1.전문 필드 값 설정(***가맹점 개발수정***)
				//############################################
				
				String mid 		= paramMap.get("mid");
				String timestamp= SignatureUtil.getTimestamp();
				String charset 	= "UTF-8";
				String format 	= "JSON";
				String authToken= paramMap.get("authToken");
				String authUrl	= paramMap.get("authUrl");
				String netCancel= paramMap.get("netCancelUrl");	
				String merchantData = paramMap.get("merchantData");
				
				//#####################
				// 2.signature 생성
				//#####################
				Map<String, String> signParam = new HashMap<String, String>();

				signParam.put("authToken",	authToken);		// 필수
				signParam.put("timestamp",	timestamp);		// 필수

				// signature 데이터 생성 (모듈에서 자동으로 signParam을 알파벳 순으로 정렬후 NVP 방식으로 나열해 hash)
				String signature = SignatureUtil.makeSignature(signParam);


				//#####################
				// 3.API 요청 전문 생성
				//#####################
				Map<String, String> authMap = new Hashtable<String, String>();

				authMap.put("mid"			,mid);			// 필수
				authMap.put("authToken"		,authToken);	// 필수
				authMap.put("signature"		,signature);	// 필수
				authMap.put("timestamp"		,timestamp);	// 필수
				authMap.put("charset"		,charset);		// default=UTF-8
				authMap.put("format"		,format);	    // default=XML


				HttpUtil httpUtil = new HttpUtil();

				try{
					//#####################
					// 4.API 통신 시작
					//#####################

					String authResultString = "";

					authResultString = httpUtil.processHTTP(authMap, authUrl);
					
					//############################################################
					//5.API 통신결과 처리(***가맹점 개발수정***)
					//############################################################
					
					String test = authResultString.replace(",", "&").replace(":", "=").replace("\"", "").replace(" ","").replace("\n", "").replace("}", "").replace("{", "");
					
								
					resultMap = ParseUtil.parseStringToMap(test); //문자열을 MAP형식으로 파싱
					
					
				  // 수신결과를 파싱후 resultCode가 "0000"이면 승인성공 이외 실패

				  //throw new Exception("강제 Exception");
					PaymentDTO dto = new PaymentDTO();
					dto.setCardNumber(resultMap.get("CARD_Num"));
					dto.setConfirmNumber(resultMap.get("applNum"));
					dto.setPaymentKind(resultMap.get("payMethod"));
					dto.setOrderNum(resultMap.get("MOID"));
					dto.setResultMessage(resultMap.get("resultMsg"));
					dto.setTid(resultMap.get("tid"));
					dto.setTotalPrice(resultMap.get("TotPrice"));
					
					if(resultMap.get("TotPrice").equals("10000")) {
						
						dto.setDays("30");
					}
					else if(resultMap.get("TotPrice").equals("54000")) {
						
						dto.setDays("180");
					}
					else if(resultMap.get("TotPrice").equals("102000")) {
						
						dto.setDays("360");
					}
					else {
						
						dto.setDays("0");
					}
					Cookie [] cookies = request.getCookies();
					if(cookies != null && cookies.length > 0) {
						for(Cookie cookie : cookies) {
							if(cookie.getName().equals("userId")) {
								MemberDTO  memDTO = memberMyMapper.memberInfo(cookie.getValue());
								AuthInfoDTO auth =  userMapper.loginSelect(cookie.getValue());
								session.setAttribute("auth", auth);
								dto.setMemNum(memDTO.getMemNum());
								memberMapper.updateStatus(memDTO.getMemNum());

							}
						}
					}
					
					int i = purchaseMapper.paymentInsert(dto);
					Cookie cookie = new Cookie("userId","");
					
					//저장경로
					cookie.setPath("/");
					
					//수명주기
					cookie.setMaxAge(0);
					
					//사용자에게 쿠키 전송
					response.addCookie(cookie);
					
				} catch (Exception ex) {

					//####################################
					// 실패시 처리(***가맹점 개발수정***)
					//####################################

					//---- db 저장 실패시 등 예외처리----//
					System.out.println(ex);

					//#####################
					// 망취소 API
					//#####################
					String netcancelResultString = httpUtil.processHTTP(authMap, netCancel);	// 망취소 요청 API url(고정, 임의 세팅 금지)

					//out.println("## 망취소 API 결과 ##");

					// 취소 결과 확인
					//out.println("<p>"+netcancelResultString.replaceAll("<", "&lt;").replaceAll(">", "&gt;")+"</p>");
				}

			}else{
				
				resultMap.put("resultCode", paramMap.get("resultCode"));
				resultMap.put("resultMsg", paramMap.get("resultMsg"));
			}

		}catch(Exception e){

			System.out.println(e);
		}
		
	}
}
