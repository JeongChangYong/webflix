package webflix.service.video;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import jakarta.servlet.http.HttpSession;
import webflix.domain.AuthInfoDTO;
import webflix.domain.MemberDTO;
import webflix.domain.VideoDTO;
import webflix.mapper.BookmarkMapper;
import webflix.mapper.MemberMyMapper;
import webflix.mapper.VideoMapper;

@Service
public class WatchingVideoService {
	@Autowired
	VideoMapper videoMapper;
	@Autowired
	MemberMyMapper memberMyMapper;
	@Autowired
	BookmarkMapper bookmarkMapper;
	public void execute(String videoNum, Model model, HttpSession session) {
		
		VideoDTO dto = videoMapper.selectOne(videoNum);
		
		AuthInfoDTO auth = (AuthInfoDTO)session.getAttribute("auth");
		
		if(auth != null) {
			if(auth.getGrade().equals("mem")) {
			MemberDTO memDto = memberMyMapper.memberInfo(auth.getUserId());
			Integer i = bookmarkMapper.bookmarkSelect(videoNum, memDto.getMemNum());
			model.addAttribute("bookmark", i);
			}
		}
		
		model.addAttribute("dto", dto);
	}
}
