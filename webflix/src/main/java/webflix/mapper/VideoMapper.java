package webflix.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import webflix.domain.StartEndPageDTO;
import webflix.domain.VideoDTO;

@Mapper
public interface VideoMapper {
	public String videoAutoNum();
	public int videoInsert(VideoDTO dto);
	public List<VideoDTO> allSelect(StartEndPageDTO sepDTO);
	public int videoCount(String searchWord);
}
