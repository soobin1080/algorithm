package 삼성역량기출;

import java.util.Scanner;

public class bj_17779_게리멘더링2 {
	static int N;
	static int[][] map;
	static boolean[][] line;
	static int cityMax, cityMin;
	static int MIN = Integer.MAX_VALUE;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		map = new int[N + 1][N + 1];

		for (int i = 1; i < N + 1; i++) {
			for (int j = 1; j < N + 1; j++) {
				map[i][j] = sc.nextInt();
			}
		}

		for (int i = 1; i < N + 1; i++) {
			for (int j = 1; j < N + 1; j++) {
				int x = i, y = j;
				for (int k = 1; k < N + 1; k++) {
					for (int l = 1; l < N + 1; l++) {
						int d1 = k, d2 = l;
						if (x < x + d1 + d2 && x + d1 + d2 <= N && 1 <= y - d1 && y - d1 < y && y < y + d2
								&& y + d2 <= N) {
							cityMax = 0;
							cityMin = Integer.MAX_VALUE;

							line = new boolean[N + 1][N + 1];
							drawLine(x, y, d1, d2);
							countPopulation(x, y, d1, d2);
						}
					}
				}
			}
		}
		System.out.println(MIN);

		sc.close();
	}

	private static void drawLine(int x, int y, int d1, int d2) {
		for (int i = 0; i <= d1; i++) {
			line[x + i][y - i] = true;
		}
		for (int i = 0; i <= d2; i++) {
			line[x + i][y + i] = true;
		}
		for (int i = 0; i <= d2; i++) {
			line[x + d1 + i][y - d1 + i] = true;
		}
		for (int i = 0; i <= d1; i++) {
			line[x + d2 + i][y + d2 - i] = true;
		}

		for (int i = x + 1; i < x + d1 + d2; i++) {
			boolean flag = false;
			for (int j = 1; j < N + 1; j++) {
				if (line[i][j] && !flag) {
					flag = true;
				} else if (line[i][j] && flag) {
					flag = false;
					break;
				}
				if (flag) {
					line[i][j] = true;
				}
			}
		}
	}

	private static void countPopulation(int x, int y, int d1, int d2) {
		int population = 0;
		for (int i = 1; i < x + d1; i++) {
			for (int j = 1; j <= y; j++) {
				if (!line[i][j])
					population += map[i][j];
			}
		}
		cityMax = Math.max(population, cityMax);
		cityMin = Math.min(population, cityMin);

		population = 0;
		for (int i = 1; i <= x + d2; i++) {
			for (int j = y + 1; j <= N; j++) {
				if (!line[i][j])
					population += map[i][j];
			}
		}
		cityMax = Math.max(population, cityMax);
		cityMin = Math.min(population, cityMin);
		population = 0;
		for (int i = x + d1; i <= N; i++) {
			for (int j = 1; j < y - d1 + d2; j++) {
				if (!line[i][j])
					population += map[i][j];
			}
		}
		cityMax = Math.max(population, cityMax);
		cityMin = Math.min(population, cityMin);
		population = 0;
		for (int i = x + d2 + 1; i <= N; i++) {
			for (int j = y - d1 + d2; j <= N; j++) {
				if (!line[i][j])
					population += map[i][j];
			}
		}
		cityMax = Math.max(population, cityMax);
		cityMin = Math.min(population, cityMin);

		population = 0;
		for (int i = 1; i < N + 1; i++) {
			for (int j = 1; j < N + 1; j++) {
				if (line[i][j])
					population += map[i][j];
			}
		}
		cityMax = Math.max(population, cityMax);
		cityMin = Math.min(population, cityMin);

		MIN = Math.min(MIN, cityMax - cityMin);
	}

}
