package chapter6;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

// 11385 Da Vinci Code
public class DaVinciCode {

	static Map<Integer, Integer> fibonacciValueToIndex = new HashMap<>();
	static Map<Integer, Integer> fibonacciIndexToValue = new HashMap<>();

	public static void main(String[] args) throws NumberFormatException,
			IOException {
		// TODO Auto-generated method stub
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter out = new BufferedWriter(new OutputStreamWriter(
				System.out));

		fibo(100);
		fibonacciIndexToValue
				.entrySet()
				.stream()
				.forEach(
						entry -> fibonacciValueToIndex.put(entry.getValue(),
								entry.getKey()));
		
		int T = Integer.parseInt(in.readLine());
		for (int i = 0; i < T; i++) {
			int N = Integer.parseInt(in.readLine());

			List<Integer> fibos = Arrays.stream(in.readLine().split(" "))
					.map(Integer::parseInt).collect(Collectors.toList());
			String message = in.readLine().trim();
			message = message.replaceAll("[^A-Z]", "");

			System.out.println(decipher(100, fibos, message).replaceFirst("\\s++$", ""));
		}
	}

	private static String decipher(int n, List<Integer> fibos, String message) {
		char[] decoded = new char[n];
		for(int i=0; i<n; i++) {
			decoded[i] = ' ';
		}
		
		for (int i=0; i<fibos.size(); i++) {
			int fiboValue = fibos.get(i);
			char toInsert = message.charAt(i);
			
			int insertIndex = fibonacciValueToIndex.get(fiboValue);
			decoded[insertIndex-1] = toInsert;
		}
		
		return new String(decoded);
		
	}
	private static int fibo(int N) {

		if (fibonacciIndexToValue.containsKey(N)) {
			return fibonacciIndexToValue.get(N);
		} else {
			int value;
			if (N == 1 || N == 2) {
				value = N;
			} else {
				value = fibo(N - 1) + fibo(N - 2);
			}
			
			fibonacciIndexToValue.put(N, value);
			return value;
		}

	}

}
