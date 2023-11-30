package webflix.service.video;

import java.io.File;
import java.net.URL;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.multipart.MultipartFile;

import jakarta.servlet.http.HttpSession;
import webflix.command.FileCommand;
import webflix.command.VideoCommand;
import webflix.domain.VideoDTO;
import webflix.mapper.VideoMapper;

@Service
public class VideoUpdateService {
	@Autowired
	VideoMapper videoMapper;
	public void execute(VideoCommand videoCommand, BindingResult result,HttpSession session, Model model) {
		
		VideoDTO dto = new VideoDTO();
		dto.setVideoName(videoCommand.getVideoName());
		dto.setVideoNum(videoCommand.getVideoNum());
		dto.setVideoTheme(videoCommand.getVideoTheme());
		List<FileCommand> list = (List<FileCommand>)session.getAttribute("fileList");
		
		VideoDTO videoDTO = videoMapper.selectOne(videoCommand.getVideoNum());
		URL resource = getClass().getClassLoader().getResource("static/upload");
		String fileDir = resource.getFile();
		 MultipartFile mf;
	     String originalFile;
	     String extension;
	     String storeName;
	     String storeFileName;
	     File file;
		
		if(!videoCommand.getVideoImage().getOriginalFilename().isEmpty()) {
			mf = videoCommand.getVideoImage();
			originalFile = mf.getOriginalFilename();
			extension = originalFile.substring(originalFile.lastIndexOf("."));
			storeName = UUID.randomUUID().toString().replace("-", "");
			storeFileName = storeName + extension;
			dto.setVideoImage(storeFileName);
			dto.setVideoImageOrigin(originalFile);
			file = new File(fileDir + "/" + storeFileName);
	         try {
	            mf.transferTo(file);
	         } catch (Exception e) {
	            e.printStackTrace();
	         }

			
		}else {
			if(list != null) {
				for(FileCommand fileCommand : list) {
	                  if(fileCommand.getStoreFile().equals(videoDTO.getVideoImage())) {
	                     result.rejectValue("videoImage", "error");
	                     model.addAttribute("error", "이미지를 입력해주세요");
	                     model.addAttribute("videoCommand", videoDTO);
	                     session.removeAttribute("fileList");
	                     return;
	                  }
	                  
	               }
				
			}
			
		}
		if(!videoCommand.getVideoFile().getOriginalFilename().isEmpty()) {
			MultipartFile mfv = videoCommand.getVideoFile();
			originalFile = mfv.getOriginalFilename();
			extension = originalFile.substring(originalFile.lastIndexOf("."));
			storeName = UUID.randomUUID().toString().replace("-", "");
			storeFileName = storeName + extension;
			dto.setVideoFile(storeFileName);
			dto.setVideoFileOrigin(originalFile);
			file = new File(fileDir + "/" + storeFileName);
	         try {
	            mfv.transferTo(file);
	         } catch (Exception e) {
	            e.printStackTrace();
	         }

			
		}else {
			if(list != null) {
				for(FileCommand fileCommand : list) {
	                  if(fileCommand.getStoreFile().equals(videoDTO.getVideoFile())) {
	                     result.rejectValue("videoFile", "error");
	                     model.addAttribute("error", "영상을 입력해주세요");
	                     model.addAttribute("videoCommand", videoDTO);
	                     session.removeAttribute("fileList");
	                     return;
	                  }
	                  
	               }
				
			}
			
		}
		
		
		int i = videoMapper.videoUpdate(dto);
	      
	      if(i>0) {
	         if(list != null) {
	            for(FileCommand fileCommand : list) {
	               file = new File(fileDir + "/" + fileCommand.getStoreFile());
	               if(file.exists())file.delete();
	            }
	         }
	      }
	      session.removeAttribute("fileList");
		
		
	}
	
	
	
	
}
