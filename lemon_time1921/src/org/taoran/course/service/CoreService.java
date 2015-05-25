package org.taoran.course.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.taoran.course.message.resp.Article;
import org.taoran.course.message.resp.Music;
import org.taoran.course.message.resp.MusicMessage;
import org.taoran.course.message.resp.NewsMessage;
import org.taoran.course.message.resp.TextMessage;
import org.taoran.course.pojo.BaiduPlace;
import org.taoran.course.pojo.UserLocation;
import org.taoran.course.util.BaiduMapUtil;
import org.taoran.course.util.MessageUtil;
import org.taoran.course.util.MySQLUtil;
import org.taoran.course.util.Tools;

/**
 * 核心服务类
 * 
 * @author 哓哓
 * @date 2015-4-9
 */
public class CoreService {
	/**
	 * 处理微信发来的请求
	 * 
	 * @param request
	 * @return
	 */
	public static String processRequest(HttpServletRequest request) {
		// 返回给微信服务器的xml消息
		String respXml = null;
		// 文本消息内容
		String respContent = null;
		try {
			// xml请求解析
			Map<String, String> requestMap = MessageUtil.parseXml(request);
			// 发送方帐号（open_id）
			String fromUserName = requestMap.get("FromUserName");
			// 公众帐号
			String toUserName = requestMap.get("ToUserName");
			// 消息类型
			String msgType = requestMap.get("MsgType");
			// 创建时间
			String createTime = requestMap.get("CreateTime");

			// 回复文本消息
			TextMessage textMessage = new TextMessage();
			textMessage.setToUserName(fromUserName);
			textMessage.setFromUserName(toUserName);
			textMessage.setCreateTime(new Date().getTime());
			textMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);

			// 文本消息
			if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_TEXT)) {
				// 文本消息内容
				String content = requestMap.get("Content").trim();

				// 功能1 --> 智能翻译
				if (content.equals("1")) {
					respContent = Tools.getTranslateUsage();
				}
				// 功能2-9
				else if (content.equals("2")) {
					respContent = Tools.getMusicUsage();
				} else if (content.equals("3") || content.equals("附近")) {
					respContent = Tools.getLocationUsage();
				} else if (content.equals("4")) {
					respContent = Tools.getChatUsage();
				} else if (content.equals("5")) {
					NewsMessage newsMessage = new NewsMessage();
					newsMessage.setToUserName(fromUserName);
					newsMessage.setFromUserName(toUserName);
					newsMessage.setCreateTime(new Date().getTime());
					newsMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_NEWS);

					Article article1 = new Article();
					article1.setTitle("\ue335人脸检测使用说明");
					article1.setDescription(Tools.getFaceUsage());
					article1.setUrl("http://1.lemontime.sinaapp.com/face.jsp");
					article1.setPicUrl("http://1.lemontime.sinaapp.com/images/facepp_inside.png");

					List<Article> articleList = new ArrayList<Article>();
					articleList.add(article1);

					newsMessage.setArticleCount(articleList.size());
					newsMessage.setArticles(articleList);

					respXml = MessageUtil.messageToXml(newsMessage);
				} else if (content.equals("6")) {
					respContent = Tools.getRecommendUsage();
				} else if (content.equals("7")) {
					respContent = "\ue32e回复'笑话'或者'讲个讲话'。";
				} else if (content.equals("8")) {
					respContent = Tools.getYuluUsage();
				} else if (content.equals("9")) {
					respContent = Tools.getSuggestUsage();
				} else if (content.equals("?") || content.equals("？")
						|| content.equals("菜单")) {
					respContent = Tools.defaultMenu;
				} else if (content.equalsIgnoreCase("music")) {
					respContent = Tools.recommendMusic();
				} else if (content.equalsIgnoreCase("book")) {
					NewsMessage newsMessage = new NewsMessage();
					newsMessage.setToUserName(fromUserName);
					newsMessage.setFromUserName(toUserName);
					newsMessage.setCreateTime(new Date().getTime());
					newsMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_NEWS);
					List<Article> articleList = new ArrayList<Article>();

					Article article1 = new Article();
					article1.setTitle("你不需要活在别人的认可里\n");
					article1.setDescription("");
					article1.setPicUrl("http://1.lemontime.sinaapp.com/images/tinshuo.jpg");
					article1.setUrl("http://mp.weixin.qq.com/s?__biz=MjM5MzE3NDE3Ng==&mid=204590224&idx=1&sn=90a6d7453c21ea6d3c4d5cc1e60f81fa&scene=1&from=singlemessage&isappinstalled=0#rd");

					Article article2 = new Article();
					article2.setTitle("要么别想，要么别放\n");
					article2.setDescription("");
					article2.setPicUrl("http://1.lemontime.sinaapp.com/images/2.jpg");
					article2.setUrl("http://mp.weixin.qq.com/s?__biz=MjM5MDMyMzg2MA==&mid=207775146&idx=1&sn=889555995ca6380ab1b1b839ad3e1ec3&scene=2&from=timeline&isappinstalled=0#rd");

					Article article3 = new Article();
					article3.setTitle("听说你还在打听我的下落\n");
					article3.setDescription("");
					article3.setPicUrl("http://1.lemontime.sinaapp.com/images/2.jpg");
					article3.setUrl("http://mp.weixin.qq.com/s?__biz=MjM5MDMyMzg2MA==&mid=206602741&idx=7&sn=5ec66fd8f7ef8c728bc0ab4f0ca028eb&scene=1&key=2e5b2e802b7041cfb0e80de307be7ae446ddd501b4ef3a08507869759c5186bec413de73a5daa5c3a6f9ceba36acb8d5&ascene=1&uin=MTczNTcxMTc2MA%3D%3D&devicetype=Windows+8&version=61000721&pass_ticket=3EXOcuhxs40Wm%2BO1TrSTMxl6o%2BtwmgRIK%2BvCmFq%2B0YIfgL9wdNZzOLODMPLl5eqJ");

					articleList.add(article1);
					articleList.add(article2);
					articleList.add(article3);
					newsMessage.setArticleCount(articleList.size());
					newsMessage.setArticles(articleList);
					respXml = MessageUtil.messageToXml(newsMessage);
				} else if (Tools.isQQFace(content)) {
					respContent = content;
				}

				// **********点歌分享*************
				// 如果以“歌曲”2个字开头
				else if (content.startsWith("歌曲")) {
					// 将歌曲2个字及歌曲后面的+、空格、-等特殊符号去掉
					String keyWord = content.replaceAll("^歌曲[\\+ ~!@#%^-_=]?",
							"");
					// 如果歌曲名称为空
					if ("".equals(keyWord)) {
						respContent = Tools.getMusicUsage();
					} else {
						String[] kwArr = keyWord.split("@");
						// 歌曲名称
						String musicTitle = kwArr[0];
						// 演唱者默认为空
						String musicAuthor = "";
						if (2 == kwArr.length)
							musicAuthor = kwArr[1];

						// 搜索音乐
						Music music = BaiduMusicService.searchMusic(musicTitle,
								musicAuthor);
						// 未搜索到音乐
						if (null == music) {
							respContent = "我已经很努力了，还是没有找到歌曲<" + musicTitle
									+ ">\n" + "啊，心塞塞/:8*/:8* 不加歌手搜索试试看";
						} else {
							// 音乐消息
							MusicMessage musicMessage = new MusicMessage();
							musicMessage.setToUserName(fromUserName);
							musicMessage.setFromUserName(toUserName);
							musicMessage.setCreateTime(new Date().getTime());
							musicMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_MUSIC);
							musicMessage.setMusic(music);
							respXml = MessageUtil.messageToXml(musicMessage);
						}
					}
				}
				// **********智能翻译*************
				else if (content.startsWith("翻译")) {
					String keyWord = content.replaceAll("^翻译", "").trim();
					if ("".equals(keyWord)) {
						respContent = Tools.getTranslateUsage();
					} else
						respContent = "\ue110结果："+ BaiduTranslateService.translate(keyWord);
				}
				// **********周边搜索*************
				else if (content.startsWith("附近")) {
					String keyWord = content.replaceAll(" 附近 ", "").trim();
					// 获取用户最后一次发送的地理位置
					UserLocation location = MySQLUtil.getLastLocation(request,
							fromUserName);
					// 未获取到
					if (null == location) {
						respContent = Tools.getLocationUsage();
					} else {
						// 根据转换后（纠偏）的坐标搜索周边POI
						List<BaiduPlace> placeList = BaiduMapUtil.searchPlace(
								keyWord, location.getBd09Lng(),
								location.getBd09Lat());
						// 未搜索到POI
						if (null == placeList || 0 == placeList.size()) {
							respContent = String.format(
									"/难过，您发送的位置附近未搜索到“%s”信息！", keyWord);
						} else {
							List<Article> articleList = BaiduMapUtil
									.makeArticleList(placeList,
											location.getBd09Lng(),
											location.getBd09Lat());
							// 回复图文消息
							NewsMessage newsMessage = new NewsMessage();
							newsMessage.setToUserName(fromUserName);
							newsMessage.setFromUserName(toUserName);
							newsMessage.setCreateTime(new Date().getTime());
							newsMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_NEWS);
							newsMessage.setArticles(articleList);
							newsMessage.setArticleCount(articleList.size());

							respXml = MessageUtil.messageToXml(newsMessage);
						}
					}
				} else {
					respContent = ChatService.chat(fromUserName, createTime, content);
				}
			}

			// **********************************
			if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_EVENT)) {
				// 事件类型
				String eventType = requestMap.get("Event");
				// 关注
				if (eventType.equals(MessageUtil.EVENT_TYPE_SUBSCRIBE)) {
					respContent = Tools.welcome;
				}
				// 取消关注
				else if (eventType.equals(MessageUtil.EVENT_TYPE_UNSUBSCRIBE)) {
					// TODO 取消订阅后用户不会再收到公众账号发送的消息，因此不需要回复
				}
				// 扫描带参数二维码
				else if (eventType.equals(MessageUtil.EVENT_TYPE_SCAN)) {
					// TODO 处理扫描带参数二维码事件
				}
				// 自定义菜单
				else if (eventType.equals(MessageUtil.EVENT_TYPE_CLICK)) {
					// TODO 处理菜单点击事件
					String eventKey = requestMap.get("EventKey");

					if (eventKey.equals("11")) {
						respContent = Tools.getTranslateUsage();
					} else if (eventKey.equals("12")) {
						respContent = Tools.getMusicUsage();
					} else if (eventKey.equals("13")) {
						respContent = Tools.getLocationUsage();
					} else if (eventKey.equals("14")) {
						NewsMessage newsMessage = new NewsMessage();
						newsMessage.setToUserName(fromUserName);
						newsMessage.setFromUserName(toUserName);
						newsMessage.setCreateTime(new Date().getTime());
						newsMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_NEWS);

						Article article1 = new Article();
						article1.setTitle("\ue335人脸检测使用说明");
						article1.setDescription(Tools.getFaceUsage());
						article1.setUrl("http://1.lemontime.sinaapp.com/face.jsp");
						article1.setPicUrl("http://1.lemontime.sinaapp.com/images/facepp_inside.png");

						List<Article> articleList = new ArrayList<Article>();
						articleList.add(article1);

						newsMessage.setArticleCount(articleList.size());
						newsMessage.setArticles(articleList);

						respXml = MessageUtil.messageToXml(newsMessage);
					} else if (eventKey.equals("21")) {
						respContent = Tools.getTuijian();
					} else if (eventKey.equals("22")) {
						respContent = Tools.getYuluUsage();
					} else if (eventKey.equals("23")) {
						respContent = "点击右上角查看历史消息即可。";
					} else if (eventKey.equals("31")) {
						respContent = Tools.getChatUsage();
					} else if (eventKey.equals("32")) {
						respContent = Tools.getJoke();
					} else if (eventKey.equals("33")) {
						respContent = Tools.getSuggestUsage();
					}
				}
			} else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_IMAGE)) {
				// 图片消息
				// 取得图片地址
				String picUrl = requestMap.get("PicUrl");
				// 人脸检测
				String detectResult = FaceService.detect(picUrl);
				respContent = detectResult;

			} else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_VOICE)) {
				// 语音消息
				int a = (int) (Math.random() * 10 + 1);
				switch (a) {
				case 1:
					respContent = "哇 你的声音好好听/:rose";
					break;
				case 2:
					respContent = "哎呦 不错哦/:strong";
					break;
				case 3:
					respContent = "哇 你是萌妹子么" + Tools.emoji(0x1F618)
							+ Tools.emoji(0x1F618);
				case 4:
					respContent = "专业评分 给你98,相信我,superstar就是你！";
					break;
				case 5:
					respContent = "赶紧去参加某是歌手把" + Tools.emoji(0x1F631);
					break;
				default:
					respContent = "都说无图无真相，知道真相的我眼泪哗啦啦的掉下来"
							+ Tools.emoji(0x1F494);
					break;
				}
			} else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_VIDEO)) {
				// 视频消息
				int a = (int) (Math.random() * 10 + 1);
				switch (a) {
				case 1:
					respContent = "哇 拍的不错哦/:rose";
					break;
				case 2:
					respContent = "哎呦 不错哦/:strong";
					break;
				case 3:
					respContent = "DuangDuangDuang~" + Tools.emoji(0x1F618)
							+ Tools.emoji(0x1F618);
				case 4:
					respContent = "相信我,下一个陈冠希就是你！";
					break;
				case 5:
					respContent = "赶紧去参加摄影大赛把" + Tools.emoji(0x1F631);
					break;
				default:
					respContent = "都说无图无真相，知道真相的我眼泪哗啦啦的掉下来"+ Tools.emoji(0x1F494);
					break;
				}
			} else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LOCATION)) {
				// 地理位置消息
				// 用户发送的经纬度
				String lng = requestMap.get("Location_Y");
				String lat = requestMap.get("Location_X");
				// 坐标转换后的经纬度
				String bd09Lng = null;
				String bd09Lat = null;
				// 调用接口转换坐标
				UserLocation userLocation = BaiduMapUtil.convertCoord(lng, lat);
				if (null != userLocation) {
					bd09Lng = userLocation.getBd09Lng();
					bd09Lat = userLocation.getBd09Lat();
				}
				// 保存用户地理位置
				MySQLUtil.saveUserLocation(request, fromUserName, lng, lat,
						bd09Lng, bd09Lat);

				StringBuffer buffer = new StringBuffer();
				buffer.append("[愉快][愉快]").append("成功接收您的位置！").append("\n\n");
				buffer.append("您可以输入搜索关键词获取周边信息了，例如：").append("\n");
				buffer.append("附近美食").append("\n");
				buffer.append("附近KTV").append("\n");
				buffer.append("附近公交站").append("\n");
				buffer.append("必须以“附近”两个字开头！");
				respContent = buffer.toString();
			}

			if (respContent != null) {
				textMessage.setContent(respContent);
				respXml = MessageUtil.messageToXml(textMessage);
			}

			if (respContent == null && respXml==null) {
				respContent = "服务器出现故障，稍后再尝试把！心塞塞~";
				textMessage.setContent(respContent);
				respXml = MessageUtil.messageToXml(textMessage);
			}

		} catch (Exception e) {
			e.printStackTrace();
			return "服务器出现故障，稍后再尝试把！心塞塞~";
		}

		return respXml;
	}
}