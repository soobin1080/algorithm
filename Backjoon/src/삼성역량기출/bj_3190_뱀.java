package 삼성역량기출;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class bj_3190_뱀 {
	static int[][] left = { { 0, -1 }, { -1, 0 }, { 0, 1 }, { 1, 0 } };
	static int[][] right = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };

	static int[] dy = { -1, 0, 1, 0 };
	static int[] dx = { 0, 1, 0, -1 };
	static List<Point> list = new LinkedList<Point>();
	static int[][] map;
	static boolean[][] visited;

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		int N = sc.nextInt();
		map = new int[N + 2][N + 2];
		visited = new boolean[N + 2][N + 2];
		for (int i = 0; i < N + 2; i++) {
			visited[0][i] = true;
			visited[N + 1][i] = true;
			visited[i][0] = true;
			visited[i][N + 1] = true;
		}

		// 사과위치
		int K = sc.nextInt();
		for (int i = 0; i < K; i++) {
			map[sc.nextInt()][sc.nextInt()] = 2;
		}

		int L = sc.nextInt();

		int[] time = new int[L];
		char[] direction = new char[L];

		for (int i = 0; i < L; i++) {
			time[i] = sc.nextInt();
			direction[i] = sc.next().charAt(0);
		}

		list.add(new Point(1, 1));
		visited[1][1] = true;
		int dir = 1; // 뱀 방향 - 0:위, 1:우, 2:아래, 3:좌

		int total_time = 0;

		boolean flag = true;
		int idx = 0;
		while (flag) {

			if (idx < L) {
				if (total_time == time[idx]) {

					char C = direction[idx];
					if (C == 'L') {
						if (dir == 1) {
							dir = 0;
						} else if (dir == 2) {
							dir = 1;
						} else if (dir == 3) {
							dir = 2;
						} else {
							dir = 3;
						}
					} else {
						if (dir == 1) {
							dir = 2;
						} else if (dir == 2) {
							dir = 3;
						} else if (dir == 3) {
							dir = 0;
						} else {
							dir = 1;
						}
					}
					idx++;
				}
			} 

			Point head = list.get(0);
			int ny = head.y + dy[dir];
			int nx = head.x + dx[dir];

			if (ny >= 1 && ny < N + 2 && nx >= 1 && nx < N + 2 && !visited[ny][nx]) {
				if (map[ny][nx] == 2) {
					list.add(0, new Point(ny, nx));
					visited[ny][nx] = true;
					map[ny][nx] = 0;
				} else {
					list.add(0, new Point(ny, nx));

					Point tail = list.get(list.size() - 1);
					visited[ny][nx] = true;
					visited[tail.y][tail.x] = false;

					list.remove(list.size() - 1);
				}
				total_time++;
			} else
				flag = false;
		}

		System.out.println(total_time+1);
		sc.close();
	}

	static void print(boolean[][] visited2) {
		for (int i = 0; i < visited2.length; i++) {
			for (int j = 0; j < visited2.length; j++) {
				if (visited[i][j] == true)
					System.out.print("*");
				else
					System.out.print(0);

			}
			System.out.println();
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
