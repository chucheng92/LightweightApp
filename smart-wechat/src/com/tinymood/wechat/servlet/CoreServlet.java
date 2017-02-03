package com.tinymood.wechat.servlet;

import com.tinymood.wechat.util.SignUtil;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 核心请求处理类
 *
 * @author nothankyou
 * @date 2017-02-03
 */

public class CoreServlet extends HttpServlet {

    private static final long serialVersionUID = -6819864684789992342L;

    /**
	 * 确认请求来自微信服务器
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 微信加密签名
		String signature = request.getParameter("signature");
		// 时间戳
		String timestamp = request.getParameter("timestamp");
		// 随机树
		String nonce = request.getParameter("nonce");
		// 随机字符串
		String echostr = request.getParameter("echostr");

		PrintWriter out = response.getWriter();

		// 通过检验signature对请求进行检验，若检验成功则原样返回echostr，表示接入成功，否则接入失败
		if (SignUtil.checkSignature(signature, timestamp, nonce)) {
			out.print(echostr);
		}
		out.close();
		out = null;
	}

//	/**
//	 * ����У���봦��΢�ŷ�������������Ϣ
//	 */
//	public void doPost(HttpServletRequest request, HttpServletResponse response)
//			throws ServletException, IOException {
//		// ��������Ӧ�ı��������ΪUTF-8
//		request.setCharacterEncoding("UTF-8");
//		response.setCharacterEncoding("UTF-8");
//
//		// ���ղ��� ΢�ż���ǩ����ʱ����������
//		String signature = request.getParameter("signature");
//		String timestamp = request.getParameter("timestamp");
//		String nonce = request.getParameter("nonce");
//
//		PrintWriter out = response.getWriter();
//		// ����У��
//		if (SignUtil.checkSignature(signature, timestamp, nonce)) {
//			// ���ú���ҵ������մ�������
//			String respXml = CoreService.processRequest(request);
//			out.print(respXml);
//		}
//		out.close();
//		out = null;
//	}
//
//	@Override
//	public void init() {
//		File indexDir = new java.io.File(ChatService.getIndexDir());
//		//������Ŀ¼�������򴴽�����
//		if (!indexDir.exists())
//			ChatService.createIndex();
//	}
}
