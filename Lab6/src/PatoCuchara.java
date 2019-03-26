
public class PatoCuchara extends Pato{
	public PatoCuchara(double a, double b)
	{
		super(a,b);
		comoQuack = new NoQuack();
		comoVuela = new NoVuela();
	}

}
