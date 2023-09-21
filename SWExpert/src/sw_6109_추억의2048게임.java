import java.util.Scanner;

public class sw_6109_추억의2048게임 {

	static int dy[] = { -1, 1, 0, 0 }; // up down left right
	static int dx[] = { 0, 0, -1, 1 };
	static int map[][];
	static int N;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int TC = sc.nextInt();

		for (int tc = 1; tc <= TC; tc++) {

			N = sc.nextInt();
			String dir = sc.next();
			map = new int[N][N];
			// 입력받기
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					map[i][j] = sc.nextInt();
				}
			}

			if (dir.equals("up")) {
				pushUp();
			} else if (dir.equals("down")) {
				pushDown();
			} else if (dir.equals("left")) {
				pushLeft();
			} else {
				pushRight();
			}

			System.out.println("#" + tc);
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					System.out.print(map[i][j] + " ");
				}
				System.out.println();
			}
		}

	}

	private static void pushUp() {
		boolean[][] plus = new boolean[N][N];

		// 움직이기
		for (int j = 0; j < N; j++) {
			for (int i = 0; i < N; i++) {
				// 1.움직일 숫자 정하기
				if (map[i][j] != 0) {
					// 2. 앞에 숫자가 있거나 벽이 있을 때까지 움직인다. (idx 구하기)
					int idx = -1;
					for (int k = i - 1; k >= 0; k--) {
						if (map[k][j] != 0) {
							idx = k;
							break;
						}
					}
					// 끝으로이동
					if (idx == -1) {
						map[0][j] = map[i][j];
						if (i != 0)
							map[i][j] = 0;
					} else {
						if (map[idx][j] == map[i][j] && !plus[idx][j]) {
							map[idx][j] += map[i][j];
							map[i][j] = 0;
							plus[idx][j] = true;
						} else {
							if (idx + 1 != i) {
								map[idx + 1][j] = map[i][j];
								map[i][j] = 0;
							}
						}
					}
				}
			}
		}
	}

	private static void pushDown() {
		boolean[][] plus = new boolean[N][N];
		// 움직이기
		for (int j = 0; j < N; j++) {
			for (int i = N - 1; i >= 0; i--) {
				// 1.움직일 숫자 정하기
				if (map[i][j] != 0) {
					// 2. 앞에 숫자가 있거나 벽이 있을 때까지 움직인다. (idx 구하기)
					int idx = -1;
					for (int k = i + 1; k < N; k++) {
						if (map[k][j] != 0) {
							idx = k;
							break;
						}
					}
					// 끝으로이동
					if (idx == -1) {
						map[N - 1][j] = map[i][j];
						if (i != N - 1)
							map[i][j] = 0;
					} else {
						if (map[idx][j] == map[i][j] && !plus[idx][j]) {
							map[idx][j] += map[i][j];
							map[i][j] = 0;
							plus[idx][j] = true;
						} else {
							if (idx - 1 != i) {
								map[idx - 1][j] = map[i][j];
								map[i][j] = 0;
							}
						}
					}
				}
			}
		}
	}

	private static void pushLeft() {
		boolean[][] plus = new boolean[N][N];

		// 움직이기
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				// 1.움직일 숫자 정하기
				if (map[i][j] != 0) {
					// 2. 앞에 숫자가 있거나 벽이 있을 때까지 움직인다. (idx 구하기)
					int idx = -1;
					for (int k = j - 1; k >= 0; k--) {
						if (map[i][k] != 0) {
							idx = k;
							break;
						}
					}
					// 끝으로이동
					if (idx == -1) {
						map[i][0] = map[i][j];
						if (j != 0)
							map[i][j] = 0;
					} else {
						if (map[i][idx] == map[i][j] && !plus[i][idx]) {
							map[i][idx] += map[i][j];
							map[i][j] = 0;
							plus[i][idx] = true;
						} else {
							if (idx + 1 != j) {
								map[i][idx + 1] = map[i][j];
								map[i][j] = 0;
							}
						}
					}
				}
			}
		}
	}

	private static void pushRight() {
		boolean[][] plus = new boolean[N][N];

		// 움직이기
		for (int i = 0; i < N; i++) {
			for (int j = N - 1; j >= 0; j--) {
				// 1.움직일 숫자 정하기
				if (map[i][j] != 0) {
					// 2. 앞에 숫자가 있거나 벽이 있을 때까지 움직인다. (idx 구하기)
					int idx = -1;
					for (int k = j + 1; k < N; k++) {
						if (map[i][k] != 0) {
							idx = k;
							break;
						}
					}
					// 끝으로이동
					if (idx == -1) {
						map[i][N - 1] = map[i][j];
						if (j != N - 1)
							map[i][j] = 0;
					} else {
						if (map[i][idx] == map[i][j] && !plus[i][idx]) {
							map[i][idx] += map[i][j];
							map[i][j] = 0;
							plus[i][idx] = true;
						} else {
							if (idx - 1 != j) {
								map[i][idx - 1] = map[i][j];
								map[i][j] = 0;
							}
						}
					}
				}
			}
		}
	}
}
