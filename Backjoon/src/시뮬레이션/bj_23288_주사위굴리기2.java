package 시뮬레이션;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.concurrent.SynchronousQueue;

public class bj_23288_주사위굴리기2 {

	static int[][] map;
	static int N, M, K;

	static int[][] dir = { { 1, 3, 6, 4 }, { 1, 2, 6, 5 }, { 1, 4, 6, 3 }, { 1, 5, 6, 2 } };// 우 상 좌 하
	static int[] dice = { 0, 1, 2, 3, 4, 5, 6 };
	static int[] rotatedir = { 0, 1, 2, 3 }; // dir 행 값 들어가있음. 인덱스0 : 우, 인덱스1: 상, 인덱스2: 좌 ,인덱스3:하
	static int score = 0;
	static int[] dy = { 0, -1, 0, 1 }; // 우,상,좌,하 점수 계산위해 넣은 배열
	static int[] dx = { 1, 0, -1, 0 };

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();
		M = sc.nextInt();
		K = sc.nextInt();

		map = new int[N][M];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				map[i][j] = sc.nextInt();
			}
		}

		// 우:0, 상:1, 좌:2, 하:3 / 시계방향이면 인덱스 +1씩 ,반시계면 인덱스 -1씩
		int d = 0;
		int startY = 0, startX = 0;
		for (int i = 0; i < K; i++) {
			// dir 방향으로 주사위를 굴린다
			int iy = startY + dy[rotatedir[d]];
			int ix = startX + dx[rotatedir[d]];
			// 만약, 이동 방향에 칸이 없다면, 이동 방향을 반대로 한 다음 한 칸 굴러간다.
			if (!(iy >= 0 && iy < N && ix >= 0 && ix < M)) {
				if (d == 0)
					d = 2;
				else if (d == 1)
					d = 3;
				else if (d == 2)
					d = 0;
				else
					d = 1;
			}

			int direction = rotatedir[d];
			int temp = dice[dir[direction][3]];
			for (int j = 2; j >= 0; j--) {
				dice[dir[direction][j + 1]] = dice[dir[direction][j]];
			}
			dice[dir[direction][0]] = temp;

			startY += dy[rotatedir[d]];
			startX += dx[rotatedir[d]];


			int bottom = dice[6];
			if (bottom > map[startY][startX]) {
				// 시계방향으로 바꾸기
				int tmp = rotatedir[3];
				for (int j = 2; j >= 0; j--) {
					rotatedir[j + 1] = rotatedir[j];
				}
				rotatedir[0] = tmp;
				
			} else if (bottom < map[startY][startX]) {
				// 반시계방향으로 바꾸기
				int tmp = rotatedir[0];
				for (int j = 0; j <3; j++) {
					rotatedir[j] = rotatedir[j + 1];
				}
				rotatedir[3] = tmp;
			
			}


			// 점수 계산하기
			Queue<Point> qu = new LinkedList<Point>();
			boolean[][] visited = new boolean[N][M];
			qu.add(new Point(startY, startX));
			visited[startY][startX] = true;
			int count = 1;
			while (!qu.isEmpty()) {
				Point p = qu.poll();
				for (int j = 0; j < 4; j++) {
					int ny = p.y + dy[j];
					int nx = p.x + dx[j];
					if (ny >= 0 && ny < N && nx >= 0 && nx < M && !visited[ny][nx]) {
						if (map[startY][startX] == map[ny][nx]) {
							visited[ny][nx] = true;
							count++;
							qu.add(new Point(ny, nx));
						}
					}
				}
			}
			score += (map[startY][startX] * count);
		}

		System.out.println(score);
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
