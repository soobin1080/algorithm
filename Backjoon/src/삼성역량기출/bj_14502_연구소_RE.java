package 삼성역량기출;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class bj_14502_연구소_RE {
	static int R, C;
	static int[][] map;
	static int MAX = 0;
	static int[] dy = { -1, 1, 0, 0 };
	static int[] dx = { 0, 0, -1, 1 };

	public static void main(String[] args) {

		// 입력받기
		Scanner sc = new Scanner(System.in);
		R = sc.nextInt();
		C = sc.nextInt();

		map = new int[R][C];
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				map[i][j] = sc.nextInt();
			}
		}

		// 벽세우기
		buildWall(0, 0, 0);

		System.out.println(MAX);

	}

	private static void buildWall(int r, int c, int wallCount) {
		if (wallCount == 3) {
			// 바이러스 퍼트리기
			spreadVirus();
			return;
		}

		for (int i = r; i < R; i++) {
			for (int j = c; j < C; j++) {
				if (map[i][j] == 0) {
					map[i][j] = 1;
					buildWall(0,0, wallCount + 1);
					map[i][j] = 0;
				}
			}
		}

	}

	private static void spreadVirus() {
		int virusMap[][] = new int[R][C];
		boolean visited[][] = new boolean[R][C];
		Queue<Node> qu = new LinkedList<>();

		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				virusMap[i][j] = map[i][j];
				if (virusMap[i][j] == 2) {
					qu.add(new Node(i, j));
				}
			}
		}
		
		while (!qu.isEmpty()) {
			Node now = qu.poll();

			for (int i = 0; i < 4; i++) {
				int y = now.y + dy[i];
				int x = now.x + dx[i];
				if (y >= 0 && y < R && x >= 0 && x < C && virusMap[y][x] == 0 && !visited[y][x]) {
					qu.add(new Node(y, x));
					virusMap[y][x] = 2;
					visited[y][x] = true;
				}
			}
		}
		
		// 안전영역크기 구하기
		int safeZone = 0;
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if (virusMap[i][j] == 0)
					safeZone++;
			}
		}

		MAX = (MAX < safeZone) ? safeZone : MAX;

	}

	static class Node {
		int y;
		int x;

		Node(int y, int x) {
			this.y = y;
			this.x = x;
		}
	}
}
