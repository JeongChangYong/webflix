package webflix.mapper;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface VideoMapper {
	public String videoAutoNum();
}
