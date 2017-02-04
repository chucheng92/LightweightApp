package com.tinymood.wechat.message.event;

/**
 * 扫描带参数二维码事件
 * 
 * @author nothankyou
 * @date 2017-02-03 20:30:15
 */
public class QRCodeEvent extends BaseEvent {
	// 未关注：event为subscribe
	// 事件KEY值，qrscene_为前缀，后面为二维码参数值
	private String eventKey1;

	// 已关注：event为SCAN
	// 事件key值，32位无符号整数，即创建二维码时的二维码scene_id
	private int eventKey2;

	// 用于换取二维码图片
	private String ticket;

	public String getEventKey1() {
		return eventKey1;
	}

	public void setEventKey1(String eventKey1) {
		this.eventKey1 = eventKey1;
	}

	public int getEventKey2() {
		return eventKey2;
	}

	public void setEventKey2(int eventKey2) {
		this.eventKey2 = eventKey2;
	}

	public String getTicket() {
		return ticket;
	}

	public void setTicket(String ticket) {
		this.ticket = ticket;
	}
}