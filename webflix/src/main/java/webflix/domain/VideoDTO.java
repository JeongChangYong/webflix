package webflix.domain;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Data
@Alias("video")
public class VideoDTO {
	String videoNum;
	String videoName;
	String videoTheme;
	String videoImage;
	String videoImageOrigin;
	String videoFile;
	String videoFileOrigin;
}
