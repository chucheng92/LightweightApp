package org.taoran.course.message.req;

/**
 * ������Ϣ
 * 
 * @author ����
 * @date 2015-4-6
 */
public class VoiceMessage extends BaseMessage {
	// ý��Id
	private String MediaId;
	// ������ʽ
	private String Format;
	// ����ʶ���� UTF-8����
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