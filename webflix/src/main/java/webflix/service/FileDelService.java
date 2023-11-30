package webflix.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpSession;
import webflix.command.FileCommand;

@Service
public class FileDelService {
	public String execute(FileCommand fileCommand, HttpSession session) {
		String num = "";
		//session을 이용한 장바구니도 가능하다.
		Boolean newFile = true; //새로운 상품인지 아닌지 확인하기위한 변수
		List<FileCommand> list = (List<FileCommand>)session.getAttribute("fileList");
		//첫 상품인 경우 session이 존재하지 않으므로(=list가 없다면) list가 없으니 list를 만들어주어야한다.
		if(list == null) {
			
			list = new ArrayList<FileCommand>();
			
		}
		// session이 있다면 list가 있다는 뜻이니까 list에 어떠한 상품이 존재하는지 확인해야한다.
		for( int i = 0 ; i <list.size(); i ++) {
			if(list.get(i).getStoreFile().equals(fileCommand.getStoreFile())) {
				//fineInfoCommand.setQty(fileInfoCommand.getQty()+1);
				list.remove(i);
				newFile = false;
				num = "0";
			}
			
		}
		// 새로운 상품인 경우
		if(newFile) {
			list.add(fileCommand);
			num = "1";
			
		}
		session.setAttribute("fileList", list);
		return num;
		
	}
}
