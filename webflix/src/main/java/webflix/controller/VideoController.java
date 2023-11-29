package webflix.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import webflix.command.VideoCommand;
import webflix.service.video.VideoAutoNumService;
import webflix.service.video.VideoWriteService;

@Controller
public class VideoController {
	@Autowired
	VideoAutoNumService videoAutoNumService;
	@Autowired
	VideoWriteService videoWriteService;
	@GetMapping("videoList")
	public String videoList() {
		
		return "thymeleaf/video/videoList";
	}
	@GetMapping("videoRegist")
	public String videoRegist(Model model) {
		videoAutoNumService.execute(model);
		return "thymeleaf/video/videoForm";
	}
	@PostMapping("videoWrite")
	public String videoWrite(@Validated VideoCommand videoCommand, BindingResult result) {
		
		if(result.hasErrors()) {
			
			return "thymeleaf/video/videoForm";
			
		}
		if(videoCommand.getVideoImage().getOriginalFilename().isEmpty()) {
			result.rejectValue("videoImage", "videoCommand.videoImage", "이미지를 추가해주세요.");
			return "thymeleaf/video/videoForm"; 
			
		}
		if(videoCommand.getVideoFile().getOriginalFilename().isEmpty()) {
			result.rejectValue("videoFile", "videoCommand.videoFile", "영상을 추가해주세요.");
			return "thymeleaf/video/videoForm"; 
			
		}
		videoWriteService.execute(videoCommand);
		return "redirect:videoList";
	}
}
