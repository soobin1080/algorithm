package 삼성역량기출;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

public class bj_16236_아기상어_RE {
	static int dy[] = { -1, 1, 0, 0 };
	static int dx[] = { 0, 0, -1, 1 };
	static int sharkSize = 2, sharkY = 0, sharkX = 0;
	static int map[][];
	static int SECOND = 0;
	static int N;

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		map = new int[N][N];
		// 1~6 :물고기 크기 / 9 : 아기 상어 위치
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				map[i][j] = sc.nextInt();
				if (map[i][j] == 9) {
					sharkY = i;
					sharkX = j;
				}
			}
		}

		int eatFish = 0;
		while (true) {
			boolean ate = bfs(sharkY, sharkX);
			if (ate)
				eatFish++;
			// 자신의 크기와 같은 수의 물고기를 먹어야 size+1
			if (sharkSize == eatFish) {
				sharkSize++;
				eatFish = 0;
			}
			// 잡아먹을 물고기 없으면 break;
			if (!ate)
				break;
		}
		System.out.println(SECOND);
	}

	private static boolean bfs(int y, int x) {
		// 먹을 수 있는 물고기 1마리 보다 많으면 이동거리 최소인 곳으로!
		// 잡아먹을 물고기 있으면 잡아먹고 빈칸처리 , qu.add(new Node(새로운 상어 위치));
		boolean visited[][] = new boolean[N][N];
		Queue<Point> qu = new LinkedList<>();
		PriorityQueue<Point> edibleFish = new PriorityQueue<>();
		qu.add(new Point(y, x));
		visited[y][x] = true;
		int distance = 0;
		while (!qu.isEmpty()) {
			int size = qu.size();
			distance++;
			for (int i = 0; i < size; i++) {
				Point now = qu.poll();
				for (int d = 0; d < 4; d++) {
					int ny = now.y + dy[d];
					int nx = now.x + dx[d];

					if (ny >= 0 && ny < N && nx >= 0 && nx < N && !visited[ny][nx]) {
						if (sharkSize > map[ny][nx] && map[ny][nx] > 0) {
							edibleFish.add(new Point(ny, nx));
							visited[ny][nx] = true;
						}
						if (sharkSize >= map[ny][nx]) {
							qu.add(new Point(ny, nx));
							visited[ny][nx] = true;
						}
					}
				}
			}
			if (edibleFish.size() > 0) {
				Point fish = edibleFish.poll();
				map[sharkY][sharkX] = 0;
				sharkY = fish.y;
				sharkX = fish.x;
				map[sharkY][sharkX] = 9;
				SECOND += distance;
				return true;
			}
		}

		return false;
	}

	static class Point implements Comparable<Point> {
		int y;
		int x;

		Point(int y, int x) {
			this.y = y;
			this.x = x;
		}

		// 우선수위는 가장 위,가장 왼쪽에 있는 물고기 -> compareTo
		@Override
		public int compareTo(Point o) {
			if (this.y == o.y)
				return this.x - o.x;
			return this.y - o.y;
		}
	}
}
