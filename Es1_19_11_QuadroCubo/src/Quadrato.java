
public class Quadrato implements Ritorno{
	
	private int n;
	
	public Quadrato() {}
	
	public int getRitorno(int p) {
		this.n = p*p;
		return this.n;
	}

}
