package gameoflife;

public class DemoInsert {

	public static void main(String[] args) {
		boolean[][] glider = {
				{false, false, false, false, false},
				{false, true, false, false, false},
				{false, false, true, true, false},
				{false, true, true, false, false},
				{false, false, false, false, false}
		};
		
		boolean[][] cells = new boolean[50][50];
		Life2.insert(glider, 1, 1, cells);
		Viewer.init(cells);
		for (int i = 0; i < 1000; i++) {
			Life2.evolve(cells);
			Viewer.draw(cells);
		}
	}

}
