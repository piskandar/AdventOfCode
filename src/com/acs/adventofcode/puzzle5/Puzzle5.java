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
		System.out.println(puzzle5.numberOfNiceNames());
	}
	
	public int numberOfNiceNames(){
		FileReader fr = null;
		BufferedReader br = null;
		int niceCount = 0;
		try {
			fr = new FileReader(new File("files/puzzle5/puzzle5input.txt"));
			br = new BufferedReader(fr);
			
			String line = br.readLine();
			
			while(line != null){
				
				if(isNice(line.trim())){
					niceCount++;
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
		
		return niceCount;
		
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

}
