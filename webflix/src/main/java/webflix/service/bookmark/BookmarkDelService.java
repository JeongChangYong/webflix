package webflix.service.bookmark;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpSession;
import webflix.domain.AuthInfoDTO;
import webflix.domain.MemberDTO;
import webflix.mapper.BookmarkMapper;
import webflix.mapper.MemberMyMapper;

@Service
public class BookmarkDelService {
	@Autowired
	MemberMyMapper memberMyMapper;
	@Autowired
	BookmarkMapper bookmarkMapper;
	
	public void execute(String videoNum, HttpSession session) {
		AuthInfoDTO auth = (AuthInfoDTO)session.getAttribute("auth");
		MemberDTO memDTO = memberMyMapper.memberInfo(auth.getUserId());
		
		bookmarkMapper.bookmarkDelete(videoNum, memDTO.getMemNum());
		
		
	}
}
