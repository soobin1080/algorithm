package 삼성역량기출;

import java.util.ArrayList;
import java.util.Scanner;

public class bj_15686_치킨배달_RE {

	static int N, M;
	static int[][] map;
	static int[][] minDistance;
	static ArrayList<Point> chicken = new ArrayList<>();
	static boolean used[];
	static int[] picked;
	static int MIN = Integer.MAX_VALUE;

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();
		M = sc.nextInt();

		map = new int[N][N];

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				map[i][j] = sc.nextInt();
				if (map[i][j] == 2)
					chicken.add(new Point(i, j));
			}
		}

		picked = new int[M];
		used = new boolean[chicken.size()];
		// 치킨집 M개 고르기
		Combi(0, 0);

		// 도시의 치킨 거리의 최솟값 구하기
		System.out.println(MIN);
	}

	private static void Combi(int n, int r) {
		if (r == M) {
			// 치킨 거리구하기
			minDistance = new int[N][N];
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (map[i][j] == 1) { // 집이면
						for (int k = 0; k < M; k++) {
							int index = picked[k];
							int y = Math.abs(i - chicken.get(index).y);
							int x = Math.abs(j - chicken.get(index).x);

							int d = y + x;
							if (minDistance[i][j] == 0)
								minDistance[i][j] = d;
							else {
								if (minDistance[i][j] > d)
									minDistance[i][j] = d;
							}

						}

					}
				}
			}
			// 도시의 치킨 거리 구하기 min이랑 비교
			int city = 0;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					city += minDistance[i][j];
				}
			}

			MIN = (MIN > city) ? city : MIN;
			
			return;
		}

		for (int i = n; i < chicken.size(); i++) {
			if (!used[i]) {
				used[i] = true;
				picked[r] = i;
				Combi(i + 1, r + 1);
				used[i] = false;
			}
		}
	}

	static class Point {
		int y;
		int x;

		Point(int y, int x) {
			this.y = y;
			this.x = x;
		}
	}

}
