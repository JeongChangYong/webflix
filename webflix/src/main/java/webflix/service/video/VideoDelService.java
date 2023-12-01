package webflix.service.video;

import java.io.File;
import java.net.URL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import webflix.domain.VideoDTO;
import webflix.mapper.VideoMapper;

@Service
public class VideoDelService {
	@Autowired
	VideoMapper videoMapper;
	public void execute(String videoNum) {
		VideoDTO dto = videoMapper.selectOne(videoNum);
		int i = videoMapper.videoDelete(videoNum);
		if(i >0) {
			URL resource = getClass().getClassLoader().getResource("static/upload");
			String fileDir = resource.getFile();
			
			File file = new File(fileDir + "/" + dto.getVideoImage());
			if(file.exists())file.delete();
			
			File file1 = new File(fileDir + "/" + dto.getVideoFile());
			if(file1.exists())file1.delete();
			
		}
	}
}
