package 삼성역량기출;

import java.util.Scanner;

public class bj_17822_원판돌리기 {
	static int N, M, T;
	static int[][] map;
	static int[] dy = { -1, 1, 0, 0 };
	static int[] dx = { 0, 0, -1, 1 };

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();
		M = sc.nextInt();
		T = sc.nextInt();
		map = new int[N + 1][M];
		for (int i = 1; i < N + 1; i++) {
			for (int j = 0; j < M; j++) {
				map[i][j] = sc.nextInt();
			}
		}

		for (int i = 0; i < T; i++) {
			rotate(sc.nextInt(), sc.nextInt(), sc.nextInt());
			findNumber();
		}

		int sum = 0;
		for (int i = 1; i < N + 1; i++) {
			for (int j = 0; j < M; j++) {
				sum += map[i][j];
			}
		}
		System.out.println(sum);
		sc.close();
	}

	private static void calculateMap() {
		int sum = 0, count = 0;
		for (int i = 1; i < N + 1; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] != 0) {
					sum += map[i][j];
					count++;
				}
			}
		}

		double average = (sum * 1.0) / count;

		for (int i = 1; i < N + 1; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] > average) {
					map[i][j]--;
				} else if (map[i][j]>=0 && map[i][j] < average) {
					map[i][j]++;
				}
			}
		}
	}

	private static void findNumber() {
		boolean change = false;
		int[][] copyMap = new int[N + 1][M];
		for (int i = 1; i < N + 1; i++) {
			for (int j = 0; j < M; j++) {
				copyMap[i][j] = map[i][j];
			}
		}

		for (int i = 1; i < N + 1; i++) {
			for (int j = 0; j < M; j++) {
				int num = copyMap[i][j];
				if (num != 0) {
					int count = 0;
					if (j == 0) {
						if (num == copyMap[i][M - 1]) {
							map[i][M - 1] = 0;
							count++;
						}
					} else if (j == M - 1) {
						if (num == copyMap[i][0]) {
							map[i][0] = 0;
							count++;
						}
					}
					for (int k = 0; k < 4; k++) {
						int y = i + dy[k];
						int x = j + dx[k];
						if (y >= 1 && y < N + 1 && x >= 0 && x < M && num == copyMap[y][x]) {
							map[y][x] = 0;
							count++;
						}

					}
					if (count > 0) {
						map[i][j] = 0;
						change = true;
					}
				}
			}
		}
		if (!change)
			calculateMap();
	}

	private static void rotate(int x, int d, int k) {
		for (int i = x; i < N + 1; i += x) {
			int[] numbers = new int[M];
			for (int j = 0; j < M; j++) {
				numbers[j] = map[i][j];
			}
			if (d == 0) {
				for (int j = 0; j < M; j++) {
					map[i][(j + k) % M] = numbers[j];
				}
			} else {
				for (int j = 0; j < M; j++) {
					map[i][(M + j - k) % M] = numbers[j];
				}
			}
		}
	}
}
