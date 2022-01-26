import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;
import pkg.*;

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
	static double distanceCheck(int x1, int y1, int x2, int y2) {
		return abs(x1-x2) + 1.3*abs(y1-y2);
	}
	static void p(String s){System.out.print(s);}
	static void pArray(int[] a) {
		p("{");
		for(int i : a) {
			p(" "+i+" ");
		}
		p("}");
	}
	public static ArrayList<Node> returnPath(Grid g, Node start, int[] end) {
		ArrayList<Node> path = new ArrayList<>();
		Stack<Node> outStack = aStarSearch(g,start,end);
		while(!outStack.empty()) {
			path.add(outStack.pop());
		}
		return path;
	}
	static Stack<Node> aStarSearch(Grid g, Node start, int[] end) {
		//start is a Node and end is an array, for very arbitrary reasons
		//Scanner sc = new Scanner(System.in);  //for debugging purposes
		Stack<Node> path = new Stack<>();     //To be interpreted into a path
		ArrayList<Node> open = new ArrayList<>();   //cells to be explored
		ArrayList<Node> closed = new ArrayList<>(); //cells already explored
		//DISTANCE: distance from goal is |xCurrent-xFinal|+|yStart-yFinal|
		//distance from start is the shortest *known* amount of cells to travel to get to that position
		HashMap<String,Integer> closedCoords = new HashMap<>(); //check if the location has already been explored
		//HashMap is a String, Integer because having two Integers to represent coords caused problems
		//The String is Xcoord + " " + Ycoord
		//The Integer is 0

		//add the origin node to the open list
		open.add(start);
		//generation count to determine distance from start
		int generation = 0;
		//multiply square size
		int m_scaleFactor = 10/(1+g.width/50);
		//node searching
		while(!open.isEmpty()) {
			Node lowestVal = open.get(0); //cell with lowest distance total(first pass always is the starting cell)
			int index = 0; //index of the coord to be removed
			int c = 0; //counter
			//find lowest total
			for(Node i : open) {
				if(i.getCost()<lowestVal.getCost()) {
					index = c;
					lowestVal = i;
				}
				c++;
			}
			generation = lowestVal.distA;
			//find neighbors, spaghetti code
			p("PROGRESS: "+lowestVal.distB+"   ");
			p("NODES: "+closed.size()+"   ");
			p("Current node: { "+lowestVal.getX()+" "+lowestVal.getY()+" }\n");
			Rectangle r = new Rectangle(lowestVal.getX()*m_scaleFactor, lowestVal.getY()*m_scaleFactor, m_scaleFactor, m_scaleFactor);
			r.setColor(new Color(255, 30, 30));
			r.fill();
			Node check;
			//North:
			if(lowestVal.getY()>0) {
				int[] coords = {lowestVal.getX(),lowestVal.getY()-1};
				String hashCheck = new String(coords[0]+" "+coords[1]);
				check = new Node(coords,generation+1,distanceCheck(lowestVal.getX(),lowestVal.getY(),end[0],end[1]));
				if(!closedCoords.containsKey(hashCheck)&&((coords[0]+coords[1])>0)&&(g.grid[coords[0]][coords[1]]==' ')) {
					//if((coords[0]+coords[1])<=0) {p("BOUNDARY  ");}
					//else if(closedCoords.containsKey(hashCheck)) {continue;} //continue; p("containsKey:North  ");
					//p("NORTH");pArray(coords);p(" COST="+check.getCost()+" ");
					if((coords[0]==end[0])&&(coords[1]==end[1])) {
						check.setParent(lowestVal);
						closed.add(check);
						break;
					}
					else {
						check.setParent(lowestVal);
						open.add(check);
					}
				} //else{p(" containsKey:North ");}
			}
			//East:
			if(lowestVal.getX()<g.width-1) {
				int[] coords = {lowestVal.getX()+1,lowestVal.getY()};
				String hashCheck = coords[0]+" "+coords[1];
				check = new Node(coords,generation+1,distanceCheck(lowestVal.getX(),lowestVal.getY(),end[0],end[1]));
				if(!closedCoords.containsKey(hashCheck)&&((coords[0]+coords[1])>0)&&(g.grid[coords[0]][coords[1]]==' ')) {
					//if(closedCoords.containsKey(hashCheck)) {continue;} //continue; p("containsKey:East  ");
					//p("EAST");pArray(coords);p(" COST="+check.getCost()+" ");
					if((coords[0]==end[0])&&(coords[1]==end[1])) {
						check.setParent(lowestVal);
						closed.add(check);
						break;
					}
					else {
						check.setParent(lowestVal);
						open.add(check);
					}
				} //else{p(" containsKey:East ");}
			}
			//South:
			if(lowestVal.getY()<g.height-1) {
				int[] coords = {lowestVal.getX(),lowestVal.getY()+1};
				String hashCheck = new String(coords[0]+" "+coords[1]);
				check = new Node(coords,generation+1,distanceCheck(lowestVal.getX(),lowestVal.getY(),end[0],end[1]));
				if(!closedCoords.containsKey(hashCheck)&&((coords[0]+coords[1])>0)&&(g.grid[coords[0]][coords[1]]==' ')) {
					//if(closedCoords.containsKey(hashCheck)) {continue;} //continue; p("containsKey:South  ");
					//p("SOUTH");pArray(coords);p(" COST="+check.getCost()+" ");
					if((coords[0]==end[0])&&(coords[1]==end[1])) {
						check.setParent(lowestVal);
						closed.add(check);
						break;
					}
					else {
						check.setParent(lowestVal);
						open.add(check);
					}
				} //else{p(" containsKey:South ");}
			}
			//West:
			if(lowestVal.getX()>0) {
				int[] coords = {lowestVal.getX()-1,lowestVal.getY()};
				String hashCheck = new String(coords[0]+" "+coords[1]);
				check = new Node(coords,generation+1,distanceCheck(lowestVal.getX(),lowestVal.getY(),end[0],end[1]));
				if(!closedCoords.containsKey(hashCheck)&&((coords[0]+coords[1])>0)&&(g.grid[coords[0]][coords[1]]==' ')) {
					//if((coords[0]+coords[1])<=0) {p("BOUNDARY  ");}
					//else if(closedCoords.containsKey(hashCheck)) {continue;} //continue; p("containsKey:West ");
					//p("WEST");pArray(coords);p(" COST="+check.getCost()+" ");
					if((coords[0]==end[0])&&(coords[1]==end[1])) {
						check.setParent(lowestVal);
						closed.add(check);
						break;
					}
					else {
						check.setParent(lowestVal);
						open.add(check);
					}
				} //else{p(" containsKey:West ");}
			}
			String hs = new String(lowestVal.getX()+" "+lowestVal.getY());
			if(!closedCoords.containsKey(hs)) {
				closed.add(open.get(index));
				closedCoords.put(hs,0);
			}
			open.remove(index);
			generation++;
			//p(" GEN="+generation+" ");
			//sc.nextLine();
		}
		//construct path
		Node pushed = closed.get(closed.size()-1);
		if((pushed.getX()+pushed.getY())!=(end[0]+end[1])) {p("Path not found!\n");return path;}
		while(true) {
			path.push(pushed);
			if((pushed.getX() == start.getX())&&(pushed.getY() == start.getY())) {
				break;
			}
			Node temp = pushed.parent;
			pushed = temp;
		}
		return path;
	}
}
