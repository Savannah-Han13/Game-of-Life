package gameoflife;

public class DemoRead {

	public static void main(String[] args) {
		boolean[][] pattern = Life2.read("switch_engine.txt");
		
		boolean[][] cells = new boolean[100][100];
		Life2.insert(pattern, 30, 30, cells);
		Viewer.init(cells);
		for (int i = 0; i < 1000; i++) {
			Life2.evolve(cells);
			Viewer.draw(cells);
		}
	}

}
