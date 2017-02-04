package com.tinymood.wechat.util;

import java.util.List;

/**
 * 调用百度翻译api查询结果
 * 
 * @author nothankyou
 * @date 2017-02-03 22:03:34
 */
public class TranslateResult {
	// 实际采用的源语言
	private String from;

	// 实际采用的目标语言
	private String to;

	// 结果体
	private List<ResultPair> trans_result;

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public List<ResultPair> getTrans_result() {
		return trans_result;
	}

	public void setTrans_result(List<ResultPair> trans_result) {
		this.trans_result = trans_result;
	}
}