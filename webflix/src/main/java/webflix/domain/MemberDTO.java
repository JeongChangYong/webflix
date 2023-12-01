package webflix.domain;

import java.util.Date;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Data
@Alias("member")
public class MemberDTO {
	   String memNum;
	   String memId;
	   String memPw;
	   String memName;
	   String memAddr;
	   String memAddrDetail;
	   String memPost;
	   Date memRegist;
	   String memPhone;
	   String memJumin;
	   String memEmail;
	   String memEmailConf;
	   Integer memPoint;
	   String memEventWin;
	   String paymentStatus;
}
