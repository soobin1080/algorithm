package kakao기출;

import java.util.LinkedList;
import java.util.Queue;

public class pg_카카오프렌즈컬러링북 {

	static int[] dy = { -1, 1, 0, 0 };
	static int[] dx = { 0, 0, -1, 1 };

	public static void main(String[] args) {
		int m = 6, n = 4;
		int[][] picture = { { 1, 1, 1, 0 }, { 1, 2, 2, 0 }, { 1, 0, 0, 1 }, { 0, 0, 0, 1 }, { 0, 0, 0, 3 },
				{ 0, 0, 0, 3 } };
		int numberOfArea = 0;
		int maxSizeOfOneArea = 0;

		boolean[][] visited = new boolean[m][n];

		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (!visited[i][j] && picture[i][j] != 0) {

					int startPointOfArea = picture[i][j];
					Queue<Point> qu = new LinkedList<Point>();
					qu.add(new Point(i, j));
					visited[i][j]=true;
					int sizeOfOneArea = 1;

					while (!qu.isEmpty()) {
						Point point = qu.poll();

						for (int k = 0; k < 4; k++) {
							int y = point.y + dy[k];
							int x = point.x + dx[k];

							if (y >= 0 && y < m && x >= 0 && x < n && !visited[y][x]
									&& picture[y][x] == startPointOfArea) {
								visited[y][x] = true;
								qu.add(new Point(y, x));
								sizeOfOneArea++;
							}
						}
					}
					numberOfArea++;
					maxSizeOfOneArea = (maxSizeOfOneArea < sizeOfOneArea) ? sizeOfOneArea : maxSizeOfOneArea;
				}
			}
		}
		System.out.println(numberOfArea + " " + maxSizeOfOneArea);
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
