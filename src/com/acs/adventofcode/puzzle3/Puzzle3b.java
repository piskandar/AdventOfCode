package com.acs.adventofcode.puzzle3;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class Puzzle3b {

//	private final static String INPUT_FILE_PATH = "files/puzzle3/puzzle3btest.txt";
	private final static String INPUT_FILE_PATH = "files/puzzle3/puzzle3input.txt";
	
	private final static int NORTH = '^';
	private final static int EAST = '>';
	private final static int SOUTH = 'v';
	private final static int WEST = '<';
	
//	2631
	public static void main(String[] args) {

		CoordinateGridWithRobot coordinateGrid =new CoordinateGridWithRobot(400,400);
		
		FileInputStream fis = null;
		InputStreamReader isr = null;
		try {

			fis = new FileInputStream(new File(INPUT_FILE_PATH));
			isr = new InputStreamReader(fis);
			
			int i;
			int position = 0;
			while((i = isr.read()) != -1)
			{
				boolean santaMove = (position % 2) == 0;
				switch (i) {
				case NORTH:
					coordinateGrid.movePoint(Direction.NORTH,santaMove);
					break;
				case SOUTH:
					coordinateGrid.movePoint(Direction.SOUTH,santaMove);
					break;
				case WEST:
					coordinateGrid.movePoint(Direction.WEST,santaMove);
					break;
				case EAST:
					
					coordinateGrid.movePoint(Direction.EAST,santaMove);
					break;
				default:
					System.err.println("INVALID DIRECTION");
					break;
				}
				position++;
			}
			coordinateGrid.countSpacesVisited();
			
		} catch (Exception e) {
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
	}

}
