package com.tinymood.wechat.message.event;

/**
 * 事件基类
 * 
 * @author nothankyou
 * @date 2017-02-03 20:16:24
 */
public class BaseEvent {
	// 开发者
	private String toUserName;

	// 发送方（用户）帐号 OpenId
	private String fromUserName;

	// 消息创建时间 整型
	private long createTime;

	// 消息类型（event）
	private String msgType;

	// 事件类型（subscribe、unsubscribe、扫描带参二维码（subscribe和SCAN）、LOCATION、自定义菜单(CLICK和VIEW)）
	private String event;

    public String getToUserName() {
        return toUserName;
    }

    public void setToUserName(String toUserName) {
        this.toUserName = toUserName;
    }

    public String getFromUserName() {
        return fromUserName;
    }

    public void setFromUserName(String fromUserName) {
        this.fromUserName = fromUserName;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public String getMsgType() {
        return msgType;
    }

    public void setMsgType(String msgType) {
        this.msgType = msgType;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }
}
