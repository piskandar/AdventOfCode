package com.acs.adventofcode.puzzle2.tests;

import static org.junit.Assert.*;

import java.awt.print.Book;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.acs.adventofcode.puzzle2.model.BoxDimensions;

public class Puzzle2Tests {

	
	
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testBoxDimensions_surfaceArea() {
		BoxDimensions dimensions = new BoxDimensions(2, 3, 4);
		int surfaceArea = dimensions.calculateSurfaceArea();
		
		assertEquals(52, surfaceArea);
	}
	
	@Test
	public void testBoxDimensions_smallestSideArea() {
		BoxDimensions dimensions = new BoxDimensions(2, 3, 4);
		int smallestSideArea = dimensions.smallestSideArea();
		
		assertEquals(6, smallestSideArea);
	}
	
	@Test
	public void testBoxDimensions_smallestPerimeter() {
		BoxDimensions dimensions = new BoxDimensions(2, 3, 4);
		int smallestParameter = dimensions.smallestParameter();
		
		assertEquals(10, smallestParameter);
	}

}
