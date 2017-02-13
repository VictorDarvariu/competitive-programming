package chapter1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

// 11799 - Horror Dash

public class HorrorDash {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(in.readLine());
		
		int[] results = new int[N];
		for (int i = 0; i < N; i++) {
			String[] tokens = in.readLine().split(" ");
			int[] childrenSpeeds = new int[tokens.length];
			for (int j=0; j<tokens.length; j++) {
				childrenSpeeds[j] = Integer.parseInt(tokens[j]);
			}
			results[i] = Arrays.stream(childrenSpeeds).max().getAsInt();
		}
		
		for (int i=0; i < N; i++) {
			System.out.printf("Case %d: %d\n", i+1, results[i]);
		}
		
	}

}
