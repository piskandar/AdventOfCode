package com.acs.adventofcode.puzzle2.model;

public class BoxDimensions {

	private int length;
	private int width;
	private int height;
	
	public BoxDimensions(int length, int width, int height) {
		this.length = length;
		this.width = width;
		this.height = height;
	}
	
	

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}
	
	public int calculateSurfaceArea(){
		return (2*length*width) + (2*width*height) + (2*height*length);
	}
	
	public int smallestSideArea(){
		
		int[] sideAreas = { length * width, width * height, height * length };
		
		int min = Integer.MAX_VALUE;
		for (int i = 0; i < sideAreas.length; i++) {
			int area = sideAreas[i];
			min = Math.min(min, area);
		}
		return min;
	} 
	
	public int smallestParameter(){
		
		int[] sidePerimeters = { 	
									2 * (length + width), 
									2 * (width + height), 
									2 * (height + length) 
							   };
		
		int min = Integer.MAX_VALUE;
		for (int i = 0; i < sidePerimeters.length; i++) {
			int perimeter = sidePerimeters[i];
			min = Math.min(min, perimeter);
		}
		
		return min;
	}
	
	public int calculateVolume(){
		return length * width * height;
	}
	@Override
	public String toString() {
		
		return String.format("L: %d W: %d H: %d, surfaceArea: %d, min: %d, volume: %d, smallest parameter: %d", length, width, height, calculateSurfaceArea(), smallestSideArea(), calculateVolume(), smallestParameter());
	}
	
}
