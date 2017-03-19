package chapter4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;

// 796 Critical Links
public class CriticalLinks {

	static int n;
	static int time;
	static LinkedList<Integer>[] adjLists;
	static boolean[] visited;
	static int[] depths;
	static int[] lowpoints;
	static int[] parents;
	static LinkedList<Pair> bridges;

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		while (true) {
			String line = in.readLine();
			if (line == null || line.trim().equals(""))
				break;
			n = Integer.parseInt(line);
			
			time = 0;
			adjLists = new LinkedList[n];
			visited = new boolean[n];
			depths = new int[n];
			lowpoints = new int[n];
			parents = new int[n];
			bridges = new LinkedList<>();

			for (int i = 0; i < n; i++) {
				adjLists[i] = new LinkedList<>();
			}

			for (int i = 0; i < n; i++) {
				String[] tokens = in.readLine().split(" ");
				int u = Integer.parseInt(tokens[0]);
				int outgoing = Integer.parseInt(tokens[1].substring(1,
						tokens[1].length() - 1));
				if (outgoing == 0)
					continue;

				for (int j = 0; j < outgoing; j++) {
					int v = Integer.parseInt(tokens[j + 2]);
					adjLists[u].addLast(v);
				}
			}

			Arrays.fill(visited, false);
			Arrays.fill(parents, -1);

			for (int i = 0; i < n; i++) {
				findBridges(i);
			}

			System.out.println(String.format("%d critical links",
					bridges.size()));
			Collections.sort(bridges);
			for (Pair bridge : bridges) {
				System.out
						.println(String.format("%d - %d", bridge.u, bridge.v));
			}
			System.out.println();
			line = in.readLine();
			if (line == null){
				break;
			}
		}

	}

	private static void findBridges(int i) {
		time++;
		visited[i] = true;
		depths[i] = time;
		lowpoints[i] = time;

		LinkedList<Integer> adjList = adjLists[i];
		for (Integer j : adjList) {
			if (!visited[j]) {
				parents[j] = i;
				findBridges(j);

				lowpoints[i] = Math.min(lowpoints[i], lowpoints[j]);
				if (lowpoints[j] > depths[i]) {
					if (i < j)
						bridges.add(new Pair(i, j));
					if (i > j)
						bridges.add(new Pair(j, i));
				}
			} else if (j != parents[i]) {
				lowpoints[i] = Math.min(lowpoints[i], depths[j]);
			}
		}

	}

	static class Pair implements Comparable<Pair> {
		int u;
		int v;

		public Pair(int u, int v) {
			this.u = u;
			this.v = v;
		}

		@Override
		public int compareTo(Pair arg0) {
			if (this.u != arg0.u) {
				return Integer.compare(this.u, arg0.u);
			}
			return Integer.compare(this.v, arg0.v);
		}

	}

}
