package webflix.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import webflix.domain.MemberDTO;
import webflix.domain.StartEndPageDTO;

@Mapper
public interface MemberMapper {
	public String memberAutoNum();
	public int memberInsert(MemberDTO dto);
	public int memberCount(String searchWord);
	public List<MemberDTO> selectAll(StartEndPageDTO sepDTO);
	public MemberDTO selectOne(String memberNum);
	public int memberUpdate(MemberDTO dto);
}
