package chapter3;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.List;

// 624 - CD
public class CD {

	static int duration;
	static int noTracks;
	static int[] tracks;
	static LinkedList<Integer> bestPath;
	static int bestSum;
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter out = new BufferedWriter(new OutputStreamWriter(
				System.out));

		while (true) {
			String line = in.readLine();
			if (line == null || line.trim().equals(""))
				break;

			String[] tokens = line.split(" ");
			duration = Integer.parseInt(tokens[0]);
			noTracks = Integer.parseInt(tokens[1]);
			tracks = new int[noTracks];
			bestPath = new LinkedList<Integer>();
			bestSum = 0;

			for (int i = 0; i < noTracks; i++) {
				tracks[i] = Integer.parseInt(tokens[i + 2]);
			}

			for (int i = 0; i < noTracks; i++) {
				dfs(new LinkedList<Integer>(), 0, i);
			}

			for (int track : bestPath) {
				out.write(track + " ");
			}
			out.write("sum:" + bestSum + "\n");
		}
		
		out.flush();
	}

	private static void dfs(LinkedList<Integer> currentPath, int currentSum, int pos) {
		int newSum = currentSum + tracks[pos];
		LinkedList<Integer> newPath = new LinkedList<>(currentPath);
		newPath.addLast(tracks[pos]);
		
		if(newSum > bestSum || (newSum == bestSum && newPath.size() > bestPath.size())) {
			bestSum = newSum;
			bestPath = newPath;
		}
		
		for(int i = pos+1; i < noTracks; i++) {
			if(newSum + tracks[i] <= duration) dfs(newPath, newSum, i);
		}
	}



}


