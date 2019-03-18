
public class GameMap implements TiledBaseMap {

	public static int WIDTH = 10;
	public static int HEIGHT = 10;
	private int[][] terrain = new int[WIDTH][HEIGHT];
	private boolean[][] visited = new boolean[WIDTH][HEIGHT];
	
	public GameMap() {
		// create some test data

		for(int i=0;i<WIDTH;i++)
		{
			for(int j=0;j<HEIGHT;j++)
			{
				terrain[i][j] = 1;
			}
		}
		terrain[5][5] = 0;
		terrain[5][6] = 0;
		terrain[5][7] = 0;
		terrain[4][8] = 0;

	}

	public void clearVisited() {
		for (int x=0;x<getWidthInTiles();x++) {
			for (int y=0;y<getHeightInTiles();y++) {
				visited[x][y] = false;
			}
		}
	}
	public boolean visited(int x, int y) {
		return visited[x][y];
	}
	
	public int getTerrain(int x, int y) {
		return terrain[x][y];
	}
	
	public boolean blocked(int x, int y) {
		// if theres a unit at the location, then it's blocked

		if(terrain[x][y] == 0)
			return true;
		return false;
	}
	@Override
	public int getWidthInTiles() {
		// TODO Auto-generated method stub
		return WIDTH;
	}

	@Override
	public int getHeightInTiles() {
		// TODO Auto-generated method stub
		return HEIGHT;
	}

	@Override
	public void pathFinderVisited(int x, int y) {
		// TODO Auto-generated method stub
		visited[x][y] = true;
	}



	@Override
	public float getCost(int sx, int sy, int tx, int ty) {
		return 1;
	}
	

}
