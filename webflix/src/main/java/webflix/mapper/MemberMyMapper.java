package webflix.mapper;

import org.apache.ibatis.annotations.Mapper;

import webflix.domain.MemberDTO;

@Mapper
public interface MemberMyMapper {
	public MemberDTO memberInfo(String memberId);
}
