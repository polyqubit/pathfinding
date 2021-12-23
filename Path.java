public class Path {
	//Checking if a path exists before actually finding a path
	public static boolean isSolvable(Grid g) {
		//Directions(in order of preference): Down(1), Right(2), Up(3), Left(4)
		int[] currentPos = {0,0};
		int[] previousPos = new int[2];
		int forbidRepeat = -1; //Prevents going in a direction opposite to what the path just came from(eg going down after going up)
	
		for(int i=0;i<g.height-1;i++) {
		if(g.grid[currentPos[0]+1][currentPos[1]]==' ') {
			System.out.println("y");
			currentPos[0]++;
		}
		else if(g.grid[currentPos[0]][currentPos[1]+1]==' ') {
			
		}
		previousPos[0] = currentPos[0];
		previousPos[1] = currentPos[1];
		}
	
		return false;
	}
}