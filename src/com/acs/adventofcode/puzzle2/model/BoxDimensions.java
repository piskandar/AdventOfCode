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
		int surfaceArea = (2*length*width) + (2*width*height) + (2*height*length);
		return surfaceArea;
	}
	
	public int smallestSideArea(){
		int side1area = length * width;
		int side2area = width * height;
		int side3area = height * length;
		
		int min = Integer.MAX_VALUE;
		min = Math.min(min, side1area);
		min = Math.min(min, side2area);
		min = Math.min(min, side3area);
		return min;
	} 
	
	@Override
	public String toString() {
		
		return String.format("L: %d W: %d H: %d, surfaceArea: %d, min: %d", length, width, height, calculateSurfaceArea(), smallestSideArea());
	}
	
}
