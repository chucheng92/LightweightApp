package org.taoran.course.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.taoran.course.pojo.Knowledge;
import org.taoran.course.pojo.UserLocation;

/**
 * Mysql���ݿ������
 * 
 * @author ����
 * @date 2015-4-10
 */
public class MySQLUtil {
	/**
	 * ��ȡMysql���ݿ�����
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
			// ����MySQL����
			Class.forName(driverName);
			// ��ȡ���ݿ�����
			System.out.println("�Ҳ������������� ����������ʧ�ܣ�");
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
			// ����MySQL����
			Class.forName(driverName);
			// ��ȡ���ݿ�����
			System.out.println("�Ҳ������������� ����������ʧ�ܣ�");
			conn = DriverManager.getConnection(connName, username, password);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	/**
	 * �ͷ�JDBC��Դ
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
	 * �����û�����λ��
	 * 
	 * @param request �������
	 * @param openId �û���OpenID
	 * @param lng �û����͵ľ���
	 * @param lat �û����͵�γ��
	 * @param bd09_lng �����ٶ�����ת����ľ���
	 * @param bd09_lat �����ٶ�����ת�����γ��
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
			// �ͷ���Դ
			ps.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * ��ȡ�û����һ�η��͵ĵ���λ��
	 * 
	 * @param request �������
	 * @param openId �û���OpenID
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
			// �ͷ���Դ
			rs.close();
			ps.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return userLocation;
	}
	
	/**
	 * ��ȡ�ʴ�֪ʶ�������м�¼
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
			// �ͷ���Դ
			mysqlUtil.releaseResources(conn, ps, rs);
		}
		return knowledgeList;
	}

	/**
	 * ��ȡ��һ�ε��������
	 * 
	 * @param openId �û���OpenID
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
			// �ͷ���Դ
			mysqlUtil.releaseResources(conn, ps, rs);
		}
		return chatCategory;
	}

	/**
	 * ����֪ʶid�����ȡһ����
	 * 
	 * @param knowledgeId �ʴ�֪ʶid
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
			// �ͷ���Դ
			mysqlUtil.releaseResources(conn, ps, rs);
		}
		return knowledgeAnswer;
	}

	/**
	 * �����ȡһ��Ц��
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
			// �ͷ���Դ
			mysqlUtil.releaseResources(conn, ps, rs);
		}
		return jokeContent;
	}

	
	/**
	 * �����ȡһ����¼
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
			// �ͷ���Դ
			mysqlUtil.releaseResources(conn, ps, rs);
		}
		return yuluContent;
	}

	/**
	 * ���������¼
	 * 
	 * @param openId �û���OpenID
	 * @param createTime ��Ϣ����ʱ��
	 * @param reqMsg �û����е���Ϣ
	 * @param respMsg �����˺Żظ�����Ϣ
	 * @param chatCategory �������
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
			// �ͷ���Դ
			mysqlUtil.releaseResources(conn, ps, rs);
		}
	}
}