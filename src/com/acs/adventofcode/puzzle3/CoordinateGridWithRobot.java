package com.acs.adventofcode.puzzle3;

public class CoordinateGridWithRobot {
	
	
	public class Point{
		int x;
		int y;
		
		@Override
		public String toString() {
			return "x: " + x + " y: " + y;
		}
	}
	
	
	private Point currentSantaPoint;
	private Point currentRobotPoint;
	private int[][] locationGrid;
	
	public CoordinateGridWithRobot(int width, int height){
		locationGrid = new int[width][height];
		currentSantaPoint = getCenterPoint(width, height);
		currentRobotPoint = getCenterPoint(width, height);
		System.out.println(currentSantaPoint);
		System.out.println(currentRobotPoint);
		locationGrid[currentSantaPoint.y][currentSantaPoint.x] = 1;
		locationGrid[currentRobotPoint.y][currentRobotPoint.x] = 1;
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
	
	public void movePoint(Direction direction, boolean moveSanta){
		if(moveSanta){
			moveSantaPoint(direction);
		}else{
			moveRobotPoint(direction);
		}
	}
	
	public void moveSantaPoint(Direction direction){
		switch(direction){
		case NORTH:
			currentSantaPoint.y = currentSantaPoint.y - 1;
			break;
		case SOUTH:
			currentSantaPoint.y = currentSantaPoint.y + 1;
			break;
		case EAST:
			currentSantaPoint.x = currentSantaPoint.x + 1;
			break;
		case WEST:
			currentSantaPoint.x = currentSantaPoint.x - 1;
			break;
		}
		
		locationGrid[currentSantaPoint.y][currentSantaPoint.x] = 1;
	}
	
	public void moveRobotPoint(Direction direction){
		switch(direction){
		case NORTH:
			currentRobotPoint.y = currentRobotPoint.y - 1;
			break;
		case SOUTH:
			currentRobotPoint.y = currentRobotPoint.y + 1;
			break;
		case EAST:
			currentRobotPoint.x = currentRobotPoint.x + 1;
			break;
		case WEST:
			currentRobotPoint.x = currentRobotPoint.x - 1;
			break;
		}
		
		locationGrid[currentRobotPoint.y][currentRobotPoint.x] = 1;
	}
}
