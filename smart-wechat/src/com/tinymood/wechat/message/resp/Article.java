package org.taoran.course.message.resp;

/**
 * 图文类
 * 
 * @author 哓哓
 * @date 2015-4-7
 */
public class Article {
	// 图文消息名称
	private String Title;
	// 描述
	private String Description;
	// 图片链接，支持JPG、PNG格式，较好的效果为大图640*320
	// 小图80*80,限制图片链接的域名需要与开发者填写的基本资料中的Url一致
	private String PicUrl;
	// 点击图文消息跳转链接
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
