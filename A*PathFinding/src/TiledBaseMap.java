
public interface TiledBaseMap {
	

	public int getWidthInTiles();


	public int getHeightInTiles();
	

	public void pathFinderVisited(int x, int y);
	

	public boolean blocked(int x, int y);
	

	public float getCost(int sx, int sy, int tx, int ty);

}
