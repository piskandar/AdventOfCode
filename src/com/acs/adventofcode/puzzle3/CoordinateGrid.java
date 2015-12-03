package com.acs.adventofcode.puzzle3;

public class CoordinateGrid {
	
	
	public class Point {
		int x;
		int y;
		
		public Point(int x, int y){
			this.x = x;
			this.y = y;
		}
	}
	
	private int placesVisited;
	private Point currentPoint;
	private int[][] locationGrid;
	
	public CoordinateGrid(int width, int height){
		locationGrid = new int[width][height];
		currentPoint = new Point(width/2, height/2);
		visitLocation(currentPoint);
		
	}
	
	private void visitLocation(Point point){
		if(locationGrid[point.y][point.x] != 1){
			locationGrid[point.y][point.x] = 1;
			placesVisited++;
		}
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
		
		visitLocation(currentPoint);
		
	}

	public int getNumberOfPlacesVisited() {
		return placesVisited;
	}
}
