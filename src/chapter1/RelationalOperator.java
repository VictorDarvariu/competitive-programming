package chapter1;

import java.util.Scanner;

// 11172 - Relational Operator
class RelationalOperator {

	public static void main(String[] args) {
		Scanner scanny = new Scanner(System.in);
		int t = scanny.nextInt();
		for (int i = 0; i < t; i++) {
			int a = scanny.nextInt();
			int b = scanny.nextInt();
			
			if(a > b) {
				System.out.println(">");
			}
			else if(a==b) {
				System.out.println("=");
			}
			else {
				System.out.println("<");
			}
		}
		
		scanny.close();
		
	}

}
