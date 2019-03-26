
public class Main {

	public static void main(String args[])
	{
		PatoCabezaRoja miPato1 = new PatoCabezaRoja(100,100);
		miPato1.hazQuack();
		miPato1.hazVuelo();
		miPato1.setComoVuela(new NoVuela());
		miPato1.setComoQuack(new NoQuack());
		miPato1.hazQuack();
		miPato1.hazVuelo();
	}
}
