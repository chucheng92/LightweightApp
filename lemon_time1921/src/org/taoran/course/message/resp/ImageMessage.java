package org.taoran.course.message.resp;

/**
 * 响应消息 -> 图片消息
 * 
 * @author 哓哓
 * @date 2015-4-7
 */
public class ImageMessage extends BaseMessage {
	private Image Image;

	public Image getImage() {
		return Image;
	}

	public void setImage(Image image) {
		Image = image;
	}
	
}
