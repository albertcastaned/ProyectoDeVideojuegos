import java.util.ArrayList;
import java.util.Collections;

//Clase que busca los nodos de una cuadricula para buscar la mejor ruta hasta el destino
public class AStarSearch {
	
	//Lista de nodos que ya han sido visitados
	private ArrayList<Node> closed = new ArrayList<Node>();
	//Lista de nodos que pueden ser visitados
	private SortedList open = new SortedList();
	
	private GameMap map;
	private Node[][] nodes;
	
	private int maxSearchDistance = 60;
	
	//Crear nodos de busqueda basados en el mapa dado
	public AStarSearch(GameMap map)
	{
		this.map = map;
		nodes = new Node[map.getWidthInTiles()][map.getHeightInTiles()];
		for (int x=0;x<map.getWidthInTiles();x++) {
			for (int y=0;y<map.getHeightInTiles();y++) {
				nodes[x][y] = new Node(x,y);
			}
		}
	}
	
	public void clear()
	{
		open.clear();
		closed.clear();
		nodes = new Node[map.getWidthInTiles()][map.getHeightInTiles()];
		for (int x=0;x<map.getWidthInTiles();x++) {
			for (int y=0;y<map.getHeightInTiles();y++) {
				nodes[x][y] = new Node(x,y);
			}
		}
	}
	//Regresa un camino dado los puntos iniciales y los puntos destino
	public Path findPath(int sx, int sy, int tx, int ty) {
		
		//Si el destino esta bloqueado, entonces no hay forma de llegar
		if (map.blocked(tx, ty)) {
			return null;
		}
		
		//Iniciar
		nodes[sx][sy].cost = 0;
		nodes[sx][sy].depth = 0;
		closed.clear();
		open.clear();
		open.add(nodes[sx][sy]);
		
		nodes[tx][ty].parent = null;

		int maxDepth = 0;

		//Mientras no haya nodos por visitar
		while ((maxDepth < maxSearchDistance) && (open.size() != 0)) {
			
			//Obtener primer nodo de la lista open

			Node current = getFirstInOpen();
			if (current == nodes[tx][ty]) {
				break;
			}
			//Quitar de open, poner al closed ya que ya fue visitado
			removeFromOpen(current);
			addToClosed(current);
			
			//Visitar los nodos vecinos
			boolean hasSolidNeighbour = false;
			for (int x=-1;x<2;x++) {
				for (int y=-1;y<2;y++) {
					
					
					//Ignorar el nodo actual
					if ((x == 0) && (y == 0)) {
						continue;
					}


					if (hasSolidNeighbour) {
						if ((x != 0) && (y != 0)) {
							continue;
						}
					}


					// Obtener posicion de el nodo vecino
					int xp = x + current.x;
					int yp = y + current.y;

					//Checar si es un tile valido
					if (isValidLocation(sx,sy,xp,yp)) {

						//Costo nuevo es el costo actual mas el costo de llegar al nodo nuevo
						
						float nextStepCost = current.cost + getMovementCost(current.x, current.y, xp, yp);
						Node neighbour = nodes[xp][yp];

						map.pathFinderVisited(xp, yp);
						//Si el nuevo costo determinado es menor al que se determino
						if (nextStepCost < neighbour.cost) {
							if (inOpenList(neighbour)) {
								removeFromOpen(neighbour);
							}
							if (inClosedList(neighbour)) {
								removeFromClosed(neighbour);
							}
						}

						if (!inOpenList(neighbour) && !(inClosedList(neighbour))) {
							neighbour.cost = nextStepCost;
							neighbour.heuristic = getHeuristicCost(xp, yp, tx, ty);
							maxDepth = Math.max(maxDepth, neighbour.setParent(current));
							addToOpen(neighbour);
						}
					}else {
						hasSolidNeighbour = true;
					}
				}
			}
		}

		// Si no se encontro camino

		if (nodes[tx][ty].parent == null) {
			return null;
		}

		
		//Si se encontro camino crear clase Path con los pasos
		Path path = new Path();
		Node target = nodes[tx][ty];
		while (target != nodes[sx][sy]) {
			path.prependStep(target.x, target.y);
			target = target.parent;
			

		}
		path.prependStep(sx,sy);
		
		// Regresar camino
		return path;
	}

	//Obtener primer nodo de lista open
	protected Node getFirstInOpen() {
		return (Node) open.first();
	}
	//Agregar a Open
	protected void addToOpen(Node node) {
		open.add(node);
	}
	//Nodo esta en open
	protected boolean inOpenList(Node node) {
		return open.contains(node);
	}
	
	//Quitar de open
	protected void removeFromOpen(Node node) {
		open.remove(node);
	}
	//Quitar de Closed
	protected void addToClosed(Node node) {
		closed.add(node);
	}
	//Esta en closed
	protected boolean inClosedList(Node node) {
		return closed.contains(node);
	}
	//Quitr de closed
	protected void removeFromClosed(Node node) {
		closed.remove(node);
	}
	
	//Lugar es valido
	protected boolean isValidLocation(int sx, int sy, int x, int y) {
		boolean invalid = (x < 0) || (y < 0) || (x >= map.getWidthInTiles()) || (y >= map.getHeightInTiles());
		
		if ((!invalid) && ((sx != x) || (sy != y))) {
			invalid = map.blocked(x, y);
		}
		
		return !invalid;
	}
	
	//Obtener costo
	public float getMovementCost(int sx, int sy, int tx, int ty) {
		return map.getCost(sx, sy, tx, ty);
	}
	
	
	//Obtener costo distancia hipotenusa
	public float getHeuristicCost(int x, int y, int tx, int ty) {
		   int dx = tx - x;
		   int dy = ty - y;

		   return (float) Math.sqrt((dx*dx) + (dy*dy));
	}
	
	//Lista sorteada
	private class SortedList {
		private ArrayList<Node> list = new ArrayList<Node>();

		public Object first() {
			return list.get(0);
		}

		public void clear() {
			list.clear();
		}
		
		@SuppressWarnings("unchecked")
		public void add(Object o) {
			list.add((Node) o);
			Collections.sort(list);
		}

		public void remove(Object o) {
			list.remove(o);
		}

		public int size() {
			return list.size();
		}
		
		public boolean contains(Object o) {
			return list.contains(o);
		}
	}
	
	
	//Clase nodo
	@SuppressWarnings("rawtypes")
	private class Node implements Comparable {
		private int x;
		private int y;
		private float cost;
		private Node parent;
		private float heuristic;
		private int depth;

		public Node(int x, int y) {
			this.x = x;
			this.y = y;
		}
		
		public int setParent(Node parent) {
			depth = parent.depth + 1;
			this.parent = parent;
			
			return depth;
		}

		public int compareTo(Object other) {
			Node o = (Node) other;
			
			float f = heuristic + cost;
			float of = o.heuristic + o.cost;
			
			if (f < of) {
				return -1;
			} else if (f > of) {
				return 1;
			} else {
				return 0;
			}
		}
	}
		


}
