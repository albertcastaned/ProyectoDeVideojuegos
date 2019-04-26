
public abstract class Pizza {

	protected String masa;
	protected String salsa;
	protected String queso;
	
	public abstract void preparar();
	public abstract void hornear();
	public abstract void cortar();
	public abstract void empacar();
	
	@Override
	public String toString()
	{
		return "Pizza [masa = " + masa + ", salsa = " + salsa + ", queso = " + queso + "]";
	}
}
