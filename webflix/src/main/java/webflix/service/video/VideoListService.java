package webflix.service.video;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import webflix.domain.StartEndPageDTO;
import webflix.domain.VideoDTO;
import webflix.mapper.VideoMapper;
import webflix.service.StartEndPageService;

@Service
public class VideoListService {
	@Autowired
	StartEndPageService startEndPageService;
	@Autowired
	VideoMapper videoMapper;
	String searchWord;
	public void execute(Model model, String searchWord, int page) {
		if(searchWord != null) {
			this.searchWord = searchWord.trim();
			
		}
		
		StartEndPageDTO sepDTO = startEndPageService.execute(page, this.searchWord);
		List<VideoDTO> list = videoMapper.allSelect(sepDTO);
		
		int count = videoMapper.videoCount(this.searchWord);
		startEndPageService.execute(page, count, model, list, this.searchWord);
		
	}
}
