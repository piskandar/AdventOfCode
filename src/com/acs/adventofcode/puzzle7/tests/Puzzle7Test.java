package com.acs.adventofcode.puzzle7.tests;

import java.util.Map;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import com.acs.adventofcode.puzzle7.Puzzle7;

public class Puzzle7Test {

	private Puzzle7 puzzle;
	
	@Before
	public void setup(){
		puzzle = new Puzzle7();
	}
	
	@Test
	public void testSignal(){
		Map<String, Integer> inputs = puzzle.getInput("123 -> x");
		
		Integer value = inputs.get("x");
		assertEquals(Integer.valueOf(123), value);
		
		inputs = puzzle.getInput("456 -> y");
		
		value = inputs.get("x");
		assertEquals(Integer.valueOf(123), value);
		
		value = inputs.get("y");
		assertEquals(Integer.valueOf(456), value);
		
		
		inputs = puzzle.getInput("x AND y -> d");
		
		value = inputs.get("x");
		assertEquals(Integer.valueOf(123), value);
		
		value = inputs.get("y");
		assertEquals(Integer.valueOf(456), value);
		
		value = inputs.get("d");
		assertEquals(Integer.valueOf(72), value);
		
		inputs = puzzle.getInput("x OR y -> e");
		
		value = inputs.get("x");
		assertEquals(Integer.valueOf(123), value);
		
		value = inputs.get("y");
		assertEquals(Integer.valueOf(456), value);
		
		value = inputs.get("d");
		assertEquals(Integer.valueOf(72), value);
		
		value = inputs.get("e");
		assertEquals(Integer.valueOf(507), value);
		
		inputs = puzzle.getInput("x LSHIFT 2 -> f");
		
		value = inputs.get("x");
		assertEquals(Integer.valueOf(123), value);
		
		value = inputs.get("y");
		assertEquals(Integer.valueOf(456), value);
		
		value = inputs.get("d");
		assertEquals(Integer.valueOf(72), value);
		
		value = inputs.get("e");
		assertEquals(Integer.valueOf(507), value);
		
		value = inputs.get("f");
		assertEquals(Integer.valueOf(492), value);
		
		inputs = puzzle.getInput("y RSHIFT 2 -> g");
		
		value = inputs.get("x");
		assertEquals(Integer.valueOf(123), value);
		
		value = inputs.get("y");
		assertEquals(Integer.valueOf(456), value);
		
		value = inputs.get("d");
		assertEquals(Integer.valueOf(72), value);
		
		value = inputs.get("e");
		assertEquals(Integer.valueOf(507), value);
		
		value = inputs.get("f");
		assertEquals(Integer.valueOf(492), value);
		
		value = inputs.get("g");
		assertEquals(Integer.valueOf(114), value);
		
		inputs = puzzle.getInput("NOT x -> h");
		
		value = inputs.get("x");
		assertEquals(Integer.valueOf(123), value);
		
		value = inputs.get("y");
		assertEquals(Integer.valueOf(456), value);
		
		value = inputs.get("d");
		assertEquals(Integer.valueOf(72), value);
		
		value = inputs.get("e");
		assertEquals(Integer.valueOf(507), value);
		
		value = inputs.get("f");
		assertEquals(Integer.valueOf(492), value);
		
		value = inputs.get("g");
		assertEquals(Integer.valueOf(114), value);
		
		value = inputs.get("h");
		assertEquals(Integer.valueOf(65412), value);
		
inputs = puzzle.getInput("NOT y -> i");
		
		value = inputs.get("x");
		assertEquals(Integer.valueOf(123), value);
		
		value = inputs.get("y");
		assertEquals(Integer.valueOf(456), value);
		
		value = inputs.get("d");
		assertEquals(Integer.valueOf(72), value);
		
		value = inputs.get("e");
		assertEquals(Integer.valueOf(507), value);
		
		value = inputs.get("f");
		assertEquals(Integer.valueOf(492), value);
		
		value = inputs.get("g");
		assertEquals(Integer.valueOf(114), value);
		
		value = inputs.get("h");
		assertEquals(Integer.valueOf(65412), value);
		
		value = inputs.get("i");
		assertEquals(Integer.valueOf(65079), value);
	}
	
	
}
