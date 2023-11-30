package webflix.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;

@Controller
public class CornerController {
	@GetMapping("detailView")
	public String prodInfo(@RequestParam("videoNum")String videoNum, Model model, HttpSession session) {
		
		
		
		return "thymeleaf/corner/detailView";
	}
}
