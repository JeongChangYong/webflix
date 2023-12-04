package webflix.mapper;

import org.apache.ibatis.annotations.Mapper;

import webflix.domain.PurchaseDTO;

@Mapper
public interface PurchaseMapper {
	public String selectNum();
	public int purchaseInsert(PurchaseDTO dto);
}
