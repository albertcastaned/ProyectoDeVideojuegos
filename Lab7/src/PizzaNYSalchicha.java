
public class PizzaNYSalchicha extends Pizza{
	public PizzaNYSalchicha()
	{
		masa = "delgada";
		salsa = "albahaca";
		queso = "gouda";
	}

	@Override
	public void preparar() {
		System.out.println("Preparando ingredientes, poner salsa y poco queso");
	}

	@Override
	public void hornear() {
		System.out.println("Horneando, 15 minutos");
		
	}

	@Override
	public void cortar() {
		System.out.println("Cortando en 8");
		
	}

	@Override
	public void empacar() {
		System.out.println("Empacando");
		
	}
}
