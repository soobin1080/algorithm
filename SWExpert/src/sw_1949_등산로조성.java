import java.util.ArrayList;
import java.util.Scanner;

public class sw_1949_등산로조성 {
	static int N, K, MAX;
	static int[][] map;
	static boolean[][] visited;
	static int highest;
	static int[] dy = { -1, 1, 0, 0 };
	static int[] dx = { 0, 0, -1, 1 };

	static ArrayList<Point> highestPoint = new ArrayList<>();

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int TC = sc.nextInt();
		for (int tc = 1; tc <= TC; tc++) {

			N = sc.nextInt();
			K = sc.nextInt();

			map = new int[N][N];
			visited = new boolean[N][N];
			highestPoint = new ArrayList<>();
			highest = MAX = 0;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					map[i][j] = sc.nextInt();
					if (map[i][j] > highest)
						highest = map[i][j];
				}
			}

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (highest == map[i][j])
						highestPoint.add(new Point(i, j));
				}
			}

			int size = highestPoint.size();
			for (int i = 0; i < size; i++) {
				Point p = highestPoint.get(i);
				for (int j = 0; j <= K; j++) {
					visited[p.y][p.x]=true;
					dfs(p.y, p.x, map[p.y][p.x], 1, j, false);
					visited[p.y][p.x]=false;
				}
			}

			System.out.println("#"+tc+" "+MAX);

		}
		sc.close();
	}

	private static void dfs(int y, int x, int height, int road, int depth, boolean construction) {
		if (MAX < road)
			MAX = road;


		for (int i = 0; i < 4; i++) {
			int iy = y + dy[i];
			int ix = x + dx[i];
			if (iy >= 0 && iy < N && ix >= 0 && ix < N && !visited[iy][ix]) {

				if (!construction) {
					if (map[iy][ix] < height) {
						visited[iy][ix] = true;
						dfs(iy, ix, map[iy][ix], road + 1, depth, false);
						visited[iy][ix] = false;
					}
					if (map[iy][ix] - depth < height && map[iy][ix] >= 0) {
						visited[iy][ix] = true;
						dfs(iy, ix, map[iy][ix] - depth, road + 1, depth, true);
						visited[iy][ix] = false;
					}
				} else {
					if (map[iy][ix] < height) {
						visited[iy][ix] = true;
						dfs(iy, ix, map[iy][ix], road + 1, depth, true);
						visited[iy][ix] = false;
					}
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
