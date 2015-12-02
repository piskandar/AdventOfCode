package com.acs.adventofcode.puzzle2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.acs.adventofcode.puzzle2.model.BoxDimensions;

public class Puzzle2 {

	private final class DimensionIndexes {
		public static final int LENGTH = 1;
		public static final int WIDTH = 2;
		public static final int HEIGHT = 3;
	}
	public static void main(String[] args) {
		FileReader fr = null;
		BufferedReader br = null;
		
		try {
			fr = new FileReader(new File("files/puzzle2/puzzle2ainput.txt"));
			br = new BufferedReader(fr);
			
			String line = br.readLine();
			List<BoxDimensions> dimensions = new ArrayList<>();
			while(line != null){
				BoxDimensions boxDimensions = dimensionsFromLine(line);
				dimensions.add(boxDimensions);
				System.out.println(boxDimensions);
				line = br.readLine();
			}
			
			calculateTotalSquareFootage(dimensions);
			calculateTotalRibbonLength(dimensions);
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(br != null){
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
			if(fr != null){
				try {
					fr.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
	}

	private static void calculateTotalRibbonLength(List<BoxDimensions> dimensions) {
		
		int totalFeet = 0;
		for (BoxDimensions boxDimensions : dimensions) {
			totalFeet += boxDimensions.smallestParameter();
			totalFeet += boxDimensions.calculateVolume();
		}
		System.out.println("Puzzle 2b answer: " + totalFeet);
	}

	private static void calculateTotalSquareFootage(List<BoxDimensions> dimensions) {
	
		int totalSquareFeet = 0;
		for (BoxDimensions boxDimensions : dimensions) {
			totalSquareFeet += boxDimensions.calculateSurfaceArea();
			totalSquareFeet += boxDimensions.smallestSideArea();
		}
		System.out.println("Puzzle 2a answer: " + totalSquareFeet);
	}

	private static BoxDimensions dimensionsFromLine(String line) {

		Pattern pattern = Pattern.compile("([0-9]+)x([0-9]+)x([0-9]+)");
		Matcher matcher = pattern.matcher(line);
		
		if(matcher.matches()){
			int length = -1;
			int width = -1;
			int height = -1;
			for(int i = 1; i <= matcher.groupCount(); i++){
				String group = matcher.group(i);
				switch (i) {
				case DimensionIndexes.LENGTH:
					length = Integer.parseInt(group);
					break;
				case DimensionIndexes.WIDTH:
					width = Integer.parseInt(group);
					break;
				case DimensionIndexes.HEIGHT:
					height = Integer.parseInt(group);
					break;
				default:
					break;
				}
			}
			return new BoxDimensions(length, width, height);
		}
		return null;
	}

}
