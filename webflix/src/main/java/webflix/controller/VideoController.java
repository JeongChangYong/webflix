package webflix.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import webflix.service.video.VideoAutoNumService;

@Controller
public class VideoController {
	@Autowired
	VideoAutoNumService videoAutoNumService;
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
	public String videoWrite() {
		
		
		return "redirect:videoList";
	}
}
