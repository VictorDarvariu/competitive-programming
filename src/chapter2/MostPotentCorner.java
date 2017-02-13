package chapter2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MostPotentCorner {

	// 10264 - The Most Potent Corner
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		while(true) {
			String line = in.readLine();
			if(line==null || line.trim().equals("")) break;
			int N = Integer.parseInt(line);
			int numberCorners = (int) Math.pow(2, N);
			
			int[] potencies = new int[numberCorners];
			
			for(int i=0; i<numberCorners; i++) {
				int weight = Integer.parseInt(in.readLine());
				
				for(int k=0; k<N; k++) {
					int neighbour = i ^ (1 << k);
					potencies[neighbour] += weight;
				}
				
			}
			int maxPotencySum = 0;
			
			for(int i=0; i<numberCorners; i++) {
				int potency = potencies[i];
				
				for(int k=0; k<N; k++) {
					int neighbour = i ^ (1 << k);
					int currPotencySum = potencies[neighbour] + potency;
					if(currPotencySum > maxPotencySum) {
						maxPotencySum = currPotencySum;
					}
				}
				
			}
			System.out.println(maxPotencySum);
		}
		
	}
}
