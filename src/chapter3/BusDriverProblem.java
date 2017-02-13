package chapter3;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Collections;

//11389 The Bus Driver Problem
public class BusDriverProblem {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter out = new BufferedWriter(new OutputStreamWriter(
				System.out));
		
		while(true) {
			String[] tokens = in.readLine().split(" ");
			int n = Integer.parseInt(tokens[0]);
			int d = Integer.parseInt(tokens[1]);
			int r = Integer.parseInt(tokens[2]);
			if(n==0&&d==0&&r==0) break;
			
			Integer[] morning = new Integer[n];
			Integer[] evening = new Integer[n];
			tokens = in.readLine().split(" ");
			for(int i=0; i<n; i++) {
				morning[i] = Integer.parseInt(tokens[i]);
			}
			
			tokens = in.readLine().split(" ");
			for(int i=0; i<n; i++) {
				evening[i] = Integer.parseInt(tokens[i]);
			}
			
			Arrays.sort(morning);
			Arrays.sort(evening, Collections.reverseOrder());
			
			int overtime = 0;
			
			for(int i=0; i<n; i++) {
				int pair = (morning[i] + evening[i]) - d;
				if(pair > 0) overtime += pair;
			}
			
			System.out.println(overtime * r);
			
		}
	}
}
