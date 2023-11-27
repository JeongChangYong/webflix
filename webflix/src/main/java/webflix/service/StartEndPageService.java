package webflix.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import webflix.domain.StartEndPageDTO;

@Service
public class StartEndPageService {
	int limit;
	public StartEndPageDTO execute(int page, String searchWord ) {
		limit = 3; // 한페이지에 3개씩 보여줄거임
		int startRow = ((page-1) * limit) + 1;
		int endRow = startRow + limit -1;
		
		StartEndPageDTO sepDTO = new StartEndPageDTO();
		sepDTO.setEndRow(endRow);
		sepDTO.setStartRow(startRow);
		sepDTO.setSearchWord(searchWord);
		return sepDTO;
		
	}
	public void execute(int page, int count, Model model , List list, String searchWord) {
		
		
		int limitPage = 10; //페이지는 10개씩 보여줄 거임
		int startPage = (int)((double)page / limitPage + 0.999-1) * limitPage +1;
		int endPage = startPage + limitPage -1;
		int maxPage = (int)((double)count/limit + 0.95);
		if(maxPage < endPage) endPage = maxPage;
		model.addAttribute("page", page);
		model.addAttribute("startPage", startPage);
		model.addAttribute("endPage", endPage);
		model.addAttribute("count", count);
		model.addAttribute("dtos", list);
		model.addAttribute("searchWord", searchWord);
		model.addAttribute("maxPage", maxPage);
	}
}
