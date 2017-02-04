package com.tinymood.wechat.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Tools {
    public static final String menu = "「梦回少年」食用指南\n"
            + "------------------------\n" + "1 点歌分享\ue03e\n"
            + "2 周边搜索\n" + "3 颜值检测" + Tools.emoji(0x1F525)
            + "\n" + "4 音乐聚合\n" + "5 文章聚合\n" + "6 天启智能机器人\n" + "7 快捷回复\n" + "8 反馈建议\n" + "更多实用功能正在开发，请期待。\n"
            + "回复“?”显示主菜单";

    public static String welcome = "\uD83D\uDE0BHello~很高兴遇见你！我不是懒，只是有时候更新的比较慢。\n先试试以下功能吧:\n" + menu;

    /**
     * 关注提示语
     *
     * @return
     */
    public static String getSubscribeMsg() {
        StringBuffer buffer = new StringBuffer();
        buffer.append(welcome).append("\n\n");
        buffer.append("祝你玩得高兴，快快发掘我的更多功能~\n\n");
        buffer.append("回复“?”显示主菜单");

        return buffer.toString();
    }

    // Debug
    public static void main(String[] args) {
        System.out.println(getHistoryArticles());
    }

    /**
     * 1、歌曲点播指南
     *
     * @return
     */
    public static String getMusicUsage() {
        StringBuffer buffer = new StringBuffer();
        buffer.append("\ue03e").append("点歌指南").append("\n\n");
        buffer.append("回复：歌曲+歌名").append("\n");
        buffer.append("例如：歌曲泡沫").append("\n");
        buffer.append("或者：歌曲泡沫@邓紫棋(用来指定歌手哦)").append("\n");
        buffer.append("搜索到歌曲后 试听一下呗 然后就可以转发给好友啦").append("\n\n");
        buffer.append("回复“?”显示主菜单");
        return buffer.toString();
    }

    /**
     * 2、周边搜索指南
     *
     * @return
     */
    public static String getLocationUsage() {
        StringBuffer buffer = new StringBuffer();
        buffer.append("\ue138").append("周边搜索指南").append("\n\n");
        buffer.append("必须先发送地理位置哦").append("\n");
        buffer.append("点击窗口底部的“+”按钮，发送位置").append("\n");
        buffer.append("然后指定关键词搜索").append("\n");
        buffer.append("格式：附近+关键词\n例如：附近美食、附近公交站、附近厕所").append("\n");
        buffer.append(Tools.emoji(0x1F525) + "小提示：有导航地图哦，再也不怕路痴了。"
                + Tools.emoji(0x1F525) + "\n\n");
        buffer.append("回复“?”显示主菜单");

        return buffer.toString();
    }

    /**
     * 3、颜值检测使用指南
     *
     * @return
     */
    public static String getFaceUsage() {
        StringBuffer buffer = new StringBuffer();
        buffer.append("颜值检测使用指南\n");
        buffer.append("方法）上传单人或多人人脸照片").append("\n");
        buffer.append("点击窗口底部的“+”按钮，发送图片。\n");
        buffer.append("回复“?”显示主菜单");

        return buffer.toString();
    }

    /**
     * 4、音乐聚合
     *
     * @return
     */
    public static String getMusicAggregation() {
        String respContent = null;
        respContent = "\ue110 音乐聚合\n"
                + "1 <a href=\"http://v.yinyuetai.com/video/2268593\">brave shine(UBW op2) - Aimer</a>\n"
                + "2 <a href=\"http://music.163.com/#/song?id=29550217\">ideal white - 俱野ましろ</a>\n"
                + "3 <a href=\"http://music.163.com/#/song?id=29736027\">believe - Kalafina</a>\n"
                + "4 <a href=\"http://music.163.com/#/song?id=560108\">MEMORIA - 他井エイル</a>\n"
                + "5 <a href=\"http://music.163.com/#/song?id=608404\">oath sign - LISA</a>\n"
                + "6 <a href=\"http://music.163.com/#/song?id=756338\">to the beginning - Kalafina</a>\n"
                + "7 <a href=\"http://music.163.com/#/song?id=30953009\">see you again-Wiz Khalifa/Charlie Puth</a>\n"
                + "8 <a href=\"http://music.163.com/#/song?id=579954\">恋爱サ┼キュレ┼ション - 花泽香菜</a>\n"
                + "9 <a href=\"http://music.qq.com/qqmusic.html?id=5034876\">You Are Beautiful - James Blunt </a>\n"
                + "10 <a href=\"http://music.qq.com/qqmusic.html?id=4825889\">时间煮雨 - 郁可唯</a>\n"
                + "11 <a href=\"http://music.qq.com/qqmusic.html?id=442823\">Live Like You're Dying - Lenka</a>\n"
                + "12 <a href=\"http://music.163.com/#/song?id=27674128\">恋するフォ┼チュンクッキ┼ - AKB48</a>\n"
                + "后台回复歌曲名或私聊少年，推荐你喜欢的歌曲。\n"
                + "少年会将各位推荐的音乐，每周进行聚合，将你的分享推荐给更多人~\n感谢你的分享和鼓励。\n\n"
                + "回复“?”显示主菜单";

        return respContent;
    }

    /**
     * 5、历史文章
     *
     * @return
     */
    public static String getHistoryArticles() {

        String respContent = "https://mp.weixin.qq.com/mp/profile_ext?action=home&__biz=MzI4MTM0MDM1OQ==&scene=124#wechat_redirect";

        return respContent;
    }


    /**
     * 6、天启智能机器人
     *
     * @return
     */
    public static String getRobotUsage() {
        StringBuffer buffer = new StringBuffer();
        buffer.append("\ue307").append("天启智能机器人食用手册").append("\n\n");
        buffer.append("方法）发送任意文字").append("\n");
        buffer.append("如'查快递''天气怎么样''翻译 小苹果''讲个笑话'").append("\n");
        buffer.append("发语音也可以哦，更多精彩等你发掘(>^ω^<)").append("\n");
        buffer.append("图灵API技术支持").append("\n");

        return buffer.toString();
    }

    /**
     * 7、快捷关键字
     *
     * @return
     */
    public static String getShortcutKeyword() {
        StringBuffer buffer = new StringBuffer();
        buffer.append("\ue126").append("快捷关键字列表").append("\n\n");
        buffer.append("使用菜单或回复music查看音乐聚合").append("\n");
        buffer.append("使用菜单或回复book查看历史文章聚合").append("\n");
        buffer.append("回复“?”显示主菜单");
        buffer.append("回复“笑话”试试");
        buffer.append("回复“天气”试试");
        buffer.append("回复“查快递”试试");
        buffer.append("回复“翻译 我爱你”试试");
        buffer.append("回复“?”显示主菜单");

        return buffer.toString();
    }

    /**
     * 8、反馈建议操作
     *
     * @return
     */
    public static String getSuggestUsage() {
        StringBuffer buffer = new StringBuffer();
        buffer.append("联系建议").append("\n\n");
        buffer.append("方法1）请直接留言").append("\n");
        buffer.append("方法2）联系管理员").append("\n");
        buffer.append("Email:xmusaber@163.com\nQQ:932191671").append("\n");
        buffer.append("回复“?”显示主菜单");

        return buffer.toString();
    }

    /**
     * 判断qq表情
     */
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

    /**
     * emoji表情转换(hex -> utf-16)
     *
     * @param hexEmoji
     * @return
     */
    public static String emoji(int hexEmoji) {
        return String.valueOf(Character.toChars(hexEmoji));
    }
}