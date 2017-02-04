package com.tinymood.wechat.util;

import com.tinymood.wechat.pojo.Knowledge;
import com.tinymood.wechat.pojo.UserLocation;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;


/**
 * Mysql数据库操作类
 *
 * @author nothankyou
 * @date 2017-02-03 22:08:13
 */
public class MySQLUtil {
	/**
	 * 获取Mysql数据库连接
	 *
	 * @return Connection
	 */
	private Connection getConn(HttpServletRequest request) {
		Connection conn = null;

		String dbName = "app_saber92";
		String host = "w.rdc.sae.sina.com.cn";
		String port = "3307";
		String username = "2l0jj5xw1k";
		String password = "hiwil13w0lw5iil1x41zy02235hx24mylyx555jy";

		String driverName = "com.mysql.jdbc.Driver";
		String dbUrl = "jdbc:mysql://";
		String serverName = host + ":" + port + "/";
		String connName = dbUrl + serverName + dbName;

		try {
			// 加载MySQL驱动
			Class.forName(driverName);
			// 获取数据库连接
			System.out.println("找不到驱动程序类 ，加载驱动失败！");
			conn = DriverManager.getConnection(connName, username, password);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}

	private Connection getConn() {
		Connection conn = null;

		String dbName = "app_saber92";
		String host = "w.rdc.sae.sina.com.cn";
		String port = "3307";
		String username = "2l0jj5xw1k";
		String password = "hiwil13w0lw5iil1x41zy02235hx24mylyx555jy";

		String driverName = "com.mysql.jdbc.Driver";
		String dbUrl = "jdbc:mysql://";
		String serverName = host + ":" + port + "/";
		String connName = dbUrl + serverName + dbName;

		try {
			// 加载MySQL驱动
			Class.forName(driverName);
			// 获取数据库连接
			System.out.println("找不到驱动程序类 ，加载驱动失败！");
			conn = DriverManager.getConnection(connName, username, password);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}

	/**
	 * 释放JDBC资源
	 *
	 */
	private void releaseResources(Connection conn, PreparedStatement ps, ResultSet rs) {
		try {
			if (null!=rs)
				rs.close();
			if (null!=ps)
				ps.cancel();
			if (null!=conn)
				conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 保存用户地理位置
	 *
	 * @param request 请求对象
	 * @param openId 用户的OpenID
	 * @param lng 用户发送的经度
	 * @param lat 用户发送的纬度
	 * @param bd09_lng 经过百度坐标转换后的经度
	 * @param bd09_lat 经过百度坐标转换后的纬度
	 */
	public static void saveUserLocation(HttpServletRequest request, String openId, String lng, String lat, String bd09_lng, String bd09_lat) {
		String sql = "insert into user_location(open_id, lng, lat, bd09_lng, bd09_lat) values (?, ?, ?, ?, ?)";
		try {
			Connection conn = new MySQLUtil().getConn(request);
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, openId);
			ps.setString(2, lng);
			ps.setString(3, lat);
			ps.setString(4, bd09_lng);
			ps.setString(5, bd09_lat);
			ps.executeUpdate();
			// 释放资源
			ps.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 获取用户最后一次发送的地理位置
	 *
	 * @param request 请求对象
	 * @param openId 用户的OpenID
	 * @return UserLocation
	 */
	public static UserLocation getLastLocation(HttpServletRequest request, String openId) {
		UserLocation userLocation = null;
		String sql = "select open_id, lng, lat, bd09_lng, bd09_lat from user_location where open_id=? order by id desc limit 0,1";
		try {
			Connection conn = new MySQLUtil().getConn(request);
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, openId);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				userLocation = new UserLocation();
				userLocation.setOpenId(rs.getString("open_id"));
				userLocation.setLng(rs.getString("lng"));
				userLocation.setLat(rs.getString("lat"));
				userLocation.setBd09Lng(rs.getString("bd09_lng"));
				userLocation.setBd09Lat(rs.getString("bd09_lat"));
			}
			// 释放资源
			rs.close();
			ps.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return userLocation;
	}

	/**
	 * 获取问答知识表中所有记录
	 *
	 * @return List<Knowledge>
	 */
	public static List<Knowledge> findAllKnowledge() {
		List<Knowledge> knowledgeList = new ArrayList<Knowledge>();
		String sql = "select * from knowledge";
		MySQLUtil mysqlUtil = new MySQLUtil();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = mysqlUtil.getConn();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				Knowledge knowledge = new Knowledge();
				knowledge.setId(rs.getInt("id"));
				knowledge.setQuestion(rs.getString("question"));
				knowledge.setAnswer(rs.getString("answer"));
				knowledge.setCategory(rs.getInt("category"));
				knowledgeList.add(knowledge);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 释放资源
			mysqlUtil.releaseResources(conn, ps, rs);
		}
		return knowledgeList;
	}

	/**
	 * 获取上一次的聊天类别
	 *
	 * @param openId 用户的OpenID
	 * @return chatCategory
	 */
	public static int getLastCategory(String openId) {
		int chatCategory = -1;
		String sql = "select chat_category from chat_log where open_id=? order by id desc limit 0,1";

		MySQLUtil mysqlUtil = new MySQLUtil();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = mysqlUtil.getConn();
			ps = conn.prepareStatement(sql);
			ps.setString(1, openId);
			rs = ps.executeQuery();
			if (rs.next()) {
				chatCategory = rs.getInt("chat_category");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 释放资源
			mysqlUtil.releaseResources(conn, ps, rs);
		}
		return chatCategory;
	}

	/**
	 * 根据知识id随机获取一个答案
	 *
	 * @param knowledgeId 问答知识id
	 * @return
	 */
	public static String getKnowledSub(int knowledgeId) {
		String knowledgeAnswer = "";
		String sql = "select answer from knowledge_sub where pid=? order by rand() limit 0,1";

		MySQLUtil mysqlUtil = new MySQLUtil();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = mysqlUtil.getConn();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, knowledgeId);
			rs = ps.executeQuery();
			if (rs.next()) {
				knowledgeAnswer = rs.getString("answer");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 释放资源
			mysqlUtil.releaseResources(conn, ps, rs);
		}
		return knowledgeAnswer;
	}

	/**
	 * 随机获取一条笑话
	 *
	 * @return String
	 */
	public static String getJoke() {
		String jokeContent = "";
		String sql = "select joke_content from joke order by rand() limit 0,1";

		MySQLUtil mysqlUtil = new MySQLUtil();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = mysqlUtil.getConn();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			if (rs.next()) {
				jokeContent = rs.getString("joke_content");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 释放资源
			mysqlUtil.releaseResources(conn, ps, rs);
		}
		return jokeContent;
	}


	/**
	 * 随机获取一条语录
	 *
	 * @return String
	 */
	public static String getYulu() {
		String yuluContent = "";
		String sql = "select yulu_content from yulu order by rand() limit 0,1";

		MySQLUtil mysqlUtil = new MySQLUtil();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = mysqlUtil.getConn();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			if (rs.next()) {
				yuluContent = rs.getString("yulu_content");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 释放资源
			mysqlUtil.releaseResources(conn, ps, rs);
		}
		return yuluContent;
	}

	/**
	 * 保存聊天记录
	 *
	 * @param openId 用户的OpenID
	 * @param createTime 消息创建时间
	 * @param reqMsg 用户上行的消息
	 * @param respMsg 公众账号回复的消息
	 * @param chatCategory 聊天类别
	 */
	public static void saveChatLog(String openId, String createTime, String reqMsg, String respMsg, int chatCategory) {
		String sql = "insert into chat_log(open_id, create_time, req_msg, resp_msg, chat_category) values(?, ?, ?, ?, ?)";

		MySQLUtil mysqlUtil = new MySQLUtil();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = mysqlUtil.getConn();
			ps = conn.prepareStatement(sql);
			ps.setString(1, openId);
			ps.setString(2, createTime);
			ps.setString(3, reqMsg);
			ps.setString(4, respMsg);
			ps.setInt(5, chatCategory);
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 释放资源
			mysqlUtil.releaseResources(conn, ps, rs);
		}
	}
}