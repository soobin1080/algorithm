package 삼성역량기출;

import java.util.Scanner;

public class bj_14500_테트로미노 {
	static int N, M, MAX = 0;
	static int[][] map;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();
		M = sc.nextInt();

		map = new int[N][M];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				map[i][j] = sc.nextInt();
			}
		}

		// 1번 테트로미노
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (j < M - 3) {
					int sum = 0;
					for (int x = j; x < j + 4; x++) {
						sum += map[i][x];
					}
					MAX = Math.max(sum, MAX);
				}
				if (i < N - 3) {
					int sum = 0;
					for (int y = i; y < i + 4; y++) {
						sum += map[y][j];
					}
					MAX = Math.max(sum, MAX);
				}
			}
		}
		// 2번 테트로미노
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (i < N - 1 && j < M - 1) {
					int sum = 0;
					for (int y = 0; y < 2; y++) {
						for (int x = 0; x < 2; x++) {
							sum += map[i + y][j + x];
						}
					}
					MAX = Math.max(sum, MAX);
				}
			}
		}
		// 3번 테트로미노
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (j < M - 2) {
					int sum = 0;
					for (int x = j; x < j + 3; x++) {
						sum += map[i][x];
					}

					if (range(i - 1, j)) {
						MAX = Math.max(sum + map[i - 1][j], MAX);
					}
					if (range(i + 1, j)) {
						MAX = Math.max(sum + map[i + 1][j], MAX);
					}

					if (range(i - 1, j + 2)) {
						MAX = Math.max(sum + map[i - 1][j + 2], MAX);
					}
					if (range(i + 1, j + 2)) {
						MAX = Math.max(sum + map[i + 1][j + 2], MAX);
					}

				}

				if (i < N - 2) {
					int sum = 0;
					for (int y = i; y < i + 3; y++) {
						sum += map[y][j];
					}

					if (range(i, j - 1)) {
						MAX = Math.max(sum + map[i][j - 1], MAX);
					}
					if (range(i, j + 1)) {
						MAX = Math.max(sum + map[i][j + 1], MAX);
					}

					if (range(i + 2, j - 1)) {
						MAX = Math.max(sum + map[i + 2][j - 1], MAX);
					}
					if (range(i + 2, j + 1)) {
						MAX = Math.max(sum + map[i + 2][j + 1], MAX);
					}

				}
			}
		}
		// 4번
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (j < M - 1) {
					int sum = 0;
					for (int x = j; x < j + 2; x++) {
						sum += map[i][x];
					}
					if (range(i - 1, j) && range(i + 1, j + 1)) {
						MAX = Math.max(sum + map[i - 1][j] + map[i + 1][j + 1], MAX);
					}
					if (range(i + 1, j) && range(i - 1, j + 1)) {
						MAX = Math.max(sum + map[i + 1][j] + map[i - 1][j + 1], MAX);
					}
				}

				if (i < N - 1) {
					int sum = 0;
					for (int y = i; y < i + 2; y++) {
						sum += map[y][j];
					}
					if (range(i, j - 1) && range(i + 1, j + 1)) {
						MAX = Math.max(sum + map[i][j - 1] + map[i + 1][j + 1], MAX);
					}
					if (range(i, j + 1) && range(i + 1, j - 1)) {
						MAX = Math.max(sum + map[i][j + 1] + map[i + 1][j - 1], MAX);
					}
				}
			}
		}
		// 5번
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (j < M - 2) {
					int sum = 0;
					for (int x = j; x < j + 3; x++) {
						sum += map[i][x];
					}
					if (range(i - 1, j + 1))
						MAX = Math.max(sum + map[i - 1][j + 1], MAX);
					if (range(i + 1, j + 1))
						MAX = Math.max(sum + map[i + 1][j + 1], MAX);
				}
				if (i < N - 2) {
					int sum = 0;
					for (int y = i; y < i + 3; y++) {
						sum += map[y][j];
					}
					if (range(i + 1, j - 1))
						MAX = Math.max(sum + map[i + 1][j - 1], MAX);
					if (range(i + 1, j + 1))
						MAX = Math.max(sum + map[i + 1][j + 1], MAX);
				}
			}
		}
		System.out.println(MAX);
		sc.close();
	}

	private static boolean range(int y, int x) {
		if (y >= 0 && y < N && x >= 0 && x < M)
			return true;
		return false;
	}

}
