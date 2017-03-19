package chapter4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

// 315 Network
public class Network {

	static boolean[][] adj;
	static boolean[] visited;
	static int[] depths;
	static int[] lowpoints;
	static int[] parents;
	static int articulationPoints;

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		while (true) {
			String line = in.readLine();
			if (line.equals("0"))
				break;

			int n = Integer.parseInt(line);
			adj = new boolean[n][n];
			visited = new boolean[n];
			depths = new int[n];
			lowpoints = new int[n];
			parents = new int[n];
			articulationPoints = 0;
			
			Arrays.fill(parents, -1);

			while (!(line = in.readLine()).equals("0")) {
				String[] tokens = line.split(" ");
				int u = Integer.parseInt(tokens[0]);
				for (int j = 1; j < tokens.length; j++) {
					int v = Integer.parseInt(tokens[j]);
					adj[u-1][v-1] = true;
					adj[v-1][u-1] = true;
				}
			}
			
			getArticulationPoints(0, 1);
			System.out.println(articulationPoints);
		}

	}

	static void getArticulationPoints(int node, int depth) {
		visited[node] = true;
		depths[node] = depth;
		lowpoints[node] = depth;

		int childCount = 0;
		boolean isArticulation = false;

		boolean[] adjRow = adj[node];
		for (int i=0; i<adjRow.length; i++) {
			if(adjRow[i]) {
				if(! visited[i]) {
					parents[i] = node;
					getArticulationPoints(i, depth+1);
					childCount++;
					if(lowpoints[i] >= depths[node]) {
						isArticulation = true;
					}
					lowpoints[node] = Math.min(lowpoints[i], lowpoints[node]);
				}
				else if(i != parents[node]) {
					lowpoints[node] = Math.min(lowpoints[node], depths[i]);
				}
			}
		}
		if((parents[node]!=-1 && isArticulation) || (parents[node] ==-1 && childCount > 1)) {
			articulationPoints++;
		}
	}

}
