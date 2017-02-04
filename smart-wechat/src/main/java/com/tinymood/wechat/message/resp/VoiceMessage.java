package com.tinymood.wechat.message.resp;

/**
 * @author nothankyou
 * @date 2017-02-03 14:24:18
 */
public class VoiceMessage extends BaseRespMessage {
	// 语音内容
	private Voice Voice;

	public com.tinymood.wechat.message.resp.Voice getVoice() {
		return Voice;
	}

	public void setVoice(com.tinymood.wechat.message.resp.Voice voice) {
		Voice = voice;
	}
}
