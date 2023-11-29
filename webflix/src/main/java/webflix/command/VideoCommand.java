package webflix.command;

import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class VideoCommand {
	String videoNum;
	@NotEmpty(message = "비디오 명을 입력해주세요.")
	String videoName;
	@NotBlank(message = "비디오 테마를 입력해주세요.")
	String videoTheme;
	@NotNull(message = "이미지를 선택해주세요.")
	MultipartFile videoImage;
	@NotNull(message = "동영상 파일을 선택해주세요.")
	MultipartFile videoFile;
	
}
