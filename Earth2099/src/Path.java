import java.util.ArrayList;


//Determina camino que seguira el enemigo despues de hacer la busqueda A*
public class Path {
	
	//Lista de pasos a seguir
	@SuppressWarnings("rawtypes")
	private ArrayList steps = new ArrayList();

	public Path() {
		//No necesita iniciar nada
	}
	
	//Regresar camino
	@SuppressWarnings("unchecked")
	public ArrayList<Step> getSteps()
	{
		return steps;
	}

	//Longitud de camino
	public int getLength() {
		return steps.size();
	}
	
	//Obtener un paso de la lista
	public Step getStep(int index) {
		return (Step) steps.get(index);
	}
	
	//Posicion de step
	public int getX(int index) {
		return getStep(index).x;
	}


	public int getY(int index) {
		return getStep(index).y;
	}

	//Agregar a la lista
	@SuppressWarnings("unchecked")
	public void appendStep(int x, int y) {
		steps.add(new Step(x,y));
	}

	//Agregar a primera posicion de la lista
	@SuppressWarnings("unchecked")
	public void prependStep(int x, int y) {
		steps.add(0, new Step(x, y));
	}

	public boolean contains(int x, int y) {
		return steps.contains(new Step(x,y));
	}



	//Clase de un punto en el mapa
	public class Step {
		private int x;
		private int y;
		

		public Step(int x, int y) {
			this.x = x;
			this.y = y;
		}

		public int getX() {
			return x;
		}

	
		public int getY() {
			return y;
		}

		public int hashCode() {
			return x*y;
		}


		public boolean equals(Object other) {
			if (other instanceof Step) {
				Step o = (Step) other;
				
				return (o.x == x) && (o.y == y);
			}
			
			return false;
		}
	}
}
