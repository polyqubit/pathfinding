/**
 * {@link Node}: used to represent a position on a {@link Grid}
 * 
 * 
 * 
 */

public class Node {
    Integer[] pos = {0,0};
    int distA = 0;
    int distB = 0;
    public Node parent;
    public Node(int[] position, int distA, int distB) {
        //distA: distance from start
        //distB: distance from goal
        if(position.length!=2) {return;}
        pos[0] = position[0];
        pos[1] = position[1];
        this.distA = distA;
        this.distB = distB;
    }
    public void setParent(Node parent) {
        this.parent = parent;
    }
    public void setGen(int gen) {
      distA = gen;
    }
    public int getX() {
        return pos[0];
    }
    public int getY() {
        return pos[1];
    }
	public int getCost() {
		return distA + distB;
	}
}
