package chapter4;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

// 11060 Beverages
// Failed - tried to use DFS (as described in CLRS)
// This isn't a typical toposort problem - the order matters here.
// Switching to trying to use Kahn's
public class BeveragesFailed {

	static int time = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter out = new BufferedWriter(new OutputStreamWriter(
				System.out));

		while (true) {
			String line = in.readLine();
			if (line == null || line.trim().equals(""))
				break;

			int noNodes = Integer.parseInt(line);
			List<Node> graphNodes = new ArrayList<>();
			for (int i = 0; i < noNodes; i++) {
				Node node = new Node();
				node.id = in.readLine();
				node.colour = "WHITE";

				graphNodes.add(node);
			}

			Map<String, List<String>> graphEdges = new HashMap<>();
			for (Node node : graphNodes) {
				graphEdges.put(node.id, new ArrayList<>());
			}

			int noEdges = Integer.parseInt(in.readLine());
			for (int i = 0; i < noEdges; i++) {
				String[] tokens = in.readLine().split(" ");
				graphEdges.get(tokens[0]).add(tokens[1]);
			}

			List<Node> sorted = solve(graphNodes, graphEdges);
			System.out.println(sorted.toString());
			in.readLine();
		}
	}

	private static List<Node> solve(List<Node> graphNodes,
			Map<String, List<String>> graphEdges) {
		time = 0;
		LinkedList<Node> sorted = new LinkedList<>();
		
		for (Node node : graphNodes) {
			if (node.colour.equals("WHITE")) {
				visit(graphNodes, graphEdges, node, sorted);
			}
		}
		return sorted;

	}

	private static void visit(List<Node> graphNodes,
			Map<String, List<String>> graphEdges, Node node, LinkedList<Node> sorted) {
		
		System.out.println("VISITING NODE " + node);
		time += 1;
		node.d = time;
		node.colour = "GRAY";
		for (Node neighbour : getNeighbours(graphNodes, graphEdges, node)) {
			if (neighbour.colour.equals("WHITE")) {
				neighbour.predecessorId = node.id;
				visit(graphNodes, graphEdges, neighbour,sorted);
			}
		}
		node.colour = "BLACK";
		time += 1;
		node.f = time;
		sorted.addFirst(node);
	}

	private static List<Node>  getNeighbours(List<Node> graphNodes,
			Map<String, List<String>> graphEdges, Node node) {
		List<Node> neighbours = new ArrayList<>();
		for(Node possibleNeighbour : graphNodes) {
			if(graphEdges.get(node.id).contains(possibleNeighbour.id)) {
				neighbours.add(possibleNeighbour);
			}
		}
		return neighbours;
	}
	
	// Defining graphs as in CLRS - see chapter 22.3, pg. 604
	static class Node {
		String id;
		String colour;
		String predecessorId;
		int d;
		int f;

		@Override
		public String toString() {
			return "Node [id=" + id + ", colour=" + colour + ", predecessorId="
					+ predecessorId + ", d=" + d + ", f=" + f + "]";
		}

	}
}
