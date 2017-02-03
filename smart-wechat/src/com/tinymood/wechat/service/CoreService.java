package com.tinymood.wechat.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.tinymood.wechat.message.resp.Article;
import com.tinymood.wechat.message.resp.Music;
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
 * ���ķ�����
 * 
 * @author ����
 * @date 2015-4-9
 */
public class CoreService {
	/**
	 * ����΢�ŷ���������
	 * 
	 * @param request
	 * @return
	 */
	public static String processRequest(HttpServletRequest request) {
		// ���ظ�΢�ŷ�������xml��Ϣ
		String respXml = null;
		// �ı���Ϣ����
		String respContent = null;
		try {
			// xml�������
			Map<String, String> requestMap = MessageUtil.parseXml(request);
			// ���ͷ��ʺţ�open_id��
			String fromUserName = requestMap.get("FromUserName");
			// �����ʺ�
			String toUserName = requestMap.get("ToUserName");
			// ��Ϣ����
			String msgType = requestMap.get("MsgType");
			// ����ʱ��
			String createTime = requestMap.get("CreateTime");

			// �ظ��ı���Ϣ
			TextMessage textMessage = new TextMessage();
			textMessage.setToUserName(fromUserName);
			textMessage.setFromUserName(toUserName);
			textMessage.setCreateTime(new Date().getTime());
			textMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);

			// �ı���Ϣ
			if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_TEXT)) {
				// �ı���Ϣ����
				String content = requestMap.get("Content").trim();

				// ����1 --> ���ܷ���
				if (content.equals("1")) {
					respContent = Tools.getTranslateUsage();
				}
				// ����2-9
				else if (content.equals("2")) {
					respContent = Tools.getMusicUsage();
				} else if (content.equals("3") || content.equals("����")) {
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
					article1.setTitle("\ue335�������ʹ��˵��");
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
					respContent = "\ue32e�ظ�'Ц��'����'��������'��";
				} else if (content.equals("8")) {
					respContent = Tools.getYuluUsage();
				} else if (content.equals("9")) {
					respContent = Tools.getSuggestUsage();
				} else if (content.equals("?") || content.equals("��")
						|| content.equals("�˵�")) {
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
					article1.setTitle("�㲻��Ҫ���ڱ��˵��Ͽ���\n");
					article1.setDescription("");
					article1.setPicUrl("http://1.lemontime.sinaapp.com/images/tinshuo.jpg");
					article1.setUrl("http://mp.weixin.qq.com/s?__biz=MjM5MzE3NDE3Ng==&mid=204590224&idx=1&sn=90a6d7453c21ea6d3c4d5cc1e60f81fa&scene=1&from=singlemessage&isappinstalled=0#rd");

					Article article2 = new Article();
					article2.setTitle("Ҫô���룬Ҫô���\n");
					article2.setDescription("");
					article2.setPicUrl("http://1.lemontime.sinaapp.com/images/2.jpg");
					article2.setUrl("http://mp.weixin.qq.com/s?__biz=MjM5MDMyMzg2MA==&mid=207775146&idx=1&sn=889555995ca6380ab1b1b839ad3e1ec3&scene=2&from=timeline&isappinstalled=0#rd");

					Article article3 = new Article();
					article3.setTitle("��˵�㻹�ڴ����ҵ�����\n");
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

				// **********������*************
				// ����ԡ�������2���ֿ�ͷ
				else if (content.startsWith("����")) {
					// ������2���ּ����������+���ո�-���������ȥ��
					String keyWord = content.replaceAll("^����[\\+ ~!@#%^-_=]?",
							"");
					// �����������Ϊ��
					if ("".equals(keyWord)) {
						respContent = Tools.getMusicUsage();
					} else {
						String[] kwArr = keyWord.split("@");
						// ��������
						String musicTitle = kwArr[0];
						// �ݳ���Ĭ��Ϊ��
						String musicAuthor = "";
						if (2 == kwArr.length)
							musicAuthor = kwArr[1];

						// ��������
						Music music = BaiduMusicService.searchMusic(musicTitle,
								musicAuthor);
						// δ����������
						if (null == music) {
							respContent = "���Ѿ���Ŭ���ˣ�����û���ҵ�����<" + musicTitle
									+ ">\n" + "����������/:8*/:8* ���Ӹ����������Կ�";
						} else {
							// ������Ϣ
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
				// **********���ܷ���*************
				else if (content.startsWith("����")) {
					String keyWord = content.replaceAll("^����", "").trim();
					if ("".equals(keyWord)) {
						respContent = Tools.getTranslateUsage();
					} else
						respContent = "\ue110�����"+ BaiduTranslateService.translate(keyWord);
				}
				// **********�ܱ�����*************
				else if (content.startsWith("����")) {
					String keyWord = content.replaceAll(" ���� ", "").trim();
					// ��ȡ�û����һ�η��͵ĵ���λ��
					UserLocation location = MySQLUtil.getLastLocation(request,
							fromUserName);
					// δ��ȡ��
					if (null == location) {
						respContent = Tools.getLocationUsage();
					} else {
						// ����ת���󣨾�ƫ�������������ܱ�POI
						List<BaiduPlace> placeList = BaiduMapUtil.searchPlace(
								keyWord, location.getBd09Lng(),
								location.getBd09Lat());
						// δ������POI
						if (null == placeList || 0 == placeList.size()) {
							respContent = String.format(
									"/�ѹ��������͵�λ�ø���δ��������%s����Ϣ��", keyWord);
						} else {
							List<Article> articleList = BaiduMapUtil
									.makeArticleList(placeList,
											location.getBd09Lng(),
											location.getBd09Lat());
							// �ظ�ͼ����Ϣ
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
				// �¼�����
				String eventType = requestMap.get("Event");
				// ��ע
				if (eventType.equals(MessageUtil.EVENT_TYPE_SUBSCRIBE)) {
					respContent = Tools.welcome;
				}
				// ȡ����ע
				else if (eventType.equals(MessageUtil.EVENT_TYPE_UNSUBSCRIBE)) {
					// TODO ȡ�����ĺ��û��������յ������˺ŷ��͵���Ϣ����˲���Ҫ�ظ�
				}
				// ɨ���������ά��
				else if (eventType.equals(MessageUtil.EVENT_TYPE_SCAN)) {
					// TODO ����ɨ���������ά���¼�
				}
				// �Զ���˵�
				else if (eventType.equals(MessageUtil.EVENT_TYPE_CLICK)) {
					// TODO ����˵�����¼�
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
						article1.setTitle("\ue335�������ʹ��˵��");
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
						respContent = "������Ͻǲ鿴��ʷ��Ϣ���ɡ�";
					} else if (eventKey.equals("31")) {
						respContent = Tools.getChatUsage();
					} else if (eventKey.equals("32")) {
						respContent = Tools.getJoke();
					} else if (eventKey.equals("33")) {
						respContent = Tools.getSuggestUsage();
					}
				}
			} else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_IMAGE)) {
				// ͼƬ��Ϣ
				// ȡ��ͼƬ��ַ
				String picUrl = requestMap.get("PicUrl");
				// �������
				String detectResult = FaceService.detect(picUrl);
				respContent = detectResult;

			} else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_VOICE)) {
				// ������Ϣ
				int a = (int) (Math.random() * 10 + 1);
				switch (a) {
				case 1:
					respContent = "�� ��������ú���/:rose";
					break;
				case 2:
					respContent = "���� ����Ŷ/:strong";
					break;
				case 3:
					respContent = "�� ����������ô" + Tools.emoji(0x1F618)
							+ Tools.emoji(0x1F618);
				case 4:
					respContent = "רҵ���� ����98,������,superstar�����㣡";
					break;
				case 5:
					respContent = "�Ͻ�ȥ�μ�ĳ�Ǹ��ְ�" + Tools.emoji(0x1F631);
					break;
				default:
					respContent = "��˵��ͼ�����֪࣬������������ứ�����ĵ�����"
							+ Tools.emoji(0x1F494);
					break;
				}
			} else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_VIDEO)) {
				// ��Ƶ��Ϣ
				int a = (int) (Math.random() * 10 + 1);
				switch (a) {
				case 1:
					respContent = "�� �ĵĲ���Ŷ/:rose";
					break;
				case 2:
					respContent = "���� ����Ŷ/:strong";
					break;
				case 3:
					respContent = "DuangDuangDuang~" + Tools.emoji(0x1F618)
							+ Tools.emoji(0x1F618);
				case 4:
					respContent = "������,��һ���¹�ϣ�����㣡";
					break;
				case 5:
					respContent = "�Ͻ�ȥ�μ���Ӱ������" + Tools.emoji(0x1F631);
					break;
				default:
					respContent = "��˵��ͼ�����֪࣬������������ứ�����ĵ�����"+ Tools.emoji(0x1F494);
					break;
				}
			} else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LOCATION)) {
				// ����λ����Ϣ
				// �û����͵ľ�γ��
				String lng = requestMap.get("Location_Y");
				String lat = requestMap.get("Location_X");
				// ����ת����ľ�γ��
				String bd09Lng = null;
				String bd09Lat = null;
				// ���ýӿ�ת������
				UserLocation userLocation = BaiduMapUtil.convertCoord(lng, lat);
				if (null != userLocation) {
					bd09Lng = userLocation.getBd09Lng();
					bd09Lat = userLocation.getBd09Lat();
				}
				// �����û�����λ��
				MySQLUtil.saveUserLocation(request, fromUserName, lng, lat,
						bd09Lng, bd09Lat);

				StringBuffer buffer = new StringBuffer();
				buffer.append("[���][���]").append("�ɹ���������λ�ã�").append("\n\n");
				buffer.append("���������������ؼ��ʻ�ȡ�ܱ���Ϣ�ˣ����磺").append("\n");
				buffer.append("������ʳ").append("\n");
				buffer.append("����KTV").append("\n");
				buffer.append("��������վ").append("\n");
				buffer.append("�����ԡ������������ֿ�ͷ��");
				respContent = buffer.toString();
			}

			if (respContent != null) {
				textMessage.setContent(respContent);
				respXml = MessageUtil.messageToXml(textMessage);
			}

			if (respContent == null && respXml==null) {
				respContent = "���������ֹ��ϣ��Ժ��ٳ��԰ѣ�������~";
				textMessage.setContent(respContent);
				respXml = MessageUtil.messageToXml(textMessage);
			}

		} catch (Exception e) {
			e.printStackTrace();
			return "���������ֹ��ϣ��Ժ��ٳ��԰ѣ�������~";
		}

		return respXml;
	}
}