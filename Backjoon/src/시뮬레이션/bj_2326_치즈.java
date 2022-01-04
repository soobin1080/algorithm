package 시뮬레이션;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class bj_2326_치즈 {
	static int cheese[][];
	static boolean air[][];
	static int dy[] = { -1, 1, 0, 0 };
	static int dx[] = { 0, 0, -1, 1 };

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		int r = sc.nextInt();
		int c = sc.nextInt();

		// 배열입력받기
		cheese = new int[r][c];

		int cheeseCnt = 0;

		for (int i = 0; i < r; i++) {
			for (int j = 0; j < c; j++) {
				cheese[i][j] = sc.nextInt();
				if (cheese[i][j] == 1)
					cheeseCnt++;
			}
		}
		int answer = 0;
		int time = 0;
		while (cheeseCnt != 0) {
			answer = cheeseCnt;
			time++;
			
			air = new boolean[r][c];
			// 공기있는 배열 확인
			a: for (int i = 0; i < r; i++) {
				for (int j = 0; j < c; j++) {
					if (cheese[i][j] == 0) {
						Queue<Node> qu = new LinkedList<Node>();
						qu.add(new Node(i, j));
						air[i][j] = true;

						while (!qu.isEmpty()) {
							Node now = qu.poll();

							for (int k = 0; k < 4; k++) {
								int ny = now.y + dy[k];
								int nx = now.x + dx[k];
								if (ny >= 0 && ny < r && nx >= 0 && nx < c && cheese[ny][nx] == 0 && !air[ny][nx]) {
									qu.add(new Node(ny, nx));
									air[ny][nx] = true;
								}
							}
						}
						break a;
					}
				}
			}

			for (int i = 0; i < r; i++) {
				for (int j = 0; j < c; j++) {
					// 공기랑 접촉하는 치즈부분
					if (cheese[i][j] == 1)
						for (int k = 0; k < 4; k++) {
							int ny = i + dy[k];
							int nx = j + dx[k];
							if (ny >= 0 && ny < r && nx >= 0 && nx < c && cheese[ny][nx] == 0 && air[ny][nx]) {
								air[i][j] = true;
							}
						}

				}
			}

			for (int i = 0; i < r; i++) {
				for (int j = 0; j < c; j++) {
					// cheese 배열값 1이고 공기 true 면 1시간후사라짐
					if (cheese[i][j] == 1 && air[i][j]) {
						cheese[i][j] = 0;
						cheeseCnt--;
					}
				}
			}
		}
		System.out.println(time);
		System.out.println(answer);
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
