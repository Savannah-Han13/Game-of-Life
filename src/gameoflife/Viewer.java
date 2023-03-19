package gameoflife;

import princeton.introcs.StdDraw;

public class Viewer {
	public static final int MAX_LENGTH = 600;	
	
	public static void init(boolean[][] cells) {
		int rows = Life2.numRows(cells);
	    int cols = Life2.numCols(cells);
	    if (rows == 0 || cols == 0) {
	    	throw new IllegalArgumentException();
	    }
	    if (rows < cols) {
	    	StdDraw.setCanvasSize(MAX_LENGTH, rows * MAX_LENGTH / cols);
	    }
	    else {
	    	StdDraw.setCanvasSize(cols * MAX_LENGTH / rows, MAX_LENGTH);
	    }
	}
	
	public static void draw(boolean[][] cells) {
	    int rows = Life2.numRows(cells);
	    int cols = Life2.numCols(cells);
	    
	    StdDraw.setXscale(0, cols);
	    StdDraw.setYscale(0, rows);
	    StdDraw.clear(StdDraw.WHITE);
	    StdDraw.setPenColor(StdDraw.RED);
	    for (int r = 0; r < rows; r++) {
	    	for (int c = 0; c < cols; c++) {
	    		if (cells[r][c]) {
	    			StdDraw.filledSquare(c + 0.5, rows - r - 0.5, 0.5);
	    		}
	    	}
	    }
	    StdDraw.show(50);
	}

	public static void main(String[] args) {
		boolean c[][] = new boolean[50][50];
		c[2][0] = true;
		Viewer.draw(c);
	}

}
