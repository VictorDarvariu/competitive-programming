package chapter3;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

// 927 Integer Sequences from Addition of Terms
public class IntegerSequences {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter out = new BufferedWriter(new OutputStreamWriter(
				System.out));
		
		long T = Long.parseLong(in.readLine());
		for (int i = 0; i < T; i++) {
			String[] tokens = in.readLine().split(" ");
			long degree = Long.parseLong(tokens[0]);
			
			long[] coeff = new long[(int) (degree+1l)];
			
			for(int j=0; j<=degree; j++) {
				coeff[j] = Long.parseLong(tokens[(int) (j + 1)]);
			}

			long d = Long.parseLong(in.readLine());
			long k = Long.parseLong(in.readLine());
			
			int currPos = 0;
			int n = 1;
			
			while(k - currPos > 0) {
				currPos += n * d;
				n++;
			}
			
			long value = getValue(degree, coeff, n-1);
			System.out.println(value);
			
		}
		
	}

	private static long getValue(long degree, long[] coeff, int n) {
		long val = 0;
		for(int j=0; j<=degree; j++) {
			val += coeff[j] * Math.pow(n, j);
		}
		return val;
	}
}
