package chapter4;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// 11631 Dark Roads
// Attempting with Kruskal.
public class DarkRoads {

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter out = new BufferedWriter(new OutputStreamWriter(
				System.out));

		while (true) {
			String line = in.readLine();
			String[] tokens = line.split(" ");
			int n = Integer.parseInt(tokens[0]);
			int m = Integer.parseInt(tokens[1]);

			if (n == 0 && m == 0) {
				out.flush();
				break;
			}

			Map<Integer, Node> nodes = new HashMap<>();
			List<Edge> edges = new ArrayList<>();

			for(int i=0; i<n; i++) {
				Node node = new Node(i, 0, null);
				node.parent = node;
				nodes.put(i, node);
			}
			
			int initSum = 0;
			int optimalSum = 0;
			for (int j = 0; j < m; j++) {
				tokens = in.readLine().split(" ");
				int u = Integer.parseInt(tokens[0]);
				int v = Integer.parseInt(tokens[1]);
				int w = Integer.parseInt(tokens[2]);
				initSum += w;
				
				edges.add(new Edge(nodes.get(u), nodes.get(v), w));
			}


			Collections.sort(edges);
			for (Edge edge : edges) {
				Node u = edge.u;
				Node v = edge.v;
				
				Node uSet = findSet(u);
				Node vSet = findSet(v);
				if(uSet.index != vSet.index) {
					optimalSum+=edge.w;
					union(uSet, vSet);
				}
			} 

			out.write((initSum - optimalSum) + "\n");
		}

	}

	
	static void union(Node x, Node y) {
		link(findSet(x), findSet(y));
	}
	
	static void link(Node x, Node y) {
		if(x.rank > y.rank) {
			y.parent = x;
		}
		else {
			x.parent = y;
			if(x.rank == y.rank) {
				y.rank = y.rank++;
			}
		}
	}


	static Node findSet(Node x) {
		if(x.index != x.parent.index) {
			x.parent = findSet(x.parent);
		}
		return x.parent;
	}
	
	static class Node {
		int index;
		int rank;

		Node parent;

		public Node(int index, int rank, Node parent) {
			this.index = index;
			this.rank = rank;
			this.parent = parent;
		}

	}

	static class Edge implements Comparable<Edge> {
		Node u;
		Node v;
		int w;

		public Edge(Node u, Node v, int w) {
			this.u = u;
			this.v = v;
			this.w = w;
		}

		@Override
		public int compareTo(Edge arg0) {
			return Integer.compare(this.w, arg0.w);
		}

	}
}