package chapter1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class RequestForPurchase {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int rfp = 0;
		while(true) {
			rfp++;
			String[] tokens = in.readLine().split(" ");
			int n = Integer.parseInt(tokens[0]);
			int p = Integer.parseInt(tokens[1]);
			if(n==0 && p==0) break;
			
			for(int i=0; i<n; i++) in.readLine();
			
			Double bestPrice = Double.MAX_VALUE;
			Integer bestItems = 0;
			String bestName = "";
			
			for(int i=0; i<p; i++) {
				String name = in.readLine();
				tokens = in.readLine().split(" ");
				Double price = Double.parseDouble(tokens[0]);
				Integer numberItems = Integer.parseInt(tokens[1]);
				
				for(int j=0; j<numberItems; j++) in.readLine();
				
				if(numberItems > bestItems) {
					bestItems = numberItems;
					bestPrice = price;
					bestName = name;
				}
				else if (numberItems == bestItems) {
					if(price < bestPrice) {
						bestItems = numberItems;
						bestPrice = price;
						bestName = name;
					}
				}
			}

			if(rfp > 1) System.out.println();
			System.out.println("RFP #" + rfp);
			System.out.println(bestName);
		}
	}
}
