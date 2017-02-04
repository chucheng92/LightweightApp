package com.tinymood.wechat.message.req;

/**
 * 音频消息
 *
 * @author nothankyou
 * @date 2017-02-03 14:04:30
 *
 */
public class VoiceMessage extends BaseReqMessage {
	// 媒体文件id
	private String mediaId;

	// 语音文件格式
	private String format;

	// 语音识别结果
	private String recognition;

    public String getMediaId() {
        return mediaId;
    }

    public void setMediaId(String mediaId) {
        this.mediaId = mediaId;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public String getRecognition() {
        return recognition;
    }

    public void setRecognition(String recognition) {
        this.recognition = recognition;
    }
}
