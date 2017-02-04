package com.tinymood.wechat.message.resp;

/**
 * @author nothankyou
 * @date 2017-02-03 14:22:08
 *
 * 视频回复消息
 */
public class VideoMessage extends BaseRespMessage {
	// 视频内容
	private Video video;

	public Video getVideo() {
		return video;
	}

	public void setVideo(Video video) {
		this.video = video;
	}
}
