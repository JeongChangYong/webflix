package webflix.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import webflix.domain.VideoDTO;

@Mapper
public interface BookmarkMapper {
	public List<VideoDTO> videoSelectAll(String videoNum);
	public Integer bookmarkSelect(String videoNum, String memNum);
	public int bookmarkInsert(String videoNum, String memNum);
	public int bookmarkDelete(String videoNum, String memNum);
	public List<VideoDTO> bookmarkList(String memNum);
	public int bookmarkDeletes(@Param("videoNums") String bookmarkDels[], @Param("memNum") String memNum);
	
}
