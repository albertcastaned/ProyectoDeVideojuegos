
public class PizzeriaTipoChicago extends Pizzeria{

	@Override
	public Pizza crearPizza(String tipo) {
		Pizza pizza = null;
		
		if(tipo == "Queso")
			pizza = new PizzaCHQueso();
		else if(tipo == "Salchicha")
			pizza = new PizzaCHSalchicha();
		else if(tipo.equals("Vegetariana"))
			pizza = new PizzaCHVegetariana();
		return pizza;
	}

}
