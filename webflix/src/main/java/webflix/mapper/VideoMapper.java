package webflix.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import webflix.domain.StartEndPageDTO;
import webflix.domain.VideoDTO;

@Mapper
public interface VideoMapper {
	public String videoAutoNum();
	public int videoInsert(VideoDTO dto);
	public List<VideoDTO> allSelect(StartEndPageDTO sepDTO);
	public int videoCount(String searchWord);
	public VideoDTO selectOne(String videoNum);
	public int videoUpdate(VideoDTO dto);
	public int videoDelete(String videoNum);
	public int productsDelete(@Param("products") String products[]);
}
