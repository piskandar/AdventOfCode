package com.acs.adventofcode.puzzle7;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.acs.adventofcode.puzzle6.Puzzle6;

public class Puzzle7 {

	private Map<String, Integer> inputMap;
	
	public Puzzle7(){
		inputMap = new HashMap<>();
	}
	
	public static void main(String[] args) {
		FileReader fr = null;
		BufferedReader br = null;
		Puzzle7 puzzle = new Puzzle7();
		try {
			fr = new FileReader(new File("files/puzzle7/puzzle7input.txt"));
			br = new BufferedReader(fr);
			
			String line = br.readLine();
			
			while(line != null){
				puzzle.getInput(line);
				line = br.readLine();
			}
			
			System.out.println(puzzle.getInputMap());
			
			
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
	
	private Map<String,Integer> getInputMap() {
		return inputMap;
	}

	public Map<String, Integer> getInput(String input) {

		Pattern pattern = Pattern.compile("([0-9]+) -> ([a-z]+)");
		Matcher matcher = pattern.matcher(input);
		
		Pattern andPattern = Pattern.compile("([a-z]+) AND ([a-z]) -> ([a-z]+)");
		Matcher andMatcher = andPattern.matcher(input);
		
		Pattern orPattern = Pattern.compile("([a-z]+) OR ([a-z]) -> ([a-z]+)");
		Matcher orMatcher = orPattern.matcher(input);
		
		Pattern lshiftPattern = Pattern.compile("([a-z]+) LSHIFT ([0-9]+) -> ([a-z]+)");
		Matcher lshiftMatcher = lshiftPattern.matcher(input);
		
		Pattern rshiftPattern = Pattern.compile("([a-z]+) RSHIFT ([0-9]+) -> ([a-z]+)");
		Matcher rshiftMatcher = rshiftPattern.matcher(input);
		
		Pattern notPattern = Pattern.compile("NOT ([a-z]+) -> ([a-z]+)");
		Matcher notMatcher = notPattern.matcher(input);
		
		if(matcher.matches()){
			
			String inputValue = matcher.group(1);
			String inputWire = matcher.group(2);
			
			System.out.println("value: " + inputValue + " to " + inputWire);
			
			inputMap.put(inputWire, Integer.parseInt(inputValue));
			
		}else if(andMatcher.matches()){
			
			String input1 = andMatcher.group(1);
			String input2 = andMatcher.group(2);
			String output = andMatcher.group(3);
			
			System.out.println("i1: " + input1 + " i2: " + input2 + " output: " + output);
			
			Integer inputValue1 = inputMap.get(input1);
			Integer inputValue2 = inputMap.get(input2);
			if(inputValue1 != null && inputValue2 != null){
			Integer value = inputValue1 & inputValue2;
			inputMap.put(output, value);
			}
			
		} else if(orMatcher.matches()){
			
			String input1 = orMatcher.group(1);
			String input2 = orMatcher.group(2);
			String output = orMatcher.group(3);
			
			System.out.println("i1: " + input1 + " i2: " + input2 + " output: " + output);
			
			Integer inputValue1 = inputMap.get(input1);
			Integer inputValue2 = inputMap.get(input2);
			if(inputValue1 != null && inputValue2 != null){
			Integer value = inputValue1 | inputValue2;
			inputMap.put(output, value);
			}
			
		} else if(lshiftMatcher.matches()){
			
			String input1 = lshiftMatcher.group(1);
			String input2 = lshiftMatcher.group(2);
			String output = lshiftMatcher.group(3);
			
			System.out.println("i1: " + input1 + " i2: " + input2 + " output: " + output);
			
			Integer inputValue1 = inputMap.get(input1);
			Integer inputValue2 = Integer.parseInt(input2);
			
			if(inputValue1 != null && inputValue2 != null){
				Integer value = inputValue1 << inputValue2;
				inputMap.put(output, value);
			}
			
		}
		else if(rshiftMatcher.matches()){
			
			String input1 = rshiftMatcher.group(1);
			String input2 = rshiftMatcher.group(2);
			String output = rshiftMatcher.group(3);
			
			System.out.println("i1: " + input1 + " i2: " + input2 + " output: " + output);
			
			Integer inputValue1 = inputMap.get(input1);
			Integer inputValue2 = Integer.parseInt(input2);
			if(inputValue1 != null && inputValue2 != null){
				Integer value = inputValue1 >> inputValue2;
				inputMap.put(output, value);
			}
			
		}else if(notMatcher.matches()){
			
			String input1 = notMatcher.group(1);
			String output = notMatcher.group(2);
			
			Integer inputValue1 = inputMap.get(input1);
			if(inputValue1 != null){
				Integer value = flipBits(inputValue1);
			
				inputMap.put(output, value);
			}
			
		}
		
		
		return inputMap;
	}

	private Integer flipBits(Integer input) {
		System.out.println(input);
		String bitString = Integer.toBinaryString(input);
		StringBuilder sb = new StringBuilder(bitString);
		while(sb.length() < 16){
			sb.insert(0, '0');
		}
		
		StringBuilder notString = new StringBuilder();
		String sbString = sb.toString();
		for (int i = 0; i < sbString.length(); i++) {
			char c = sbString.charAt(i);
			if(c == '1'){
				notString.append('0');
			}else if(c == '0'){
				notString.append('1');
			}
		}
		
		Integer i = Integer.valueOf(notString.toString(), 2);
		
		return i;
	}

}
