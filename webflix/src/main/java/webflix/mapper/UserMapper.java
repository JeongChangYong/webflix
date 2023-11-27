package webflix.mapper;

import org.apache.ibatis.annotations.Mapper;

import webflix.domain.AuthInfoDTO;
import webflix.domain.MemberDTO;

@Mapper
public interface UserMapper {
	public int userInsert(MemberDTO dto);
	public int userCheckUpdate(String email);
	public AuthInfoDTO loginSelect(String userId);
}
