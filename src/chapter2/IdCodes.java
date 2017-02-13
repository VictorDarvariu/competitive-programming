package chapter2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// Next Permutation algorithm - key idea:
// R-L scan, find first violating order - index j
// R-L scan, find first bigger than j - index i
// swap chars at i, j
// reverse new i+1 - onwards
public class IdCodes {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		while (true) {
			String code = in.readLine();
			if (code.equals("#"))
				break;

			int j = -1;
			int i = -1;

			for (int k = code.length() - 1; k > 0; k--) {
				if (code.charAt(k - 1) < code.charAt(k)) {
					j = k - 1;
					break;
				}
			}

			if (j == -1) {
				System.out.println("No Successor");
				continue;
			}

			for (int k = code.length() - 1; k > 0; k--) {
				if (code.charAt(k) > code.charAt(j)) {
					i = k;
					break;
				}
			}

			String suffix = code.substring(j+1, i) + String.valueOf(code.charAt(j)) + code.substring(i+1, code.length());
			code = code.substring(0, j) + String.valueOf(code.charAt(i))
					+ reverse(suffix);
			
			System.out.println(code);
		}
	}

	public static String reverse(String input) {
		char[] in = input.toCharArray();
		int begin = 0;
		int end = in.length - 1;
		char temp;
		while (end > begin) {
			temp = in[begin];
			in[begin] = in[end];
			in[end] = temp;
			end--;
			begin++;
		}
		return new String(in);
	}
}
