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
	static int distanceCheck(int x1, int y1, int x2, int y2) {
		return abs(x1-x2) + abs(y1-y2);
	}
	public static ArrayList returnPath(Grid g, Node start, int[] end) {
		ArrayList<Node> path = new ArrayList<>();
		Stack<Node> outStack = aStarSearch(g,start,end);
		while(!outStack.empty()) {
			path.add(outStack.pop());
		}
		return path;
	}
	static Stack<Node> aStarSearch(Grid g, Node start, int[] end) {
		//start is a Node and end is an array, for very arbitrary reasons
		Stack<Node> path = new Stack<>();     //To be interpreted into a path
		ArrayList<Node> open = new ArrayList<>();   //cells to be explored
		ArrayList<Node> closed = new ArrayList<>(); //cells already explored
		ArrayList<Node> avoid = new ArrayList<>(); //obstacle cells
		//DISTANCE: distance from goal is |xCurrent-xFinal|+|yStart-yFinal|
		//distance from start is the shortest *known* amount of cells to travel to get to that position

		//add the origin node to the open list
		open.add(start);
		//generation count to determine distance from start
		int generation = 0;
		//node searching
		while(!open.isEmpty()) {
			Node lowestVal = open.get(0); //cell with lowest distance total(first pass always is the starting cell)
			int index = 0; //index of the coord to be removed
			int c = 0; //counter
			//find lowest total
			for(Node i : open) {
				if((i.getX()+i.getY())<(lowestVal.getX()+lowestVal.getY())) {
					index = c;
					lowestVal = i;
				}
				c++;
			}
			//find neighbors, spaghetti code
			//North:
			Node check;
			if(lowestVal.getY()>0) {
				int[] coords = {lowestVal.getX(),lowestVal.getY()-1};
				check = new Node(coords,generation+1,distanceCheck(lowestVal.getX(),lowestVal.getY(),end[0],end[1]));
				if((coords[0]==end[0])&&(coords[1]==end[1])) {check.setParent(lowestVal);closed.add(check);break;}
				else if(g.grid[lowestVal.getX()][lowestVal.getY()-1]==' ') {
					check.setParent(lowestVal);
					open.add(check);
				}
				else {
					closed.add(check);
					avoid.add(check);
				}
			}
			//East:
			if(lowestVal.getX()<g.width-1) {
				int[] coords = {lowestVal.getX()+1,lowestVal.getY()};
				check = new Node(coords,generation+1,distanceCheck(lowestVal.getX(),lowestVal.getY(),end[0],end[1]));
				if((coords[0]==end[0])&&(coords[1]==end[1])) {check.setParent(lowestVal);closed.add(check);break;}
				else if(g.grid[lowestVal.getX()+1][lowestVal.getY()]==' ') {
					check.setParent(lowestVal);
					open.add(check);
				}
				else {
					closed.add(check);
					avoid.add(check);
				}
			}
			//South:
			if(lowestVal.getY()<g.height-1) {
				int[] coords = {lowestVal.getX(),lowestVal.getY()+1};
				check = new Node(coords,generation+1,distanceCheck(lowestVal.getX(),lowestVal.getY(),end[0],end[1]));
				if((coords[0]==end[0])&&(coords[1]==end[1])) {check.setParent(lowestVal);closed.add(check);break;}
				else if(g.grid[lowestVal.getX()][lowestVal.getY()+1]==' ') {
					check.setParent(lowestVal);
					open.add(check);
				}
				else {
					avoid.add(check);
					closed.add(check);
				}
			}
			//West:
			if(lowestVal.getX()>0) {
				int[] coords = {lowestVal.getX()-1,lowestVal.getY()};
				check = new Node(coords,generation+1,distanceCheck(lowestVal.getX(),lowestVal.getY(),end[0],end[1]));
				if((coords[0]==end[0])&&(coords[1]==end[1])) {check.setParent(lowestVal);closed.add(check);break;}
				else if(g.grid[lowestVal.getX()-1][lowestVal.getY()]==' ') {
					check.setParent(lowestVal);
					open.add(check);
				}
				else {
					avoid.add(check);
					closed.add(check);
				}
			}
			closed.add(open.get(index));
			open.remove(index);
			generation++;
			System.out.print(generation);
		}
		//construct path
		Node pushed = closed.get(closed.size()-1);
		int c = 0;
		while(true) {
			path.push(pushed);
			if((pushed.getX() == start.getX())&&(pushed.getY() == start.getY())) {
				break;
			}
			Node temp = pushed.parent;
			pushed = temp;
			c++;
			System.out.print(c);
		}
		return path;
	}
}
