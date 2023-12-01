package webflix.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;
import webflix.service.video.WatchingVideoService;

@Controller
public class CornerController {
	@Autowired
	WatchingVideoService watchingVideoService;
	
	@GetMapping("detailView")
	public String prodInfo(@RequestParam("videoNum")String videoNum, Model model) {
		
		
		watchingVideoService.execute(videoNum, model);
		return "thymeleaf/corner/detailView";
	}
}
