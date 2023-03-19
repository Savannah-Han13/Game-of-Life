package gameoflife;

import java.util.Random;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class Life2 {

	/**
	 * This module provides functions for the 2-dimensional Game of Life.
	 * 
	 * The 2-dimensional Game of Life occurs on an n-by-n array of
	 * cells where each cell is either alive or dead. The population of cells
	 * evolves from one generation to the next according to three rules:
	 * 
	 * 1. Any live cell survives into the next generation if it has 2 or 3
	 * living neighbours in its 1-neighborhood (the cell itself does not
	 * count towards the number of living neighbors).
	 * 2. Any dead cell becomes alive in the next generation if it has 3
	 * living neighbours in its 1-neighborhood.
	 * 3. All other live cells die, and all other dead cells remain dead.
	 * 
	 * The rules are applied to each cell simultaneously to compute the
	 * next generation of cells.
	 * 
	 * The 1-neighborhood of a cell consists of the cell itself and its
	 * eight neighbours, which are the cells that are horizontally, vertically,
	 * or diagonally adjacent (if those neighbors exist).
	 */

	/**
	 * Name: Savannah
	 * Student #: 20350667
	 * Course: CISC124
	 */

	/**
	 * Returns the number of rows in a two-dimensional array of cells.
	 * @param cells : list-of-list of boolean
	 * 		The cells of a 2D Game of Life.
	 * @return the number or rows in 'cells'.
	 */
	public static int numRows(boolean[][] cells) {
		return cells.length;
	}
	/**
	 * Returns the number of columns in a two-dimensional array of cells.
	 * 
	 * @param cells: 2D array of boolean
	 *  		The cells of a 2D Game of Life.
	 * @return the number or columns in 'cells'.
	 */
	public static int numCols(boolean[][] cells) {
		if (numRows(cells) > 0) {
			return cells[0].length;
		}
		return 0;
	}
	/**
	 * Returns True if row and col are valid indexes for the two-dimensional
	 * array of cells.
	 * 
	 * @param cells : 2D array of boolean
	 * 		The cells of a 2D Game of Life.
	 * @param row : int
	 * 		A row index.
	 * @param col : int
	 * 		A column index.
	 * @return boolean
	 * 		True if row and col are valid indexes for the two-dimensional
	 * 		list of cells.
	 */
	public static boolean isValid(boolean[][] cells, int row, int col) {
		if (row < 0 || row >= numRows(cells)) {
			return false;
		}
		if (col < 0 || col >= numCols(cells)) {
			return false;
		}
		return true;
	}
	/**
	 * Returns a new two-dimensional array that is equal to the two-dimensional list
	 * 'cells'.
	 *  
	 * The returned list has the same number of rows and columns as `cells`
	 * and each element of the returned list is equal to the corresponding
	 * element in 'cells'.
	 *   
	 * @param cells : 2D array of boolean
	 * 		The cells of a 2D Game of Life.
	 * @return array
	 * 		A 2D array equal to `cells`
	 */
	public static boolean[][] clone(boolean[][]cells){	
		int rows = numRows(cells);
		int cols = numCols(cells);
		// make a two-dimensional list having rows rows and cols columns
		// all values are set to False
		//
		// start with an empty list
		boolean[][] copy = new boolean[rows][cols];
		for (int i = 0; i < rows; i++) {
			for(int j = 0; j < cols; j++) {
				copy[i][j] = false;
			}
		}

		// copy the values from cells into copy
		for (int r = 0; r < rows; r++) {
			for(int c = 0; c < cols; c++) {
				copy[r][c] = cells[r][c];
			}
		}
		return copy;
	}
	/**
	 * Prints the cells of a 2D Game of Life followed by a new line where the
	 * alive cells are represented as # and the dead cells are represented
	 * as - with no separator between cells. Each row of `cells` is printed
	 * on a separate line.
	 * 
	 * @param cells : 2D array of boolean
	 * 		The n-by-n cells of a 2D Game of Life.
	 */
	public static void printCells(boolean[][]cells) {
		for (boolean[] row: cells) {
			for (boolean c: row) {
				if (c) {
					System.out.print("# ");
				}
				else {
					System.out.print("- ");
				}
			}
			System.out.println();
		}
	}
	/**
	 * Return the 1-neighborhood of cells around a specified cell.
	 * The 1-neighborhood of a cell consists of cells[row][col] and its
	 * eight neighbours, which are the cells that are horizontally, vertically,
	 * or diagonally adjacent (if those neighbors exist).
	 * 
	 * @param cells : 2D array of boolean
	 * 		The n-by-n cells of a 2D Game of Life.
	 * @param row : int
	 * 		The row index of the cell to get the neigborhood of.
	 * @param col : int
	 * 		The column index of the cell to get the neigborhood of.
	 * @return list of boolean
	 * 		An array containing the cells in the 1-neigborhood of cells
	 * 		around a specified cell.
	 * @throws ValueError
	 * 		If 'row' or 'col' is not a valid non-negative index for cells
	 */
	public static boolean[][] neighbourhood(boolean[][] cells, int row, int col) {
		if (!isValid(cells, row, col)) {
			throw new IllegalArgumentException();
		}
		// make a 3x3 to store the neighborhood
		boolean[][]nhood = {{false, false, false},
				{false, false, false},
				{false, false, false}};
		// upper-left indexes of the neighborhood
		// these might be out of bounds
		int left = col - 1;
		int top = row - 1;
		// get the neighbourhood
		for (int r = 0; r < 3; r++) {
			// row index for cells
			int cellsRow = top + r;
			for (int c = 0; c < 3; c++) {
				// column index for cells
				int cellsCol = left + c;
				if (isValid(cells, cellsRow, cellsCol)) {
					nhood[r][c] = cells[cellsRow][cellsCol];
				}
			}
		}
		return nhood;
	}
	/**
	 * Returns true if the specified cell is alive.
	 * 
	 * @param cells a two-dimensional array
	 * @param row a row index
	 * @param col a column index
	 * @return true if the specified cell is alive
	 * @throws IllegalArgumentException if row or col is not a valid index for
	 *                                   cells
	 */
	public static boolean isAlive(boolean[][] cells, int row, int col) {
		if (!Life2.isValid(cells, row, col)) {
			throw new IllegalArgumentException();
		}
		return cells[row][col];
	}
	/**
	 * Return True if a specified cell is alive.
	 * 
	 * Returns True if cells[row][col] is True, and False otherwise.
	 * 
	 * @param cells: 2D array of bool
	 * 		The cells of a 2D Game of Life.
	 * @param row : int
	 * 		The row index of the cell.
	 * @param col : int
	 * 		The column index of the cell.
	 * @return boolean
	 * 		True if cells[row][col] is True, and False otherwise.
	 * @throws ValueError
	 * 		If 'row' or 'col' is not a valid non-negative index for cells
	 */
	public static int numAlive(boolean[][] cells) {
		int nAlive = 0;
		for (boolean[] row: cells) {
			for (boolean elem: row) {
				if (elem) {
					nAlive ++;
				}
			}
		}
		return nAlive;
	}
	/**
	 * Returns true if the cell {@code cells[row][col]} becomes alive in the next
	 * generation, false otherwise. The method does not modify the array; it
	 * simply determines whether or not a cell should become alive in the 
	 * next generation.
	 * 
	 * <p>
	 * A cell becomes alive if it is currently dead and its neighborhood has
	 * exactly 3 alive cells.
	 * 
	 * @param cells a two-dimensional array
	 * @param row   a row index
	 * @param col   a column index
	 * @return true if the cell {@code cells[row][col]} becomes alive in the next
	 *         generation, false otherwise
	 * @throws IllegalArgumentException if row or col is not a valid index for
	 *                                   cells
	 */
	public static boolean isBorn(boolean[][] cells, int row, int col) {
		if (!isValid(cells, row, col)) {
			throw new IllegalArgumentException();
		}
		if (cells[row][col]) {
			return false;
		}
		boolean[][] neighbourhood = neighbourhood(cells, row, col);
		int countAlive = numAlive(neighbourhood);
		return countAlive == 3;
	}

	/**
	 * Returns true if the cell {@code cells[row][col]} survives into the next
	 * generation, false otherwise. The method does not modify the array; it
	 * simply determines whether or not a cell should remain alive in the 
	 * next generation.
	 * 
	 * <p>
	 * A cell survives into the next generation if it is currently alive and if its
	 * 8 neighbors have 2 or 3 alive cells.
	 * 
	 * @param cells a two-dimensional array
	 * @param row   a row index
	 * @param col   a column index
	 * @return true if the cell {@code cells[row][col]} survives into the next
	 *         generation, false otherwise
	 * @throws IllegalArgumentException if row or col is not a valid index for
	 *                                   cells
	 */
	public static boolean survives(boolean[][] cells, int row, int col) {
		if (!isValid(cells, row, col)) {
			throw new IllegalArgumentException();
		}
		if (!cells[row][col]) {
			return false;
		}
		boolean[][] neighbourhood = neighbourhood(cells, row, col);
		int countAlive = numAlive(neighbourhood);
		return countAlive == 3 || countAlive == 4;
	}
	/**
	 * Updates {@code cells} so that it is equal to the next generation of cells.
	 * 
	 * <p>
	 * See the assignment document for details.
	 * 
	 * @param cells a two-dimensional array
	 */
	public static void evolve(boolean[][] cells) {
		boolean[][] passingGeneration = clone(cells);
		for (int r = 0; r < numRows(cells); r++) {
			for (int c = 0; c < numCols(cells); c++) {
				cells[r][c] = survives(passingGeneration, r, c) || isBorn(passingGeneration, r, c);
			}
		}
	}
	/**
	 * Randomly sets each element of {@code cells} to true or false with
	 * equal probability.
	 * 
	 * @param cells a two-dimensional array
	 */
	public static void randomize(boolean[][] cells) {
		Random tf = new Random();
		for (int r = 0; r < numRows(cells); r++) {
			for (int c = 0; c < numCols(cells); c++) {
				cells[r][c] = tf.nextBoolean();
			}
		}
	}
	/**
	 * Inserts a pattern of cells into another array of cells. The pattern replaces
	 * the elements in {@code cells} starting at {@code cells[row][col]}. The
	 * pattern must fit completely within the array {@code cells}, otherwise no
	 * cells are replaced and false is returned.
	 * 
	 * @param pattern a 2d array of replacement cells
	 * @param row     the row index of the upper-left corner of cells where the
	 *                replacement should begin
	 * @param col     the column index of the upper-left corner of cells where the
	 *                replacement should begin
	 * @param cells   a 2d array of cells
	 * @return true if the pattern fits within cells, false otherwise
	 * @throws IllegalArgumentException if row or col is not a valid index for cells
	 */
	public static boolean insert(boolean[][] pattern, int row, int col, boolean[][] cells) {
		int patternRows = pattern.length;
		int patternCols = pattern[0].length;

		if (!isValid(cells, row, col)) {
			throw new IllegalArgumentException();
		}
		else {
			if (!isValid(cells, row + patternRows - 1, col) || !isValid(cells, row, col + patternCols - 1)) {
				return false;
			}
			else {
				for (int r = 0; r < patternRows; r++) {
					for(int c = 0; c < patternCols; c++) {
						cells[row + r][col + c] = pattern[r][c];
					}
				}
				return true;
			}
		}

	}
	/**
	 * Reads a pattern of cells from a file. The pattern format is identical
	 * to the output of {@code printCells}. The pattern files must be located
	 * in the {@code patterns} folder of the eclipse project.
	 * 
	 * @param filename the filename of a pattern file
	 * @return a 2d array of cells
	 */
	public static boolean[][] read(String filename) {
		try {
			Path path = FileSystems.getDefault().getPath("patterns", filename);
			List<String> lines = Files.readAllLines(path);
			// your code starts here
			int linesRow = lines.size();
			int linesCol = lines.get(0).length();
			boolean[][] pattern = new boolean[linesRow][linesCol];

			char aliveSymbol = '#';

			for (int r = 0; r < linesRow; r++) {
				String curCellRow = lines.get(r);
				for (int c = 0; c < linesCol; c++) {
					char curCell = curCellRow.charAt(c);
					if (curCell == aliveSymbol) {
						pattern[r][c] = true;
					}
					else {
						pattern[r][c] = false;
					}
				}
			}
			return pattern;
			// your code ends here
		} catch (Exception ex) {
			// some sort of error occurred while reading the file
			ex.printStackTrace();
			System.exit(1);
		}
		return null;
	}
}
