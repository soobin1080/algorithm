package 삼성역량기출;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class bj_13460_구슬탈출2 {
	static int N, M, MIN = Integer.MAX_VALUE;
	static char[][] map;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new char[N][M];

		int ry = 0, rx = 0, by = 0, bx = 0;
		for (int i = 0; i < N; i++) {
			String string = br.readLine();
			for (int j = 0; j < M; j++) {
				map[i][j] = string.charAt(j);
				if (map[i][j] == 'R') {
					ry = i;
					rx = j;
				} else if (map[i][j] == 'B') {
					by = i;
					bx = j;
				}
			}
		}

		escape(map, ry, rx, by, bx, 0);
		if (MIN > 10)
			MIN = -1;
		System.out.println(MIN);

	}

	private static void escape(char[][] map2, int RY, int RX, int BY, int BX, int moveCount) {
		if (moveCount > 10)
			return;

		for (int dir = 1; dir <= 4; dir++) {
			char[][] copyMap = new char[N][M];

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					copyMap[i][j] = map2[i][j];
				}
			}
			boolean r = false, b = false;
			int ry = RY, rx = RX, by = BY, bx = BX;
			// 움직이기 상-1 하-2 좌-3 우-4
			switch (dir) {
			case 1:
				if (ry > by) {
					// B구슬부터 움직이기
					while (by - 1 > 0 && by - 1 < N - 1) {
						if (copyMap[by - 1][bx] == '.') {
							by--;
						} else if (copyMap[by - 1][bx] == 'O') {
							b = true;
							by--;
							break;
						} else
							break;
					}
					copyMap[BY][BX] = '.';
					if (map2[by][bx] == 'O')
						copyMap[by][bx] = 'O';
					else
						copyMap[by][bx] = 'B';
					while (ry - 1 > 0 && ry - 1 < N - 1) {
						if (copyMap[ry - 1][rx] == '.') {
							ry--;
						} else if (copyMap[ry - 1][rx] == 'O') {
							r = true;
							ry--;
							break;
						} else
							break;
					}
					copyMap[RY][RX] = '.';
					if (map2[ry][rx] == 'O')
						copyMap[ry][rx] = 'O';
					else
						copyMap[ry][rx] = 'R';
				} else {// R구슬부터 움직이기
					while (ry - 1 > 0 && ry - 1 < N - 1) {
						if (copyMap[ry - 1][rx] == '.') {
							ry--;
						} else if (copyMap[ry - 1][rx] == 'O') {
							r = true;
							ry--;
							break;
						} else
							break;
					}
					copyMap[RY][RX] = '.';
					if (map2[ry][rx] == 'O')
						copyMap[ry][rx] = 'O';
					else
						copyMap[ry][rx] = 'R';
					while (by - 1 > 0 && by - 1 < N - 1) {
						if (copyMap[by - 1][bx] == '.') {
							by--;
						} else if (copyMap[by - 1][bx] == 'O') {
							b = true;
							by--;
							break;
						} else
							break;
					}
					copyMap[BY][BX] = '.';
					if (map2[by][bx] == 'O')
						copyMap[by][bx] = 'O';
					else
						copyMap[by][bx] = 'B';
				}
				break;
			case 2:
				if (ry < by) {
					// B구슬부터 움직이기
					while (by + 1 > 0 && by + 1 < N - 1) {
						if (copyMap[by + 1][bx] == '.') {
							by++;
						} else if (copyMap[by + 1][bx] == 'O') {
							b = true;
							by++;
							break;
						} else
							break;
					}
					copyMap[BY][BX] = '.';
					if (map2[by][bx] == 'O')
						copyMap[by][bx] = 'O';
					else
						copyMap[by][bx] = 'B';
					while (ry + 1 > 0 && ry + 1 < N - 1) {
						if (copyMap[ry + 1][rx] == '.') {
							ry++;
						} else if (copyMap[ry + 1][rx] == 'O') {
							r = true;
							ry++;
							break;
						} else
							break;
					}
					copyMap[RY][RX] = '.';
					if (map2[ry][rx] == 'O')
						copyMap[ry][rx] = 'O';
					else
						copyMap[ry][rx] = 'R';
				} else {// R구슬부터 움직이기
					while (ry + 1 > 0 && ry + 1 < N - 1) {
						if (copyMap[ry + 1][rx] == '.') {
							ry++;
						} else if (copyMap[ry + 1][rx] == 'O') {
							r = true;
							ry++;
							break;
						} else
							break;
					}
					copyMap[RY][RX] = '.';
					if (map2[ry][rx] == 'O')
						copyMap[ry][rx] = 'O';
					else
						copyMap[ry][rx] = 'R';
					while (by + 1 > 0 && by + 1 < N - 1) {
						if (copyMap[by + 1][bx] == '.') {
							by++;
						} else if (copyMap[by + 1][bx] == 'O') {
							b = true;
							by++;
							break;
						} else
							break;
					}
					copyMap[BY][BX] = '.';
					if (map2[by][bx] == 'O')
						copyMap[by][bx] = 'O';
					else
						copyMap[by][bx] = 'B';
				}
				break;
			case 3:
				if (rx > bx) {
					// B구슬부터 움직이기
					while (bx - 1 > 0 && bx - 1 < M - 1) {
						if (copyMap[by][bx - 1] == '.') {
							bx--;
						} else if (copyMap[by][bx - 1] == 'O') {
							b = true;
							bx--;
							break;
						} else
							break;
					}
					copyMap[BY][BX] = '.';
					if (map2[by][bx] == 'O')
						copyMap[by][bx] = 'O';
					else
						copyMap[by][bx] = 'B';
					while (rx - 1 > 0 && rx - 1 < M - 1) {
						if (copyMap[ry][rx - 1] == '.') {
							rx--;
						} else if (copyMap[ry][rx - 1] == 'O') {
							r = true;
							rx--;
							break;
						} else
							break;
					}
					copyMap[RY][RX] = '.';
					if (map2[ry][rx] == 'O')
						copyMap[ry][rx] = 'O';
					else
						copyMap[ry][rx] = 'R';
				} else {// R구슬부터 움직이기
					while (rx - 1 > 0 && rx - 1 < M - 1) {
						if (copyMap[ry][rx - 1] == '.') {
							rx--;
						} else if (copyMap[ry][rx - 1] == 'O') {
							r = true;
							rx--;
							break;
						} else
							break;
					}
					copyMap[RY][RX] = '.';
					if (map2[ry][rx] == 'O')
						copyMap[ry][rx] = 'O';
					else
						copyMap[ry][rx] = 'R';
					while (bx - 1 > 0 && bx - 1 < M - 1) {
						if (copyMap[by][bx - 1] == '.') {
							bx--;
						} else if (copyMap[by][bx - 1] == 'O') {
							b = true;
							bx--;
							break;
						} else
							break;
					}
					copyMap[BY][BX] = '.';
					if (map2[by][bx] == 'O')
						copyMap[by][bx] = 'O';
					else copyMap[by][bx] = 'B';
				}
				break;
			case 4:
				if (rx < bx) {
					// B구슬부터 움직이기
					while (bx + 1 > 0 && bx + 1 < M - 1) {
						if (copyMap[by][bx + 1] == '.') {
							bx++;
						} else if (copyMap[by][bx + 1] == 'O') {
							b = true;
							bx++;
							break;
						} else
							break;
					}
					copyMap[BY][BX] = '.';
					if (map2[by][bx] == 'O')
						copyMap[by][bx] = 'O';
					else copyMap[by][bx] = 'B';
					while (rx + 1 > 0 && rx + 1 < M - 1) {
						if (copyMap[ry][rx + 1] == '.') {
							rx++;
						} else if (copyMap[ry][rx + 1] == 'O') {
							r = true;
							rx++;
							break;
						} else
							break;
					}
					copyMap[RY][RX] = '.';
					if (map2[ry][rx] == 'O')
						copyMap[ry][rx] = 'O';
					else copyMap[ry][rx] = 'R';
				} else {// R구슬부터 움직이기
					while (rx + 1 > 0 && rx + 1 < M - 1) {
						if (copyMap[ry][rx + 1] == '.') {
							rx++;
						} else if (copyMap[ry][rx + 1] == 'O') {
							r = true;
							rx++;
							break;
						} else
							break;
					}
					copyMap[RY][RX] = '.';
					if (map2[ry][rx] == 'O')
						copyMap[ry][rx] = 'O';
					else copyMap[ry][rx] = 'R';
					while (bx + 1 > 0 && bx + 1 < M - 1) {
						if (copyMap[by][bx + 1] == '.') {
							bx++;
						} else if (copyMap[by][bx + 1] == 'O') {
							b = true;
							bx++;
							break;
						} else
							break;
					}
					copyMap[BY][BX] = '.';
					if (map2[by][bx] == 'O')
						copyMap[by][bx] = 'O';
					else
						copyMap[by][bx] = 'B';
				}
				break;
			}

			if (BY == by && BX == bx && RY == ry && RX == rx)
				continue;

			if (!r && !b) {
				/*
				 * for (int i = 0; i < N; i++) { for (int j = 0; j < M; j++) {
				 * System.out.print(copyMap[i][j]); } System.out.println(); }
				 * System.out.println("ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ");
				 */
				escape(copyMap, ry, rx, by, bx, moveCount + 1);
			} else if (r && !b) {
				MIN = Math.min(MIN, moveCount + 1);
				continue;
			} else
				continue;
		}
	}
}
