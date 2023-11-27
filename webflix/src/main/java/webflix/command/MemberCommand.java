package webflix.command;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class MemberCommand {
	String memNum;
	@NotEmpty(message = "아이디를 입력해주세요")
	String memId;
	@Pattern(regexp = "^(?=.*?[A-Za-z])(?=.*?[0-9])(?=.*?[~!@#$%^&*-+]).{8,}$" , message = "영문자와 숫자 그리고 특수문자가 포함된 8글자 이상")
	String memPw;
	@NotBlank(message = "비밀번호 확인을 입력해주세요.")
	String memPwCon;
	@NotEmpty(message = "이름을 입력해주세요")
	String memName;
	@NotEmpty(message = "주소를 입력해주세요")
	String memAddr;
	String memAddrDetail;
	String memPost;
	@NotEmpty(message = "번호를 입력해주세요")
	String memPhone;
	@NotEmpty(message = "이메일을 입력해주세요")
	String memEmail;
	//string은 not empty나 not blank 그 외는 not null
	@NotEmpty(message = "주민등록번호를 입력해주세요")
	String memJumin;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	Date memRegist;
	public boolean isMemberPwEqualsMemberPwCon() {
		
		return memPw.equals(memPwCon);
	}
	Integer memPoint;
	String memEventWin;
}
