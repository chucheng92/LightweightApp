package com.tinymood.wechat.message.event;

/**
 * 自定义菜单事件
 * 
 * @author nothankyou
 * @date 2017-02-03 20:37:50
 */
public class MenuEvent extends BaseEvent {
	// 事件为CLICK：事件key值,与自定义菜单接口中key值对应
	private String eventKey1;

	// 事件为VIEW：事件key值，跳转的url
	private String eventKey2;

	public String getEventKey1() {
		return eventKey1;
	}

	public void setEventKey1(String eventKey1) {
		this.eventKey1 = eventKey1;
	}

	public String getEventKey2() {
		return eventKey2;
	}

	public void setEventKey2(String eventKey2) {
		this.eventKey2 = eventKey2;
	}
}
