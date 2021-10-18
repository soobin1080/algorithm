package 삼성역량기출;

import java.util.LinkedList;
import java.util.Scanner;

public class bj_3190_뱀_RE {
	static int dy[] = { 0, 1, 0, -1 }; // 오른쪽 회전 : 우,하,좌,상
	static int dx[] = { 1, 0, -1, 0 };
	static char rotationTime[] = new char[10001];

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int K = sc.nextInt();

		int map[][] = new int[N][N];
		boolean snake[][] = new boolean[N][N];

		for (int i = 0; i < K; i++) {
			int y = sc.nextInt() - 1;
			int x = sc.nextInt() - 1;
			map[y][x] = 1;
		}

		int totalSecond = 0;

		// 방향 변환 횟수 L
		int L = sc.nextInt();

		LinkedList<Node> S = new LinkedList<>(); // 맨앞이 꼬리좌표
		S.add(new Node(0, 0));
		snake[0][0] = true;
		int direction = 0;

		for (int i = 0; i < L; i++) {
			int x = sc.nextInt(); // x초 이동
			char c = sc.next().charAt(0); // L이면 왼쪽 D면 오른쪽으로 90도 회전
			rotationTime[x] = c;
		}
		// 뱀 움직이기
		boolean move = true;
		a: while (move) {

			totalSecond++;

			Node head = S.getLast();
			int ny = dy[direction] + head.y;
			int nx = dx[direction] + head.x;
			if (ny >= 0 && ny < N && nx >= 0 && nx < N && !snake[ny][nx]) {
				S.add(new Node(ny, nx));
				snake[ny][nx] = true;
				if (map[ny][nx] != 1) { // 사과없으면
					Node tail = S.poll();
					snake[tail.y][tail.x] = false;
				} else if (map[ny][nx] == 1) {
					map[ny][nx] = 0;
				}
			} else {
				break a;
			}

			if (rotationTime[totalSecond] == 'L') {
				direction--;
				direction = (direction < 0) ? 3 : direction;
				//direction = (direction-- < 0) ? 3 : direction; 이건 왜 안돼?!
			} else if (rotationTime[totalSecond] == 'D') {
				direction = (direction + 1) % 4;
			}
		}
		System.out.println(totalSecond);
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
