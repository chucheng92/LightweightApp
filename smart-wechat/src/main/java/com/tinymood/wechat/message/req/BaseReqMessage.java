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
	private String ToUserName;

	// 发送方（用户） OpenId
	private String FromUserName;

	// 创建时间
	private long CreateTime;

	// 消息类型(text/image/location/link/voice/video/shortvideo)
    // 注意：voice和video消息实际也能接收 不过拿不到相应文件
	private String MsgType;

	// 消息id 64位
	private long MsgId;

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

    public long getMsgId() {
        return MsgId;
    }

    public void setMsgId(long msgId) {
        MsgId = msgId;
    }
}
