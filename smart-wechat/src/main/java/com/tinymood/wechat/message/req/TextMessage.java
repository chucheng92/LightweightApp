package com.tinymood.wechat.message.req;

/**
 * @author nothankyou
 * @date 2017-02-03 14:00:46
 *
 */
public class TextMessage extends BaseReqMessage {
	//消息内容
	private String content;

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
}
