package chapter2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Map;
import java.util.TreeMap;

public class HardwoodSpecies {

	// 10226 Hardwood Species
	public static void main(String[] args) throws IOException {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter out = new BufferedWriter(new OutputStreamWriter(
				System.out));

		int T = Integer.parseInt(in.readLine());
		in.readLine();

		for (int i = 0; i < T; i++) {
			Map<String, Double> trees = new TreeMap<>();
			while (true) {
				String tree = in.readLine();
				if (tree == null || tree.trim().equals(""))
					break;

				if (trees.containsKey(tree)) {
					trees.put(tree, trees.get(tree) + 1);
				} else {
					trees.put(tree, 1d);
				}
			}

			double sum = trees.entrySet().stream()
					.map(Map.Entry<String, Double>::getValue)
					.reduce(0d, (a, b) -> a + b);
			trees.entrySet().stream().forEach(x -> 
					System.out.println(String.format("%s %.4f", x.getKey(), (x.getValue() / sum * 100))));
		
			if(i!=T-1) System.out.println();
		}

	}

}
