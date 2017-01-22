package chapter1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 11559 - Event Planning

public class EventPlanning {

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		while(true) {
			String str = in.readLine();
			if(str==null || str.isEmpty()) break;
			String[] tokens = str.split(" ");
			int N = Integer.parseInt(tokens[0]);
			int B = Integer.parseInt(tokens[1]);
			int H = Integer.parseInt(tokens[2]);
			int W = Integer.parseInt(tokens[3]);
			
			int bestPrice = Integer.MAX_VALUE;
			
			for(int i=0; i<H; i++) {
				int p = Integer.parseInt(in.readLine());
				tokens = in.readLine().split(" ");
				int[] a = new int[W];
				for(int j=0; j<W; j++) {
					a[j] = Integer.parseInt(tokens[j]);
					
					if(a[j] >= N) {
						int thisWeek = N * p;
						if(thisWeek <= B && thisWeek < bestPrice) {
							bestPrice = thisWeek;
						}	
					}
				}
			}
			
			if(bestPrice != Integer.MAX_VALUE) {
				System.out.println(bestPrice);
			}
			else {
				System.out.println("stay home");
			}
		}
		
	}
	
}
