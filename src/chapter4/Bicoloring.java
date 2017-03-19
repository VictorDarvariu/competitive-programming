package chapter4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

// 10004 Bicoloring
public class Bicoloring {

	static boolean[][] adj;
	static char[] colours;
	static int n, l, u, v;

	public static void main(String[] args) throws NumberFormatException,
			IOException {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		while (true) {
			String line = in.readLine();
			n = Integer.parseInt(line);
			if (n == 0)
				break;
			l = Integer.parseInt(in.readLine());

			adj = new boolean[n][n];
			colours = new char[n];

			for (int i = 0; i < l; i++) {
				String[] tokens = in.readLine().split(" ");
				u = Integer.parseInt(tokens[0]);
				v = Integer.parseInt(tokens[1]);
				adj[u][v] = true;
				adj[v][u] = true;
			}
			Arrays.fill(colours, 'n');
			colours[0] = 'b';
			System.out.println(colour(0) ? "BICOLORABLE." : "NOT BICOLORABLE.");
		}
	}

	static boolean colour(int node) {
		boolean ans = true;
		for (int i = 0; i < n; i++) {
			if (adj[node][i]) {
				if (colours[i] != 'n' & colours[i] == colours[node]) {
					return false;
				} else if (colours[i] == 'n') {
					colours[i] = (colours[node] == 'g' ? 'b' : 'g');
					ans = ans && colour(i);
				}
			}
		}
		return ans;
	}
}
