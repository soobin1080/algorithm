package 시뮬레이션;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class bj_11559_puyopuyo {
	static int R = 12, C = 6;
	static char[][] map = new char[R][C];
	static boolean[][] visited;
	static int dy[] = { -1, 1, 0, 0 };
	static int dx[] = { 0, 0, -1, 1 };
	static int MAX = 0;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		// 입력받기
		for (int i = 0; i < R; i++) {
			map[i] = br.readLine().toCharArray();
		}

		while (true) {
			// 뿌요터트리기
			// 1. 4개이상 같은색
			// 2. 여러 그룹 터져도 연쇄는 1번으로 친다

			boolean change = false;
			visited = new boolean[R][C];
			for (int i = 0; i < R; i++) {
				for (int j = 0; j < C; j++) {
					if (map[i][j] != '.' && !visited[i][j]) {
						if (bfs(i, j)) {
							change = true;
						}
					}
				}
			}

			if (change) {
				MAX++;
				// 터졌으면 중력 적용하기
				for (int j = 0; j < C; j++) {
					int gravity = 0;
					boolean flag = false;
					for (int i = R - 1; i >= 0; i--) {
						if (map[i][j] == '.') {
							gravity++;
							if (!flag) {
							}
						} else {
							if (i + gravity < R && gravity!=0) {
								map[i + gravity][j] = map[i][j];
								map[i][j] = '.';
							}
						}
					}
				}
			} else
				break;
		}

		System.out.println(MAX);
	}

	private static boolean bfs(int i, int j) {

		int bomb = 0;
		Queue<Point> p = new LinkedList<>();
		Queue<Point> remove = new LinkedList<>();
		char start = map[i][j];
		p.add(new Point(i, j));
		remove.add(new Point(i, j));
		visited[i][j] = true;

		int puyo = 1;
		while (!p.isEmpty()) {
			Point now = p.poll();

			for (int k = 0; k < 4; k++) {
				int ny = now.y + dy[k];
				int nx = now.x + dx[k];

				if (ny >= 0 && ny < R && nx >= 0 && nx < C && !visited[ny][nx]) {
					if (start == map[ny][nx]) {
						p.add(new Point(ny, nx));
						remove.add(new Point(ny, nx));
						puyo++;
						visited[ny][nx] = true;
					}
				}
			}
		}
		// 뿌요터트리기
		if (puyo >= 4) {
			while (!remove.isEmpty()) {
				Point now = remove.poll();
				map[now.y][now.x] = '.';
			}
			bomb++;
		}

		if (bomb == 0)
			return false;
		else
			return true;
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
