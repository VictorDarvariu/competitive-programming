package chapter6;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class HomeworkChecker {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter out = new BufferedWriter(new OutputStreamWriter(
				System.out));
		
		int correctAnswers = 0;
		outer: while(true) {
			String line = in.readLine();
			
			if(line==null || line.trim().equals("")) {
				System.out.println(correctAnswers);
				break;
			}
			
			int stage = 0;
			
			StringBuilder firstNo = new StringBuilder();
			char operation = '\0';
			StringBuilder secondNo = new StringBuilder();
			StringBuilder resultNo = new StringBuilder();
			
			
			for(char theChar : line.toCharArray()) {
				if(theChar=='?') continue outer;
				if(stage==0) {
					if(theChar=='+' || theChar=='-') {
						operation = theChar;
						stage++;
					}
					else {
						firstNo.append(theChar);
					}
				}
				else if (stage==1) {
					if(theChar=='=') {
						stage++;
					}
					else {
						secondNo.append(theChar);
					}
				}
				else if (stage==2) {
					if(theChar=='=') {
					}
					else {
						resultNo.append(theChar);
					}
				}
			}
			
			
			int first = Integer.parseInt(firstNo.toString());
			int second = Integer.parseInt(secondNo.toString());
			int result = Integer.parseInt(resultNo.toString());
			
			if(operation=='+') {
				if(first + second == result) correctAnswers++;
			}
			else if(operation=='-') {
				if(first - second == result) correctAnswers++;
			}
			
		}
		
	}
}
