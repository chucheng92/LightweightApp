package com.tinymood.wechat.message.req;

/**
 * 请求消息基类
 *
 * @author nothankyou
 * @date 2017-02-03 13:59:00
 *
 */
public class BaseReqMessage {
	// 接收方（开发者）
	private String toUserName;

	// 发送方（用户） OpenId
	private String fromUserName;

	// 创建时间
	private long createTime;

	// 消息类型(text/image/location/link/voice/video/shortvideo)
    // 注意：voice和video消息实际也能接收 不过拿不到相应文件
	private String msgType;

	// 消息id 64位
	private long msgId;

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

    public long getMsgId() {
        return msgId;
    }

    public void setMsgId(long msgId) {
        this.msgId = msgId;
    }
}
