package gameoflife;

public class DemoEvolve {
	public static void main(String[] args) {
		boolean[][] cells = { 
				{ true, false, true, false, true }, 
				{ false, true, false, true, false },
				{ true, true, true, true, true }, 
				{ false, true, false, true, false },
				{ true, false, true, false, true }, 
				{ false, true, false, true, false } };
		for (int gen = 1; gen <= 5; gen++) {
			System.out.println("Generation " + gen);
			Life2.printCells(cells);
			Life2.evolve(cells);
			System.out.println();
		}
	}
}
