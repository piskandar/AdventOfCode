package com.acs.adventofcode.puzzle1;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Puzzle1b {

	private final static String INPUT_FILE_PATH = "/Users/devfloater56/Documents/mars_workspace/AdventOfCode/files/puzzle1/puzzle1input.txt";
	
	private final static int OPEN_PARENTHESES = '(';
	private final static int CLOSE_PARENTHESES = ')';
	
	public static void main(String[] args)
	{
		
		int floor = 0;
		FileInputStream fis = null;
		InputStreamReader isr = null;
		try {

			fis = new FileInputStream(new File(INPUT_FILE_PATH));
			isr = new InputStreamReader(fis);
			
			int i;
			int position = 1;
			while((i = isr.read()) != -1)
			{
				if(i == OPEN_PARENTHESES){
					floor++;
				}else if (i == CLOSE_PARENTHESES){
					floor--;
				}
				System.out.println(position);
				
				if(floor == -1){
					System.out.println("floor: " + floor + " position: " + position);
					break;
				}
				position++;
			}
			
			System.out.println("floor: " + floor);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			if(isr != null){
				try {
					isr.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			if(fis != null){
				try {
					fis.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
//		
		
	}

}
