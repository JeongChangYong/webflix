package webflix.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import webflix.command.LoginCommand;
import webflix.service.video.MainVideoListService;

@Controller
public class MainController {
	@Autowired
	MainVideoListService mainVideoListService;
	@RequestMapping("/")
	public String index(@ModelAttribute("loginCommand") LoginCommand loginCommand,Model model, HttpSession session, HttpServletRequest request) {
		
		
		mainVideoListService.execute(null, model, session);
		return "thymeleaf/index";
	}
}
