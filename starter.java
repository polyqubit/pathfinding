//import java.util.ArrayList;

public class starter {
	static void p(String s) {System.out.print(s);}
	public static void main(String[] args) {
		Grid g = new Grid(10,10);
		g.constructGrid();
		p("Success\n");
		g.print();
		int[] start = {0,0};
		int[] end = {g.width-1,g.height-1}; //redo to let user define
		Path.returnPath(g, new Node(start,0,0), end);
	}
}