package chapter2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.List;

// 11988 Broken Keyboard (a.k.a. Beiju Text)
// TLE :'(
public class BrokenKeyboard {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));

		
		while(true) {
			String line = in.readLine();
			if(line==null || line.equals("")) {
				out.flush();
				break;
			}
			
			List<Character> text = new LinkedList<>();

			int insertPosition = 0;
			char c = '\0';
			
			for(int i=0; i<line.length(); i++) {
				c = line.charAt(i);
				if(c == '[') {
					insertPosition = 0;
				}
				else if(c == ']') {
					insertPosition = text.size();
				}
				else {
					text.add(insertPosition++, c);						
				}
			}
			
			StringBuilder sb = new StringBuilder();
			for(int i=0; i<text.size(); i++) {
				sb.append(text.get(i));
			}
			
			out.write(sb.toString());
			out.write("\n");
		}
	}
}
