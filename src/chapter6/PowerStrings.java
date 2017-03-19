package chapter6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 10298 Power Strings
public class PowerStrings {

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		while(true) {
			String line = in.readLine();
			if(line.trim().equals(".")) break;
			
			int n = line.length();
			boolean found = false;
			outer: for(int i=1; i<=n; i++) {
				if(n%i!=0) {
					continue;
				}
				boolean flag = true;
				String pastSubstr = line.substring(0, i);
				for(int index=i; index<=n-i; index+=i) {
					String currSubstr = line.substring(index, index+i);
					if(! currSubstr.equals(pastSubstr)) {
						flag = false;
						continue outer;
					}
					pastSubstr = currSubstr;
				}
				if(flag) {
					System.out.println(n / i);
					found = true;
					break outer;
				}
			}
			if(!found) System.out.println(1);
		}

	}

}
