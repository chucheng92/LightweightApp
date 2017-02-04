package com.tinymood.wechat.message.resp;

/**
 * @author nothankyou
 * @date 2017-02-03 14:16:36
 *
 * 图片回复消息
 *
 */
public class MusicMessage extends BaseRespMessage {
	// 音乐内容
	private Music music;

	public Music getMusic() {
		return music;
	}

	public void setMusic(Music music) {
		this.music = music;
	}
}
