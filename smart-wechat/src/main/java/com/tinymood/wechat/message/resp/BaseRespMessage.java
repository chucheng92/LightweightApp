package com.tinymood.wechat.message.resp;

/**
 * @author nothankyou
 * @date 2017-02-03 14:07:56
 *
 * 基础回复消息类
 */
public class BaseRespMessage {
	// 接收方（用户 openId）
	private String ToUserName;

	// 发送方（开发者）
	private String FromUserName;

	// 创建时间
	private long CreateTime;

	// 消息类型(text/image/music/voice/video/news)
    // 注意：voice和video消息只能在编辑模式下使用
	private String MsgType;

	// 星标标识
	private int FuncFlag;

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

    public int getFuncFlag() {
        return FuncFlag;
    }

    public void setFuncFlag(int funcFlag) {
        FuncFlag = funcFlag;
    }
}
