package webflix.service.video;

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
public class MainVideoListService {
	@Autowired
	BookmarkMapper bookmarkMapper;
	@Autowired
	MemberMyMapper memberMyMapper;
	public void execute(String videoNum, Model model, HttpSession session) {
		List<VideoDTO> list = bookmarkMapper.videoSelectAll(videoNum);
		AuthInfoDTO auth = (AuthInfoDTO)session.getAttribute("auth");
		
		if(auth != null) {
			if(auth.getGrade().equals("mem")) {
			MemberDTO dto = memberMyMapper.memberInfo(auth.getUserId());
			Integer i = bookmarkMapper.bookmarkSelect(videoNum, dto.getMemNum());
			model.addAttribute("bookmark", i);
			}
		}
		
		
		model.addAttribute("list", list);
		
		
		
	}
}
