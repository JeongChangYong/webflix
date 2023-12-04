package webflix.service.bookmark;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import jakarta.servlet.http.HttpSession;
import webflix.domain.AuthInfoDTO;
import webflix.domain.MemberDTO;
import webflix.domain.VideoDTO;
import webflix.mapper.BookmarkMapper;
import webflix.mapper.MemberMyMapper;

@Service
public class BookmarkListService {
	@Autowired
	MemberMyMapper memberMyMapper;
	@Autowired
	BookmarkMapper bookmarkMapper;
	public void execute(HttpSession session, Model model) {
		AuthInfoDTO auth = (AuthInfoDTO)session.getAttribute("auth");
		MemberDTO memDTO = memberMyMapper.memberInfo(auth.getUserId());
		
		List<VideoDTO> list = bookmarkMapper.bookmarkList(memDTO.getMemNum());
		
		model.addAttribute("dtos", list);
		
		
	}
}
