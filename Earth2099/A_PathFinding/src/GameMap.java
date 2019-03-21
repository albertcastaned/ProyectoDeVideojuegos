
public class GameMap implements TiledBaseMap {

	//Dimensiones por TILE
	public static int TileWIDTH = Main.getVentanaAncho()/80;
	public static int TileHEIGHT = Main.getVentanaAltura()/80;
	
	//Tiles que tienen colision
	private int[][] terrain = new int[TileWIDTH][TileHEIGHT];
	//Tiles que ya han sido visitados en la busqueda A*
	private boolean[][] visited = new boolean[TileWIDTH][TileHEIGHT];
	
	public GameMap() {
		
		//Crear tiles del mapa
		
		for(int i=0;i<TileWIDTH;i++)
		{
			for(int j=0;j<TileHEIGHT;j++)
			{
				//Poner valor de terreno como 0 osea espacio libre
				terrain[i][j] = 0;
			}
		}
		//Poner estos tiles como bloques solidos
		terrain[0][5] = 1;
		terrain[1][4] = 1;
		terrain[1][3] = 1;
		terrain[1][2] = 1;
		terrain[1][1] = 1;
		

		terrain[2][4] = 1;
		terrain[3][4] = 1;
		terrain[4][4] = 1;
		terrain[5][4] = 1;
		terrain[5][5] = 1;
		terrain[5][6] = 1;
		terrain[5][7] = 1;
		terrain[4][8] = 1;
		terrain[5][8] = 1;
		terrain[7][4] = 1;
		terrain[7][5] = 1;
		terrain[7][6] = 1;
		terrain[7][7] = 1;

	}

	//Limpiar tiles visitados
	public void clearVisited() {
		for (int x=0;x<getWidthInTiles();x++) {
			for (int y=0;y<getHeightInTiles();y++) {
				visited[x][y] = false;
			}
		}
	}
	
	//Obtener si el tile ya ha sido visitado
	public boolean visited(int x, int y) {
		return visited[x][y];
	}
	
	
	//Obtener tipo de terreno del tile
	public int getTerrain(int x, int y) {
		return terrain[x][y];
	}
	
	//Obtener si un tile esta bloqueado
	public boolean blocked(int x, int y) {
		if(terrain[x][y] == 1)
			return true;
		return false;
	}
	
	
	@Override
	public int getWidthInTiles() {
		return TileWIDTH;
	}

	@Override
	public int getHeightInTiles() {
		return TileHEIGHT;
	}

	//Asignar tile como visitado
	@Override
	public void pathFinderVisited(int x, int y) {
		visited[x][y] = true;
	}



	//Obtener costo
	@Override
	public float getCost(int sx, int sy, int tx, int ty) {
		return 1;
	}
	

}
