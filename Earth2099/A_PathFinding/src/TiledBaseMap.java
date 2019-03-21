


//Tipo de mapa dividio en cuadros / cuadricula
public interface TiledBaseMap {
	

	//Numero de tiles en ancho
	public int getWidthInTiles();

	//Numero de tiles en ancho
	public int getHeightInTiles();
	
	//Asignar tile en posicion x,y como visitado
	public void pathFinderVisited(int x, int y);
	
	//Checar si tile no esta bloqueado por objeto de colision solido
	public boolean blocked(int x, int y);
	
	//Obtener costo de llegar a este tile usado para A*
	public float getCost(int sx, int sy, int tx, int ty);

}
