import java.util.ArrayList;

public class starter {
	static void p(String s) {System.out.print(s);}
	public static void main(String[] args) {
		Grid g = new Grid(10,10);
		g.constructGrid();
		p("Success\n");
		g.print();
		if(Path.isSolvable(g)) {
		p("\nMaze has a solution\n");
		} else {p("\nUnsolvable\n");}
	}
}