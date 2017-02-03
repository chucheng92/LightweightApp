package com.tinymood.wechat.message.resp;

/**
 * @author nothankyou
 * @date 2017-02-03 13:56:37
 *
 */
public class Article {
	// 标题
	private String Title;

	// 描述
	private String Description;

	// 封面图 640*320
	// 小图 80*80
	private String PicUrl;

	// 文章url
	private String Url;

	public String getTitle() {
		return Title;
	}

	public void setTitle(String title) {
		Title = title;
	}

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}

	public String getPicUrl() {
		return PicUrl;
	}

	public void setPicUrl(String picUrl) {
		PicUrl = picUrl;
	}

	public String getUrl() {
		return Url;
	}

	public void setUrl(String url) {
		Url = url;
	}

}
