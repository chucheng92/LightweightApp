package com.tinymood.wechat.message.resp;

import java.util.List;

/**
 * @author nothankyou
 * @date 2017-02-03 14:18:30
 *
 * 图文消息
 *
 */
public class NewsMessage extends BaseRespMessage {
	// 图文数量，限制为10条以内
	private int articleCount;

	// 图文集合，默认第一个item为大图
	private List<Article> articles;

	public int getArticleCount() {
		return articleCount;
	}

	public void setArticleCount(int articleCount) {
		this.articleCount = articleCount;
	}

	public List<Article> getArticles() {
		return articles;
	}

	public void setArticles(List<Article> articles) {
		this.articles = articles;
	}
}
