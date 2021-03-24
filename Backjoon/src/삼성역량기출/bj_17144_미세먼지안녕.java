package 삼성역량기출;

import java.util.Scanner;

public class bj_17144_미세먼지안녕 {
	static int[] dy = { -1, 1, 0, 0 };
	static int[] dx = { 0, 0, -1, 1 };

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int R = sc.nextInt();
		int C = sc.nextInt();
		int T = sc.nextInt();

		int[][] map = new int[R][C];

		int topOfAircleaner = 0;
		int bottomOfAircleaner = 0;
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				map[i][j] = sc.nextInt();
				if (map[i][j] == -1)
					if (topOfAircleaner == 0)
						topOfAircleaner = i;
					else
						bottomOfAircleaner = i;
			}
		}

		for (int tc = 0; tc < T; tc++) {

			int[][] diffusion = new int[R][C];

			// 미세먼지 확산
			for (int i = 0; i < R; i++) {
				for (int j = 0; j < C; j++) {
					if (map[i][j] > 0) {
						int countOfArea = 0; // 확산된 칸 수
						for (int k = 0; k < 4; k++) {
							int iy = i + dy[k];
							int ix = j + dx[k];
							if (iy < R && iy >= 0 && ix < C && ix >= 0 && map[iy][ix] != -1) {
								diffusion[iy][ix] += (map[i][j] / 5);
								countOfArea++;
							}
						}
						map[i][j] -= (map[i][j] / 5 * countOfArea);
						if(map[i][j]<0)
							map[i][j]=0;
					}
				}
			}

			for (int i = 0; i < R; i++) {
				for (int j = 0; j < C; j++) {
					map[i][j] += diffusion[i][j];
				}
			}

			
			// 공기청정기
			// top
			// 아래쪽 이동
			for (int i = topOfAircleaner - 1; i >= 0; i--) {
				if (map[i + 1][0] == -1)
					continue;
				map[i + 1][0] = map[i][0];
			}
			// 왼쪽 이동
			for (int i = 1; i < C; i++) {
				map[0][i - 1] = map[0][i];
			}
			// 위쪽 이동
			for (int i = 1; i <= topOfAircleaner; i++) {
				map[i - 1][C - 1] = map[i][C - 1];
			}
			// 오른쪽 이동
			for (int i = C - 2; i >= 1; i--) {
				map[topOfAircleaner][i + 1] = map[topOfAircleaner][i];
			}
			map[topOfAircleaner][1]=0;

			// bottom
			// 위쪽 이동
			for (int i = bottomOfAircleaner + 1; i < R; i++) {
				if (map[i -1][0] == -1)
					continue;
				map[i - 1][0] = map[i][0];
			}
			// 왼쪽 이동
			for (int i = 1; i < C; i++) {
				map[R - 1][i - 1] = map[R - 1][i];
			}
			// 아래쪽 이동
			for (int i = R - 2; i >= bottomOfAircleaner; i--) {
				map[i + 1][C - 1] = map[i][C - 1];
			}
			// 오른쪽 이동
			for (int i = C - 2; i >= 1; i--) {
				map[bottomOfAircleaner][i + 1] = map[bottomOfAircleaner][i];
			}
			map[bottomOfAircleaner][1]=0;
			
			
		}
		
		
		int sum = 0;
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if(map[i][j]>0)
				sum+=map[i][j];
			}
		}
		System.out.println(sum);

		sc.close();
	}

}
