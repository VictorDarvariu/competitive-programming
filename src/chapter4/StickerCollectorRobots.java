package chapter4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

// 11831 Sticker Collector Robots
public class StickerCollectorRobots {

	static Map<String, Character> directions = new HashMap<>();

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		fillDirections();

		while (true) {
			String[] tokens = in.readLine().split(" ");
			int N = Integer.parseInt(tokens[0]);
			int M = Integer.parseInt(tokens[1]);
			int S = Integer.parseInt(tokens[2]);
			if (N == 0 && M == 0 && S == 0)
				break;

			char[][] grid = new char[N][M];
			int currI = 0;
			int currJ = 0;
			char orientation = 'N';
			int stickers = 0;

			for (int i = 0; i < N; i++) {
				String gridRow = in.readLine();
				for (int j = 0; j < M; j++) {
					grid[i][j] = gridRow.charAt(j);
					if (grid[i][j] == 'N' || grid[i][j] == 'S'
							|| grid[i][j] == 'L' || grid[i][j] == 'O') {
						currI = i;
						currJ = j;
						orientation = grid[i][j];
					}
				}
			}
			String instructions = in.readLine();
			for (int i = 0; i < instructions.length(); i++) {
				char instruction = instructions.charAt(i);
				if (instruction == 'D' || instruction == 'E') {
					String directionKey = new StringBuilder()
							.append(orientation).append(instruction).toString();
					orientation = directions.get(directionKey);
				} else if (instruction == 'F') {
					int nextI = findNextI(orientation, currI);
					int nextJ = findNextJ(orientation, currJ);

					if (nextI < 0 || nextI >= N || nextJ < 0 || nextJ >= M) {
						continue;
					} else if (grid[nextI][nextJ] == '#') {
						continue;
					} else {
						currI = nextI;
						currJ = nextJ;

						if (grid[currI][currJ] == '*') {
							stickers++;
							grid[currI][currJ] = '.';
						}
					}

				}

			}
			System.out.println(stickers);

		}

	}

	private static int findNextI(char orientation, int currI) {
		switch (orientation) {
		case 'N':
			return currI - 1;
		case 'S':
			return currI + 1;
		default:
			return currI;
		}

	}

	private static int findNextJ(char orientation, int currJ) {
		switch (orientation) {
		case 'L':
			return currJ + 1;
		case 'O':
			return currJ - 1;
		default:
			return currJ;
		}
	}

	private static void fillDirections() {
		directions.put("ND", 'L');
		directions.put("NE", 'O');

		directions.put("SD", 'O');
		directions.put("SE", 'L');

		directions.put("LD", 'S');
		directions.put("LE", 'N');

		directions.put("OD", 'N');
		directions.put("OE", 'S');
	}
}
