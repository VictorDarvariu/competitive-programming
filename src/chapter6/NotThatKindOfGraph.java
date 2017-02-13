package chapter6;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Collections;

// 10800 Not That Kind of Graph
public class NotThatKindOfGraph {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter out = new BufferedWriter(new OutputStreamWriter(
				System.out));
		
		int T = Integer.parseInt(in.readLine());
		for (int t = 0; t < T; t++) {
			String seq = in.readLine();
			
			int len = seq.length();
			
			char[][] data = new char[len][100];
			for(int i=0; i<len; i++) {
				for(int j=0; j<100; j++) {
					data[i][j] = ' ';
				}
			}

			int currX = 0;
			
			int currY = 50;
			int maxY = 0;
			int minY = 100;
			
			for (int i = 0; i < seq.length(); i++) {
				char dir = seq.charAt(i);
				switch(dir) {
				case 'R':
					data[currX][currY] = '/';
					maxY = Math.max(currY, maxY);
					minY = Math.min(currY, minY);
					currY++;
					break;
				case 'C':
					data[currX][currY] = '_';
					maxY = Math.max(currY, maxY);
					minY = Math.min(currY, minY);
					break;
				case 'F':
					currY--;
					data[currX][currY] = '\\';
					maxY = Math.max(currY, maxY);
					minY = Math.min(currY, minY);
					break;
				} 
				currX++;
			}
			
			
			out.write(String.format("Case #%d:\n", t+1));
			for(int j=maxY; j>=minY; j--) {
				StringBuilder sb = new StringBuilder();
				sb.append("| ");
				for(int i=0; i<len; i++){
					sb.append(data[i][j]);
				}
				out.write(sb.toString().replaceFirst("\\s++$", ""));
				out.write("\n");

			}
			out.write("+" + String.join("", Collections.nCopies(len+2, "-")) + "\n");
			out.write("\n");
		}
		out.flush();
	}
}
