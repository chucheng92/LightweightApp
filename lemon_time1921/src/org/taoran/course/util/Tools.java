package org.taoran.course.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Tools {
	public static final String defaultMenu = "------------------------\n" + "推荐: 周边搜索\n"
			+ "------------------------\n" + "1 智能翻译 2 点歌分享\ue03e\n"
			+ "3 周边搜索 4 我是Lemon\n" + "5 颜值检测 强势来袭" + Tools.emoji(0x1F525)
			+ "\n" + "6 本周推荐 7 讲个笑话\n" + "8 经典语录 9 反馈建议\n"+"更多实用功能正在开发，请期待。\n\n"
			+ "或直接点击菜单体验";
	public static String welcome = "亲，怎么现在才来呢？\ue11b\n\n我是你生活娱乐的好帮手，快快体验吧！么么哒~";

	/**
	 * 歌曲点播使用指南
	 * 
	 * @return
	 */
	public static String getMusicUsage() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("\ue03e").append("点歌分享操作指南").append("\n\n");
		buffer.append("回复：歌曲+歌名").append("\n");
		buffer.append("例如：歌曲泡沫").append("\n");
		buffer.append("或者：歌曲泡沫@邓紫棋(用来指定歌手哦)").append("\n");
		buffer.append("搜索到歌曲后 试听一下呗 然后就可以转发给好友啦").append("\n\n");
		buffer.append("回复“?”显示主菜单");
		return buffer.toString();
	}

	/**
	 * 获得主界面
	 * 
	 * @return
	 */
	public static String getJoke() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("/::D").append("笑话使用指南").append("\n\n");
		buffer.append("回复‘笑话’即可。").append("\n\n");
		buffer.append("回复“?”显示主菜单");

		return buffer.toString();
	}

	/**
	 * 天气预报使用指南
	 * 
	 * @return
	 */
	public static String getWeatherUsage() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("天气查询指南").append("\n\n");
		buffer.append("回复：天气+城市").append("\n");
		buffer.append("例如：天气厦门").append("\n");
		buffer.append("回复“?”显示主菜单");
		return buffer.toString();
	}

	/**
	 * emoji表情转换(hex -> utf-16)
	 * 
	 * @param hexEmoji
	 * @return
	 */
	public static String emoji(int hexEmoji) {
		return String.valueOf(Character.toChars(hexEmoji));
	}

	/**
	 * 关注提示语
	 * 
	 * @return
	 */
	public static String getSubscribeMsg() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("您是否有过出门在外四处找ATM、公交车，KTV或公厕的经历？").append("\n\n");
		buffer.append("您是否有过出差在外搜寻美食或娱乐场所的经历？").append("\n\n");
		buffer.append("周边搜索为您的出行保驾护航，为您提供专业的周边生活指南，回复“附近”开始体验吧！");
		return buffer.toString();
	}

	/**
	 * 周边搜索指南
	 * 
	 * @return
	 */
	public static String getLocationUsage() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("\ue138").append("周边搜索使用说明").append("\n\n");
		buffer.append("必须先发送地理位置哦").append("\n");
		buffer.append("点击窗口底部的“+”按钮，发送位置").append("\n");
		buffer.append("然后指定关键词搜索").append("\n");
		buffer.append("格式：附近+关键词\n例如：附近美食、附近公交站、附近厕所").append("\n");
		buffer.append(Tools.emoji(0x1F525) + "小提示：有导航地图哦，再也不怕路痴了。"
				+ Tools.emoji(0x1F525));
		return buffer.toString();
	}

	/**
	 * 翻译使用指南
	 * 
	 * @return
	 */
	public static String getTranslateUsage() {
		StringBuffer buffer = new StringBuffer();
		buffer.append(emoji(0xe148)).append("智能翻译使用指南").append("\n\n");
		buffer.append("支持以下翻译方向：").append("\n");
		buffer.append("    中 -> 英").append("\n");
		buffer.append("    英 -> 中").append("\n");
		buffer.append("    日 -> 中").append("\n\n");
		buffer.append("使用示例：").append("\n");
		buffer.append("    翻译我是中国人").append("\n");
		buffer.append("    翻译dream").append("\n");
		buffer.append("    翻译さようなら").append("\n\n");
		buffer.append("百度翻译 技术支持");
		return buffer.toString();
	}

	/**
	 * 公交查询使用指南
	 * 
	 * @return
	 */
	public static String getBusUsage() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("公交查询使用说明").append("\n\n");
		buffer.append("方法1）发送城市线路").append("\n");
		buffer.append("方法2）指定关键词搜索").append("\n");
		buffer.append("格式：城市关键词\n例如：厦门135路").append("\n");
		buffer.append("注：功能尚未开放期间，输入”附近公交站“0.0").append("\n");
		return buffer.toString();
	}

	/**
	 * 游戏使用指南
	 * 
	 * @return
	 */
	public static String getGameUsage() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("玩游戏使用说明").append("\n\n");
		buffer.append("方法1）发送翻译语句").append("\n");
		buffer.append("点击窗口底部的“+”按钮，选择“翻译”，点“发送”").append("\n\n");
		buffer.append("方法2）指定关键词搜索").append("\n");
		return buffer.toString();
	}

	/**
	 * 人脸检测使用指南
	 * 
	 * @return
	 */
	public static String getFaceUsage() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("\n");
		buffer.append("方法）上传单人或多人人脸照片").append("\n");
		buffer.append("点击窗口底部的“+”按钮，发送图片。");
		return buffer.toString();
	}

	/**
	 * 聊天使用指南
	 * 
	 * @return
	 */
	public static String getChatUsage() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("\ue307").append("Lemon智能聊天").append("\n\n");
		buffer.append("Lucene引擎技术支持").append("\n");
		buffer.append("赶紧试试吧(>^ω^<)").append("\n");
		buffer.append("方法）发送任意文字").append("\n");

		return buffer.toString();
	}

	/**
	 * 反馈建议操作
	 * 
	 * @return
	 */
	public static String getSuggestUsage() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("联系建议").append("\n\n");
		buffer.append("方法1）请直接留言").append("\n");
		buffer.append("方法2）联系管理员").append("\n");
		buffer.append("Email:932191671@qq.com\nQQ:932191671").append("\n");
		return buffer.toString();
	}

	public static String recommendMusic() {
		String respContent = null;
		respContent = "\ue110 本周推荐1 歌单FATE\n"
				+ "主打 <a href=\"http://v.yinyuetai.com/video/2268593\">brave shine(UBW op2) - Aimer</a>\n\n"
				+ "1 <a href=\"http://music.163.com/#/song?id=29550217\">ideal white - 綾野ましろ</a>\n"
				+ "2 <a href=\"http://music.163.com/#/song?id=29736027\">believe - Kalafina</a>\n"
				+ "3 <a href=\"http://music.163.com/#/song?id=560108\">MEMORIA - 藍井エイル</a>\n"
				+ "4 <a href=\"http://music.163.com/#/song?id=608404\">oath sign - LISA</a>\n"
				+ "5 <a href=\"http://music.163.com/#/song?id=756338\">to the beginning - Kalafina</a>\n\n"
				+ "\ue110 本周推荐2 关于音乐\n"
				+ "主打 <a href=\"http://music.163.com/#/song?id=30953009\">see you again-Wiz Khalifa/Charlie Puth</a>\n\n"
				+ "1 <a href=\"http://music.163.com/#/song?id=579954\">恋爱サーキュレーション - 花泽香菜</a>\n"
				+ "2 <a href=\"http://music.qq.com/qqmusic.html?id=5034876\">You Are Beautiful - James Blunt </a>\n"
				+ "3 <a href=\"http://music.qq.com/qqmusic.html?id=4825889\">时间煮雨 - 郁可唯</a>\n"
				+ "4 <a href=\"http://music.qq.com/qqmusic.html?id=442823\">Live Like You're Dying - Lenka</a>\n"
				+ "5 <a href=\"http://music.163.com/#/song?id=27674128\">恋するフォーチュンクッキー - AKB48</a>\n"
				+ "\n回复music或book获取指定内容推荐Mua~";
		return respContent;
	}

	public static String getRecommendUsage() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("\ue126").append("推荐功能使用说明").append("\n\n");
		buffer.append("回复Music推荐音乐").append("\n");
		buffer.append("回复Book推荐文章").append("\n");
		return buffer.toString();
	}

	public static boolean isQQFace(String content) {
		boolean result = false;

		// 判断QQ表情的正则表达式
		String qqfaceRegex = "/::\\)|/::~|/::B|/::\\||/:8-\\)|/::<|/::$|/::X|/::Z|/::'\\(|/::-\\||/::@|/::P|/::D|/::O|/::\\(|/::\\+|/:--b|/::Q|/::T|/:,@P|/:,@-D|/::d|/:,@o|/::g|/:\\|-\\)|/::!|/::L|/::>|/::,@|/:,@f|/::-S|/:\\?|/:,@x|/:,@@|/::8|/:,@!|/:!!!|/:xx|/:bye|/:wipe|/:dig|/:handclap|/:&-\\(|/:B-\\)|/:<@|/:@>|/::-O|/:>-\\||/:P-\\(|/::'\\||/:X-\\)|/::\\*|/:@x|/:8\\*|/:pd|/:<W>|/:beer|/:basketb|/:oo|/:coffee|/:eat|/:pig|/:rose|/:fade|/:showlove|/:heart|/:break|/:cake|/:li|/:bome|/:kn|/:footb|/:ladybug|/:shit|/:moon|/:sun|/:gift|/:hug|/:strong|/:weak|/:share|/:v|/:@\\)|/:jj|/:@@|/:bad|/:lvu|/:no|/:ok|/:love|/:<L>|/:jump|/:shake|/:<O>|/:circle|/:kotow|/:turn|/:skip|/:oY|/:#-0|/:hiphot|/:kiss|/:<&|/:&>";

		Pattern p = Pattern.compile(qqfaceRegex);
		Matcher m = p.matcher(content);
		if (m.matches()) {
			result = true;
		}
		return result;
	}

	public static String getYuluUsage() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("让自己做一个美好的人，从读书开始吧").append("\n\n");
		buffer.append("回复’席慕容‘或者‘语录‘").append("\n");
		buffer.append("Tip：回复next可收取下一条").append("\n");
		return buffer.toString();
	}

	public static String getTuijian() {
		String respContent = null;
		respContent = "\ue110 本周推荐1 歌单FATE\n"
				+ "主打 <a href=\"http://v.yinyuetai.com/video/2268593\">brave shine(UBW op2) - Aimer</a>\n\n"
				+ "1 <a href=\"http://music.163.com/#/song?id=29550217\">ideal white - 綾野ましろ</a>\n"
				+ "2 <a href=\"http://music.163.com/#/song?id=29736027\">believe - Kalafina</a>\n"
				+ "3 <a href=\"http://music.163.com/#/song?id=560108\">MEMORIA - 藍井エイル</a>\n"
				+ "4 <a href=\"http://music.163.com/#/song?id=608404\">oath sign - LISA</a>\n"
				+ "5 <a href=\"http://music.163.com/#/song?id=756338\">to the beginning - Kalafina</a>\n\n"
				+ "\ue110 本周推荐2 关于音乐\n"
				+ "主打 <a href=\"http://music.163.com/#/song?id=30953009\">see you again-Wiz Khalifa/Charlie Puth</a>\n\n"
				+ "1 <a href=\"http://music.163.com/#/song?id=579954\">恋爱サーキュレーション - 花泽香菜</a>\n"
				+ "2 <a href=\"http://music.qq.com/qqmusic.html?id=5034876\">You Are Beautiful - James Blunt </a>\n"
				+ "3 <a href=\"http://music.qq.com/qqmusic.html?id=4825889\">时间煮雨 - 郁可唯</a>\n"
				+ "4 <a href=\"http://music.qq.com/qqmusic.html?id=442823\">Live Like You're Dying - Lenka</a>\n"
				+ "5 <a href=\"http://music.163.com/#/song?id=27674128\">恋するフォーチュンクッキー - AKB48</a>\n";
		return respContent;
	}
}
