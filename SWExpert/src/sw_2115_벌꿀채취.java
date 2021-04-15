import java.util.Scanner;

public class sw_2115_벌꿀채취 {
	static int N, M, C, MAX;
	static int[][] map;
	static boolean[][] selected;
	static int[][] honeyCapicity;
	static boolean[] used;
	static int cost1, cost2;

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		int TC = sc.nextInt();
		for (int tc = 1; tc <= TC; tc++) {

			N = sc.nextInt();
			M = sc.nextInt();
			C = sc.nextInt();
			MAX = 0;
			cost1 = 0;
			cost2 = 0;
			map = new int[N][N];
			honeyCapicity = new int[2][M];
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					map[i][j] = sc.nextInt();
				}
			}

			honeySelect();
			System.out.println("#" + tc + " " + MAX);
		}
		sc.close();
	}

	private static void honeySelect() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				selected = new boolean[N][N];
				if (N - j > M) {
					for (int l = 0; l < M; l++) {
						selected[i][j + l] = true;
						honeyCapicity[0][l] = map[i][j + l];
					}

					// 두번째 사람
					for (int a = 0; a < N; a++) {
						a: for (int b = 0; b < N; b++) {
							if (!selected[a][b] && N - b > M) {
								// 첫번째 사람이랑 겹치는지 확인
								for (int c = 0; c < M; c++) {
									if (selected[a][b + c])
										continue a;
								}

								for (int c = 0; c < M; c++) {
									selected[a][b + c] = true;
									honeyCapicity[1][c] = map[a][b + c];
								}

								// 벌꿀채취
								for (int k = 1; k < M; k++) {
									used = new boolean[M];
									saveMaxProfit(0, 0, k);
								}

								int cost = cost1 + cost2;
								MAX = (MAX < cost) ? cost : MAX;

								for (int c = 0; c < M; c++) {
									selected[a][b + c] = false;
								}
							}
						}
					}

					for (int l = 0; l < M; l++) {
						selected[i][j + l] = false;
					}
				}
			}
		}
	}

	private static void saveMaxProfit(int idx, int cnt, int n) {
		if (cnt == n) {
			int p1 = 0, p2 = 0, c1 = 0, c2 = 0;

			for (int i = 0; i < M; i++) {
				if (used[i]) {
					p1 += honeyCapicity[0][i];
					p2 += honeyCapicity[1][i];
				}
			}
			if (p1 <= C)
				for (int i = 0; i < M; i++) {
					if (used[i]) {
						c1 += (honeyCapicity[0][i] * honeyCapicity[0][i]);
					}
				}
			if (p2 <= C)
				for (int i = 0; i < M; i++) {
					if (used[i]) {
						c2 += (honeyCapicity[1][i] * honeyCapicity[1][i]);
					}
				}

			cost1 = (cost1 > c1) ? cost1 : c1;
			cost2 = (cost2 > c2) ? cost2 : c2;
			return;
		}

		if (idx < M) {
			used[idx] = true;
			saveMaxProfit(idx + 1, cnt + 1, n);
			used[idx] = false;
			saveMaxProfit(idx + 1, cnt, n);
		}
	}

}