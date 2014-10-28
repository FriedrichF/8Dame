
public class solver {

	public static void main(String[] args) {
		int zaehler = 0;
		spielfeld spielfeld_ = new spielfeld();
		while(spielfeld_.globalMaxima > 0){
			zaehler++;
			spielfeld_.makeFeld();
			int localMax = spielfeld_.localMaxima;
			do{
				localMax = spielfeld_.localMaxima;
				spielfeld_.setHeuristik();
				spielfeld_.searchHeuristikMin();
				spielfeld_.setHeuristik();
				spielfeld_.calcGlobalMax();
				
//				spielfeld_.printBrett(spielfeld_.brettDamen);
//				spielfeld_.printBrettHeuristik(spielfeld_.brettHeuristik);
			}while(localMax != spielfeld_.localMaxima);
		}
		
		spielfeld_.printBrett(spielfeld_.brettDamen);
		spielfeld_.printBrettHeuristik(spielfeld_.brettHeuristik);
		System.out.println(zaehler);
	}

}
