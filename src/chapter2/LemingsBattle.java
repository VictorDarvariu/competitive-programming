package chapter2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

//978 - Lemmings Battle!
public class LemingsBattle {

	public static void main(String[] args) throws NumberFormatException,
			IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter out = new BufferedWriter(new OutputStreamWriter(
				System.out));

		int T = Integer.parseInt(in.readLine());

		for (int i = 0; i < T; i++) {

			Comparator<Integer> comp = new Comparator<Integer>() {
				public int compare(Integer arg0, Integer arg1) {
					return - (arg0.compareTo(arg1));
				}
			};
			
			PriorityQueue<Integer> green = new PriorityQueue<>(comp);
			PriorityQueue<Integer> blue = new PriorityQueue<>(comp);
			String[] tokens = in.readLine().split(" ");
			int B = Integer.parseInt(tokens[0]);
			int SG = Integer.parseInt(tokens[1]);
			int SB = Integer.parseInt(tokens[2]);

			for (int j = 0; j < SG; j++) {
				green.add(Integer.parseInt(in.readLine()));
			}

			for (int j = 0; j < SB; j++) {
				blue.add(Integer.parseInt(in.readLine()));
			}
			
			while(true) {
				if(green.size() == 0 && blue.size() == 0) {
					System.out.println("green and blue died");
					break;
				}
				if(green.size() == 0) {
					System.out.println("blue wins");
					while(blue.size()!=0) {
						System.out.println(blue.poll());
					}
					break;
				}
				else if (blue.size() == 0) {
					System.out.println("green wins");
					while(green.size()!=0) {
						System.out.println(green.poll());
					}
					break;
				}
				else {
					List<Integer> greenRemaining = new ArrayList<>();
					List<Integer> blueRemaining = new ArrayList<>();
					int battleSize = Math.min(B, Math.min(green.size(), blue.size()));
					
					for(int j=0; j<battleSize; j++) {
						int greenLemming = green.poll();
						int blueLemming = blue.poll();

						if(greenLemming > blueLemming) {
							greenRemaining.add(greenLemming - blueLemming);
						}
						else if (greenLemming < blueLemming) {
							blueRemaining.add(blueLemming - greenLemming);
						}
					}
					
					green.addAll(greenRemaining);
					blue.addAll(blueRemaining);
				}
				
				
			}
			if(i!=T-1) System.out.println();
		}
	}

}
