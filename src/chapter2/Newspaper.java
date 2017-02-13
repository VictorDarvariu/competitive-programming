package chapter2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Map;

// 11340 - Newspaper
public class Newspaper {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int testCases = Integer.parseInt(in.readLine());
		
		for(int testCase = 0; testCase < testCases; testCase++) {
			int numberPaidCharacters = Integer.parseInt(in.readLine());
			Map<Character, Integer> paidCharacters = new HashMap<>();
			
			for(int i=0; i<numberPaidCharacters; i++) {
				String[] tokens = in.readLine().split(" ");
				char paidChar = tokens[0].charAt(0);
				int cents = Integer.parseInt(tokens[1]);
				paidCharacters.put(paidChar, cents);
			}
			
			int valueInCents = 0;
			int numberLines = Integer.parseInt(in.readLine());
			
			for(int i=0; i<numberLines; i++) {
				String line = in.readLine();
				for(int j=0; j<line.length(); j++) {
					char currentCharacter = line.charAt(j);
					if(paidCharacters.containsKey(currentCharacter)) {
						valueInCents += paidCharacters.get(currentCharacter);
					}
				}
			}
			
			double valueInDollars = ((double) valueInCents) / 100;
			out.write(String.format("%.2f$\n", valueInDollars));
		}
		out.flush();
	}
}
