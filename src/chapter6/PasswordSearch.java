package chapter6;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

// 902 Password Search
public class PasswordSearch {

	static Compy compy = new Compy();

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter out = new BufferedWriter(new OutputStreamWriter(
				System.out));

		while (true) {
			String line = in.readLine();
			if (line == null || line.trim().equals("")) {
				out.flush();
				break;
			}

			String[] tokens = line.split(" ");
			int passwordLength = Integer.parseInt(tokens[0]);
			String message;
			if (tokens.length == 1) {
				message = in.readLine();
			} else {
				message = tokens[1];
			}

			Map<String, Integer> substringFrequencies = new HashMap<>();

			int endIndex = message.length() - passwordLength;

			for (int i = 0; i <= endIndex; i++) {
				String substr = message.substring(i, i + passwordLength);

				if (substringFrequencies.containsKey(substr)) {
					substringFrequencies.put(substr,
							substringFrequencies.get(substr) + 1);
				} else {
					substringFrequencies.put(substr, 1);
				}
			}

			String password = substringFrequencies.entrySet().stream()
					.max(compy).get().getKey();
			out.write(password + "\n");
		}
	}

	static class Compy implements Comparator<Map.Entry<String, Integer>> {
		public int compare(Entry<String, Integer> arg0,
				Entry<String, Integer> arg1) {
			return (arg0.getValue().compareTo(arg1.getValue()));
		}
	}
}
