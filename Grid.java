import java.io.*;
import java.util.ArrayList;
import java.util.Random;

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
		for(int i=0;i<width;i++) {
			System.out.print("-");
		}
		System.out.println();
		for(int i=0;i<grid.length;i++) {
			for(int j=0;j<grid[i].length;j++) {
				System.out.print(grid[i][j]);
			}
			System.out.println("|");
		}
		for(int i=0;i<width;i++) {
			System.out.print("-");
		}
		System.out.println("");
	}
	public void constructGrid() {
		//basically a maze generator, minus any skill
		for(int i=0;i<height;i++) {
			for(int j=0;j<width;j++) {
				grid[i][j] = rand.nextDouble()<0.7 ? ' ' : 'x';
			}
		}
		grid[0][0] = ' ';
    grid[width-1][height-1] = ' ';
	}
}