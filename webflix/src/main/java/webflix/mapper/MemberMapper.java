package webflix.mapper;

import org.apache.ibatis.annotations.Mapper;

import webflix.domain.MemberDTO;

@Mapper
public interface MemberMapper {
	public String memberAutoNum();
	public int memberInsert(MemberDTO dto);
}
