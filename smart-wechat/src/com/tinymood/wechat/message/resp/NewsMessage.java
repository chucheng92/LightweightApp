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
	// 图文数量
	private int ArticleCount;

	// 图文集合
	private List<Article> Articles;

	public int getArticleCount() {
		return ArticleCount;
	}

	public void setArticleCount(int articleCount) {
		ArticleCount = articleCount;
	}

	public List<Article> getArticles() {
		return Articles;
	}

	public void setArticles(List<Article> articles) {
		Articles = articles;
	}

}
