package 삼성역량기출;

import java.util.ArrayList;
import java.util.Scanner;

public class bj_15685_드래곤커브 {
	static int N, count = 0;
	static boolean[][] map;
	static int[] dy = { 0, -1, 0, 1 };
	static int[] dx = { 1, 0, -1, 0 };

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();
		map = new boolean[101][101];

		for (int i = 0; i < N; i++) {
			int x = sc.nextInt();
			int y = sc.nextInt();
			int d = sc.nextInt();
			int g = sc.nextInt();
			dragonCurve(x, y, d, g);
		}
		
		countOfSquare();
		System.out.println(count);
		sc.close();
	}

	private static void countOfSquare() {
		for (int i = 0; i < 100; i++) {
			for (int j = 0; j < 100; j++) {
				if (map[i][j] && map[i][j + 1] && map[i + 1][j] && map[i + 1][j + 1])
					count++;
			}
		}
	}

	private static void dragonCurve(int x, int y, int d, int g) {
		ArrayList<Integer> list = new ArrayList<>();
		list.add(d);
		for (int i = 0; i < g; i++) {
			int size=list.size();
			for (int j = size - 1; j >= 0; j--) {
				int dir = (list.get(j) + 1) % 4;
				list.add(dir);
			}
		}

		map[y][x] = true;
		for (int i = 0; i < list.size(); i++) {
			y += dy[list.get(i)];
			x += dx[list.get(i)];
			map[y][x] = true;
		}
	}

}
