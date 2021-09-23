package 삼성역량기출;

import java.util.Scanner;

public class bj_21610_마법사상어와비바라기 {
	static int[] dy = { 0, 0, -1, -1, -1, 0, 1, 1, 1 };
	static int[] dx = { 0, -1, -1, 0, 1, 1, 1, 0, -1 };
	static boolean[][] cloudMap; // 구름 있는곳
	static int[][] map; // 바구니 안에 물 양
	static int[][] moveInfo;
	static int N, M;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();

		map = new int[N][N];
		// 바구니 안에 있는 물의 양 입력받기
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				map[i][j] = sc.nextInt();
			}
		}

		moveInfo = new int[M][2];// d:[i][0] , s:[i][1]
		for (int i = 0; i < M; i++) {
			moveInfo[i][0] = sc.nextInt();
			moveInfo[i][1] = sc.nextInt();
		}

		cloudMap = new boolean[N][N];
		// 비바라기 시전
		cloudMap[N - 1][0] = cloudMap[N - 1][1] = cloudMap[N - 2][0] = cloudMap[N - 2][1] = true;
		for (int i = 0; i < M; i++) {
			int d = moveInfo[i][0];
			int s = moveInfo[i][1];
			// 구름 이동 & 비내리기
			moveCloud(d, s);
			// 물복사버그 마법
			copyWater();
			// 구름 생성
			makeCloud();
		}
		int sum = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				sum += map[i][j];
			}
		}
		System.out.println(sum);
	}

	private static void printMap() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
	}

	private static void makeCloud() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (map[i][j] >= 2 && !cloudMap[i][j]) {
					map[i][j] -= 2;
					cloudMap[i][j] = true;
				} else if (cloudMap[i][j])
					cloudMap[i][j] = false;
			}
		}
	}

	private static void copyWater() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				// 비구름 있었던곳이면
				if (cloudMap[i][j]) {
					// 대각선으로 검사
					int count = 0;
					for (int k = 2; k <= 8; k += 2) {
						int iy = dy[k] + i;
						int ix = dx[k] + j;
						if (iy >= 0 && iy < N && ix >= 0 && ix < N && map[iy][ix] > 0) {
							count++;
						}
					}
					map[i][j] += count;
				}
			}
		}
	}

	private static void moveCloud(int d, int s) {
		boolean[][] newCloudMap = new boolean[N][N];

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				newCloudMap[i][j] = cloudMap[i][j];
			}
		}

		// 구름 이동
		cloudMap=new boolean[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (newCloudMap[i][j]) {
					
					int iy = i, ix = j;
					for (int k = 0; k < s; k++) {
						iy += dy[d];
						ix += dx[d];
						if (iy == N)
							iy = 0;
						else if (iy == -1)
							iy = N - 1;
						if (ix == N)
							ix = 0;
						else if (ix == -1)
							ix = N - 1;
					}
					cloudMap[iy][ix] = true;
					//비내리기
					map[iy][ix]++;
				}
			}
		}
	}

}
