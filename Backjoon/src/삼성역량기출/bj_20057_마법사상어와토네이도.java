package 삼성역량기출;

import java.util.Scanner;

public class bj_20057_마법사상어와토네이도 {

	static int[][] map;
	static int[] dx = { -1, 0, 1, 0 };// 좌,하,우,상
	static int[] dy = { 0, 1, 0, -1 };
	static int N, sum;

	static int[][] sx = { { 1, 1, 0, 0, -2, 0, 0, -1, -1, -1 }, { -1, 1, -2, 2, 0, -1, 1, -1, 1, 0 },
			{ -1, -1, 0, 0, 2, 0, 0, 1, 1, 1 }, { -1, 1, -2, 2, 0, -1, 1, -1, 1, 0 } };
	static int[][] sy = { { -1, 1, -2, 2, 0, -1, 1, -1, 1, 0 }, { -1, -1, 0, 0, 2, 0, 0, 1, 1, 1 },
			{ -1, 1, -2, 2, 0, -1, 1, -1, 1, 0 }, { 1, 1, 0, 0, -2, 0, 0, -1, -1, -1 } };
	static int[] amount = { 1, 1, 2, 2, 5, 7, 7, 10, 10, 0 };

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();

		map = new int[N][N];

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				map[i][j] = sc.nextInt();
			}
		}

		// 토네이도 시작점
		int x = N / 2, y = N / 2;
		boolean out = false;
		int move = 0, dir = 0;
		sum=0;
		// 토네이도
		a: while (!out) {
			move += 1;
			for (int i = 0; i < 2; i++) {
				for (int j = 0; j < move; j++) {
					int ix = x + dx[dir];
					int iy = y + dy[dir];

					if (ix >= 0 && ix < N && iy >= 0 && iy < N) {
						x = ix;
						y = iy;
						// 모래계산
						stackSand(x, y, dir);
					} else {
						out = true;
						break a;
					}
				}
				dir = (dir + 1) % 4;

			}
		}
		System.out.println(sum);
	}

	private static void stackSand(int x, int y, int dir) {
		int sand = map[y][x];
		int left_sand = sand;
		for (int i = 0; i < 10; i++) {
			int ix = x + sx[dir][i];
			int iy = y + sy[dir][i];

			int spread_sand = (sand * amount[i]) / 100;
			left_sand -= spread_sand;
			if (ix >= 0 && ix < N && iy >= 0 && iy < N) {
				if (i != 9) {
					map[iy][ix] += spread_sand;
				} else {
					map[iy][ix] += left_sand;
				}

			} else {
				if(i!=9) {
					sum += spread_sand;
				}else
					sum+=left_sand;
			}
		}
		map[y][x]=0;

	}

	private static void print() {
		// TODO Auto-generated method stub
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				System.out.print(map[i][j]+" ");
			}
			System.out.println();
		}
	}

}
