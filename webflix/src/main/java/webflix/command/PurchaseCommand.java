package webflix.command;

import java.util.Date;

import lombok.Data;

@Data
public class PurchaseCommand {
	String orderNum;
	Date orderDate;
	Integer orderPrice;
	String deliveryName;
	String deliveryAddr;
	String deliveryAddrDetail;
	String deliveryPost;
	String deliveryPhone;
	String message;
	String purchaseStatus;
	String memNum;
}
