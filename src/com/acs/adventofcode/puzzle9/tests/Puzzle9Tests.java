package com.acs.adventofcode.puzzle9.tests;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class Puzzle9Tests {

	Puzzle9 puzzle;
	
	@Before
	public void setup(){
		puzzle = new Puzzle9();
	}
	
	@Test
	public void testParsing(){
		Route route = puzzle.parseRoute("London to Dublin = 464");
		assertEquals("London", route.getFrom());
		assertEquals("Dublin", route.getTo());
		assertEquals(464, route.getDistance());
		
		route = puzzle.parseRoute("London to Belfast = 518");
		assertEquals("London", route.getFrom());
		assertEquals("Belfast", route.getTo());
		assertEquals(518, route.getDistance());
		
		route = puzzle.parseRoute("Dublin to Belfast = 141");
		assertEquals("Dublin", route.getFrom());
		assertEquals("Belfast", route.getTo());
		assertEquals(141, route.getDistance());
	}
	
	@Test
	public void testGetAllStops(){
		Route route = puzzle.parseRoute("London to Dublin = 464");

		route = puzzle.parseRoute("London to Belfast = 518");
		
		route = puzzle.parseRoute("Dublin to Belfast = 141");
		
		List<String> allStops = puzzle.getAllStops(); 
		
		assertEquals(3, allStops.size());
		assertTrue(allStops.contains("London"));
		assertTrue(allStops.contains("Belfast"));
		assertTrue(allStops.contains("Dublin"));
		
		
	}
	
	@Test
	public void testPossibleRoutes(){
		Route route = puzzle.parseRoute("London to Dublin = 464");

		route = puzzle.parseRoute("London to Belfast = 518");
		
		route = puzzle.parseRoute("Dublin to Belfast = 141");
		
		List<String> allStops = puzzle.getAllStops();
		
		List<String> routeCombos = puzzle.getPossibleRoutes(); 
		
		
		
		
	}
}
