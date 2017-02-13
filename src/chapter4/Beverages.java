package chapter4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.PriorityQueue;

// 11060 Beverages
public class Beverages {

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int caseNumber = 1;
		while (true) {
			String line = in.readLine();
			if(line==null||line.trim().equals("")) break;
			
			int n = Integer.parseInt(line);
			
			String[] idToNames = new String[n];
			HashMap<String, Integer> namesToId = new HashMap<>(); 
			boolean[][] adjacency = new boolean[n][n];
			int[] inDegrees = new int[n];
			
			for(int i=0; i<n; i++) {
				String name = in.readLine();
				idToNames[i] = name;
				namesToId.put(name, i);
			}
			
			int m = Integer.parseInt(in.readLine());
			
			for(int i=0; i<m; i++) {
				String[] names = in.readLine().split(" ");
				String from = names[0];
				String to = names[1];
				// getting WA for no reason, googling forums showed me there's duplicate pairs...
				if (!adjacency[namesToId.get(from)][namesToId.get(to)]) {
					adjacency[namesToId.get(from)][namesToId.get(to)]=true;
					inDegrees[namesToId.get(to)]++;
				}
			}
			
			// Simple implementation of Kahn's algorithm.
			PriorityQueue<Integer> queue = new PriorityQueue<>();
			for(int i=0; i<n; i++) {
				if(inDegrees[i] == 0) queue.offer(i);
			}
			
			StringBuilder sb = new StringBuilder();
			sb.append(String.format("Case #%d: Dilbert should drink beverages in this order:", caseNumber++));
			while(! queue.isEmpty()) {
				int id = queue.poll();
				for(int i=0; i<n; i++) {
					if(adjacency[id][i]) {
						inDegrees[i]--;
						if(inDegrees[i]==0) {
							queue.offer(i);
						}
					}
				}
				sb.append(" " + idToNames[id]);
			}
			sb.append(".\n");
			System.out.println(sb.toString());
			
			line = in.readLine();
			if(line==null) break;
		}
	}
}