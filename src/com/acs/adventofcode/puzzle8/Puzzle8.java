package com.acs.adventofcode.puzzle8;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import com.acs.adventofcode.puzzle3.Direction;

public class Puzzle8 {

	private final static String INPUT_FILE_PATH = "files/puzzle8/puzzle8input.txt";
	
	
	public static void main(String[] args) {
		FileInputStream fis = null;
		InputStreamReader isr = null;
		try {

			fis = new FileInputStream(new File(INPUT_FILE_PATH));
			isr = new InputStreamReader(fis);
			
			int i;
			int lineLength = 0;
			int charLength = 0;
			int totalStringLength= 0;
			char[] buffer = new char[50];
			int maxLength = Integer.MIN_VALUE;
			while((i = isr.read()) != -1)
			{
				
				if(i == 10 || i == 13){
					charLength += lineLength;
					if(lineLength > maxLength){
						maxLength = lineLength;
					}
					int strLength = getStringLength(buffer);
					System.out.println(strLength);
					buffer = new char[50];
					totalStringLength += strLength;
					lineLength = 0;
					continue;
				}else{
					buffer[lineLength] = (char)i;
					
					lineLength++;
				}
				
				
			}
			System.out.println("Max:" + maxLength);
			System.out.println("Char length: " + charLength);
			System.out.println("total String Length: " + totalStringLength);
		System.out.println(charLength - totalStringLength);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(isr != null){
				try {
					isr.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
			if(fis != null){
				try {
					fis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}


	private static int getStringLength(char[] buffer) {
		int strLength = 0;
		
		for (int j = 0; j < buffer.length; j++) {
			char c = buffer[j];
			if(c != 0){
				if(c == '\\'){
					if((j + 1) < buffer.length){
						char c1 = buffer[j + 1];
						if(c1 == 'x'){
							if((j + 1)+2 < buffer.length){
								strLength++;
								j+= 3;
							}
						}else if(c == '\\'){
							j++;
							strLength++;
						}else if(c == '"'){
							j++;
							strLength++;
						}
						
						
					}
				}else if(c != '"') {
					strLength++;
				}
				
			}
		}
		return strLength;
	}
}
