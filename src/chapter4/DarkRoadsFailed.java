package chapter4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;


// 11631 Dark Roads
// Sadly, TLE. :(
// Will have to attempt with (simplified) Kruskal.
public class DarkRoadsFailed {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		while (true) {
			String line = in.readLine();
			String[] tokens = line.split(" ");
			int n = Integer.parseInt(tokens[0]);
			int m = Integer.parseInt(tokens[1]);

			if (n == 0 && m == 0)
				break;

			Map<Integer, LinkedList<Edge>> outEdges = new HashMap<>();
			Map<Integer, Node> numberToNode = new HashMap<>();
			List<Node> nodes = new ArrayList<>();
			for (int i = 0; i < n; i++) {
				Node toAdd = new Node(i, -1, Integer.MAX_VALUE);
				nodes.add(toAdd);
				numberToNode.put(i, toAdd);
			}

			int initSum = 0;
			for (int j = 0; j < m; j++) {
				tokens = in.readLine().split(" ");
				int firstNumber = Integer.parseInt(tokens[0]);
				int secondNumber = Integer.parseInt(tokens[1]);
				int weight = Integer.parseInt(tokens[2]);

				if (!outEdges.containsKey(firstNumber)) {
					outEdges.put(firstNumber, new LinkedList<Edge>());
				}
				if (!outEdges.containsKey(secondNumber)) {
					outEdges.put(secondNumber, new LinkedList<Edge>());
				}

				outEdges.get(firstNumber).add(
						new Edge(numberToNode.get(firstNumber), numberToNode
								.get(secondNumber), weight));
				outEdges.get(secondNumber).add(
						new Edge(numberToNode.get(secondNumber), numberToNode
								.get(firstNumber), weight));

				initSum += weight;
			}

			PriorityQueue<Node> Q = new PriorityQueue<Node>(
					new Comparator<Node>() {
						@Override
						public int compare(Node arg0, Node arg1) {
							return Integer.compare(arg0.key, arg1.key);
						}
					});
			nodes.get(0).key = 0;
			Q.addAll(nodes);
			boolean[] visited = new boolean[n];
			Arrays.fill(visited, false);
			
			while (!Q.isEmpty()) {
				Node currNode = Q.poll();
				visited[currNode.number] = true;
				for (Edge edge : outEdges.get(currNode.number)) {
					Node neighbourNode = numberToNode.get(edge.to.number);
					if (visited[neighbourNode.number] == false
							&& edge.weight < neighbourNode.key) {
						neighbourNode.parentNumber = currNode.number;
						neighbourNode.key = edge.weight;
						Q.remove(neighbourNode);
						Q.offer(neighbourNode);
					}
				}
			}

			System.out.println(initSum
					- nodes.stream().mapToInt(Node::getKey).sum());

		}

	}

	static class Edge {
		Node from;
		Node to;
		int weight;

		public Edge(Node from, Node to, int weight) {
			this.from = from;
			this.to = to;
			this.weight = weight;
		}

		@Override
		public String toString() {
			return "Edge [from=" + from + ", to=" + to + ", weight=" + weight
					+ "]";
		}
	}

	static class Node {
		int number;
		int parentNumber;
		int key;

		public Node(int number, int parent, int key) {
			this.number = number;
			this.parentNumber = parent;
			this.key = key;
		}

		public int getKey() {
			return key;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + number;
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Node other = (Node) obj;
			if (number != other.number)
				return false;
			return true;
		}

		@Override
		public String toString() {
			return "Node [number=" + number + ", parentNumber=" + parentNumber
					+ ", key=" + key + "]";
		}

	}
	
	

}