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
	public static ArrayList<Integer> aStarSearch(Grid g, Node start, int[] end) {
    //start is a Node and end is an array, for very arbitrary reasons
		ArrayList<Integer> path = new ArrayList<>();     //1-DOWN, 2-RIGHT, 3-UP, 4-LEFT
		ArrayList<Node> open = new ArrayList<>();   //cells to be explored
		ArrayList<Node[]> closed = new ArrayList<>(); //cells already explored
		//DISTANCE: distance from goal is |xCurrent-xFinal|+|yStart-yFinal|
		//distance from start is the shortest *known* amount of cells to travel to get to that position

		//add the origin node to the open list
		open.add(start);

		//actual pathfinding
		while(!open.isEmpty()) {
			Node lowestVal = open.get(0); //cell with lowest distance total(first pass always is the starting cell)
      ArrayList<Node> successors = new ArrayList<>();//successors to selected node
			int index = 0; //index of the coord to be removed
			int c = 0; //counter
			//find lowest total
			for(Node i : open) {
				if((i.getX()+i.getY())<(lowestVal[2]+lowestVal[3])) {
					index = c;
					for(int j=0;j<i.length;j++) {
						lowestVal = i;
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
