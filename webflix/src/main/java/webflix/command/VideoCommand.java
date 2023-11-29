package webflix.command;

import lombok.Data;

@Data
public class VideoCommand {
	String videoNum;
	String videoName;
	String videoTheme;
	String videoImage;
	String videoFile;
	String videoFileOrigin;
}
