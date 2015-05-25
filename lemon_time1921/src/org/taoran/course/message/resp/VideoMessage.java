package org.taoran.course.message.resp;

/**
 * 响应消息 -> 视频消息消息
 * 
 * @author 哓哓
 * @date 2015-4-6
 */
public class VideoMessage extends BaseMessage {
	private Video Video;

	public Video getVideo() {
		return Video;
	}

	public void setVideo(Video video) {
		Video = video;
	}

}
