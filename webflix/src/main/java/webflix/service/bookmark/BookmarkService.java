package webflix.service.bookmark;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpSession;
import webflix.domain.AuthInfoDTO;
import webflix.domain.MemberDTO;
import webflix.mapper.BookmarkMapper;
import webflix.mapper.MemberMyMapper;

@Service
public class BookmarkService {
	@Autowired
	MemberMyMapper memberMyMapper;
	@Autowired
	BookmarkMapper bookmarkMapper;
	public String execute(String videoNum , HttpSession session) {
		AuthInfoDTO auth = (AuthInfoDTO)session.getAttribute("auth");
		MemberDTO memDTO = memberMyMapper.memberInfo(auth.getUserId());
		Integer i = bookmarkMapper.bookmarkSelect(videoNum, memDTO.getMemNum());
		if(i== null) {
			
			
			bookmarkMapper.bookmarkInsert(videoNum,memDTO.getMemNum());
			return "1";
		}else if(i == 1) {
			
			bookmarkMapper.bookmarkDelete(videoNum, memDTO.getMemNum());
			return "0";
		}
		return null;
		
	}
}
