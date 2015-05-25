package org.taoran.course.message.req;

/**
 * 视频消息
 * 
 * @author 哓哓
 * @date 2014-4-7
 */
public class VideoMessage extends BaseMessage {
	// 视频媒体Id
	private String MediaId;
	// 视频消息缩略图的媒体Id
	private String ThumbMediaId;

	public String getMediaId() {
		return MediaId;
	}

	public void setMediaId(String mediaId) {
		MediaId = mediaId;
	}

	public String getThumbMediaId() {
		return ThumbMediaId;
	}

	public void setThumbMediaId(String thumbMediaId) {
		ThumbMediaId = thumbMediaId;
	}

}
