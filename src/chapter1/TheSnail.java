package chapter1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;

//573 - The Snail

public class TheSnail {

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		List<Integer> statuses = new LinkedList<>();
		while(true) {
			String[] tokens = in.readLine().split(" ");
			double H = Double.parseDouble(tokens[0]);
			double U = Double.parseDouble(tokens[1]);
			double D = Double.parseDouble(tokens[2]);
			double F = Double.parseDouble(tokens[3]);
			
			if(H==0) break;
			int currentDay = 1;
			double currentHeight = 0;
			double climb = U;
			double fatigue = (U * F) / 100;
			
			while(true) {
				if(climb > 0) currentHeight += climb;
				if(currentHeight > H) {
					statuses.add(currentDay);
					break;
				}
				
				currentHeight -= D;
				if(currentHeight < 0) {
					statuses.add(-currentDay);
					break;
				}
				
				currentDay++;
				climb = climb - fatigue;
			}
			
		}
		printResults(statuses);
	}
	
	public static void printResults(List<Integer> statuses) {
		for (Integer status : statuses) {
			if (status < 0) {
				System.out.printf("failure on day %d\n", -status);
			}
			else {
				System.out.printf("success on day %d\n", status);
			}
		}
	}
	
}