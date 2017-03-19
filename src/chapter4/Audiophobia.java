package chapter4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

// 10048 - Audiophobia
public class Audiophobia {
	static int dfsResult;
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int t = 1;
		while (true) {
			String[] tokens = in.readLine().split(" ");
			int C = Integer.parseInt(tokens[0]);
			int S = Integer.parseInt(tokens[1]);
			int Q = Integer.parseInt(tokens[2]);
			if (C == 0 && S == 0 && Q == 0)
				break;
			if(t!=1) System.out.println();

			Map<Integer, Node> nodes = new HashMap<>();
			List<Edge> edges = new ArrayList<>();

			for (int i = 1; i <= C; i++) {
				Node node = new Node(i, 0, null);
				node.parent = node;
				nodes.put(i, node);
			}

			for (int i = 1; i <= S; i++) {
				tokens = in.readLine().split(" ");
				int u = Integer.parseInt(tokens[0]);
				int v = Integer.parseInt(tokens[1]);
				int w = Integer.parseInt(tokens[2]);
				edges.add(new Edge(nodes.get(u), nodes.get(v), w));
			}
			Collections.sort(edges);
			List<Edge> msf = kruskal(nodes, edges);
			System.out.println(String.format("Case #%d", t++));
			for (int i = 1; i <= Q; i++) {
				tokens = in.readLine().split(" ");
				int u = Integer.parseInt(tokens[0]);
				int v = Integer.parseInt(tokens[1]);
				if (findSet(nodes.get(u)) != findSet(nodes.get(v))) {
					System.out.println("no path");
					continue;
				}

				System.out.println(findMaxDFS(nodes, msf, u, v));

			}

		}
	}

	private static void dfs(Node currNode, Map<Integer, Node> nodes,
			Map<Integer, List<Edge>> neighbours, Set<Integer> visited,
			int target, int maxNoise) {
		if (currNode.index == target) {
			dfsResult = maxNoise;
			return;
		}
		Set<Integer> newVisited = new HashSet<Integer>(visited);
		newVisited.add(currNode.index);
		for (Edge edge : neighbours.get(currNode.index)) {
			Node nextNode = (currNode.index == edge.v.index) ? edge.u : edge.v;
			if (!newVisited.contains(nextNode.index)) {
				int newMaxNoise = Math.max(maxNoise, edge.w);
				dfs(nextNode, nodes, neighbours, newVisited, target,
						newMaxNoise);
			}
		}
	}

	private static int findMaxDFS(Map<Integer, Node> nodes, List<Edge> msf,
			int u, int v) {
		// TODO: Finish proper DFS implementation... (too braindead)
		Map<Integer, List<Edge>> neighbours = new HashMap<>();
		int n = nodes.size();
		for (int i = 1; i <= n; i++) {
			neighbours.put(i, new LinkedList<Edge>());
		}
		for (Edge edge : msf) {
			int from = edge.u.index;
			int to = edge.v.index;
			neighbours.get(from).add(edge);
			neighbours.get(to).add(edge);
		}

		int maxNoise = Integer.MIN_VALUE;
		Set<Integer> visited = new HashSet<>();

		dfs(nodes.get(u), nodes, neighbours, visited, v, maxNoise);
		return dfsResult;

	}

	private static List<Edge> kruskal(Map<Integer, Node> nodes, List<Edge> edges) {
		List<Edge> msf = new ArrayList<Edge>();
		for (Edge edge : edges) {
			Node u = edge.u;
			Node v = edge.v;

			Node uSet = findSet(u);
			Node vSet = findSet(v);
			if (uSet.index != vSet.index) {
				msf.add(edge);
				union(uSet, vSet);
			}
		}
		return msf;
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

		Node parent;

		public Node(int index, int rank, Node parent) {
			this.index = index;
			this.rank = rank;
			this.parent = parent;
		}

		@Override
		public String toString() {
			return "Index: " + index;
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

		@Override
		public String toString() {
			return String.format("(%d) -%d-> (%d)", u.index, w, v.index);
		}

	}
}
