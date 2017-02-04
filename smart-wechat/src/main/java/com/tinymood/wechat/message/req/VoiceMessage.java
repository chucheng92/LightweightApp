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
	private String MediaId;

	// 语音文件格式
	private String Format;

	// 语音识别结果
	private String Recognition;

    public String getMediaId() {
        return MediaId;
    }

    public void setMediaId(String mediaId) {
        MediaId = mediaId;
    }

    public String getFormat() {
        return Format;
    }

    public void setFormat(String format) {
        Format = format;
    }

    public String getRecognition() {
        return Recognition;
    }

    public void setRecognition(String recognition) {
        Recognition = recognition;
    }
}
