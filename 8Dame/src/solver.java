import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;


public class solver {

	public static void main(String[] args) {
		List<Integer> globalMaximas = new ArrayList<Integer>();
		int zaehler = 0;
		int localMax;
		spielfeld spielfeld_ = new spielfeld();
		while(spielfeld_.globalMaxima > 0){
			zaehler++;
			spielfeld_.makeFeld();
			localMax = 100;
			
			spielfeld_.setHeuristik();
			
			do{
				localMax = spielfeld_.globalMaxima;
				spielfeld_.searchHeuristikMin();
				spielfeld_.setHeuristik();
				spielfeld_.calcGlobalMax();
				
//				System.out.println(spielfeld_.globalMaxima);
//				spielfeld_.printBrett(spielfeld_.brettDamen, spielfeld_.brettHeuristik);
			}while(localMax > spielfeld_.globalMaxima);
			globalMaximas.add(localMax);
		}
		
		spielfeld_.printBrett(spielfeld_.brettDamen, spielfeld_.brettHeuristik);
		System.out.println(zaehler);
		
//		PrintWriter pWriter = null; 
//        try { 
//            pWriter = new PrintWriter(new BufferedWriter(new FileWriter("maximas.txt"))); 
//            for(int wert:globalMaximas){
//            	pWriter.println(wert); 
//            	pWriter.println("100"); 
//            }
//        } catch (IOException ioe) { 
//            ioe.printStackTrace(); 
//        } finally { 
//            if (pWriter != null){ 
//                pWriter.flush(); 
//                pWriter.close(); 
//            } 
//        } 
	}

}
