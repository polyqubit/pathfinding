import java.util.Random;
import pkg.*;

public class Grid {
	public int width, height;
	public char[][] grid;
	Random rand = new Random();
	public Grid() {
		width = 10;
		height = 10;
	}
	public Grid(int width, int height) {
		this.width = width;
		this.height = height;
		if((width>9)&&(height>9)) {
			grid = new char[width][height];
		}
		else {grid = new char[10][10];}
	}
	public void print() {
		int m_scaleFactor =(int) 10/(1+width/75);
		Rectangle r;
		System.out.println("");
		for(int i=0;i<width+2;i++) {
			System.out.print("-");
		}
		System.out.println();
		for(int i=0;i<grid.length;i++) {
			System.out.print("|");
			for(int j=0;j<grid[i].length;j++) {
				System.out.print(grid[i][j]);
				switch(grid[i][j]) {
					case ' ':
						r = new Rectangle(j*m_scaleFactor, i*m_scaleFactor, m_scaleFactor, m_scaleFactor);
						r.setColor(new Color(100, 200, 255));
						r.fill();
					break;
					case '*':
						r = new Rectangle(j*m_scaleFactor, i*m_scaleFactor, m_scaleFactor, m_scaleFactor);
						r.setColor(new Color(0, 0, 0));
						r.fill();
					break;
					case 'O':
						r = new Rectangle(j*m_scaleFactor, i*m_scaleFactor, m_scaleFactor, m_scaleFactor);
						r.setColor(new Color(30, 255, 30));
						r.fill();
					break;
				}
			}
			System.out.println("|");
		}
		for(int i=0;i<width+2;i++) {
			System.out.print("-");
		}
		System.out.println("");
	}
	public void constructGrid() {
		//basically a maze generator, minus any skill
		for(int i=0;i<height;i++) {
			for(int j=0;j<width;j++) {
				grid[i][j] = rand.nextDouble()<0.8 ? ' ' : '*';
			}
		}
		grid[0][0] = ' ';
		grid[width-1][height-1] = ' ';
	}
}