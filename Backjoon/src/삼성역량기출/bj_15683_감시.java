package 삼성역량기출;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class bj_15683_감시 {

	static int[][] map;
	static int[] dy = { 0, 1, 0, -1 }; // 우,하,좌,상
	static int[] dx = { 1, 0, -1, 0 };
	static int N, M;
	static int MIN = Integer.MAX_VALUE;
	static List<Point> list = new LinkedList<Point>();

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();
		M = sc.nextInt();

		map = new int[N][M];
		boolean[][] visited = new boolean[N][M];

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				map[i][j] = sc.nextInt();
			}
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] > 0 && map[i][j] < 6) {
					list.add(new Point(i, j));
				}
			}
		}

		Watch(0, 0, visited);
		System.out.println(MIN);
		sc.close();

	}

	static void Watch(int cctvIndex, int direction, boolean[][] visited) {
		if (cctvIndex == list.size()) {
			// 사각지대 최소 구하기
			int area = 0;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if (map[i][j] != 6 && !visited[i][j]) {
						area++;
					}
				}
			}
			MIN = (area < MIN) ? area : MIN;
			return;
		}
		boolean[][] copy_watched = new boolean[N][M];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				copy_watched[i][j] = visited[i][j];
			}
		}

		Point cctv = list.get(cctvIndex);
		int cctvNumber = map[cctv.y][cctv.x];

		if (cctvNumber == 5) {
			checkArea(cctv, copy_watched);
			Watch(cctvIndex + 1, 0, copy_watched);
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					copy_watched[i][j] = visited[i][j];
				}
			}
		} else if (cctvNumber == 2) {
			for (int dir = 0; dir < 2; dir++) {
				checkArea(cctv, dir, copy_watched);
				Watch(cctvIndex + 1, dir, copy_watched);

				for (int i = 0; i < N; i++) {
					for (int j = 0; j < M; j++) {
						copy_watched[i][j] = visited[i][j];
					}
				}
			}
		} else {
			for (int dir = 0; dir < 4; dir++) {
				checkArea(cctv, dir, copy_watched);
				Watch(cctvIndex + 1, dir, copy_watched);
				for (int i = 0; i < N; i++) {
					for (int j = 0; j < M; j++) {
						copy_watched[i][j] = visited[i][j];
					}
				}
			}
		}

	}

	static void checkArea(Point cctv, int dir, boolean[][] copy_watched) {
		int cctvNumber = map[cctv.y][cctv.x];
		int n = 0;
		if (cctvNumber == 1) {
			if (dir == 1 || dir == 3)
				n = N;
			else
				n = M;
			for (int j = 0; j < n; j++) {
				int y = cctv.y + dy[dir] * j;
				int x = cctv.x + dx[dir] * j;

				if (y >= 0 && y < N && x >= 0 && x < M && !copy_watched[y][x]) {
					if (map[y][x] == 6) //벽통과해서 감시 불가능
						break;
					copy_watched[y][x] = true; // 감시가능 구역
				}
			}
		} else if (cctvNumber == 2) {
			for (int i = 0; i < 2; i++) {
				dir = dir + 2 * i;
				if (dir == 1 || dir == 3)
					n = N;
				else
					n = M;
				for (int j = 0; j < n; j++) {
					int y = cctv.y + dy[dir] * j;
					int x = cctv.x + dx[dir] * j;

					if (y >= 0 && y < N && x >= 0 && x < M && !copy_watched[y][x]) {
						if (map[y][x] == 6) //벽통과해서 감시 불가능
							break;
						copy_watched[y][x] = true; // 감시가능 구역
					}
				}
			}
		} else if (cctvNumber == 3) {
			for (int i = 0; i < 2; i++) {
				dir = (dir + i) % 4;
				if (dir == 1 || dir == 3)
					n = N;
				else
					n = M;
				for (int j = 0; j < n; j++) {
					int y = cctv.y + dy[dir] * j;
					int x = cctv.x + dx[dir] * j;

					if (y >= 0 && y < N && x >= 0 && x < M && !copy_watched[y][x]) {
						if (map[y][x] == 6) //벽통과해서 감시 불가능
							break;
						copy_watched[y][x] = true; // 감시가능 구역
					}
				}
			}
		} else if (cctvNumber == 4) {
			for (int i = 0; i < 3; i++) {
				dir = (dir + i) % 4;
				if (dir == 1 || dir == 3)
					n = N;
				else
					n = M;
				for (int j = 0; j < n; j++) {
					int y = cctv.y + dy[dir] * j;
					int x = cctv.x + dx[dir] * j;

					if (y >= 0 && y < N && x >= 0 && x < M && !copy_watched[y][x]) {
						if (map[y][x] == 6) //벽통과해서 감시 불가능
							break;
						copy_watched[y][x] = true; // 감시가능 구역
					}
				}
			}
		}
	}

	static void checkArea(Point cctv, boolean[][] copy_watched) {
		int n = 0;
		for (int i = 0; i < 4; i++) {
			if (i == 1 || i == 3)
				n = N;
			else
				n = M;
			for (int j = 0; j < n; j++) {

				int y = cctv.y + dy[i] * j;
				int x = cctv.x + dx[i] * j;

				if (y >= 0 && y < N && x >= 0 && x < M && !copy_watched[y][x]) {
					if (map[y][x] == 6) //벽통과해서 감시 불가능
						break;
					copy_watched[y][x] = true; // 감시가능 구역
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
