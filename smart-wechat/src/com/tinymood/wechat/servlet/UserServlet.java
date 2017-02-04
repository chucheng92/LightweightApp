package com.tinymood.wechat.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 使用JDBC测试新浪SAE数据库
 *
 * @author 哓哓
 *
 */

public class UserServlet extends HttpServlet {

	private static final long serialVersionUID = 119132134141414675L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("gb2312");
		response.setCharacterEncoding("gb2312");

		PrintWriter out = response.getWriter();
		// 查询user
		List<HashMap<String, Object>> userList = queryUser(request);
		// 遍历List集合
		for (HashMap<String, Object> map : userList) {
			out.println(map.get("name") + " " + map.get("age"));
		}
		out.flush();
		out.close();
	}

	/**
	 * 查询SAE MySQL数据库中user表
	 */
	private List<HashMap<String, Object>> queryUser(HttpServletRequest request) {

		List<HashMap<String, Object>> userList = new ArrayList<HashMap<String, Object>>();

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
			Connection conn = DriverManager.getConnection(connName, username, password);
			// 定义查询SQL语句
			String sql = "select * from user";
			// 创建PreparedStatement对象(包含已编译的sql语句)
			PreparedStatement ps = conn.prepareStatement(sql);
			// 执行查询并获取结果集
			ResultSet rs = ps.executeQuery();

			// 遍历结果集
			while (rs.next()) {
				HashMap<String, Object> userMap = new HashMap<String, Object>();
				userMap.put("name", rs.getString("name"));
				userMap.put("age", rs.getInt("age"));
				userList.add(userMap);
			}
			// 关闭连接，释放资源
			rs.close();
			ps.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return userList;
	}
}
