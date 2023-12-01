package webflix.mapper;

import org.apache.ibatis.annotations.Mapper;

import webflix.domain.MemberDTO;

@Mapper
public interface MemberMyMapper {
	public MemberDTO memberInfo(String memId);
	public int memberPwUpdate(String userPw, String memId);
	public int memberDrop(String memId);
	public int memberInfoUpdate(MemberDTO dto);
}
