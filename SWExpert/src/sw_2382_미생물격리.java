import java.util.Scanner;

public class sw_2382_미생물격리 {
	static Cell[][] map; 
	static int N, M, K;
	static int[] dy = { 0, -1, 1, 0, 0 };
	static int[] dx = { 0, 0, 0, -1, 1 };

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int TC = sc.nextInt();

		for (int tc = 1; tc <= TC; tc++) {

			N = sc.nextInt();
			M = sc.nextInt();
			K = sc.nextInt();

			map = new Cell[N][N];

			for (int i = 0; i < K; i++) {
				int y = sc.nextInt();
				int x = sc.nextInt();
				int count = sc.nextInt();
				int dir = sc.nextInt();
				map[y][x] = new Cell(y, x, count, dir, count);
			}

			for (int i = 0; i < M; i++) {
				// 미생물 이동
				moveCells();
			}
			int sum = 0;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (map[i][j] != null) {
						sum += map[i][j].count;
					}
				}
			}

			System.out.println("#" + tc + " " + sum);
		}

		sc.close();
	}

	private static void moveCells() {
		Cell[][] copyMap = new Cell[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (map[i][j] != null) {
					copyMap[i][j] = map[i][j];
					copyMap[i][j].max = copyMap[i][j].count;
				}
			}
		}

		map = new Cell[N][N];

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (copyMap[i][j] != null) {
					Cell c = copyMap[i][j];
					int y = c.y + dy[c.dir];
					int x = c.x + dx[c.dir];
					if (y >= 0 && y < N && x >= 0 && x < N) {
						if (map[y][x] == null) {
							map[y][x] = new Cell(y, x, c.count, c.dir, c.max);
						} else {
							if (map[y][x].max < c.max) {
								map[y][x] = new Cell(y, x, c.count + map[y][x].count, c.dir, c.max);
							} else
								map[y][x] = new Cell(y, x, c.count + map[y][x].count, map[y][x].dir, map[y][x].max);
						}
						copyMap[i][j] = null;
						if (y == 0 || y == N - 1 || x == 0 || x == N - 1) {
							map[y][x].count /= 2;
							changedirection(map[y][x]);
						}
					}
				}
			}
		}

	}

	private static void changedirection(Cell cell) {
		if (cell.dir == 1)
			cell.dir = 2;
		else if (cell.dir == 2)
			cell.dir = 1;
		else if (cell.dir == 3)
			cell.dir = 4;
		else if (cell.dir == 4)
			cell.dir = 3;
	}

	static class Cell {
		int y;
		int x;
		int count;
		int dir;
		int max; // 합칠 때 필요 다 합친 후 count로 바꿔주기

		Cell(int y, int x, int count, int dir, int max) {
			this.y = y;
			this.x = x;
			this.count = count;
			this.dir = dir;
			this.max = max;
		}
	}

}
