package 시뮬레이션;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class bj_2638_치즈 {

	static int[][] map;
	static int N, M;
	static boolean[][] visited;

	static int[] dy = { -1, 1, 0, 0 };// 상 하 좌 우
	static int[] dx = { 0, 0, -1, 1 };

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();
		M = sc.nextInt();

		map = new int[N][M];

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				map[i][j] = sc.nextInt();
			}
		}
		Queue<Point> cheese = new LinkedList<Point>();
		
		int time = 0;
		while (true) {
			// 공기들 있는 곳 다 방문 처리 true; -> 치즈 내에 구멍있는 곳이랑 구분하기 위해서
			visited = new boolean[N][M];
			a: for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if (!visited[i][j] && map[i][j] == 0) {
						bfs(i, j);
						break a;
					}
				}
			}
			// visited false && 0 인 곳 -> 공기 안 통한 구멍
			// 주변에 vistied true && 0 인칸 >=2
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if (map[i][j] == 1) {
						int count = 0;
						for (int k = 0; k < 4; k++) {
							int ny = i + dy[k];
							int nx = j + dx[k];
							if (map[ny][nx] == 0 && visited[ny][nx]) {
								count++;
							}
						}
						if (count >= 2) {
							cheese.add(new Point(i, j));
						}
					}
				}
			}

			if (cheese.size() == 0) {
				break;
			} else
				time++;

			// 치즈 녹이기
			while (!cheese.isEmpty()) {
				Point now = cheese.poll();
				int y = now.y;
				int x = now.x;

				map[y][x] = 0;
			}
		}

		System.out.println(time);

	}

	private static void bfs(int i, int j) {
		Queue<Point> qu = new LinkedList<>();
		qu.add(new Point(i, j));
		visited[i][j] = true;
		while (!qu.isEmpty()) {
			Point now = qu.poll();

			for (int k = 0; k < 4; k++) {
				int ny = now.y + dy[k];
				int nx = now.x + dx[k];
				if (ny >= 0 && ny < N && nx >= 0 && nx < M && !visited[ny][nx] && map[ny][nx] == 0) {
					visited[ny][nx] = true;
					qu.add(new Point(ny, nx));
				}
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
