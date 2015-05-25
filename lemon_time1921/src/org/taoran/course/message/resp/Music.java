package org.taoran.course.message.resp;

/**
 * 音乐类
 * 
 * @author 哓哓
 * @date 2015-4-6
 */
public class Music {
	// 音乐名
	private String Title;
	// 描述
	private String Description;
	// 音乐链接
	private String MusicUrl;
	// 高质量音乐链接，wifi环境优先使用该链接播放
	private String HQMusicUrl;

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

	public String getMusicUrl() {
		return MusicUrl;
	}

	public void setMusicUrl(String musicUrl) {
		MusicUrl = musicUrl;
	}

	public String getHQMusicUrl() {
		return HQMusicUrl;
	}

	public void setHQMusicUrl(String hQMusicUrl) {
		HQMusicUrl = hQMusicUrl;
	}

}
