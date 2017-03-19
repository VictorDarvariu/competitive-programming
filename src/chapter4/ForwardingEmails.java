package chapter4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

//12442 Forwarding Emails
public class ForwardingEmails {

	static int[] edges;
	static int[] visited;
	static int[] sums;

	public static void main(String[] args) throws NumberFormatException,
			IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(in.readLine());

		for (int t = 1; t <= T; t++) {
			String line = in.readLine();
			if (line == null || line.trim().equals(""))
				break;
			int N = Integer.parseInt(line);
			edges = new int[N+1];
			visited = new int[N+1];
			sums = new int[N+1];
			
			Arrays.fill(sums, -1);
			Arrays.fill(visited, 0);
			
			int bestNode = 0;
			int bestSum = 0;
			
			for (int i = 1; i <= N; i++) {
				String[] tokens = in.readLine().split(" ");
				int u = Integer.parseInt(tokens[0]);
				int v = Integer.parseInt(tokens[1]);
				edges[u] = v;
			}

			for (int i = 1; i <= N; i++) {
				if (sums[i] == -1) {
					traverse(i);
				}
				if(sums[i] > bestSum) {
					bestNode = i;
					bestSum = sums[i];
				}
			}
			System.out.println(String.format("Case %d: %d", t , bestNode));
		}
	}

	private static int traverse(int i) {
		visited[i] = 1;
		int total = 0;
		int next = edges[i];
		if (next!=-1 && visited[next] == 0) {
			total = 1 + traverse(next);
		}
		visited[i] = 0;
		return sums[i] = total;
	}

}
