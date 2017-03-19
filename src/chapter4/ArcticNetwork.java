package chapter4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// 10369 - Arctic Network
public class ArcticNetwork {

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(in.readLine());

		for (int t = 0; t < T; t++) {
			String[] tokens = in.readLine().split(" ");
			int S = Integer.parseInt(tokens[0]);
			int P = Integer.parseInt(tokens[1]);

			Map<Integer, Node> nodes = new HashMap<>();
			List<Edge> edges = new ArrayList<>();

			for (int i = 0; i < P; i++) {
				tokens = in.readLine().split(" ");
				int x = Integer.parseInt(tokens[0]);
				int y = Integer.parseInt(tokens[1]);

				Node node = new Node(i, 0, x, y, null);
				node.parent = node;
				
				nodes.put(i, node);
			}

			for (int i = 0; i < P; i++) {
				for (int j = 0; j < P; j++) {
					if (i != j) {
						Node first = nodes.get(i);
						Node second = nodes.get(j);
						double distance = Math.sqrt(Math.pow(
								(first.x - second.x), 2)
								+ Math.pow((first.y - second.y), 2));
						edges.add(new Edge(first, second, distance));
					}
				}
			}
			
			List<Edge> mst = new ArrayList<Edge>();
			Collections.sort(edges);
			for(Edge edge : edges) {
				Node u = edge.u;
				Node v = edge.v;
				
				Node setU = findSet(u);
				Node setV = findSet(v);
				if(setU.index != setV.index) {
					mst.add(edge);
					union(setU, setV);
				}
			}
			
			System.out.println(String.format("%.2f", mst.get(mst.size() - S).w));
			

		}
	}

	static void union(Node x, Node y) {
		link(findSet(x), findSet(y));
	}

	static void link(Node x, Node y) {
		if (x.rank > y.rank) {
			y.parent = x;
		} else {
			x.parent = y;
			if (x.rank == y.rank) {
				y.rank = y.rank++;
			}
		}
	}

	static Node findSet(Node x) {
		if (x.index != x.parent.index) {
			x.parent = findSet(x.parent);
		}
		return x.parent;
	}

	static class Node {
		int index;
		int rank;

		int x;
		int y;

		Node parent;

		public Node(int index, int rank, int x, int y, Node parent) {
			this.index = index;
			this.rank = rank;
			this.x = x;
			this.y = y;
			this.parent = parent;
		}

	}

	static class Edge implements Comparable<Edge> {
		Node u;
		Node v;
		double w;

		public Edge(Node u, Node v, double w) {
			this.u = u;
			this.v = v;
			this.w = w;
		}

		@Override
		public int compareTo(Edge arg0) {
			return Double.compare(this.w, arg0.w);
		}

	}

}
