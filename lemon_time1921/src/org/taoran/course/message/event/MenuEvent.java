package org.taoran.course.message.event;

/**
 * 自定义菜单事件
 * 
 * @author 哓哓
 * @date 2015-4-9
 */
public class MenuEvent extends BaseEvent {
	// 事件key值,与自定义菜单接口中key值对应
	private String EventKey;

	public String getEventKey() {
		return EventKey;
	}

	public void setEventKey(String eventKey) {
		EventKey = eventKey;
	}

}
