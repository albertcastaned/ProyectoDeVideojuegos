import java.io.IOException;

public class GameMap implements TiledBaseMap {

	//Dimensiones por TILE 80x80
	public static int TileWIDTH = 8000/80;
	public static int TileHEIGHT = 8000/80;
	
	//Tiles que tienen colision
	private int[][] terrain = new int[TileWIDTH][TileHEIGHT];
	//Tiles que ya han sido visitados en la busqueda A*
	private boolean[][] visited = new boolean[TileWIDTH][TileHEIGHT];
	
	//Clase que generara el mapa
	private MapGenerator mapGenerator;
	public GameMap() {
		
		try {
			mapGenerator = new MapGenerator();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//Crear tiles del mapa copiando la matriz del map generator
		terrain = mapGenerator.placeTile();



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
	
	
	//Regresar dimensiones de tiles
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
