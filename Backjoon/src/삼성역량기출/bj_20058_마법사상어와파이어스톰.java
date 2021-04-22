package 삼성역량기출;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class bj_20058_마법사상어와파이어스톰 {
	static int N, Q, MAX = 0;
	static int[][] map;
	static int[] dy = { 0, 0, -1, 1 };
	static int[] dx = { -1, 1, 0, 0 };

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int n = sc.nextInt();
		N = (int) Math.pow(2, n);
		map = new int[N][N];

		Q = sc.nextInt();

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				map[i][j] = sc.nextInt();
			}
		}

		for (int i = 0; i < Q; i++) {
			int L = sc.nextInt();
			fireStorm(L);
		}

		// 남아있는 얼음의 합
		int ice = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				ice += map[i][j];
			}
		}

		System.out.println(ice);

		// 가장 큰 덩어리
		findIce();
		System.out.println(MAX);

		sc.close();
	}

	private static void findIce() {
		boolean[][] visited = new boolean[N][N];

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				// bfs
				if (map[i][j] > 0 && !visited[i][j]) {
					Queue<Point> qu = new LinkedList<Point>();
					visited[i][j] = true;
					qu.add(new Point(i, j));
					int iceSize = 1;
					while (!qu.isEmpty()) {
						Point p = qu.poll();
						int y = p.y;
						int x = p.x;

						for (int d = 0; d < 4; d++) {
							int ny = y + dy[d];
							int nx = x + dx[d];

							if (ny >= 0 && ny < N && nx >= 0 && nx < N && map[ny][nx] > 0 && !visited[ny][nx]) {
								visited[ny][nx] = true;
								qu.add(new Point(ny, nx));
								iceSize++;
							}
						}
					}
					MAX = (MAX < iceSize) ? iceSize : MAX;
				}
			}
		}
	}

	private static void fireStorm(int L) {

		int size = (int) Math.pow(2, L);
		System.out.println(size);
		for (int i = 0; i < N; i += size) {
			for (int j = 0; j < N; j += size) {
				// 분할된 격자 90도 회전시키기
				int[][] rotate = new int[size][size];
				for (int y = 0; y < size; y++) {
					for (int x = 0; x < size; x++) {
						rotate[y][x] = map[i + y][j + x];
					}
				}

				int[][] copyRotate = new int[size][size];
				for (int y = 0; y < size; y++) {
					for (int x = 0; x < size; x++) {
						copyRotate[x][size - 1 - y] = rotate[y][x];
					}
				}

				for (int y = 0; y < size; y++) {
					for (int x = 0; x < size; x++) {
						map[i + y][j + x] = copyRotate[y][x];
					}
				}
			}
		}

		int[][] copyMap = new int[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				copyMap[i][j] = map[i][j];
			}
		}
		// 얼음양 줄이기
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				int count = 0;
				for (int d = 0; d < 4; d++) {
					int y = i + dy[d];
					int x = j + dx[d];

					if (y >= 0 && y < N && x >= 0 && x < N && copyMap[y][x] > 0)
						count++;
				}
				if (count < 3 && copyMap[i][j] > 0)
					map[i][j]--;
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
