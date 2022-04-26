package 시뮬레이션;

import java.util.Scanner;

public class bj_18808_스티커붙이기 {

	static int map[][];
	static int N, M, K;
	static int sticker[][];

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		K = sc.nextInt();

		map = new int[N][M];
		for (int i = 0; i < K; i++) {

			// 스티커 입력받기
			int n = sc.nextInt();
			int m = sc.nextInt();

			sticker = new int[n][m];
			for (int j = 0; j < n; j++) {
				for (int k = 0; k < m; k++) {
					sticker[j][k] = sc.nextInt();
				}
			}
			for (int rotateCnt = 0; rotateCnt < 4; rotateCnt++) {

				// 1. 스티커 붙일 수 있는 공간 찾기
				boolean flag = findAttachArea(sticker.length, sticker[0].length);
				// 2. 없으면 90도 회전시키기 -> 다시 공간 찾기
				if (!flag) {
					rotateMap(sticker.length, sticker[0].length);
				} else {
					break;
				}
			}

		}

		// 스티커 붙은 칸 세기
		int answer = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] == 1)
					answer++;
			}
		}
		System.out.println(answer);
	}

	private static void rotateMap(int n, int m) {
		int[][] copyMap = new int[n][m];

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				copyMap[i][j] = sticker[i][j];
			}
		}
		if (m != n) {// 직사각형일때
			sticker = new int[m][n];
		}
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				sticker[j][n - i - 1] = copyMap[i][j];
			}
		}
	}

	private static boolean findAttachArea(int n, int m) {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				boolean attach = true;
				a: for (int k = 0; k < n; k++) {// 스티커 배열
					for (int l = 0; l < m; l++) {
						int ny = k + i;
						int nx = l + j;
						if (ny >= 0 && ny < N && nx >= 0 && nx < M) {
							if (map[ny][nx] == 1 && sticker[k][l] == 1) {
								attach = false;
							}
						} else
							attach = false;
						if (!attach)
							break a;
					}
				}

				if (attach) {
					for (int k = 0; k < n; k++) {
						for (int l = 0; l < m; l++) {
							if (map[i + k][j + l] == 0)
								map[i + k][j + l] = sticker[k][l];
						}
					}
					return true;
				}
			}
		}
		return false;

	}

	private static void printMap(int n, int m, int[][] sticker) {
		if (n != m) {
			for (int i = 0; i < m; i++) {
				for (int j = 0; j < n; j++) {
					System.out.print(sticker[i][j]);
				}
				System.out.println();
			}
			System.out.println();
		} else
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < m; j++) {
					System.out.print(sticker[i][j]);
				}
				System.out.println();
			}
		System.out.println();
	}

}
