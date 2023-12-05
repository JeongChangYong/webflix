package webflix.mapper;

import org.apache.ibatis.annotations.Mapper;

import webflix.domain.PaymentDTO;
import webflix.domain.PurchaseDTO;

@Mapper
public interface PurchaseMapper {
	public String selectNum();
	public int purchaseInsert(PurchaseDTO dto);
	public int paymentInsert(PaymentDTO dto);
	public int purchaseStatusUpdate(String status, String orderNum);
	public PurchaseDTO purchaseSelect(String orderNum);
	public Integer paymentSelect(String memNum);
}
