
public class Pato {
	
	protected ComoVuela comoVuela;
	protected ComoQuack comoQuack;
	protected double size,weight;
	
	public Pato(double size, double weight)
	{
		this.size = size;
		this.weight = weight;
	}
	
	public void hazQuack()
	{
		comoQuack.quack();
	}
	public void hazVuelo()
	{
		comoVuela.vuela();
	}
	
	public void setComoVuela(ComoVuela comoVuela)
	{
		this.comoVuela = comoVuela;
	}
	public void setComoQuack(ComoQuack comoQuack)
	{
		this.comoQuack = comoQuack;
	}


}
