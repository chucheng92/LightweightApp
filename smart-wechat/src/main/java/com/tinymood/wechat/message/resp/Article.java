package com.tinymood.wechat.message.resp;

/**
 * @author nothankyou
 * @date 2017-02-03 13:56:37
 *
 */
public class Article {
	// 标题
	private String title;

	// 描述
	private String description;

	// 大图 640*320
	// 小图 80*80
	private String picUrl;

	// 点击跳转链接，即文章url
	private String url;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPicUrl() {
		return picUrl;
	}

	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
}
