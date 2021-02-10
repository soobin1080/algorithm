package 삼성역량기출;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class bj_14503_로봇청소기 {

	static int[] dy = { 0, -1, 0, 1 }; // 로봇청소기의 왼쪽
	static int[] dx = { -1, 0, 1, 0 };

	static int[] sy = { 1, 0, -1, 0 };// 로봇청소기 후진
	static int[] sx = { 0, -1, 0, 1 };
	static int N, M;

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();

		int y = sc.nextInt();
		int x = sc.nextInt();
		int d = sc.nextInt();

		int[][] map = new int[N][M];
		boolean[][] visited = new boolean[N][M];

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				map[i][j] = sc.nextInt();
				if (map[i][j] == 1) {
					visited[i][j] = true;
				}
			}
		}

		Queue<Point> qu = new LinkedList<>();
		int count = 1;// 시작점 청소
		visited[y][x]=true;
		qu.add(new Point(y, x, d));

		while (!qu.isEmpty()) {

			Point point = qu.poll();
			int nd = point.d;
			boolean move = false;
			for (int i = 0; i < 4; i++) {
				
				int ny = point.y + dy[nd];
				int nx = point.x + dx[nd];
				
				nd--;
				if (nd == -1)
					nd = 3;
				
				if (ny >= 0 && ny < N && nx >= 0 && nx < M && !visited[ny][nx]) {
					visited[ny][nx] = true;
					count++;
					move = true;
					qu.add(new Point(ny, nx, nd));
					break;
				}
			}

			if (!move) {

				int ny = point.y + sy[nd];
				int nx = point.x + sx[nd];

				if (ny >= 0 && ny < N && nx >= 0 && nx < M) {
					if (map[ny][nx] == 0) {
						qu.add(new Point(ny, nx, nd));
					}
				}
			}

		}

		System.out.println(count);

	}


	static class Point {
		int y;
		int x;
		int d;

		Point(int y, int x, int d) {
			this.y = y;
			this.x = x;
			this.d = d;

		}
	}
}
