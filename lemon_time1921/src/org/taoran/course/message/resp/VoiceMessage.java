package org.taoran.course.message.resp;

/**
 * 响应消息 -> 语音消息
 * 
 * @author 哓哓
 * @date 2015-4-7
 */
public class VoiceMessage extends BaseMessage {
	private Voice Voice;

	public Voice getVoice() {
		return Voice;
	}

	public void setVoice(Voice voice) {
		Voice = voice;
	}
}
