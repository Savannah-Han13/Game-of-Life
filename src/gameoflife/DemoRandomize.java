package gameoflife;

public class DemoRandomize {

	public static void main(String[] args) {
		boolean[][] cells = new boolean[25][30];
		Life2.randomize(cells);
		Viewer.init(cells);
		for (int i = 0; i < 1000; i++) {
			Life2.evolve(cells);
			Viewer.draw(cells);
		}
	}

}
