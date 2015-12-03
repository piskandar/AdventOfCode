package com.acs.adventofcode.puzzle3;

public class CoordinateGrid {
	
	
	public class Point{
		int x;
		int y;
		
		@Override
		public String toString() {
			return "x: " + x + " y: " + y;
		}
	}
	
	
	private Point currentPoint;
	private int[][] locationGrid;
	
	public CoordinateGrid(int width, int height){
		locationGrid = new int[width][height];
		currentPoint = getCenterPoint(width, height);
		System.out.println(currentPoint);
		locationGrid[currentPoint.y][currentPoint.x] = 1;
	}
	
	private Point getCenterPoint(int width, int height) {
		
		Point p = new Point();
		p.x = width/2;
		p.y = height/2;
		
		
		
		return p;
	}

	public void printGrid(){
		for (int i = 0; i < locationGrid.length; i++) {
			for (int j = 0; j < locationGrid[i].length; j++) {
				System.out.print(locationGrid[i][j] + "\t");
			}
			System.out.println();
		}
	}
	
	public void countSpacesVisited(){
		int count = 0;
		for (int i = 0; i < locationGrid.length; i++) {
			for (int j = 0; j < locationGrid[i].length; j++) {
				if(locationGrid[i][j] == 1){
					count++;
				}
			}
			
		}
		System.out.println("count: " + count);
	}
	
	public void movePoint(Direction direction){
		switch(direction){
		case NORTH:
			currentPoint.y = currentPoint.y - 1;
			break;
		case SOUTH:
			currentPoint.y = currentPoint.y + 1;
			break;
		case EAST:
			currentPoint.x = currentPoint.x + 1;
			break;
		case WEST:
			currentPoint.x = currentPoint.x - 1;
			break;
		}
		
		locationGrid[currentPoint.y][currentPoint.x] = 1;
	}
}
