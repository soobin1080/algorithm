package 삼성역량기출;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class bj_12100_2048easy {
	static int N, MAX = 0;
	static int[][] MAP;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();
		MAP = new int[N][N];

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				MAP[i][j] = sc.nextInt();
			}
		}

		playGame(MAP, 0, 0, 5);
		
		System.out.println(MAX);
		sc.close();
	}

	private static void playGame(int[][] map, int dir, int cnt, int n) {
		if (cnt == n) {
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					MAX = Math.max(map[i][j], MAX);
				}
			}
			return;
		}
		for (int i = 0; i < 4; i++) {
			int d = (dir + i) % 4;

			// 복사하기
			int[][] copyMap = new int[N][N];

			for (int y = 0; y < N; y++) {
				for (int x = 0; x < N; x++) {
					copyMap[y][x] = map[y][x];
				}
			}
			// 움직이기
			move(copyMap, d);

			playGame(copyMap, d, cnt + 1, n);
		}
	}

	private static void move(int[][] copyMap, int d) {
		Queue<Integer> que = new LinkedList<>();

		switch (d) {
		case 0:
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (copyMap[j][i] != 0)
						que.add(copyMap[j][i]);
				}

				int index = 0;
				while (!que.isEmpty()) {
					int preNumber = que.poll();
					int number = 0;
					if (que.size() != 0)
						number = que.peek();
					if (preNumber == number) {
						copyMap[index][i] = preNumber + number;
						que.poll();
					} else
						copyMap[index][i] = preNumber;
					index++;
				}

				while (index != N) {
					copyMap[index][i] = 0;
					index++;
				}
			}

			break;
		case 1:
			for (int i = 0; i < N; i++) {
				for (int j = N - 1; j >= 0; j--) {
					if (copyMap[j][i] != 0)
						que.add(copyMap[j][i]);
				}

				int index = N - 1;
				while (!que.isEmpty()) {
					int preNumber = que.poll();
					int number = 0;
					if (que.size() != 0)
						number = que.peek();

					if (preNumber == number) {
						copyMap[index][i] = preNumber + number;
						que.poll();
					} else
						copyMap[index][i] = preNumber;
					index--;
				}

				while (index != -1) {
					copyMap[index][i] = 0;
					index--;
				}
			}
			break;
		case 2:
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (copyMap[i][j] != 0)
						que.add(copyMap[i][j]);
				}

				int index = 0;
				while (!que.isEmpty()) {
					int preNumber = que.poll();
					int number = 0;
					if (que.size() != 0)
						number = que.peek();

					if (preNumber == number) {
						copyMap[i][index] = preNumber + number;
						que.poll();
					} else
						copyMap[i][index] = preNumber;
					index++;
				}

				while (index != N) {
					copyMap[i][index] = 0;
					index++;
				}
			}
			break;
		case 3:
			for (int i = 0; i < N; i++) {
				for (int j = N - 1; j >= 0; j--) {
					if (copyMap[i][j] != 0)
						que.add(copyMap[i][j]);
				}

				int index = N - 1;
				while (!que.isEmpty()) {
					int preNumber = que.poll();
					int number = 0;
					if (que.size() != 0)
						number = que.peek();

					if (preNumber == number) {
						copyMap[i][index] = preNumber + number;
						que.poll();
					} else
						copyMap[i][index] = preNumber;
					index--;
				}

				while (index != -1) {
					copyMap[i][index] = 0;
					index--;
				}
			}
			break;
		}

	}

}
