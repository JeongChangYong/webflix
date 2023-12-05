package webflix.domain;

import java.util.Date;

import org.apache.ibatis.type.Alias;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
@Alias("payment")
public class PaymentDTO {
	String orderNum;
	String paymentKind;
	String totalPrice;
	String confirmNumber;
	String cardNumber;
	String tid;
	String resultMessage;
	@DateTimeFormat(pattern = "yyyy-MM-dd" )
	Date paymentDate;
	String memNum;
	String day;
}
