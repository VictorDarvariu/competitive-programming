package chapter3;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

// 10684 - The Jackpot
public class Jackpot {

	public static void main(String[] args) throws NumberFormatException,
			IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter out = new BufferedWriter(new OutputStreamWriter(
				System.out));

		while (true) {
			String line = in.readLine();
			if(line==null || line.trim().equals("")) continue;
			int N = Integer.parseInt(line);
			if (N == 0)
				break;

			int[] bets = new int[N];
			int pos = 0;

			while (pos < N) {
				String[] tokens = in.readLine().split(" ");
				for (String token : tokens) {
					bets[pos++] = Integer.parseInt(token);
				}
			}

			int maxSum = 0;
			int currSum = 0;

			for (int bet : bets) {
				if (currSum + bet < 0) {
					currSum = 0;
					continue;

				}
				currSum += bet;
				if (currSum > maxSum) {
					maxSum = currSum;
				}
			}

			if (maxSum > 0) {
				System.out.println("The maximum winning streak is " + maxSum
						+ ".");
			} else {
				System.out.println("Losing streak.");
			}
		}

	}
}
