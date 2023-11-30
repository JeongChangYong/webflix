package webflix.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import webflix.domain.VideoDTO;

@Mapper
public interface BookmarkMapper {
	public List<VideoDTO> videoSelectAll(String videoNum);
}
