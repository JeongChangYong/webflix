package webflix.service.video;

import java.io.File;
import java.net.URL;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import webflix.command.VideoCommand;
import webflix.domain.VideoDTO;
import webflix.mapper.VideoMapper;

@Service
public class VideoWriteService {
	@Autowired
	VideoMapper videoMapper;
	public void execute(VideoCommand videoCommand) {
		VideoDTO dto = new VideoDTO();
		
		dto.setVideoTheme(videoCommand.getVideoTheme());
		dto.setVideoName(videoCommand.getVideoName());
		dto.setVideoNum(videoCommand.getVideoNum());
		
		
		URL resource = getClass().getClassLoader().getResource("static/upload");
		String fileDir =resource.getFile();
		
		
		
		MultipartFile mfImg = videoCommand.getVideoImage();
		String originalFile = mfImg.getOriginalFilename();
		String extension = originalFile.substring(originalFile.lastIndexOf("."));
		String storeName = UUID.randomUUID().toString().replace("-", "");
		String storeFileName = storeName + extension;
		dto.setVideoImage(storeFileName);
		dto.setVideoImageOrigin(originalFile);
		
		
		
		File file = new File(fileDir + "/"+ storeFileName);
		try {mfImg.transferTo(file);
		} catch (Exception e) {e.printStackTrace();}
		
		
		MultipartFile mfvideo = videoCommand.getVideoFile();
		originalFile = mfvideo.getOriginalFilename();
		extension = originalFile.substring(originalFile.lastIndexOf("."));
		storeName = UUID.randomUUID().toString().replace("-", "");
		storeFileName = storeName + extension;
		dto.setVideoFile(storeFileName);
		dto.setVideoFileOrigin(originalFile);
		
		
		file = new File(fileDir + "/"+ storeFileName);
		try {mfvideo.transferTo(file);
		} catch (Exception e) {e.printStackTrace();}

		

		
		videoMapper.videoInsert(dto);
	}
}
