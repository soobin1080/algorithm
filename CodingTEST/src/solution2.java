import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class solution2 {
	static int dy[] = { -1, 1, 0, 0 };
	static int dx[] = { 0, 0, -1, 1 };
	static char[][] room;

	public static void main(String[] args) {
		String[][] places = { { "PPPPP", "XXXOX", "OPXPX", "OOXOX", "POXXP" },
				{ "POOPX", "OXPXP", "PXXXO", "OXXXO", "OOOPP" }, { "PXOPX", "OXOXP", "OXPXX", "OXXXP", "POOXX" },
				{ "OOOXX", "XOOOX", "OOOXX", "OXOOX", "OOOOO" }, { "PXPXP", "XPXPX", "PXPXP", "XPXPX", "PXPXP" } };
		int[] answer = new int[5];
		for (int i = 0; i < 5; i++) {
			room = new char[5][5];
			for (int j = 0; j < 5; j++) {
				room[j] = places[i][j].toCharArray();
			}
			
			for (int j = 0; j < answer.length; j++) {
				for (int j2 = 0; j2 < answer.length; j2++) {
					System.out.print(room[j][j2]+" ");
				}
				System.out.println();
			}
			System.out.println();

			// 대기실 하나 탐색
			int anw = 1;
			a: for (int r = 0; r < room.length; r++) {
				for (int c = 0; c < room.length; c++) {
					if (room[r][c] == 'P') {
						anw = bfs(r, c);
						if(anw==0)
							break a;
					}
				}
			}

			answer[i] = anw;
		}
		System.out.println(Arrays.toString(answer));
	}

	private static int bfs(int r, int c) {
		Queue<Point> qu = new LinkedList<>();
		qu.add(new Point(r, c));
		boolean visited[][] = new boolean[5][5];
		visited[r][c] = true;
		for (int i = 0; i < 2; i++) {
			int size = qu.size();
			if (size != 0)
				for (int j = 0; j < size; j++) {
					Point p = qu.poll();
					for (int d = 0; d < 4; d++) {
						int iy = p.y + dy[d];
						int ix = p.x + dx[d];
						if (iy >= 0 && iy < 5 && ix >= 0 && ix < 5 && !visited[iy][ix]) {
							if (room[iy][ix] == 'O') {
								qu.add(new Point(iy, ix));
								visited[iy][ix] = true;
							} else if (room[iy][ix] == 'P') {
								return 0;

							}
						}
					}
				}
		}
		return 1;
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
