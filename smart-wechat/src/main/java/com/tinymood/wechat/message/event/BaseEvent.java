package com.tinymood.wechat.message.event;

/**
 * 事件基类
 * 
 * @author nothankyou
 * @date 2017-02-03 20:16:24
 */
public class BaseEvent {
	// 开发者
	private String ToUserName;

	// 发送方（用户）帐号 OpenId
	private String FromUserName;

	// 消息创建时间 整型
	private long CreateTime;

	// 消息类型（event）
	private String MsgType;

	// 事件类型（subscribe、unsubscribe、扫描带参二维码（subscribe和SCAN）、LOCATION、自定义菜单(CLICK和VIEW)）
	private String Event;

    public String getToUserName() {
        return ToUserName;
    }

    public void setToUserName(String toUserName) {
        ToUserName = toUserName;
    }

    public String getFromUserName() {
        return FromUserName;
    }

    public void setFromUserName(String fromUserName) {
        FromUserName = fromUserName;
    }

    public long getCreateTime() {
        return CreateTime;
    }

    public void setCreateTime(long createTime) {
        CreateTime = createTime;
    }

    public String getMsgType() {
        return MsgType;
    }

    public void setMsgType(String msgType) {
        MsgType = msgType;
    }

    public String getEvent() {
        return Event;
    }

    public void setEvent(String event) {
        Event = event;
    }
}
