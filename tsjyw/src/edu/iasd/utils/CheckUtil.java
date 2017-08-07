package edu.iasd.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;


/*
 * ����΢�Ž����Ƿ���ȷ��ֻ�ڵ�һ�ν���ʱ������֤
 * ͨ��get����
 * ΢�Ž���ָ��http://mp.weixin.qq.com/wiki/8/f9a0b8382e0b77d87b3bcc1ce6fbc104.html
 */
public class CheckUtil {
	private final static String token = "imooc";
	public static boolean check(String signature,String timestamp,String nonce)
	{
		String arr[] = new String[]{token,timestamp,nonce}; 
		Arrays.sort(arr);
		StringBuffer content = new StringBuffer();
		for(int i=0;i<arr.length;i++)
		{
			content.append(arr[i]);
		}
		
		String temp = SHA1(content.toString());
		return temp.equals(signature);
		
		
	}
	public static String SHA1(String decript) {
		try {
			MessageDigest digest = java.security.MessageDigest
					.getInstance("SHA-1");
			digest.update(decript.getBytes());
			byte messageDigest[] = digest.digest();
			// Create Hex String
			StringBuffer hexString = new StringBuffer();
			// �ֽ�����ת��Ϊ ʮ������ ��
			for (int i = 0; i < messageDigest.length; i++) {
				String shaHex = Integer.toHexString(messageDigest[i] & 0xFF);
				if (shaHex.length() < 2) {
					hexString.append(0);
				}
				hexString.append(shaHex);
			}
			return hexString.toString();

		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return "";
	}
}
