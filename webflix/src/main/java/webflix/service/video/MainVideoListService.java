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

@Service
public class MainVideoListService {
	@Autowired
	BookmarkMapper bookmarkMapper;
	public void execute(String videoNum, Model model, HttpSession session) {
		List<VideoDTO> list = bookmarkMapper.videoSelectAll(videoNum);
		AuthInfoDTO auth = (AuthInfoDTO)session.getAttribute("auth");
		/*
		if(auth != null) {
			if(auth.getGrade().equals("mem")) {
			MemberDTO dto = memberMyMapper.memberInfo(auth.getUserId());
			Integer i = cartWishMapper.wishGoodsSelect(goodsNum, dto.getMemberNum());
			model.addAttribute("wish", i);
			}
		}
		*/
		
		model.addAttribute("list", list);
		
		
		
	}
}
