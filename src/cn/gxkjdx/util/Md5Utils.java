package cn.gxkjdx.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Base64.Encoder;

public class Md5Utils {
	
	private static final String salt="Pwetio!WEW345"; 
	
	public static String md5(String source) {
		
		
		try {
			MessageDigest digest = MessageDigest.getInstance("MD5");
			
			source=source+salt;
			byte[] result = digest.digest(source.getBytes());
			
			Encoder encoder = Base64.getEncoder();
			return encoder.encodeToString(result);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static void main(String[] args) {
		 System.out.println(md5("123456"));
	}

}
