package webflix.service.video;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import webflix.command.VideoCommand;
import webflix.mapper.VideoMapper;

@Service
public class VideoAutoNumService {
	@Autowired
	VideoMapper videoMapper;
	public void execute(Model model) {
		
		String videoNum = videoMapper.videoAutoNum();
		VideoCommand videoCommand = new VideoCommand();
		videoCommand.setVideoNum(videoNum);
		
		model.addAttribute("videoCommand", videoCommand);
		
	}
}
