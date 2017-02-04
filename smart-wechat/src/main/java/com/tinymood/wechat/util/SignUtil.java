package com.tinymood.wechat.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

/**
 * 请求检验工具类
 *
 * @author nothankyou
 * @date 2017-1-6
 */
public class SignUtil {
    // 与接口配置信息中的token要一致
	private static String token = "smart-wechat";

	/**
	 * 验证签名
	 * 
	 * @param signature
	 * @param timestamp
	 * @param nonce
	 * @return
	 */
	public static boolean checkSignature(String signature, String timestamp,
			String nonce) {
		String[] arr = new String[] { token, timestamp, nonce };
		// 将token,timestamp,nonce字典排序
		Arrays.sort(arr);
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < arr.length; ++i) {
			sb.append(arr[i]);
		}

		MessageDigest md = null;
		String encryptionStr = null;

		try {
			md = MessageDigest.getInstance("SHA-1");
			// 将三个字符串拼接成一个字符串进行sha-1加密
			byte[] digest = md.digest(sb.toString().getBytes());
			encryptionStr = byteArrayToStr(digest);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}

		sb = null;
		// 将sha-1加密后的字符串与signature对比，表示请求来源于微信
		return encryptionStr != null ? encryptionStr.equals(signature.toUpperCase()) : false;
	}

	/**
	 * 将字节数组转化为十六进制字符串
	 * 
	 * @param byteArray
	 * @return
	 */
	private static String byteArrayToStr(byte[] byteArray) {
		String strDigest = "";
		for (int i = 0; i < byteArray.length; ++i) {
			strDigest += byteToHexStr(byteArray[i]);
		}
		return strDigest;
	}

	/**
     * 将字节转化为十六进制字符串
     *
	 * @param b
	 * @return
	 */
	private static String byteToHexStr(byte b) {
		char[] Digit = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A',
				'B', 'C', 'D', 'E', 'F' };
		char[] tempArr = new char[2];
		tempArr[0] = Digit[(b >>> 4) & 0X0F];
		tempArr[1] = Digit[b & 0X0F];

		String s = new String(tempArr);

		return s;
	}
}
