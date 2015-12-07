package com.acs.adventofcode.puzzle6.tests;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import com.acs.adventofcode.puzzle6.Puzzle6;
import com.acs.adventofcode.puzzle6.Puzzle6.Command;
import com.acs.adventofcode.puzzle6.Puzzle6.Range;

public class Puzzle6Tests {

	private Puzzle6 puzzle;

	@Before
	public void setUp() throws Exception {
		puzzle = new Puzzle6(1000);
	}

	@Test
	public void testTurnOn() {
		
		puzzle = new Puzzle6(10);
		
		int lightsOn = puzzle.processCommand("turn on 0,0 through 9,9");
		
		assertEquals(100, lightsOn);
		
		puzzle = new Puzzle6(1000); 
		lightsOn = puzzle.processCommand("turn on 0,0 through 999,999");
		
		assertEquals(1_000_000, lightsOn);
	}
	
	@Test
	public void testTurnOff() {
		
		puzzle = new Puzzle6(10);
		
		int lightsOn = puzzle.processCommand("turn off 0,0 through 9,9");
		
		assertEquals(0, lightsOn);
		
		puzzle = new Puzzle6(1000); 
		lightsOn = puzzle.processCommand("turn on 499,499 through 500,500");
		
		assertEquals(4, lightsOn);
		lightsOn = puzzle.processCommand("turn off 499,499 through 500,500");
		
		assertEquals(0, lightsOn);
	}
	
	
	@Test
	public void testToggle() {
				
		puzzle = new Puzzle6(1000); 
		int lightsOn = puzzle.processCommand("turn on 0,0 through 999,0");
		
		assertEquals(1000, lightsOn);
		lightsOn = puzzle.processCommand("toggle 0,0 through 999,0");
//		
		assertEquals(0, lightsOn);
		
		lightsOn = puzzle.processCommand("turn on 0,0 through 999,999");
		
		assertEquals(1_000_000, lightsOn);
		
		lightsOn = puzzle.processCommand("toggle 0,0 through 999,0");
		
		assertEquals(999000, lightsOn);
	}

	@Test
	public void testGetCommandFromLine() {
		Command command = puzzle.getCommandFromLine("turn on 313,306 through 363,621");
		assertEquals(Command.TURN_ON, command);

		command = puzzle.getCommandFromLine("toggle 173,401 through 496,407");
		assertEquals(Command.TOGGLE, command);

		command = puzzle.getCommandFromLine("turn off 87,577 through 484,608");
		assertEquals(Command.TURN_OFF, command);
	}
	
	@Test
	public void testGetStartRange(){
		Range range = puzzle.getStartRange("turn on 313,306 through 363,621");
		assertEquals(313, range.start);
		assertEquals(306, range.end);
		
		range = puzzle.getStartRange("toggle 173,401 through 496,407");
		assertEquals(173, range.start);
		assertEquals(401, range.end);
		
		range = puzzle.getStartRange("turn off 87,577 through 484,608");
		assertEquals(87, range.start);
		assertEquals(577, range.end);
	}
	
	@Test
	public void testGetEndRange(){
		Range range = puzzle.getEndRange("turn on 313,306 through 363,621");
		assertEquals(363, range.start);
		assertEquals(621, range.end);
		
		range = puzzle.getEndRange("toggle 173,401 through 496,407");
		assertEquals(496, range.start);
		assertEquals(407, range.end);
		
		range = puzzle.getEndRange("turn off 87,577 through 484,608");
		assertEquals(484, range.start);
		assertEquals(608, range.end);
	}

}
