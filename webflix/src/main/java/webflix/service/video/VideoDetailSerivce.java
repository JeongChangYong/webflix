package webflix.service.video;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import webflix.domain.VideoDTO;
import webflix.mapper.VideoMapper;

@Service
public class VideoDetailSerivce {
	@Autowired
	VideoMapper videoMapper;
	public void execute(String videoNum, Model model) {
		
		VideoDTO dto = videoMapper.selectOne(videoNum);
		
		model.addAttribute("videoCommand", dto);
		
		
		
		
	}
}
