package com.tinymood.wechat.message.resp;

/**
 * @author nothankyou
 * @date 2017-02-03 14:10:26
 *
 * 文本回复消息
 */
public class TextMessage extends BaseRespMessage {
	// 文本内容
	private String Content;

	public String getContent() {
		return Content;
	}

	public void setContent(String content) {
		Content = content;
	}
}
