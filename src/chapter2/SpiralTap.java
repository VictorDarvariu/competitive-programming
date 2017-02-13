package chapter2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

// 10920 - Spiral Tap
public class SpiralTap {

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter out = new BufferedWriter(new OutputStreamWriter(
				System.out));

		while (true) {
			String[] tokens = in.readLine().split(" ");
			long size = Long.parseLong(tokens[0]);
			long pos = Long.parseLong(tokens[1]);

			if (size == 0 && pos == 0) {
				out.flush();
				break;
			}

			long currX = size  / 2 + 1;
			long currY = size / 2 + 1;

			int increment = 1;
			boolean nextVertical = true;

			long totalSquares = size * size;
			long currPos = 1;
			if (currPos == pos) {
				out.write(String.format("Line = %d, column = %d.\n", currX, currY));
				continue;
			}

			outer: while (currPos < totalSquares) {
				// <hackmeister>
				if(currPos + (2 * increment) + (2 * (increment+1)) < pos) {
					currPos = currPos + (2 * increment) + (2 * (increment+1));
					currX = currX - 1;
					currY = currY + 1;
					
					if (currPos  == pos) {
						out.write(String.format("Line = %d, column = %d.\n", currX, currY));
						break outer;
					}
					
					increment += 2;
					continue;
				}
				// </hackmeister>
				
				int currIncrement = 0;
				while(currIncrement < increment) {
					if (currPos == pos) {
						out.write(String.format("Line = %d, column = %d.\n", currX, currY));
						break outer;
					}
					
					if(nextVertical && (increment % 2 ==1)) currX = currX + 1;
					if(nextVertical && (increment % 2 ==0)) currX = currX - 1;
					if(!nextVertical && (increment % 2 ==1)) currY = currY - 1;
					if(!nextVertical && (increment % 2 ==0)) currY = currY + 1;
					
					currIncrement++;
					currPos++;
				}
				
				if (nextVertical) {
					nextVertical = false;
				} else {
					increment++;
					nextVertical = true;
				}
			}
		}

	}

}
