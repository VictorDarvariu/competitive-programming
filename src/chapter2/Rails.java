package chapter2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Stack;

public class Rails {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter out = new BufferedWriter(new OutputStreamWriter(
				System.out));
		
		while(true) {
			String line = in.readLine();
			if(line==null||line.trim().equals("")) break;
			int trainSize = Integer.parseInt(line);
			if(trainSize==0) break;
			perm: while(true) {
				String[] tokens = in.readLine().trim().split(" ");
				if(tokens[0].equals("0")) {
					System.out.println();
					break;
				}
				
				int[] permutation = new int[trainSize];
				permutation = Arrays.stream(tokens).mapToInt(Integer::parseInt).toArray();
				Stack<Integer> station = new Stack<>();
				int currCarriage = 0;
				
				for (int i : permutation) {
					while(i > currCarriage && currCarriage < trainSize) {
						station.push(++currCarriage);
					}
					
					if(station.pop() != i) {
						System.out.println("No");
						continue perm;
					}
					
				}
				System.out.println("Yes");
			}
		}
		
	}

}

