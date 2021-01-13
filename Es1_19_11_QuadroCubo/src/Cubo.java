
public class Cubo implements Ritorno{
	
	private int n;
	
	public Cubo() {}
	
	public int getRitorno(int p) {
		this.n = p*p*p;
		return this.n;
	}

}
