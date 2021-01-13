
public class quaCubo {
	
	public static void main(String[] args) {
		
		Ritorno[] r = new Ritorno[3];
		
		r[0] = new Numero();
		r[1] = new Quadrato();
		r[2] = new Cubo();
		
		for(Ritorno x : r) {
			System.out.println(x.getRitorno(2));
		}
	}

}
