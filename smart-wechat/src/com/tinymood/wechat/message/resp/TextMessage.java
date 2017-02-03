package org.taoran.course.message.resp;

/**
 * 响应消息 -> 文本消息
 * 
 * @author 哓哓
 * @date 2015-4-6
 */
public class TextMessage extends BaseMessage {
	// 回复的消息内容
	private String Content;

	public String getContent() {
		return Content;
	}

	public void setContent(String content) {
		Content = content;
	}
}
