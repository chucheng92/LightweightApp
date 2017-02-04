package com.tinymood.wechat.message.req;

/**
 * @author nothankyou
 * @date 2017-02-03 14:06:36
 *
 */
public class VideoMessage extends BaseReqMessage {
	// 视频消息媒体id
	private String MediaId;

	// 视频消息缩略图的媒体id
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
