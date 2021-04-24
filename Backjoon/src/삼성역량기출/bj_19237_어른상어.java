package 삼성역량기출;

import java.util.Scanner;

public class bj_19237_어른상어 {
	static int N, M, K, LEFT;
	static Smell[][] map;
	static Shark[] shark;
	static int[] dy = { -1, 1, 0, 0 };
	static int[] dx = { 0, 0, -1, 1 };
	static int[][][] direction;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();
		M = sc.nextInt();
		K = sc.nextInt();

		map = new Smell[N][N];
		shark = new Shark[M + 1];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				int n = sc.nextInt();
				if (n > 0) {
					shark[n] = new Shark(i, j, 0, true);
					map[i][j] = new Smell(n, K, true);
				}
			}
		}
		for (int i = 1; i <= M; i++) {
			shark[i].dir = sc.nextInt() - 1;
		}

		direction = new int[M + 1][4][4];
		for (int i = 1; i <= M; i++) {
			for (int j = 0; j < 4; j++) {
				for (int j2 = 0; j2 < 4; j2++) {
					direction[i][j][j2] = sc.nextInt() - 1;
				}
			}
		}

		// 한마리 남을 때까지
		LEFT = M;
		int time = 0;
		while (LEFT != 1 && time <= 1000) {
			time++;
			// 상어 이동
			moveShark();

			// 상어없는 곳 냄새 하나씩 -1 하기
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (map[i][j] != null && !map[i][j].shark) {
						map[i][j].smell--;
						if (map[i][j].smell == 0)
							map[i][j] = null;
					}
				}
			}
		}
		if (LEFT == 1 && time <= 1000)
			System.out.println(time);
		else
			System.out.println(-1);

		sc.close();
	}

	private static void moveShark() {
		Smell[][] copyMap = new Smell[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (map[i][j] != null)
					copyMap[i][j] = new Smell(map[i][j].sharkNum, map[i][j].smell, map[i][j].shark);
			}
		}

		for (int i = 1; i < M + 1; i++) {
			if (shark[i].alive) {
				int y = shark[i].y;
				int x = shark[i].x;
				int dir = shark[i].dir;
				// 4방향 확인 하기
				boolean move = false;
				for (int d = 0; d < 4; d++) {
					int nd = direction[i][dir][d];
					int ny = y + dy[nd];
					int nx = x + dx[nd];
					if (ny >= 0 && ny < N && nx >= 0 && nx < N && copyMap[ny][nx] == null) {
						if (map[ny][nx] == null) {
							map[ny][nx] = new Smell(i, K, true);
							map[y][x].shark = false;
							shark[i].y = ny;
							shark[i].x = nx;
							shark[i].dir = nd;
							move = true;
							break;
						} else { // 다른 상어 있을 때
							int sharkNum = map[ny][nx].sharkNum;
							if (sharkNum > i) {
								// 원래 있던 거 보다 작은 숫자일 때
								map[ny][nx] = new Smell(i, K, true);
								map[y][x].shark = false;
								shark[sharkNum].alive = false;
								LEFT--;
								shark[i].y = ny;
								shark[i].x = nx;
								shark[i].dir = nd;
								move = true;
								break;
							} else {
								// 원래 있던 거 보다 큰 숫자면 죽음
								shark[i].alive = false;
								map[y][x].shark = false;
								LEFT--;
								move = true;
								break;
							}
						}
					}
				}
				// 다 돌아도 갈 곳이 없을때 방향바꾸고 다시 4방향 확인하기
				if (!move) {
					changeDir(i);
					dir = shark[i].dir;
					for (int d = 0; d < 4; d++) {
						int nd = direction[i][dir][d];
						int ny = y + dy[nd];
						int nx = x + dx[nd];
						if (ny >= 0 && ny < N && nx >= 0 && nx < N) {
							if (copyMap[ny][nx] != null && copyMap[ny][nx].sharkNum == i) {
								map[ny][nx] = new Smell(i, K, true);
								map[y][x].shark = false;
								shark[i].y = ny;
								shark[i].x = nx;
								shark[i].dir = nd;
								break;
							}
						}
					}
				}
			}
		}
	}

	private static void changeDir(int i) {
		int dir = shark[i].dir;
		if (dir == 0)
			dir = 1;
		else if (dir == 1)
			dir = 0;
		else if (dir == 2)
			dir = 3;
		else if (dir == 3)
			dir = 2;
	}

	static class Smell {
		int sharkNum;
		int smell;
		boolean shark;

		Smell(int sharkNum, int smell, boolean shark) {
			this.sharkNum = sharkNum;
			this.smell = smell;
			this.shark = shark;
		}
	}

	static class Shark {
		int y;
		int x;
		int dir;
		boolean alive;

		Shark(int y, int x, int dir, boolean alive) {
			this.y = y;
			this.x = x;
			this.dir = dir;
			this.alive = alive;
		}
	}

}
