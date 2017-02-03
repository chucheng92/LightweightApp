package org.taoran.course.service;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.taoran.course.pojo.Face;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 人脸检测服务
 * 
 * @author 哓哓
 * @date 2015-4-15
 */
public class FaceService {
	/**
	 * 发送http请求
	 * 
	 * @param requestUrl
	 *            请求地址
	 * @return String
	 */
	private static String httpRequest(String requestUrl) {
		StringBuffer buffer = new StringBuffer();
		try {
			URL url = new URL(requestUrl);
			HttpURLConnection httpUrlConn = (HttpURLConnection) url
					.openConnection();
			httpUrlConn.setDoInput(true);
			httpUrlConn.setRequestMethod("GET");
			httpUrlConn.connect();
			// 将返回的输入流转换成字符串
			InputStream inputStream = httpUrlConn.getInputStream();
			InputStreamReader inputStreamReader = new InputStreamReader(
					inputStream, "utf-8");
			BufferedReader bufferedReader = new BufferedReader(
					inputStreamReader);

			String str = null;
			while ((str = bufferedReader.readLine()) != null) {
				buffer.append(str);
			}
			bufferedReader.close();
			inputStreamReader.close();
			// 释放资源
			inputStream.close();
			inputStream = null;
			httpUrlConn.disconnect();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return buffer.toString();
	}

	/**
	 * 调用Face++ API实现人脸检测
	 * 
	 * @param picUrl
	 *            待检测图片的访问地址
	 * @return List<Face> 人脸列表
	 */
	private static List<Face> faceDetect(String picUrl) {
		List<Face> faceList = new ArrayList<Face>();
		try {
			// 拼接Face++人脸检测的请求地址
			String queryUrl = "http://apicn.faceplusplus.com/v2/detection/detect?url=URL&api_secret=API_SECRET&api_key=API_KEY";
			// 对URL进行编码
			queryUrl = queryUrl.replace("URL",
					java.net.URLEncoder.encode(picUrl, "UTF-8"));
			queryUrl = queryUrl.replace("API_KEY",
					"0bcb1a3096ae11c9d69d3fa6dcc80f7a");
			queryUrl = queryUrl.replace("API_SECRET",
					"HAHLEIlYu-F4vCzotuvV3nVuMwJCT7-5");
			// 调用人脸检测接口
			String json = httpRequest(queryUrl);
			// 解析返回json中的Face列表
			JSONArray jsonArray = JSONObject.fromObject(json).getJSONArray(
					"face");
			// 遍历检测到的人脸
			for (int i = 0; i < jsonArray.size(); i++) {
				// face
				JSONObject faceObject = (JSONObject) jsonArray.get(i);
				// attribute
				JSONObject attrObject = faceObject.getJSONObject("attribute");
				// position
				JSONObject posObject = faceObject.getJSONObject("position");
				Face face = new Face();
				face.setFaceId(faceObject.getString("face_id"));
				face.setAgeValue(attrObject.getJSONObject("age")
						.getInt("value"));
				face.setAgeRange(attrObject.getJSONObject("age")
						.getInt("range"));
				face.setGenderValue(genderConvert(attrObject.getJSONObject(
						"gender").getString("value")));
				face.setGenderConfidence(attrObject.getJSONObject("gender")
						.getDouble("confidence"));
				face.setRaceValue(raceConvert(attrObject.getJSONObject("race")
						.getString("value")));
				face.setRaceConfidence(attrObject.getJSONObject("race")
						.getDouble("confidence"));
				face.setSmilingValue(attrObject.getJSONObject("smiling")
						.getDouble("value"));
				face.setCenterX(posObject.getJSONObject("center")
						.getDouble("x"));
				face.setCenterY(posObject.getJSONObject("center")
						.getDouble("y"));
				faceList.add(face);
			}
			// 将检测出的Face按从左至右的顺序排序
			Collections.sort(faceList);
		} catch (Exception e) {
			faceList = null;
			e.printStackTrace();
		}
		return faceList;
	}

	/**
	 * 性别转换（英文->中文）
	 * 
	 * @param gender
	 * @return
	 */
	private static String genderConvert(String gender) {
		String result = "男性";
		if ("Male".equals(gender))
			result = "Male(男性)";
		else if ("Female".equals(gender))
			result = "Female(女性)";

		return result;
	}

	/**
	 * 人种转换（英文->中文）
	 * 
	 * @param race
	 * @return
	 */
	private static String raceConvert(String race) {
		String result = "黄色";
		if ("Asian".equals(race))
			result = "Yellow（黄色）";
		else if ("White".equals(race))
			result = "White（白色）";
		else if ("Black".equals(race))
			result = "Black（黑色）";
		return result;
	}

	/**
	 * 根据人脸识别结果组装消息
	 * 
	 * @param faceList
	 *            人脸列表
	 * @return
	 */
	private static String makeMessage(List<Face> faceList) {
		DecimalFormat df = new DecimalFormat("0.00");
		StringBuffer buffer = new StringBuffer();
		buffer.append("***************************\nResult:\n");
		// 检测到1张脸
		if (1 == faceList.size()) {
//			buffer.append("共检测到").append(faceList.size()).append("张人脸")
//					.append("\n");
			buffer.append("total of ").append(faceList.size()).append(" face detected.")
			.append("\n");
			for (Face face : faceList) {
				buffer.append("\ue307");
				buffer.append(face.getRaceValue()).append(" race(人种), ");
				buffer.append(face.getGenderValue()).append(", ");
				buffer.append(face.getAgeValue()).append(" years old.\n");
				buffer.append("Race Confidence:").append(df.format(face.getRaceConfidence())).append("%\n");
				buffer.append("Smiling Value:").append(df.format(face.getSmilingValue()));
//				if (face.getAgeValue() <= 15)
//					buffer.append("\ue307描述：真羡慕你，小破孩，好好玩耍吧\ue40d\n");
//				else if (face.getAgeValue() > 15 && face.getAgeValue() <= 22 && face.getGenderValue() == "女性") {
//					buffer.append("\ue307描述：你就是含苞待放的花蕾，年轻真是任性啊\ue40d\n").append(
//							"你和明星唐嫣达到了惊人的"
//									+ String.format("%.2f",
//											90.0 + Math.random() * 10)
//									+ "相似度，太逆天了把！\n");
//				} else if (face.getAgeValue() > 15 && face.getAgeValue() <= 22
//						&& face.getGenderValue() == "男性") {
//					buffer.append("\ue307描述：你就是含苞待放的花蕾，年轻真是任性啊\ue40d\n").append(
//							"你和男神吴彦祖达到了惊人的"
//									+ String.format("%.2f",
//											90.0 + Math.random() * 10)
//									+ "相似度，简直膜拜！\n");
//				} else if (face.getAgeValue() > 22 && face.getAgeValue() <= 28
//						&& face.getGenderValue() == "女性") {
//					buffer.append("\ue307描述：正是青春年少时，青春如梦，岁月如歌，且行且珍惜。").append(
//							"你和女神陈妍希达到了惊人的"
//									+ String.format("%.2f",
//											80.0 + Math.random() * 10)
//									+ "棒极了！\n");
//				} else if (face.getAgeValue() > 22 && face.getAgeValue() <= 28
//						&& face.getGenderValue() == "男性") {
//					buffer.append("\ue307描述：正是青春年少时，青春如梦，岁月如歌，且行且珍惜。").append(
//							"你和明星谢霆锋达到了惊人的"
//									+ String.format("%.2f",
//											80.0 + Math.random() * 10)
//									+ "\n的相似度，好厉害！");
//				} else if (face.getGenderValue() == "男性")
//					buffer.append("\ue307描述：哎呀,人脸看着有些许沧桑，不过也有一种成熟的魅力。\n").append(
//							"你和男神刘德华达到了"
//									+ String.format("%.2f",
//											70.0 + Math.random() * 10)
//									+ "的相似度，好厉害！\n");
//				else {
//					buffer.append("\ue307描述：哎呀,人脸看着虽然已不在年轻，但你的气质还是自然散发。\n").append(
//							"和国际章的脸相达到了"
//									+ String.format("%.2f",
//											70.0 + Math.random() * 10)
//									+ "的相似度，还不错哦！\n");
//				}

				buffer.append("\n***************************\n");
			}
		}
		// 检测到2-10张脸
		else if (faceList.size() > 1 && faceList.size() <= 10) {
//			buffer.append("共检测到 ").append(faceList.size())
//					.append(" 人，按脸部中心从左至右为：").append("\n");
			buffer.append("total of ").append(faceList.size())
			.append(" faces detected.\nAccording to the center of the face from left to right:").append("\n");
			for (Face face : faceList) {
				buffer.append("\ue307");
				buffer.append(face.getRaceValue()).append("race(人种), ");
				buffer.append(face.getGenderValue()).append(", ");
				buffer.append(face.getAgeValue()).append(" years old.\n");
				buffer.append("Race Confidence:").append(df.format(face.getRaceConfidence())).append("%\n");
				buffer.append("Smiling Value:").append(df.format(face.getSmilingValue()));
//				if (face.getAgeValue() <= 15)
//					buffer.append("\ue307描述：真羡慕你，小破孩，好好玩耍吧\ue40d\n");
//				else if (face.getAgeValue() > 15 && face.getAgeValue() <= 22 && face.getGenderValue() == "女性") {
//					buffer.append("\ue307描述：你就是含苞待放的花蕾，年轻真是任性啊\ue40d\n").append(
//							"你和明星唐嫣达到了惊人的"
//									+ String.format("%.2f",
//											90.0 + Math.random() * 10)
//									+ "相似度，太逆天了把！\n");
//				} else if (face.getAgeValue() > 15 && face.getAgeValue() <= 22
//						&& face.getGenderValue() == "男性") {
//					buffer.append("\ue307描述：你就是含苞待放的花蕾，年轻真是任性啊\ue40d\n").append(
//							"你和男神吴彦祖达到了惊人的"
//									+ String.format("%.2f",
//											90.0 + Math.random() * 10)
//									+ "相似度，简直膜拜！\n");
//				} else if (face.getAgeValue() > 22 && face.getAgeValue() <= 28
//						&& face.getGenderValue() == "女性") {
//					buffer.append("\ue307描述：正是青春年少时，青春如梦，岁月如歌，且行且珍惜。").append(
//							"你和女神陈妍希达到了惊人的"
//									+ String.format("%.2f",
//											80.0 + Math.random() * 10)
//									+ "棒极了！\n");
//				} else if (face.getAgeValue() > 22 && face.getAgeValue() <= 28
//						&& face.getGenderValue() == "男性") {
//					buffer.append("\ue307描述：正是青春年少时，青春如梦，岁月如歌，且行且珍惜。").append(
//							"你和明星谢霆锋达到了惊人的"
//									+ String.format("%.2f",
//											80.0 + Math.random() * 10)
//									+ "\n的相似度，好厉害！");
//				} else if (face.getGenderValue() == "男性")
//					buffer.append("\ue307描述：哎呀,人脸看着有些许沧桑，不过也有一种成熟的魅力。\n").append(
//							"你和男神刘德华达到了"
//									+ String.format("%.2f",
//											70.0 + Math.random() * 10)
//									+ "的相似度，好厉害！\n");
//				else {
//					buffer.append("\ue307描述：哎呀,人脸看着虽然已不在年轻，但你的气质还是自然散发。\n").append(
//							"和国际章的脸相达到了"
//									+ String.format("%.2f",
//											70.0 + Math.random() * 10)
//									+ "的相似度，还不错哦！\n");
//				}

				buffer.append("\n**************************\n");
			}
		}
		// 检测到10张脸以上
		else if (faceList.size() > 10) {
			buffer.append("共检测到 ").append(faceList.size()).append(" 张人脸")
					.append("\n");
			// 统计各人种、性别的人数
			int asiaMale = 0;
			int asiaFemale = 0;
			int whiteMale = 0;
			int whiteFemale = 0;
			int blackMale = 0;
			int blackFemale = 0;
			for (Face face : faceList) {
				if ("黄色".equals(face.getRaceValue()))
					if ("男性".equals(face.getGenderValue()))
						asiaMale++;
					else
						asiaFemale++;
				else if ("白色".equals(face.getRaceValue()))
					if ("男性".equals(face.getGenderValue()))
						whiteMale++;
					else
						whiteFemale++;
				else if ("黑色".equals(face.getRaceValue()))
					if ("男性".equals(face.getGenderValue()))
						blackMale++;
					else
						blackFemale++;
			}
			if (0 != asiaMale || 0 != asiaFemale)
				buffer.append("黄色人种：").append(asiaMale).append("男")
						.append(asiaFemale).append("女").append("\n");
			if (0 != whiteMale || 0 != whiteFemale)
				buffer.append("白色人种：").append(whiteMale).append("男")
						.append(whiteFemale).append("女").append("\n");
			if (0 != blackMale || 0 != blackFemale)
				buffer.append("黑色人种：").append(blackMale).append("男")
						.append(blackFemale).append("女").append("\n");

		}

		// 移除末尾空格
		buffer = new StringBuffer(buffer.substring(0, buffer.lastIndexOf("\n")));
		return buffer.toString();
	}

	/**
	 * 提供给外部调用的人脸检测方法
	 * 
	 * @param picUrl
	 *            待检测图片的访问地址
	 * @return String
	 */
	public static String detect(String picUrl) {
		// 默认回复信息
		String result = "我的算法还没有髙到处理未知的图片哦，上传清晰的人脸试试把\ue059";
		List<Face> faceList = faceDetect(picUrl);
		if (!faceList.isEmpty()) {
			result = makeMessage(faceList);
		}
		
		return result;
	
	}

	public static void main(String[] args) {
		String picUrl = "http://a4.att.hudong.com/32/13/300000895028127824138388216_950.jpg";
		System.out.println(detect(picUrl));
	}
}
