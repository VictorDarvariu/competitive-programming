package chapter1;

import java.util.Scanner;

// 10114 - Loansome Car Buyer
public class LoansomeCarBuyer {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		
		while(true) {
			int numberOfMonths = in.nextInt();
			if(numberOfMonths < 0) {
				break;
			}
			double downPayment = in.nextDouble();
			double loan = in.nextDouble();
			int depreciations = in.nextInt();
			double[] depreciationRecords = new double[numberOfMonths+1];
			for(int i=0; i<depreciations; i++) {
				int position = in.nextInt();
				depreciationRecords[position] = in.nextDouble();
			}
			
			for(int i=1; i<=numberOfMonths; i++) {
				if(depreciationRecords[i] == 0.0d) {
					depreciationRecords[i] = depreciationRecords[i-1];
				}
			}
			
			double amountOwned = loan;
			double carValue = (loan + downPayment) * (1 - depreciationRecords[0]);
			
			int month = 0;
			while(amountOwned >= carValue) {
				month++;
				amountOwned = amountOwned - (loan/numberOfMonths);
				carValue = carValue * (1 - depreciationRecords[month]);
			}
			
			if (month==1) System.out.println("1 month");
			else System.out.println(String.valueOf(month) + " months");
			
		}
		
	}
}
