package com.tinymood.wechat.service;

import com.tinymood.wechat.message.resp.*;
import com.tinymood.wechat.pojo.BaiduPlace;
import com.tinymood.wechat.pojo.UserLocation;
import com.tinymood.wechat.util.BaiduMapUtil;
import com.tinymood.wechat.util.MessageUtil;
import com.tinymood.wechat.util.MySQLUtil;
import com.tinymood.wechat.util.Tools;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;


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

            // 用户帐号（open_id）
            String fromUserName = requestMap.get("FromUserName");

            // 公众帐号
            String toUserName = requestMap.get("ToUserName");

            // 消息类型
            String msgType = requestMap.get("MsgType");

            // 创建时间
            String createTime = requestMap.get("CreateTime");

            //语音识别结果
            String recognition = requestMap.get("Recognition");

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

                if (content.equals("?") || content.equals("？")) {
                    respContent = Tools.menu;
                } else if (content.startsWith("歌曲")) {
                    respXml = musicFuntion(content, fromUserName, toUserName);
                    if (respXml == null) {
                        respXml = "";
                    }
                    return respXml;
                } else if (content.startsWith("附近")) {
                    respContent = "代码调试中，不久将会开放。发语音和我聊聊吧~";
//                    respXml = locationFunction(request, content, fromUserName, toUserName);
//                    if (respXml == null) {
//                        respXml = "";
//                    }
//                    return respXml;
                } else if (content.equalsIgnoreCase("music")) {
                    respContent = Tools.getMusicAggregation();
                } else if (content.equalsIgnoreCase("book")) {
                    respContent = Tools.getHistoryArticles();
                } else {
                    switch (content) {
                        case "1":
                            respContent = Tools.getMusicUsage();
                            break;
                        case "2":
                            respContent = Tools.getLocationUsage();
                            break;
                        case "3":
                            respContent = Tools.getFaceUsage();
                            break;
                        case "4":
                            respContent = Tools.getMusicAggregation();
                            break;
                        case "5":
                            respContent = "文章精选还在排版中，可以先试试查看历史消息~";
                            break;
                        case "6":
                            respContent = Tools.getRobotUsage();
                            break;
                        case "7":
                            respContent = Tools.getShortcutKeyword();
                            break;
                        case "8":
                            respContent = Tools.getSuggestUsage();
                            break;
                        default:
                            respContent = TulingService.getTulingResult(content);
                            break;
                    }
                }
            } else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_EVENT)) {
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
                // 扫描带参数二维码，未关注
                else if (eventType.equals(MessageUtil.EVENT_TYPE_QRCODEWITHPARAMS_NOT_FOLLOW)) {
                    String qrscene = requestMap.get("EventKey");
                    respContent = Tools.welcome + "其中EventKey为二维码参数值，即qrscene_" + qrscene;
                }
                // 扫描带参数二维码，已关注
                else if (eventType.equals(MessageUtil.EVENT_TYPE_QRCODEWITHPARAMS_FOLLOW)) {
                    String scene_id = requestMap.get("EventKey");
                    respContent = "二维码场景值scene_id=" + scene_id;
                }
                // 自定义菜单CLICK事件
                else if (eventType.equals(MessageUtil.EVENT_TYPE_CLICK)) {
                    // TODO 处理菜单点击事件
                    String eventKey = requestMap.get("EventKey");

                    if (eventKey.equals("11")) {
                        respContent = Tools.getMusicUsage();
                    } else if (eventKey.equals("12")) {
                        respContent = Tools.getLocationUsage();
                    } else if (eventKey.equals("13")) {
                        respContent = Tools.getFaceUsage();
                    } else if (eventKey.equals("21")) {
                        respContent = Tools.getMusicAggregation();
                    } else if (eventKey.equals("22")) {
                        respContent = Tools.getHistoryArticles();
                    } else if (eventKey.equals("31")) {
                        respContent = "关于 梦回少年";
                    } else if (eventKey.equals("32")) {
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

            } else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_VIDEO) || msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_SHORTVIDEO)) {
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
                        break;
                    case 4:
                        respContent = "相信我,下一个陈冠希就是你！";
                        break;
                    case 5:
                        respContent = "赶紧去参加摄影大赛把" + Tools.emoji(0x1F631);
                        break;
                    default:
                        respContent = "都说无图无真相，知道真相的我眼泪哗啦啦的掉下来" + Tools.emoji(0x1F494);
                        break;
                }
            } else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LOCATION)) {
                // 地理位置消息
                // 用户发送的经纬度
                String lng = requestMap.get("Location_Y"); // 经度
                String lat = requestMap.get("Location_X"); // 纬度

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
            } else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_VOICE)) {
                respContent = "\uD83D\uDE0B" + TulingService.getTulingResult(recognition);
            }

            if (null == respContent) {
                respContent = "";
            }
            textMessage.setContent(respContent);
            respXml = MessageUtil.messageToXml(textMessage);

        } catch (Exception e) {
            e.printStackTrace();
            return "服务器出现故障，稍后再尝试把！心塞塞~";
        }

        return respXml;
    }

    /**
     * 1、点歌分享
     *
     * @param content
     * @param fromUserName
     * @param toUserName
     * @return 音乐消息
     */
    private static String musicFuntion(String content, String fromUserName, String toUserName) {
        String respXml = null;
        String respContent = null;

        // 未找到音乐回复文本消息，找到回复音乐文件
        TextMessage textMessage = new TextMessage();
        textMessage.setToUserName(fromUserName);
        textMessage.setFromUserName(toUserName);
        textMessage.setCreateTime(new Date().getTime());
        textMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);

        // 如果以“歌曲”2个字开头
        // 将歌曲2个字及歌曲后面的+、空格、-等特殊符号去掉
        String keyWord = content.replaceAll("^歌曲[\\+ ~!@#%^-_=]?",
                "");
        // 如果歌曲名称为空
        if ("".equals(keyWord)) {
            respContent = Tools.getMusicUsage();
            textMessage.setContent(respContent);
            respXml = MessageUtil.messageToXml(textMessage);
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
//                respContent = "我已经很努力了，还是没有找到歌曲<" + musicTitle
//                        + ">\n" + "啊，心塞塞/:8*/:8* 不加歌手搜索试试看";
                respContent = "API迁移过程中，请耐心等待开放。发语音和我聊会吧~";
                textMessage.setContent(respContent);
                respXml = MessageUtil.messageToXml(textMessage);
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
        return respXml;
    }

    /**
     * 2、周边搜索
     *
     * @param request
     * @param content
     * @param fromUserName
     * @param toUserName
     * @return news图文
     */
    private static String locationFunction(HttpServletRequest request, String content, String fromUserName, String toUserName, String respContent, String respXml) {
        String keyWord = content.replaceAll("附近", "").trim();
        // 获取用户最后一次发送的地理位置
        UserLocation location = MySQLUtil.getLastLocation(request,
                fromUserName);
        // 未获取到
        if (null == location) {
            respContent = Tools.getLocationUsage();
        } else {
            // 根据转换后（纠偏）的坐标搜索周边POI
            List<BaiduPlace> placeList = null;
            try {
                placeList = BaiduMapUtil.searchPlace(
                        keyWord, location.getBd09Lng(),
                        location.getBd09Lat());
            } catch (Exception e) {
                e.printStackTrace();
            }
            // 未搜索到POI
            if (null == placeList || 0 == placeList.size()) {
                respContent = String.format(
                        "/难过，您发送的位置附近未搜索到“%s”信息！", keyWord);
            } else {
                List<Article> articleList = BaiduMapUtil.makeArticleList(placeList, location.getBd09Lng()
                        , location.getBd09Lat());
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

        return respXml;
    }
}