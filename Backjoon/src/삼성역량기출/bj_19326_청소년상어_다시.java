package 삼성역량기출;

import java.util.Scanner;

public class bj_19326_청소년상어_다시 {

	static int[] dx = { 0, -1, -1, -1, 0, 1, 1, 1 };
	static int[] dy = { -1, -1, 0, 1, 1, 1, 0, -1 };
	static Fish[] fish;
	static int[][] map;
	static int MAX, A = 0;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		map = new int[4][4];
		fish = new Fish[17];
		MAX = 0;

		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				map[i][j] = sc.nextInt();// 물고기번호
				fish[map[i][j]] = new Fish(sc.nextInt() - 1, true, i, j);
			}
		}

		Fish shark = new Fish(fish[map[0][0]].dir, fish[map[0][0]].alive, fish[map[0][0]].y, fish[map[0][0]].x);
		fish[map[0][0]].alive = false; // 상어 시작점에 있는 물고기 죽음
		map[0][0] = 99;// 상어번호

		int sum = 0;

		// 상어이동
		moveShark(shark, sum);

		System.out.println(MAX);
	}

	private static void moveShark(Fish shark, int sum) {
		// 상어가 더 이상 갈 곳 없을 때 return
		MAX = (MAX < sum) ? sum : MAX;

		int[][] copy_map = new int[4][4];
		Fish[] copy_fish = new Fish[17];
		for (int i = 1; i < copy_fish.length; i++) {
			copy_fish[i] = new Fish(0, true, 0, 0);
		}

		copyState(copy_map, copy_fish);
		// 물고기 이동
		moveFish();

		// 4*4 행렬이라서 i<4
		for (int i = 1; i < 4; i++) {
			int iy = dy[shark.dir] * i + shark.y;
			int ix = dx[shark.dir] * i + shark.x;

			// 상어가 갈 수 있는 곳인지 확인
			if (iy >= 0 && iy < 4 && ix >= 0 && ix < 4 && map[iy][ix] != 0) {
				
				// 칸에 있는 물고기 먹기
				int dead_fish = copy_map[iy][ix];
				copy_fish[dead_fish].alive = false;

				// 칸으로 상어 이동
				copy_map[shark.y][shark.x] = 0;
				copy_map[iy][ix] = 99;

				Fish new_shark = new Fish(copy_fish[dead_fish].dir, true, iy, ix);
				moveShark(new_shark, sum + dead_fish);

				copy_fish[dead_fish].alive = true;
				copy_map[iy][ix] = 0;
				copy_map[shark.y][shark.x] = 99;

			}
		}
		rollback(copy_map, copy_fish);

	}

	private static void rollback(int[][] copy_map, Fish[] copy_fish) {
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				map[i][j] = copy_map[i][j];
			}
		}
		for (int i = 1; i < 17; i++) {
			fish[i].dir = copy_fish[i].dir;
			fish[i].alive = copy_fish[i].alive;
			fish[i].y = copy_fish[i].y;
			fish[i].x = copy_fish[i].x;

		}
	}

	private static void copyState(int[][] copy_map, Fish[] copy_fish) {
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				copy_map[i][j] = map[i][j];
			}
		}

		for (int i = 1; i < 17; i++) {
			copy_fish[i].dir = fish[i].dir;
			copy_fish[i].alive = fish[i].alive;
			copy_fish[i].y = fish[i].y;
			copy_fish[i].x = fish[i].x;
		}
	}

	private static int[][] copyArray(int[][] map) {
		int[][] copy_map = new int[4][4];

		for (int i = 0; i < copy_map.length; i++) {
			for (int j = 0; j < copy_map.length; j++) {

				copy_map[i][j] = map[i][j];
			}
		}

		return copy_map;
	}

	private static void moveFish() {
		for (int i = 1; i < 17; i++) {
			if (fish[i].alive) {
				Fish now = fish[i];

				for (int j = 0; j < 8; j++) {
					int dir = (now.dir + j) % 8;
					int ix = now.x + dx[dir];
					int iy = now.y + dy[dir];

					if (ix >= 0 && ix < 4 && iy >= 0 && iy < 4 && map[iy][ix] != 99) {
						fish[i].dir = dir;
						fish[i].x = ix;
						fish[i].y = iy;

						if (map[iy][ix] != 0) {
							// 번호 바꾸기
							int fish_num = map[iy][ix];
							map[iy][ix] = map[now.y][now.x];
							map[now.y][now.x] = fish_num;

							// 위치 재저장
							fish[fish_num].x = now.x;
							fish[fish_num].y = now.y;

						} else {
							map[iy][ix] = map[now.y][now.x];
							map[now.y][now.x] = 0;
						}
						break;
					}
				}
			}
		}
	}

	private static void printMap(int[][] map) {
		// TODO Auto-generated method stub
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map.length; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
	}

	static class Fish {
		int dir;
		boolean alive;
		int y;
		int x;

		Fish(int dir, boolean alive, int y, int x) {
			this.dir = dir;
			this.alive = alive;
			this.y = y;
			this.x = x;
		}
	}

}
