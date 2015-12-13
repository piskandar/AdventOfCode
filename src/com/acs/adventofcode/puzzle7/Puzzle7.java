package com.acs.adventofcode.puzzle7;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Puzzle7 {

	public enum Type {
		NOT,
		AND,
		OR,
		LSHIFT,
		RSHIFT,
		AND_NUM_FIRST
	}
	
	private Map<String, Integer> inputMap;
	private Map<String, String> equationMap;;
	
	private Comparator<String> treeMapComparator = new Comparator<String>() {

		@Override
		public int compare(String o1, String o2) {
			if(o1.length() == o2.length()){
				return o1.compareTo(o2);
			}
			
			if(o1.length() < o2.length()){
				return -1;
			}
			
			if(o1.length() > o2.length()){
				return 1;
			}
			return 0;
		}
	};
	public Puzzle7(){
		inputMap = new HashMap<>();
		equationMap = new TreeMap<>(treeMapComparator);
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
//				puzzle.getInput(line);
				puzzle.scanInput(line);
				line = br.readLine();
			}
			
			puzzle.equate();
			
			
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
	public void equate(){

		Map<String,Integer> evaluatedValues = new TreeMap<>(treeMapComparator);
		for (Entry<String,String> entry : equationMap.entrySet()) {
			
			if(!entry.getKey().equals("a")){
				System.out.println(entry.getKey() + " = " + entry.getValue()) ;
				if(entry.getKey().equals("b")){
					evaluatedValues.put(entry.getKey(), 956);
					
				}else if(entry.getKey().equals("c")){
					evaluatedValues.put(entry.getKey(), Integer.parseInt(entry.getValue()));
				}else{
					Object[] processed = process(entry.getValue() + " -> " + entry.getKey());
					if(processed != null){
						Type type = null;
						for (int i = 0; i < processed.length; i++) {
							if(processed[i] instanceof Type){
								type = (Type)processed[i];
								break;
							}
						}
						
						switch(type){
							case RSHIFT:
								String output = (String)processed[0];
								
								String input = (String)processed[1];
								Integer value = (Integer)processed[2];
								
								Integer storedValue = evaluatedValues.get(input);
								
								evaluatedValues.put(output, storedValue.intValue() >> value.intValue()); 
								break;
							case LSHIFT:
								output = (String)processed[0];
								
								input = (String)processed[1];
								value = (Integer)processed[2];
								
								storedValue = evaluatedValues.get(input);
								
								evaluatedValues.put(output, storedValue.intValue() << value.intValue()); 
							
								break;
							case OR:
								output = (String)processed[0];
								
								input = (String)processed[1];
								String strvalue = (String)processed[2];
								
								Integer storedValue1 = evaluatedValues.get(input);
								Integer storedValue2 = evaluatedValues.get(strvalue);
								
								evaluatedValues.put(output, storedValue1.intValue() | storedValue2.intValue()); 
							
								break;
							case AND:
								output = (String)processed[0];
								
								input = (String)processed[1];
								strvalue = (String)processed[2];
								
								storedValue1 = evaluatedValues.get(input);
								storedValue2 = evaluatedValues.get(strvalue);
								
								evaluatedValues.put(output, storedValue1.intValue() & storedValue2.intValue()); 
							
								break;
							case AND_NUM_FIRST:
								output = (String)processed[0];	
								
								Integer intinput = (Integer)processed[1];
								strvalue = (String)processed[2];
								
								storedValue2 = evaluatedValues.get(strvalue);
								
								evaluatedValues.put(output, intinput.intValue() & storedValue2.intValue()); 
								break;
								
							case NOT:
								output = (String)processed[0];
								input = (String)processed[1];
								
								storedValue1 = evaluatedValues.get(input);
								
								evaluatedValues.put(output, flipBits(storedValue1));
								break;
						}
					}else{
						throw new RuntimeException("processed incorrectly " + entry.getValue() + " -> " + entry.getKey());
					}
					System.out.println(processed);
				}
			}
		}
		
		for (Entry<String,Integer> entry : evaluatedValues.entrySet()) {
			System.out.println(entry.getKey() + " = " + entry.getValue()) ;
			
			
			
			
			
		}
	}
	
	public void scanInput(String line){
		String input = line.substring(line.indexOf('>') + 2);
		String value = line.substring(0, line.indexOf('-'));
//		System.out.println(input);
		
		equationMap.put(input, value.trim());
	}
	
	private Map<String,Integer> getInputMap() {
		return inputMap;
	}
	
	
	
	public Object[] process(String input){
		
		Pattern pattern = Pattern.compile("([0-9]+) -> ([a-z]+)");
		Matcher matcher = pattern.matcher(input);
		
		Pattern andPattern1 = Pattern.compile("([a-z]+) AND ([a-z]+) -> ([a-z]+)");
		Matcher andMatcher1 = andPattern1.matcher(input);
		
		Pattern andPattern2 = Pattern.compile("([0-9]+) AND ([a-z]+) -> ([a-z]+)");
		Matcher andMatcher2 = andPattern2.matcher(input);
		
		Pattern orPattern = Pattern.compile("([a-z]+) OR ([a-z]+) -> ([a-z]+)");
		Matcher orMatcher = orPattern.matcher(input);
		
		Pattern lshiftPattern = Pattern.compile("([a-z]+) LSHIFT ([0-9]+) -> ([a-z]+)");
		Matcher lshiftMatcher = lshiftPattern.matcher(input);
		
		Pattern rshiftPattern = Pattern.compile("([a-z]+) RSHIFT ([0-9]+) -> ([a-z]+)");
		Matcher rshiftMatcher = rshiftPattern.matcher(input);
		
		Pattern notPattern = Pattern.compile("NOT ([a-z]+) -> ([a-z]+)");
		Matcher notMatcher = notPattern.matcher(input);
		
		if(rshiftMatcher.matches()){
			String input1 = rshiftMatcher.group(1);
			String input2 = rshiftMatcher.group(2);
			String output = rshiftMatcher.group(3);
			
			Integer inputValue2 = Integer.parseInt(input2);
			
			return new Object[] {output, input1, inputValue2, Type.RSHIFT };
			
			
		} else if(lshiftMatcher.matches()){
			String input1 = lshiftMatcher.group(1);
			String input2 = lshiftMatcher.group(2);
			String output = lshiftMatcher.group(3);
			
			Integer inputValue2 = Integer.parseInt(input2);
			
			return new Object[] {output, input1, inputValue2, Type.LSHIFT };
			
			
		}else if(andMatcher1.matches()){
			
			String input1 = andMatcher1.group(1);
			String input2 = andMatcher1.group(2);
			String output = andMatcher1.group(3);
			
			
			return new Object[] {output, input1, input2, Type.AND };
			
		} else if(andMatcher2.matches()){
			
			String input1 = andMatcher2.group(1);
			String input2 = andMatcher2.group(2);
			String output = andMatcher2.group(3);
			
			
			return new Object[] {output, Integer.parseInt(input1), input2, Type.AND_NUM_FIRST };
			
		} 
		else if(orMatcher.matches()){
			
			String input1 = orMatcher.group(1);
			String input2 = orMatcher.group(2);
			String output = orMatcher.group(3);
			
			
			return new Object[] {output, input1, input2, Type.OR };
			
		}else if(notMatcher.matches()){
			
			String input1 = notMatcher.group(1);
			String output = notMatcher.group(2);
			
			return new Object[] {output, input1, Type.NOT };
			
		}
		return null;
		
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
