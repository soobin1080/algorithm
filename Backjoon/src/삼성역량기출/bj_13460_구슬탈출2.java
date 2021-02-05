package 삼성역량기출;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class bj_13460_구슬탈출2 {

	static int[] dy = { -1, 1, 0, 0 };
	static int[] dx = { 0, 0, -1, 1 };
	static char[][] map;
	static boolean[][] visited;

	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int r = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());
		map = new char[r][c];
		visited = new boolean[r][c];
		Ball ball = new Ball(0, 0, 0, 0, 0);

		int hy = 0, hx = 0;

		for (int i = 0; i < r; i++) {
			String s = br.readLine();
			for (int j = 0; j < c; j++) {
				map[i][j] = s.charAt(j);
				System.out.print(map[i][j]);
				if (map[i][j] == 'R') {
					ball.ry = i;
					ball.rx = j;
				} else if (map[i][j] == 'B') {
					ball.by = i;
					ball.bx = j;
				} else if (map[i][j] == 'O') {
					hy = i;
					hx = j;
				}
			}
			System.out.println();
		}

		Queue<Ball> qu = new LinkedList<Ball>();

		qu.add(ball);
		int count = 0;
		boolean flag = false;

		a: while (!qu.isEmpty()) {
			Ball now = qu.poll();

			count = now.count;
			if (count > 10) {
				count = -1;
				break;
			}
			for (int i = 0; i < 4; i++) {
				Ball newBall = new Ball(0, 0, 0, 0, 0);
				boolean red = false;
				boolean blue = false;
				// 빨간볼 움직이기
				int iy = now.ry + dy[i];
				int ix = now.rx + dx[i];

				if (iy >= 0 && iy < r && ix >= 0 && ix < c) {
					newBall.ry = now.ry;
					newBall.rx = now.rx;
					
					if (map[iy][ix] == '.') {
						newBall.ry = iy;
						newBall.rx = ix;

						map[iy][ix] = 'R';
						map[now.ry][now.rx] = '.';
						red = true;
					} else if (map[iy][ix] == 'O') {
						flag = true;
					}
				}

				// 파란볼 움직이기
				iy = now.by + dy[i];
				ix = now.bx + dx[i];

				if (iy >= 0 && iy < r && ix >= 0 && ix < c) {
					newBall.by = now.by;
					newBall.bx = now.bx;

					if (map[iy][ix] == '.') {
						newBall.by = iy;
						newBall.bx = ix;

						map[iy][ix] = 'B';
						map[now.by][now.bx] = '.';
						blue = true;
					} else if (map[iy][ix] == 'O') {
						flag = false;
					}
				}

				if (flag) {
					count = now.count++;
					break a;
				}

				if (red || blue) {
					newBall.count = now.count++;
					qu.add(newBall);
				}
			}

		}
		System.out.println(count);

	}

	static class Ball {
		int ry;
		int rx;
		int by;
		int bx;
		int count;

		Ball(int ry, int rx, int by, int bx, int count) {
			this.ry = ry;
			this.rx = rx;
			this.by = by;
			this.bx = bx;
			this.count = count;
		}
	}

}
