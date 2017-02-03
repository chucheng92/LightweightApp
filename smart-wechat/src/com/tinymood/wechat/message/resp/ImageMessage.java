package com.tinymood.wechat.message.resp;

/**
 * @author nothankyou
 * @date 2017-02-03 14:12:20
 *
 * 图片回复消息
 */
public class ImageMessage extends BaseRespMessage {
	// 图片内容
	private Image Image;

	public Image getImage() {
		return Image;
	}

	public void setImage(Image image) {
		Image = image;
	}
	
}
