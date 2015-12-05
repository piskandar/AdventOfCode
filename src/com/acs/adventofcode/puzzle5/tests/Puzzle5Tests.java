package com.acs.adventofcode.puzzle5.tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.acs.adventofcode.puzzle5.Puzzle5;

public class Puzzle5Tests {

	private Puzzle5 puzzle;
	
	@Before
	public void setup(){
		puzzle = new Puzzle5();
	}
	
	@Test
	public void testHas3Vowels(){
		
		assertTrue(puzzle.contains3Vowels("aei"));
		assertTrue(puzzle.contains3Vowels("xazegov"));
		assertTrue(puzzle.contains3Vowels("aeiouaeiouaeiou"));
		assertTrue(puzzle.contains3Vowels("hththththththahthththtthththtthtththththththththththththehththththththththththththththththththitihththttththththththththht"));
		
		assertFalse(puzzle.contains3Vowels("msc"));
		assertFalse(puzzle.contains3Vowels("aeks"));
		assertFalse(puzzle.contains3Vowels("as"));
	}
	
	@Test
	public void testSameCharacterRepeatedTwiceInARow(){
		assertTrue(puzzle.atLeastTwoCharactersRepeated("xx"));
		assertTrue(puzzle.atLeastTwoCharactersRepeated("abcdde"));
		assertTrue(puzzle.atLeastTwoCharactersRepeated("aabbccdd"));
		
		assertFalse(puzzle.atLeastTwoCharactersRepeated("msc"));
		assertFalse(puzzle.atLeastTwoCharactersRepeated("aeksae"));
		assertFalse(puzzle.atLeastTwoCharactersRepeated("as"));
		
	}
	
	@Test
	public void testDoesNotContainsSpecificSubstring(){
		//ab, cd, pq, or xy
		assertFalse(puzzle.doesNotContainsSpecificSubstring("haegwjzuvuyypxyu"));
		assertFalse(puzzle.doesNotContainsSpecificSubstring("abcdde"));
		assertFalse(puzzle.doesNotContainsSpecificSubstring("aabbccdd"));
		
		assertTrue(puzzle.doesNotContainsSpecificSubstring("sdkjfhkhewkfnsds"));
		
	}
	
	
	
	@Test
	public void testIsNice() {
		assertTrue(puzzle.isNice("ugknbfddgicrmopn"));
		assertTrue(puzzle.isNice("aaa"));
		assertFalse(puzzle.isNice("jchzalrnumimnmhp"));
		assertFalse(puzzle.isNice("haegwjzuvuyypxyu"));
		assertFalse(puzzle.isNice("dvszwmarrgswjxmb"));
		
	}

}
