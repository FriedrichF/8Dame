import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;


public class spielfeld {
	
	int[][] brettHeuristik = new int[8][8];
	boolean[][] brettDamen = new boolean[8][8];
	public int globalMaxima = 100;
	public int localMaxima = 1001;
	List<String> heuristikMinimum = new ArrayList<String>();
	
	public spielfeld(){
//		makeFeld();
	}
	
	public void calcGlobalMax(){
		int count = 0;
		for(int i = 0; i < 8; i++){
			for(int x = 0; x < 8; x++){
				if(brettDamen[i][x])
					count += brettHeuristik[i][x];
			}
		}
		globalMaxima = count;
	}
	
	public void searchHeuristikMin(){
		Random random = new Random();
		int randomKey = heuristikMinimum.size();
		String value = heuristikMinimum.get(random.nextInt(randomKey));
		
		String[] nextStep = value.split(",");
		
		makeStep(Integer.parseInt(nextStep[0]), Integer.parseInt(nextStep[1]));
	}
	
	private void makeStep(int reihe, int spalte) {
		for(int i = 0; i < 8; i++){
			if(brettDamen[reihe][i]){
				brettDamen[reihe][i] = false;
				brettHeuristik[reihe][i] = checkPosition(reihe, i);
			}
		}
		
		brettDamen[reihe][spalte] = true;
	}

	public void makeFeld(){
		for (boolean[] row: brettDamen)
			Arrays.fill(row, Boolean.FALSE);
		Random randomColumn = new Random();
		int randomValue = 0;
		for(int i = 0; i < 8; i++){
			randomValue = randomColumn.nextInt(7);
			brettDamen[i][randomValue] = true;
		}
	}
	
	public void setHeuristik(){
		int value = 0;
		for(int i = 0; i < 8; i++){
			for(int x = 0; x < 8; x++){
				value = checkPosition(i,x);
				brettHeuristik[i][x] = value;
				if(!brettDamen[i][x] && value == localMaxima){
					heuristikMinimum.add(i+","+ x);
				}else if(!brettDamen[i][x] && value < localMaxima){
					localMaxima = value;
					heuristikMinimum.clear();
					heuristikMinimum.add(i+","+x);
				}
			}
		}
	}
	
	private int checkPosition(int i, int x){
		int counter = 0;
		
		counter += countHorizontal(i,x);
		counter += countVertical(i,x);
		counter += countDia(i,x);
		
		return counter;
	}
	
	private int countDia(int reihe, int spalte) {
		int reiheHoch = reihe;
		int reiheRunter = reihe;
		int count = 0;
		
		for(int nextSpalte = spalte+1; nextSpalte < 8; nextSpalte++){
			if(reiheHoch < 7 && brettDamen[++reiheHoch][nextSpalte])
				count++;
			if(reiheRunter > 0 && brettDamen[--reiheRunter][nextSpalte])
				count++;
		}
		
		reiheHoch = reihe;
		reiheRunter = reihe;
		
		for(int nextSpalte = spalte-1; nextSpalte >= 0; nextSpalte--){
			if(reiheHoch < 7 && brettDamen[++reiheHoch][nextSpalte])
				count++;
			if(reiheRunter > 0 && brettDamen[--reiheRunter][nextSpalte])
				count++;
		}
		
		return count;
	}

	private int countVertical(int reihe, int spalte) {
		int count = 0;
		for(int i = 0; i < 8; i++){
			//Pruefen ob das feld eine Dame enthaelt und nicht das eigene Feld ist
			if(i != reihe && brettDamen[i][spalte])
				count++;
		}
		return count;
	}

	private int countHorizontal(int reihe, int spalte) {
		int count = 0;
		for(int i = 0; i < 8; i++){
			//Pruefen ob das feld eine Dame enthaelt und nicht das eigene Feld ist
			if(i != spalte && brettDamen[reihe][i])
				count++;
		}
		return count;
	}

	public void printBrett(boolean[][] brett){
		System.out.println("---------------------------------");
		for(int i = 0; i < 8; i++){
			System.out.print("|");
			for(int x = 0; x < 8; x++){
				if(brett[i][x])
					System.out.print(" D |");
				else
					System.out.print("   |");
			}
			System.out.println();
			System.out.println("---------------------------------");
		}
	}
	
	public void printBrettHeuristik(int[][] brett){
		System.out.println("---------------------------------");
		for(int i = 0; i < 8; i++){
			System.out.print("|");
			for(int x = 0; x < 8; x++){
				System.out.print(" " + brett[i][x] + " |");
			}
			System.out.println();
			System.out.println("---------------------------------");
		}
	}
	
}
