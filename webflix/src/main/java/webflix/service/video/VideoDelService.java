package webflix.service.video;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import webflix.mapper.VideoMapper;

@Service
public class VideoDelService {
	@Autowired
	VideoMapper videoMapper;
	public void execute(String videoNum) {
		
		videoMapper.videoDelete(videoNum);
	}
}
