package com.tinymood.wechat.message.req;

/**
 * @author nothankyou
 * @date 2017-02-03 14:06:36
 *
 */
public class VideoMessage extends BaseReqMessage {
	// 视频消息媒体id
	private String mediaId;

	// 视频消息缩略图的媒体id
	private String thumbMediaId;

	public String getMediaId() {
		return mediaId;
	}

	public void setMediaId(String mediaId) {
		this.mediaId = mediaId;
	}

	public String getThumbMediaId() {
		return thumbMediaId;
	}

	public void setThumbMediaId(String thumbMediaId) {
		this.thumbMediaId = thumbMediaId;
	}
}
