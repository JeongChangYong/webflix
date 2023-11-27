package webflix.service.member;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import webflix.domain.MemberDTO;
import webflix.domain.StartEndPageDTO;
import webflix.mapper.MemberMapper;
import webflix.service.StartEndPageService;

@Service
public class MemberListService {
	@Autowired
	MemberMapper memberMapper;
	@Autowired
	StartEndPageService startEndPageService;
	String searchWord;
	public void execute(Model model , String searchWord, int page) {
		if(searchWord != null) {
			this.searchWord = searchWord.trim();
			
		}
		
		StartEndPageDTO sepDTO = startEndPageService.execute(page, this.searchWord);
		int count = memberMapper.memberCount(this.searchWord);
		List<MemberDTO> list = memberMapper.selectAll(sepDTO);
		startEndPageService.execute(page, count, model, list, this.searchWord);
		
	}
}
