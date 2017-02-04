package com.tinymood.wechat.message.req;

/**
 * @author nothankyou
 * @date 2017-02-03 14:01:37
 *
 */
public class ImageMessage extends BaseReqMessage {
	// 图片url
	private String PicUrl;

	// 图片消息媒体id
	private String MediaId;

	public String getPicUrl() {
		return PicUrl;
	}

	public void setPicUrl(String picUrl) {
		PicUrl = picUrl;
	}

	public String getMediaId() {
		return MediaId;
	}

	public void setMediaId(String mediaId) {
		MediaId = mediaId;
	}
}
