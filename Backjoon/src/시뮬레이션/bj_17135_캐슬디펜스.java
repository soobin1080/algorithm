package 시뮬레이션;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class bj_17135_캐슬디펜스 {

	static int N, M, D;
	static int map[][];
	static boolean archer[];
	static int MAX = 0;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();
		M = sc.nextInt();
		D = sc.nextInt();

		map = new int[N][M];
		archer = new boolean[M];

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				map[i][j] = sc.nextInt();
			}
		}

		// 성벽에 궁수 배치, 조합
		arrangeArchers(0, 0, 3);

		System.out.println(MAX);

	}

	private static void printMap(int[][] map2) {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				System.out.print(map2[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println("============================");
	}

	static void arrangeArchers(int idx, int r, int n) {

		if (idx > M)
			return;
		if (r == n) {
			killEnemy();
			return;
		}

		for (int i = idx; i < archer.length; i++) {

			archer[i] = true;
			arrangeArchers(i + 1, r + 1, n);
			archer[i] = false;

		}
	}

	static void killEnemy() {
		int copyMap[][] = new int[N][M];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				copyMap[i][j] = map[i][j];
			}
		}

		int killEnemy = 0;

		// 적 N번 이동
		for (int i = 0; i < N; i++) {
			Queue<Integer> Y = new LinkedList<>();
			Queue<Integer> X = new LinkedList<>();

			for (int idx = 0; idx < archer.length; idx++) {
				int enemyY = Integer.MAX_VALUE;
				int enemyX = Integer.MAX_VALUE;
				int minD = Integer.MAX_VALUE;
				if (archer[idx]) {
					for (int j = 0; j < N; j++) {
						for (int k = 0; k < M; k++) {
							if (copyMap[j][k] == 1) {
								int d = Math.abs(j - N) + Math.abs(k - idx);

								if (minD >= d) {
									if (d <= D) {
										if (minD > d) {
											minD = d;
											enemyY = j;
											enemyX = k;
										} else {
											if (enemyX > k) {
												enemyY = j;
												enemyX = k;
											}
										}
									}
								}
							}
						}
					}

					Y.add(enemyY);
					X.add(enemyX);
				}
			}
			int size = Y.size();
			// 적 제거, 좌표에서 제거
			for (int j = 0; j < size; j++) {
				int y = Y.poll();
				int x = X.poll();
				if (y < N && x < M && copyMap[y][x] == 1) {
					copyMap[y][x] = 0;
					killEnemy++;
				}
			}

			// 적 이동, 맵 밖으로 이동하면 제외
			for (int k = 0; k < M; k++) {
				copyMap[N - 1][k] = 0;
			}
			for (int j = N - 2; j >= 0; j--) {
				for (int k = 0; k < M; k++) {
					copyMap[j + 1][k] = copyMap[j][k];
				}
			}
			for (int k = 0; k < M; k++) {
				copyMap[0][k] = 0;
			}

			MAX = (killEnemy > MAX) ? killEnemy : MAX;
		}
	}
}
