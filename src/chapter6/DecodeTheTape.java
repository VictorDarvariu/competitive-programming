package chapter6;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

// 10878  Decode The Tape
public class DecodeTheTape {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter out = new BufferedWriter(new OutputStreamWriter(
				System.out));
		
		while(true) {
			String line = in.readLine();
			
			if (line==null||line.trim().equals("")) {
				out.flush();
				break;
			}
			if (line.charAt(0)=='_') continue;
			
			int character = 0;
			
			for(int i=0; i<=7; i++) {
				if(i==4) continue;
				
				char currChar = line.charAt(i+2);
				if(currChar=='o') {
					character++;
				}
				if(i!=7) character = character << 1;
			}
			
			out.write((char) character);
			
		}
	}
}


