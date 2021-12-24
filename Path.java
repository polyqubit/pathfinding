import java.util.*;

public class Path {
	static ArrayList<Integer> travel = new ArrayList<>();
	//Checking if a path exists before actually finding a path
	public static boolean isSolvable(Grid g) {
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
	}
}