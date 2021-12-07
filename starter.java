import java.util.ArrayList;

public class starter {
	public static void main(String[] args) {
		Grid g = new Grid(10,10);
		g.constructGrid();
		System.out.println("Success");
		g.print();
	}
}