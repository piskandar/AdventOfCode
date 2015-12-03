package com.acs.adventofcode.puzzle3;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class Puzzle3 {

	private final static String INPUT_FILE_PATH = "files/puzzle3/puzzle3input.txt";
	
	private final static int NORTH = '^';
	private final static int EAST = '>';
	private final static int SOUTH = 'v';
	private final static int WEST = '<';
	
	public static void main(String[] args) {

		CoordinateGrid coordinateGrid =new CoordinateGrid(200,200);
		
		FileInputStream fis = null;
		InputStreamReader isr = null;
		try {

			fis = new FileInputStream(new File(INPUT_FILE_PATH));
			isr = new InputStreamReader(fis);
			
			int i;
			while((i = isr.read()) != -1)
			{
				switch (i) {
				case NORTH:
					System.out.println("NORTH");
					coordinateGrid.movePoint(Direction.NORTH);
					break;
				case SOUTH:
					System.out.println("SOUTH");
					coordinateGrid.movePoint(Direction.SOUTH);
					break;
				case WEST:
					System.out.println("WEST");
					coordinateGrid.movePoint(Direction.WEST);
					break;
				case EAST:
					System.out.println("EAST");
					coordinateGrid.movePoint(Direction.EAST);
					break;
				default:
					System.err.println("INVALID DIRECTION");
					break;
				}
				
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
