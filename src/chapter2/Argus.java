package chapter2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

// 1203 Argus
public class Argus {

	public static void main(String[] args) throws IOException {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter out = new BufferedWriter(new OutputStreamWriter(
				System.out));
		List<Instruction> instructions = new ArrayList<>();

		PriorityQueue<Instruction> instructionQueue = new PriorityQueue<>(100,
				new Comparator<Instruction>() {
					public int compare(Instruction arg0, Instruction arg1) {
						if (arg0.period != arg1.period)
							return arg0.period - arg1.period;
						return arg0.qNum - arg1.qNum;
					}
				});

		while (true) {
			String line = in.readLine();
			if (line.trim().equals("#"))
				break;

			String[] tokens = line.split(" ");
			int qNum = Integer.parseInt(tokens[1]);
			int period = Integer.parseInt(tokens[2]);

			Instruction instr = new Instruction(qNum, period);
			instructions.add(instr);

		}

		Collections.sort(instructions, new Comparator<Instruction>() {
			public int compare(Instruction arg0, Instruction arg1) {
				if (arg0.period != arg1.period)
					return arg0.period - arg1.period;
				return arg0.qNum - arg1.qNum;
			}
		});

		int query = Integer.parseInt(in.readLine());
		
		int prune = gcdArray(instructions.stream().mapToInt(x -> x.period).toArray());

		for (int i = 0; i < instructions.size(); i++) {
			int j = 0;
			Instruction instruction = instructions.get(i);
			while (j < query && ((j+1) * instruction.period < prune * query)) {
				instructionQueue.add(new Instruction(instruction.qNum, (++j)
						* instruction.period));
			}
		}

		int polled = 0;
		while (polled < query) {
			out.write(instructionQueue.poll().qNum + "\n");
			polled++;
		}

		out.flush();

	}
	
	static int gcdArray(int[] arr) {
		int gcd = arr[0];
	    for(int i = 1; i < arr.length; i++) 
	        gcd = gcd(gcd, arr[i]);
	    return gcd;
	}
	
	static int gcd(int a, int b) {
		if (b == 0)
			return a;
		return gcd(b, a % b);
	}
	
	static class Instruction {
		int qNum;
		int period;

		public Instruction(int qNum, int period) {
			this.qNum = qNum;
			this.period = period;
		}

		public String toString() {
			return "Instruction [qNum=" + qNum + ", period=" + period + "]";
		}

	}


}
