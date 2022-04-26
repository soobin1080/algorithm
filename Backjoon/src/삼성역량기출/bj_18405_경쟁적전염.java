package 삼성역량기출;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class bj_18405_경쟁적전염 {
	static int N, K, S, X, Y;
	static int dy[] = { -1, 1, 0, 0 };
	static int dx[] = { 0, 0, -1, 1 };

	static class Point implements Comparable<Point> {
		int y;
		int x;
		int virusNum;

		Point(int y, int x, int virusNum) {
			this.y = y;
			this.x = x;
			this.virusNum = virusNum;
		}

		@Override
		public int compareTo(Point o) {
			// TODO Auto-generated method stub
			return this.virusNum - o.virusNum;
		}
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		K = sc.nextInt();

		int[][] map = new int[N + 1][N + 1];
		List<Point> virus = new LinkedList<>();
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				map[i][j] = sc.nextInt();
				if (map[i][j] != 0)
					virus.add(new Point(i, j, map[i][j]));
			}
		}

		S = sc.nextInt();
		X = sc.nextInt();
		Y = sc.nextInt();

		// S초 동안
		int second = 0;
		while (!virus.isEmpty() && second < S) {
			int virusCnt = virus.size();
			// 바이러스번호 작은 순으로 정렬
			Collections.sort(virus);
			for (int i = 0; i < virusCnt; i++) {

				Point now = virus.get(0);
				virus.remove(0);
				int virusNum = now.virusNum;
				for (int k = 0; k < 4; k++) {
					int ny = now.y + dy[k];
					int nx = now.x + dx[k];
					if (ny >= 1 && ny <= N && nx >= 1 && nx <= N && map[ny][nx] == 0) {
						map[ny][nx] = virusNum;
						virus.add(new Point(ny, nx, virusNum));
					}
				}

			}
			second++;
		}

		System.out.println(map[X][Y]);

	}

}
