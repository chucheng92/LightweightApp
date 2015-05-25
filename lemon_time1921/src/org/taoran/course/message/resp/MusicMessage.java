package org.taoran.course.message.resp;

/**
 * 响应消息 -> 音乐消息
 * 
 * @author 哓哓
 * @date 2015-4-6
 */
public class MusicMessage extends BaseMessage {
	//音乐
	private Music Music;

	public Music getMusic() {
		return Music;
	}

	public void setMusic(Music music) {
		Music = music;
	}

}
