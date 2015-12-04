package com.acs.adventofcode.puzzle4;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Puzzle4 {

	public static void main(String[] args) throws NoSuchAlgorithmException {
		String input = "ckczppom";
		System.out.println(getNum(input, "00000"));
		
		System.out.println(getNum(input, "000000"));
	}
	
	final protected static char[] hexArray = "0123456789ABCDEF".toCharArray();

	public static String bytesToHex(byte[] bytes) {
		char[] hexChars = new char[bytes.length * 2];
		for (int j = 0; j < bytes.length; j++) {
			int v = bytes[j] & 0xFF;
			hexChars[j * 2] = hexArray[v >>> 4];
			hexChars[j * 2 + 1] = hexArray[v & 0x0F];
		}
		return new String(hexChars);
	}
	
	public static int getNum(String input, String prefix) throws NoSuchAlgorithmException{
		MessageDigest digest = MessageDigest.getInstance("MD5");
		
		int num = 1;
		String test = "";
		String testString = "";
		do{
			testString = String.format("%s%d", input,num);
			digest.update(testString.getBytes());
			byte[] bytes = digest.digest();
			test = bytesToHex(bytes);
			num++;
			
		}while(!test.startsWith(prefix));
		
		return num - 1;
	}

}
