package 삼성역량기출;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class bj_14502_연구소 {

	static int[][] map;
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };
	static int N, M, MAX = 0;
	static boolean[][] visited;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();
		M = sc.nextInt();

		map = new int[N][M];
		visited = new boolean[N][M];

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				map[i][j] = sc.nextInt();
			}
		}

		wall(0);
		System.out.println(MAX);
	}

	private static void bfs() {

		int[][] copy_map = new int[N][M];
		visited = new boolean[N][M];
		Queue<Node> qu = new LinkedList<Node>();
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				copy_map[i][j] = map[i][j];
				if (copy_map[i][j] == 2) {
					qu.add(new Node(i, j));
				}
			}
		}

		while (!qu.isEmpty()) {
			Node node = qu.poll();

			for (int i = 0; i < 4; i++) {
				int y = node.y + dy[i];
				int x = node.x + dx[i];

				if (y >= 0 && y < N && x >= 0 && x < M && !visited[y][x] && copy_map[y][x] == 0) {
					visited[y][x] = true;
					copy_map[y][x] = 2;
					qu.add(new Node(y, x));
				}
			}
		}

		int safe = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (copy_map[i][j] == 0) {
					safe++;
				}
			}
		}

		MAX = (MAX > safe) ? MAX : safe;
	}

	private static void wall(int count) {

		if (count > 2) {
			bfs();
			return;
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] == 0) {
					map[i][j] = 1;
					wall(count + 1);
					map[i][j] = 0;
				}
			}
		}

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
