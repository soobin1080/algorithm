package 삼성역량기출;

import java.util.Scanner;

public class bj_23289_온풍기안녕 {
	static int R, C, K, W;
	static int[][] map;

	static int dy[] = { 0, 0, -1, 1 };
	static int dx[] = { 1, -1, 0, 0 };

	public static void main(String[] args) {
		// 입력받기
		Scanner sc = new Scanner(System.in);
		R = sc.nextInt();
		C = sc.nextInt();
		K = sc.nextInt();

		map = new int[R][C];

		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				map[i][j] = sc.nextInt();
			}
		}

		// 벽
		W = sc.nextInt();
		for (int i = 0; i < W; i++) {
			int y = sc.nextInt() - 1;
			int x = sc.nextInt() - 1;
			int t = sc.nextInt();
		}

		// 온풍기 바람보내기

	}

}
