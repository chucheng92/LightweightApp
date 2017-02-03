package org.taoran.course.message.resp;

/**
 * 消息基类(公众号 -> 用户)
 * 
 * @author 哓哓
 * @date 2015-4-6
 */
public class BaseMessage {
	// 接收方（用户）openId
	private String ToUserName;
	// 发送方 开发者微信号
	private String FromUserName;
	// 消息创建时间
	private long CreateTime;
	// 消息类型
	private String MsgType;
	// 位0x0001被标记时，星标刚收到的消息
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
