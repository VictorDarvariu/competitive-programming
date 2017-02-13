package chapter2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class JollyJumpers {
	public static void main(String[] args) throws IOException {
	
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		while(true) {
			boolean jolly = true;
			String line = in.readLine();
			if(line==null || line.trim().equals("")) break;
			String[] tokens = line.split(" ");
			int n = Integer.parseInt(tokens[0]);
			int[] numbers = new int[n];
			for(int i=0; i<n; i++) {
				numbers[i] = Integer.parseInt(tokens[i+1]);
			}
			
			int[] diffs = new int[n-1];
			for(int i=0; i<n-1; i++) {
				diffs[i] = Math.abs(numbers[i] - numbers[i+1]);
			}
			
			Arrays.sort(diffs);
			for(int i=0; i<n-1; i++) {
				if(diffs[i] != (i+1)) {
					jolly = false;
					break;
				}
			}
			if(jolly) System.out.println("Jolly"); else System.out.println("Not jolly");
		}
	}
}
