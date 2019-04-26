import java.util.Scanner;

public class Main {

	
	public static void menu()
	{
		System.out.println("1.- Pizza Queso Nueva York");
		System.out.println("2.- Pizza Salchicha Nueva York");
		System.out.println("3.- Pizza Vegetariana Nueva York");
		System.out.println("4.- Pizza Queso Chicago");
		System.out.println("5.- Pizza Salchicha Chicago");
		System.out.println("6.- Pizza Vegetariana Chicago");
		
		System.out.println("7.- Salir");
	}
	public static void main(String args[])
	{
		@SuppressWarnings("resource")
		Scanner entrada = new Scanner(System.in);
		

		
		Pizzeria pizzeriaNY = new PizzeriaTipoNY();
		Pizzeria pizzeriaCH = new PizzeriaTipoChicago();
		
		
		int opcionPizzeria=1;
		while(opcionPizzeria !=7)
		{
			Pizza pizza = null;
			menu();
			opcionPizzeria = entrada.nextInt();
			switch(opcionPizzeria)
			{
			case 1:
				pizza = pizzeriaNY.ordenarPizza("Queso");
				break;
			case 2:
				pizza = pizzeriaNY.ordenarPizza("Salchicha");
				break;
			case 3:
				pizza = pizzeriaNY.ordenarPizza("Vegetariana");
				break;
			case 4:
				pizza = pizzeriaCH.ordenarPizza("Queso");
				break;
			case 5:
				pizza = pizzeriaCH.ordenarPizza("Salchicha");
				break;
			case 6:
				pizza = pizzeriaCH.ordenarPizza("Vegetariana");

			}
			if(pizza!=null)
				System.out.println("Aqui esta tu pizza " + pizza.toString());

		}

	}
}
