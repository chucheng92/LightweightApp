package com.tinymood.wechat.service;

import java.io.IOException;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import net.sf.json.JSONObject;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

/**
 * 调用图灵机器人api接口，获取智能回复内容
 */
public class TulingService {
    /**
     * 调用图灵机器人api接口，获取智能回复内容，解析获取自己所需结果
     *
     * @param content
     * @return
     */
    public static String getTulingResult(String content) {
        /** 此处为图灵api接口，参数key根据用户注册的不同，需要修改 */
        String apiUrl = "http://www.tuling123.com/openapi/api?key=29b96fd1f0c24e2baecadf33ace838b5&info=";
        String param = "";
        try {
            //将参数转为url编码
            param = apiUrl + URLEncoder.encode(content, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        /** 发送httpget请求 */
        HttpGet request = new HttpGet(param);
        String result = "";
        try {
            HttpResponse response = new DefaultHttpClient().execute(request);

            //200即正确的返回码
            if (response.getStatusLine().getStatusCode() == 200) {
                result = EntityUtils.toString(response.getEntity());
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        /** 请求失败处理 */
        if (null == result) {
            return "对不起，刚来地球让我缓缓……";
        }


        JSONObject json = JSONObject.fromObject(result);

        //code=40005.40006.40007错误代码分别为：暂不支持该功能，服务器升级中，服务器数据格式异常
        if (40005 == json.getInt("code") || 40006 == json.getInt("code") || 40007 == json.getInt("code")) {

            result = "@#$#^%^*&^*@$@^……";
            //系统输出错误代码
            System.out.println(json.getString("text"));
        } else if (40004 == json.getInt("code")) {
            //code=40004,当天服务请求次数已经用完
            result = "小的今天实在已经精疲力尽了。。请大人明天再来聊吧";

        } else if (100000 == json.getInt("code")) {
            //以code=100000为例，参考图灵机器人api文档
            result = json.getString("text");
        } else if (200000 == json.getInt("code")) {
            result = new StringBuffer().append("<a href='").append(json.getString("url")).append("'>").append(json.getString("text")).append("</a>").toString();
        }

        return result;
    }

    public static void main(String[] args) {
        String res = getTulingResult("鱼香肉丝怎么做");
        System.out.println(res);
    }
}
