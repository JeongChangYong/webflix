package webflix.mapper;

import org.apache.ibatis.annotations.Mapper;

import webflix.domain.VideoDTO;

@Mapper
public interface VideoMapper {
	public String videoAutoNum();
	public int videoInsert(VideoDTO dto);
}
