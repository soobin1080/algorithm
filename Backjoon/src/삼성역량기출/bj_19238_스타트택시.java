package 삼성역량기출;

import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class bj_19238_스타트택시 {
	static int N, M, F;

	static int[][] map;
	static Point taxi;
	static Point[] destination;
	static int taken = 0; // 택시에 태운 사람 번호

	static int[] dy = { -1, 1, 0, 0 };
	static int[] dx = { 0, 0, -1, 1 };

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		F = sc.nextInt();

		map = new int[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				map[i][j] = sc.nextInt();
				if (map[i][j] == 1)
					map[i][j] = -1;
			}
		}

		taxi = new Point(sc.nextInt() - 1, sc.nextInt() - 1);
		destination = new Point[M + 1];
		for (int i = 1; i <= M; i++) {
			int y = sc.nextInt() - 1;
			int x = sc.nextInt() - 1;
			map[y][x] = i;
			destination[i] = new Point(sc.nextInt() - 1, sc.nextInt() - 1);
		}

		int passenger = 0; // 목적지 도착 고객
		while (F > 0 && M > passenger) {
			
			// 제자리 손님 있나 확인
			int distance = 0;
			if (map[taxi.y][taxi.x] > 0) {
				taken = map[taxi.y][taxi.x];
				map[taxi.y][taxi.x]=0;
			} else {
				// 가까운 손님 찾기
				distance = bfs();
			}
			if (taken == 0 || distance == -1) {// 손님 못찾음
				F = -1;
				break;
			} else { // 손님 찾음
				F -= distance;
				// 연료 체크
				if (F < 0) {
					// 손님 못태움
					F = -1;
					break;
				}
				// 손님 태움, 목적지 가기
				distance = bfs(taken);
				if (distance == -1) { // 목적지 못찾음
					F = -1;
					break;
				}
				// 목적지 찾음
				F -= distance;
				// 연료 체크
				if (F < 0) { // 목적지 못감
					F = -1;
					break;
				}
				// 목적지 도착, 연료 충전, 완료 고객 +1
				F += (2 * distance);
				passenger++;
			}

		}

		System.out.println(F);

		sc.close();
	}

	private static int bfs(int perNum) {
		int distance = 0;
		boolean[][] visited = new boolean[N][N];
		Queue<Point> qu = new LinkedList<>();
		int ty = taxi.y;
		int tx = taxi.x;
		qu.add(new Point(ty, tx));
		while (!qu.isEmpty()) {
			distance++;
			int size = qu.size();
			for (int s = 0; s < size; s++) {
				Point p = qu.poll();
				int y = p.y;
				int x = p.x;
				for (int i = 0; i < 4; i++) {
					int ny = y + dy[i];
					int nx = x + dx[i];

					if (ny >= 0 && ny < N && nx >= 0 && nx < N && !visited[ny][nx] && map[ny][nx] != -1) {
						if (ny == destination[perNum].y && nx == destination[perNum].x) {
							taxi.y = ny;
							taxi.x = nx;
							taken = 0;
							return distance;
						} else {
							visited[ny][nx] = true;
							qu.add(new Point(ny, nx));
						}
					}
				}
			}
		}

		return -1;
	}

	private static int bfs() {
		int distance = 0;
		boolean[][] visited = new boolean[N][N];
		Queue<Point> qu = new LinkedList<>();
		int ty = taxi.y;
		int tx = taxi.x;
		qu.add(new Point(ty, tx));
		LinkedList<Point> shortest = new LinkedList<>();

		boolean find = false;
		while (!qu.isEmpty()) {
			distance++;
			int size = qu.size();
			for (int s = 0; s < size; s++) {

				Point p = qu.poll();
				int y = p.y;
				int x = p.x;
				for (int i = 0; i < 4; i++) {
					int ny = y + dy[i];
					int nx = x + dx[i];

					if (ny >= 0 && ny < N && nx >= 0 && nx < N && !visited[ny][nx] && map[ny][nx] != -1) {
						if (map[ny][nx] == 0) {
							visited[ny][nx] = true;
							qu.add(new Point(ny, nx));
						} else {
							visited[ny][nx] = true;
							shortest.add(new Point(ny, nx));
							find = true;
						}
					}
				}
			}
			if (find)
				break;
		}

		if (find) {
			Collections.sort(shortest);
			Point p = shortest.get(0);
			taken = map[p.y][p.x];
			taxi.y = p.y;
			taxi.x = p.x;
			map[p.y][p.x] = 0;
			return distance;
		}

		return -1;
	}

	static class Point implements Comparable<Point> {
		int y;
		int x;

		Point(int y, int x) {
			this.y = y;
			this.x = x;
		}

		@Override
		public int compareTo(Point o) {
			if (this.y == o.y)
				return this.x - o.x;
			return this.y - o.y;
		}
	}

}
