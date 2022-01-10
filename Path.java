import java.util.*;

public class Path {
	//Checking if a path exists before actually finding a path
	//EDIT(1/5/22): Checking whether a path exists is redundant as the actual pathfinding algorithm should already have that functionality 
	/* public static boolean isSolvable(Grid g) {
		//Directions(in order of preference): Down(1), Right(2), Up(3), Left(4)
		int[] currentPos = {0,0};  //Graph is expressed {y,x}
		int[] repeatCheck = new int[5]; //Tries to avoid repeating actions
		int c = 0; //counter for repeatCheck
		int forbidRepeat = -1; //Prevents going in a direction opposite to what the path just came from(eg going down after going up)
	
		for(int i=0;i<2*(g.height*g.width)-1;i++) {
			previousPos[0] = currentPos[0];
			previousPos[1] = currentPos[1];
			
			if((currentPos[0]!=(g.height-1))&&((currentPos[0]+1)<=g.height)&&(g.grid[currentPos[0]+1][currentPos[1]]==' ')&&(forbidRepeat!=1)) {
				currentPos[0]++;
				forbidRepeat = -1;
				System.out.println("DOWN");
				travel.add(1);
			}
			else if((currentPos[1]!=(g.width-1))&&((currentPos[1]+1)<=g.width)&&(g.grid[currentPos[0]][currentPos[1]+1]==' ')&&(forbidRepeat!=2)) {
				currentPos[1]++;
				forbidRepeat = -1;
				System.out.println("RIGHT");
				travel.add(2);
			}
			else if((currentPos[0]!=0)&&(g.grid[currentPos[0]-1][currentPos[1]]==' ')) {
				currentPos[0]--;
				forbidRepeat = 1;
				System.out.println("UP");
				travel.add(3);
			}
			else if((currentPos[1]!=0)&&(g.grid[currentPos[0]][currentPos[1]-1]==' ')) {
				currentPos[1]--;
				forbidRepeat = 2;
				System.out.println("LEFT");
				travel.add(4);
			}
			else {return false;}
			if((currentPos[0]==(g.height-1))&&(currentPos[1]==(g.width-1))) {return true;}
			c++;
		}
		return false;
	} */
	static int abs(int a) {return (a<0) ? -1*a : a;}
	static int distanceCheck(int[] cell, int[] goal) {
		return abs(cell[0]-goal[0]) + abs(cell[1]-goal[1]);
	}
	public static ArrayList<Integer> aStarSearch(Grid g, int[] start, int[] end) { 
		if((start.length!=2)||(end.length!=2)) {return null;} //doesnt make sense for a coordinate to not have 2 elements
		ArrayList<Integer> path = new ArrayList<>();     //1-DOWN, 2-RIGHT, 3-UP, 4-LEFT
		ArrayList<Integer[]> open = new ArrayList<>();   //coords to be explored
		ArrayList<Integer[]> closed = new ArrayList<>(); //coords already explored
		//NOTE on Integer[]: always 4 element array - first two elements
		//are x,y; next element is the distance from the starting position,
		//and the last is the distance from the goal node
		//DISTANCE: distance from goal is |xCurrent-xFinal|+|yStart-yFinal|
		//distance from start is the shortest *known* amount of cells to travel to get to that position

		//add the origin node to the open list
		Integer[] START = {start[0],start[1],0,0}; //guide said to leave both distances at 0 idk
		open.add(START);

		//actual pathfinding
		while(!open.isEmpty()) {
			Integer[] lowestVal = open.get(0); //coord of cell with lowest distance total(first pass always is the starting cell)
			int index = 0; //index of the coord to be removed
			int c = 0; //counter
			//find lowest total
			for(Integer[] i : open) {
				if((i[2]+i[3])<(lowestVal[2]+lowestVal[3])) {
					index = c;
					for(int j=0;j<i.length;j++) {
						lowestVal[j] = i[j];
					}
				}
				c++;
			}
			//find neighbors, spaghetti code
            //North:
			if(lowestVal[1]>0) {
            	if(g.grid[lowestVal[0]][lowestVal[1]-1]==' ') {
					
				}
			}
			closed.add(open.get(index));
			open.remove(index);
		}
		return path;
	}
}
