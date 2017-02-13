package chapter1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

// 11507 - Bender B. Rodríguez Problem

public class BenderBRodriguez {

	public static void main(String[] args) throws NumberFormatException, IOException {
		Map<String, String> directionsMap = new HashMap<>();
		
		directionsMap.put("+x +y", "+y");
		directionsMap.put("+x -y", "-y");
		directionsMap.put("+x +z", "+z");
		directionsMap.put("+x -z", "-z");
		
		directionsMap.put("-x +y", "-y");
		directionsMap.put("-x -y", "+y");
		directionsMap.put("-x +z", "-z");
		directionsMap.put("-x -z", "+z");
		
		directionsMap.put("+y +y", "-x");
		directionsMap.put("+y -y", "+x");
		directionsMap.put("+y +z", "+y");
		directionsMap.put("+y -z", "+y");
		
		directionsMap.put("-y +y", "+x");
		directionsMap.put("-y -y", "-x");
		directionsMap.put("-y +z", "-y");
		directionsMap.put("-y -z", "-y");
		
		directionsMap.put("+z +y", "+z");
		directionsMap.put("+z -y", "+z");
		directionsMap.put("+z +z", "-x");
		directionsMap.put("+z -z", "+x");
		
		directionsMap.put("-z +y", "-z");
		directionsMap.put("-z -y", "-z");
		directionsMap.put("-z +z", "+x");
		directionsMap.put("-z -z", "-x");
		
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		while(true) {
			String line = in.readLine();
			if(line.equals("") || line==null) break;
			int L = Integer.parseInt(line);
			if(L==0) break;
			String[] bends= in.readLine().split(" ");
			
			String direction = "+x";
			
			for (String bend : bends) {
				if(! bend.equals("No")) {
					direction = directionsMap.get(direction + " " + bend);
				}
			}
			
			System.out.println(direction);
		}
	}
}
