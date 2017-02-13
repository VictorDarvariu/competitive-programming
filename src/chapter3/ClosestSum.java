package chapter3;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

// 10487 - Closest Sums
public class ClosestSum {

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter out = new BufferedWriter(new OutputStreamWriter(
				System.out));
		int caseNumber = 1;

		while(true) {
			int size = Integer.parseInt(in.readLine().trim());
			if(size==0) break;

			System.out.println("Case " + caseNumber + ":");
			int[] numbers = new int[size];
			for(int i=0; i<size; i++) {
				numbers[i] = Integer.parseInt(in.readLine().trim());
			}
			
			int m = Integer.parseInt(in.readLine().trim());
			
			int[] queries = new int[m];
			for(int i=0; i<m; i++) {
				queries[i] = Integer.parseInt(in.readLine().trim());
			}
			
			for(int query : queries) {
				int closestSum = numbers[0] + numbers[1];
				for(int i=0; i<size; i++) {
					for(int j=i+1; j<size; j++) {
						int sum = numbers[i] + numbers[j];
						if(Math.abs(sum - query) < Math.abs(closestSum - query)) {
							closestSum = sum;
						}
					}
				}
				System.out.println(String.format("Closest sum to %d is %d.", query, closestSum));
				
			}
			
			caseNumber++;
			
		}
	}

}
