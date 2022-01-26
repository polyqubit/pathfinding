import java.util.ArrayList;
import java.util.Scanner;

import pkg.Canvas;

public class starter {
	static void p(String s) {System.out.print(s);}
	public static void main(String[] args) {
		try (Scanner sc = new Scanner(System.in)) {
			p("Enter side length of grid:");
			int w = sc.nextInt(); sc.nextLine();
			Grid g = new Grid(w,w);
			g.constructGrid();
			g.print();
			p("\nGrid constructed successfully\n");

			int[] start = {0,0};
			int[] end = {g.width-1,g.height-1}; //redo to let user define
			ArrayList<Node> path = Path.returnPath(g, new Node(start,0,0), end);
			//p("Pathfinding algorithm complete, remember to add success check\n");
			for(Node i : path) {
				//p("{ ");
				//p(i.getX()+" "+i.getY());
				//p(" }");
				g.grid[i.getX()][i.getY()] = 'O';
			}
			Canvas.getInstance().clear();
			g.print();
			p("\n");
			for(int i=0;i<path.size()-1;i++) {
				//more spaghetti
				//for some reason getX refers to vertical on Grid
				if((path.get(i+1).getY()-(path.get(i).getY()))>0) {
					p("EAST ");
				}
				else if((path.get(i+1).getX()-(path.get(i).getX())>0)) {
					p("SOUTH ");
				}
				else if((path.get(i).getY()-(path.get(i+1).getY())>0)) {
					p("WEST ");
				}
				else if((path.get(i).getX()-(path.get(i+1).getX())>0)) {
					p("NORTH ");
				}
			}
		}
	}
}