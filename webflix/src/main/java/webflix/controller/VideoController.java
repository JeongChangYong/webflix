package webflix.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.servlet.http.HttpSession;
import webflix.command.FileCommand;
import webflix.command.VideoCommand;
import webflix.service.FileDelService;
import webflix.service.video.ProductsDeleteService;
import webflix.service.video.VideoAutoNumService;
import webflix.service.video.VideoDelService;
import webflix.service.video.VideoDetailSerivce;
import webflix.service.video.VideoListService;
import webflix.service.video.VideoUpdateService;
import webflix.service.video.VideoWriteService;

@Controller
public class VideoController {
	@Autowired
	VideoAutoNumService videoAutoNumService;
	@Autowired
	VideoWriteService videoWriteService;
	@Autowired
	VideoListService videoListService;
	@Autowired
	VideoDetailSerivce videoDetailSerivce;
	@Autowired
	VideoUpdateService videoUpdateService;
	@Autowired
	FileDelService fileDelService;
	@Autowired
	VideoDelService videoDelService;
	@Autowired
	ProductsDeleteService productsDeleteService;
	@GetMapping("videoList")
	public String videoList(Model model, @RequestParam(value = "searchWord", required = false) String searchWord,
			@RequestParam(value = "page", required = false, defaultValue = "1") int page) {
		videoListService.execute(model, searchWord, page);
		return "thymeleaf/video/videoList";
	}

	@GetMapping("videoRegist")
	public String videoRegist(Model model) {
		videoAutoNumService.execute(model);
		return "thymeleaf/video/videoForm";
	}

	@PostMapping("videoWrite")
	public String videoWrite(@Validated VideoCommand videoCommand, BindingResult result) {

		if (result.hasErrors()) {

			return "thymeleaf/video/videoForm";

		}
		if (videoCommand.getVideoImage().getOriginalFilename().isEmpty()) {
			result.rejectValue("videoImage", "videoCommand.videoImage", "이미지를 추가해주세요.");
			return "thymeleaf/video/videoForm";

		}
		if (videoCommand.getVideoFile().getOriginalFilename().isEmpty()) {
			result.rejectValue("videoFile", "videoCommand.videoFile", "영상을 추가해주세요.");
			return "thymeleaf/video/videoForm";

		}
		videoWriteService.execute(videoCommand);
		return "redirect:videoList";
	}

	@GetMapping("videoDetail")
	public String videoDetail(@RequestParam("videoNum") String videoNum, Model model) {
		videoDetailSerivce.execute(videoNum, model);

		return "thymeleaf/video/videoInfo";
	}

	@GetMapping("videoUpdate")
	public String videoUpdate(@RequestParam("videoNum") String videoNum, Model model) {
		videoDetailSerivce.execute(videoNum, model);

		return "thymeleaf/video/videoModify";
	}

	@PostMapping("videoUpdate")
	public String videoUpdate(@Validated VideoCommand videoCommand, BindingResult result, HttpSession session,
			Model model) {
		videoUpdateService.execute(videoCommand, result, session, model);
		if (result.hasErrors()) {

			return "thymeleaf/video/videoModify";
		}

		return "redirect:videoDetail?videoNum=" + videoCommand.getVideoNum();

	}

	@PostMapping("fileDel")
	public @ResponseBody String fsileDel(FileCommand fileCommand, HttpSession session) {

		return fileDelService.execute(fileCommand, session);

	}
	@GetMapping("videoDel")
	public String videoDel(@RequestParam("videoNum")String videoNum) {
		videoDelService.execute(videoNum);
		return "redirect:videoList";
	}
	@PostMapping("productsDelete")
	public String productsDelete(@RequestParam(value="memDels")String memDels[]) {
		productsDeleteService.execute(memDels);
		return "redirect:videoList";
	}
	
}
