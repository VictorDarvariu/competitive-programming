package chapter3;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

// 441 Lotto
public class Lotto {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter out = new BufferedWriter(new OutputStreamWriter(
				System.out));

		boolean first = true;
		while (true) {
			String line = in.readLine();
			if (line == null || line.trim().equals(""))
				break;

			String[] tokens = line.split(" ");
			int k = Integer.parseInt(tokens[0]);
			if(k==0) break;
			
			if(! first) System.out.println();

			int[] numbers = new int[k];
			for(int i=0; i<k; i++) {
				numbers[i] = Integer.parseInt(tokens[i+1]);
			}

			int diff = k - 6;
			
			for(int a=0; a<=diff; a++) {
				for(int b=a+1; b<=diff+1; b++) {
					for(int c=b+1; c<=diff+2; c++) {
						for(int d=c+1; d<=diff+3; d++) {
							for(int e=d+1; e<=diff+4; e++) {
								for(int f=e+1; f<=diff+5; f++) {
									System.out.println(numbers[a] + " "
											+ numbers[b] + " "
											+ numbers[c] + " "
											+ numbers[d] + " "
											+ numbers[e] + " "
											+ numbers[f]);
								}	
							}	
						}	
					}	
				}
			}
			
			first = false;
			
		}
	}
}
