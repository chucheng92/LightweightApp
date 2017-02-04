package com.tinymood.wechat.message.resp;

/**
 * @author nothankyou
 * @date 2017-02-03 14:07:56
 *
 * 基础回复消息类
 */
public class BaseRespMessage {
	// 接收方（用户 openId）
	private String toUserName;

	// 发送方（开发者）
	private String fromUserName;

	// 创建时间
	private long createTime;

	// 消息类型(text/image/music/voice/video/news)
    // 注意：voice和video消息只能在编辑模式下使用
	private String msgType;

	// 星标标识
	private int starFlag;

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

    public int getStarFlag() {
        return starFlag;
    }

    public void setStarFlag(int starFlag) {
        this.starFlag = starFlag;
    }
}
