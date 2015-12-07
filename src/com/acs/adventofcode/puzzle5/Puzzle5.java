package com.acs.adventofcode.puzzle5;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Puzzle5 {

	private static final char[] VOWELS = { 'a', 'e', 'i', 'o', 'u' };

	
	public static void main(String[] args) {
		Puzzle5 puzzle5 = new Puzzle5();
		int[] niceCounts = puzzle5.numberOfNiceNames();
		System.out.println("nice 1: " + niceCounts[0]);
		System.out.println("nice 2: " + niceCounts[1]);
	}
	
	public int[] numberOfNiceNames(){
		FileReader fr = null;
		BufferedReader br = null;
		int nice1Count = 0;
		int nice2Count = 0;
		try {
			fr = new FileReader(new File("files/puzzle5/puzzle5input.txt"));
			br = new BufferedReader(fr);
			
			String line = br.readLine();
			
			while(line != null){
				
				if(isNice(line.trim())){
					nice1Count++;
				}
				
				if(isNice2(line.trim())){
					nice2Count++;
				}
				line = br.readLine();
			}
			
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
		
		return new int[] {nice1Count, nice2Count};
		
	}
	
	
	public boolean isNice(String str) {
		boolean isNice = contains3Vowels(str);
		isNice = isNice && atLeastTwoCharactersRepeated(str);
		isNice = isNice && doesNotContainsSpecificSubstring(str);
		return isNice;
	}

	public boolean contains3Vowels(String str) {

		List<Character> vowels = new ArrayList<>();
		if (str.length() < 3) { // fail fast check
			return false;
		}

		for (int i = 0; i < str.length(); i++) {
			char c = str.charAt(i);

			if (Character.isLetter(c)) {
				for (int j = 0; j < VOWELS.length; j++) {
					if (c == VOWELS[j]) {
						vowels.add(c);
					}
				}
			}

		}

		return vowels.size() >= 3;
	}

	public boolean atLeastTwoCharactersRepeated(String str) {

		if (str.length() < 2) { // fail fast check
			return false;
		}

		char previousCharacter = 0;

		for (int i = 0; i < str.length(); i++) {
			char currentCharacter = str.charAt(i);

			if (currentCharacter == previousCharacter) {
				return true;
			}
			previousCharacter = currentCharacter;
		}

		return false;
	}

	public boolean doesNotContainsSpecificSubstring(String str) {
		// //ab, cd, pq, or xy
		return !(str.contains("ab") ||  str.contains("cd") || str.contains("pq") || str.contains("xy"));
	}

	public boolean isNice2(String str) {
		boolean isNice = containsPair(str);
		isNice = isNice && letterRepeats(str);
		return isNice;
	}

	public boolean containsPair(String str) {
		
		if(str.length() < 2) { //fail fast
			return false;
		}
		
		//get pairs of characters
		List<String> pairs = getPairsOfCharacters(str);
		int count = 0;
		for (String pair : pairs) {
			count = getCount(str, pair);
			if(count >= 2){
				return true;
			}
		}
		
		return false;
	}
	
	private int getCount(String str, String substring){
		int lastIndex = 0;
		int count = 0;

		while(lastIndex != -1){

		    lastIndex = str.indexOf(substring,lastIndex);

		    if(lastIndex != -1){
		        count ++;
		        lastIndex += substring.length();
		    }
		}
		return count;
	}

	public  List<String> getPairsOfCharacters(String str) {

		char[] buffer = new char[2];
		List<String> pairs = new ArrayList<>();
		
		for (int i = 0; i < str.length() - 1; i++) {
			buffer[0] = str.charAt(i);
			buffer[1] = str.charAt(i + 1);
			
			String pair = new String(buffer);
			if(!pairs.contains(pair)){
				pairs.add(pair);
			}
		}
		
		return pairs;
	}

	public boolean letterRepeats(String str) {
		for (int i = 0; i < str.length() - 2; i++) {
			
			char currentChar = str.charAt(i);
			char repeat = str.charAt(i + 2);
			if(currentChar == repeat){
				return true;
			}
		}
		
		return false;
	}

}
