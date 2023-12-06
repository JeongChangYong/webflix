package webflix.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.servlet.http.HttpSession;
import webflix.service.bookmark.BookmarkDelService;
import webflix.service.bookmark.BookmarkDelsService;
import webflix.service.bookmark.BookmarkListService;
import webflix.service.bookmark.BookmarkService;
import webflix.service.purchase.PaymentCheckService;
import webflix.service.video.WatchingVideoService;

@Controller
public class CornerController {
	@Autowired
	WatchingVideoService watchingVideoService;
	@Autowired
	BookmarkService bookmarkService;
	@Autowired
	BookmarkListService bookmarkListService;
	@Autowired
	BookmarkDelsService bookmarkDelsService;
	@Autowired
	BookmarkDelService bookmarkDelService;
	@Autowired
	PaymentCheckService paymentCheckService;
	@GetMapping("detailView")
	public String prodInfo(@RequestParam("videoNum")String videoNum, Model model, HttpSession session) {
		
		
		watchingVideoService.execute(videoNum, model, session);
		paymentCheckService.execute(session,videoNum);
		
		//return "thymeleaf/corner/detailView";
		return paymentCheckService.execute(session, videoNum);
	}@PostMapping("bookmarkAdd")
	public @ResponseBody String bookmarkAdd(@RequestParam("videoNum")String videoNum,
			HttpSession session) {
		
		return bookmarkService.execute(videoNum, session);
	}
	@GetMapping("bookmarkList")
	public String bookmarkList(HttpSession session, Model model ) {
		bookmarkListService.execute(session, model);
		return "thymeleaf/corner/bookmarkList";
	}
	@PostMapping("bookmarkDels")
	public String bookmarkDels(@RequestParam("bookmarkDel")String bookmarkDels [], HttpSession session) {
		bookmarkDelsService.execute(bookmarkDels, session);
		
		return "redirect:bookmarkList";
	}
	@GetMapping("bookmarkDel")
	public String bookmarkDel(@RequestParam("videoNum") String videoNum, HttpSession session ) {
		bookmarkDelService.execute(videoNum, session);
		return "redirect:bookmarkList";
	}
}
