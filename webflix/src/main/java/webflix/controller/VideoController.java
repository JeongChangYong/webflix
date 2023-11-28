package webflix.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class VideoController {
	@GetMapping("videoList")
	public String videoList() {
		
		return "thymeleaf/video/videoList";
	}
	@GetMapping("videoRegist")
	public String videoRegist() {
		
		return "thymeleaf/video/videoForm";
	}
}
