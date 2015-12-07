package com.acs.adventofcode.puzzle6;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Puzzle6 {

	private static final int SIZE = 1000;
	private int[][] lightGrid;
	
	public enum Command {
		TURN_ON,
		TURN_OFF,
		TOGGLE
	}
	
	public class Range {
		public int start;
		public int end;
	}
	
	public Puzzle6(int size){
		lightGrid = new int[size][size];
	}
	
	public static void main(String[] args) {
		FileReader fr = null;
		BufferedReader br = null;
		Puzzle6 puzzle6 = new Puzzle6(1000);
		try {
			fr = new FileReader(new File("files/puzzle6/puzzle6input.txt"));
			br = new BufferedReader(fr);
			
			String line = br.readLine();
			
			while(line != null){
				puzzle6.processCommand(line);
				line = br.readLine();
			}
			
			System.out.println(puzzle6.countLights());
			
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
	
	
	public int processCommand(String line) {
		Command command = getCommandFromLine(line);
		Range startRange = getStartRange(line);
		Range endRange = getEndRange(line);
		
		switch (command) {
		case TURN_ON:
			turnOnLights(startRange, endRange);
			break;
		case TURN_OFF:
			turnOffLights(startRange, endRange);
			break;
		case TOGGLE:
			toggleLights(startRange, endRange);
			break;
		default:
			break;
		}
		
		int lightsOn = countLights();
		
		return lightsOn;
		
	}


	private void toggleLights(Range startRange, Range endRange) {
		int startX = startRange.start;
		int endX = endRange.start;
		
		int startY = startRange.end;
		int endY = endRange.end;
		
		for (int i = startX; i <= endX ; i++) {
			
			for (int j = startY; j <= endY; j++) {
				if(lightGrid[i][j] == 0)
				{
					lightGrid[i][j] = 1;
				}else{
					lightGrid[i][j] = 0;
				}
		
			}
		}
		
		
	}

	private void turnOffLights(Range startRange, Range endRange) {

		int startX = startRange.start;
		int endX = endRange.start;
		
		int startY = startRange.end;
		int endY = endRange.end;
		
		for (int i = startX; i <= endX ; i++) {
			
			for (int j = startY; j <= endY; j++) {
				lightGrid[i][j] = 0;
		
			}
		}
	}

	public int countLights() {
		int count = 0;
		for (int i = 0; i < lightGrid.length; i++) {
			for (int j = 0; j < lightGrid[i].length; j++) {
				if(lightGrid[i][j] == 1){
					count++;
				}
			}
		}
		return count;
	}

	public void turnOnLights(Range startRange, Range endRange) {
		int startX = startRange.start;
		int endX = endRange.start;
		
		int startY = startRange.end;
		int endY = endRange.end;
		
		for (int i = startX; i <= endX ; i++) {
			
			for (int j = startY; j <= endY; j++) {
				lightGrid[i][j] = 1;
		
			}
		}
		
		
	}

	public Range getEndRange(String line) {
		Pattern pattern = Pattern.compile("([0-9]+,[0-9]+)");
		Matcher matcher = pattern.matcher(line);
		if(matcher.find()){
			matcher.find();
			Range range = new Range();
			String match = matcher.group(1);
			
			String start = match.substring(0, match.indexOf(','));
			String end = match.substring(match.indexOf(',') + 1);
			
			range.start = Integer.parseInt(start);
			range.end = Integer.parseInt(end);
			
			return range;
		}
		
		return null;
	}


	public Range getStartRange(String line) {

		Pattern pattern = Pattern.compile("([0-9]+,[0-9]+)");
		Matcher matcher = pattern.matcher(line);
		if(matcher.find()){
			Range range = new Range();
			String match = matcher.group(1);
			
			String start = match.substring(0, match.indexOf(','));
			String end = match.substring(match.indexOf(',') + 1);
			
			range.start = Integer.parseInt(start);
			range.end = Integer.parseInt(end);
			
			return range;
		}
		
		return null;
	}


	public Command getCommandFromLine(String line) {
		if(line.startsWith("turn on")){
			return Command.TURN_ON;
		}
		
		if(line.startsWith("turn off")){
			return Command.TURN_OFF;
		}
		
		if(line.startsWith("toggle")){
			return Command.TOGGLE;
		}
		
		return null;
	}

}
