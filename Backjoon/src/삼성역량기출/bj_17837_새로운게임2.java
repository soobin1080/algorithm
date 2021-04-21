package 삼성역량기출;

import java.util.LinkedList;
import java.util.Scanner;

public class bj_17837_새로운게임2 {
	static int N, K;
	static boolean gameover = false;
	static int[][] color;
	static LinkedList<Horse> horse;
	static LinkedList<Integer>[][] chess;
	static int[] dy = { 0, 0, -1, 1 };
	static int[] dx = { 1, -1, 0, 0 };

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();
		K = sc.nextInt();
		color = new int[N][N];
		horse = new LinkedList<>();

		chess = new LinkedList[N][N];

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				chess[i][j] = new LinkedList<>();
				color[i][j] = sc.nextInt();
			}
		}

		// 말 위치 저장
		for (int i = 0; i < K; i++) {
			int y = sc.nextInt() - 1;
			int x = sc.nextInt() - 1;
			int d = sc.nextInt() - 1;
			horse.add(new Horse(y, x, d));
			chess[y][x].add(i);
		}

		int turn = 1;
		a: while (turn < 1000) {
			for (int i = 0; i < K; i++) {
				moveHorse(i, 0);

				if (gameover) {
					break a;
				}
			}
			turn++;
		}

		if(gameover)
			System.out.println(turn);
		else
			System.out.println(-1);


		sc.close();
	}

	private static void moveHorse(int i, int depth) {

		Horse h = horse.get(i);
		int y = h.y; // 주소를 참조하기 때문에 밑에서 값을 바꾸면 제대로 된 결과값을 도출할 수 없기 때문에 따로 변수로 지정해서 해주자. 3행*n열 배열로 해도
						// 괜찮을 듯
		int x = h.x;
		int d = h.d;
		int ny = y + dy[d];
		int nx = x + dx[d];
		int size = chess[y][x].size();

		// i번째 말이 있는 위치 구하기
		int index = 0;
		for (int j = 0; j < size; j++) {
			if (i == chess[y][x].get(j)) {
				index = j;
				break;
			}
		}

		if (ny >= 0 && ny < N && nx >= 0 && nx < N) {
			if (color[ny][nx] == 0) {
				for (int j = index; j < size; j++) {
					int n = chess[y][x].get(j);
					chess[ny][nx].add(n);
					horse.get(n).y = ny;
					horse.get(n).x = nx;
				}
				for (int j = index; j < size; j++) {
					chess[y][x].removeLast();
				}
			} else if (color[ny][nx] == 1) {
				for (int j = size - 1; j >= index; j--) {
					int n = chess[y][x].get(j);
					chess[ny][nx].add(n);
					horse.get(n).y = ny;
					horse.get(n).x = nx;
				}
				for (int j = index; j < size; j++) {
					chess[y][x].removeLast();
				}
			} else {
				if (depth == 0) {
					changeDir(i);
					moveHorse(i, 1);
				}
			}
			if (chess[ny][nx].size() >= 4)
				gameover = true;

		} else {
			if (depth == 0) {
				changeDir(i);
				moveHorse(i, 1);
			}
		}

	}

	private static void changeDir(int i) {

		switch (horse.get(i).d) {
		case 0:
			horse.get(i).d = 1;
			break;
		case 1:
			horse.get(i).d = 0;
			break;
		case 2:
			horse.get(i).d = 3;
			break;
		case 3:
			horse.get(i).d = 2;
			break;

		}
	}

	static class Horse {
		int y;
		int x;
		int d;

		Horse(int y, int x, int d) {
			this.y = y;
			this.x = x;
			this.d = d;
		}
	}
}