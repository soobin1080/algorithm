package 삼성역량기출;

import java.util.Scanner;

public class bj_19236_청소년상어 {

	static int[] dx = { 0, -1, -1, -1, 0, 1, 1, 1 };
	static int[] dy = { -1, -1, 0, 1, 1, 1, 0, -1 };
	static Fish[] fish;
	static int[][] map;
	static int MAX;

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

		// 상어 위치, 방향
		int y = 0, x = 0, dir = fish[map[0][0]].dir;
		fish[map[0][0]].alive = false; // 상어 시작점에 있는 물고기 죽음
		int sum = map[0][0];
		map[0][0] = -1;// 상어번호

		// 상어이동
		moveShark(map, y, x, dir, fish, sum);

		System.out.println(MAX);
		sc.close();
	}

	static void moveFish(int[][] map, Fish[] fish) {
		for (int i = 1; i < 17; i++) {
			if (fish[i].alive) {
				Fish now = new Fish(fish[i].dir, fish[i].alive, fish[i].y, fish[i].x);

				for (int j = 0; j < 8; j++) {
					int dir = (now.dir + j) % 8;
					int iy = now.y + dy[dir];
					int ix = now.x + dx[dir];

					if (ix >= 0 && ix < 4 && iy >= 0 && iy < 4 && map[iy][ix] != -1) {
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

	static void moveShark(int[][] map, int y, int x, int dir, Fish[] fish, int sum) {

		MAX = (MAX < sum) ? sum : MAX;

		// 배열 복사하기 - 상태 저장
		int[][] copy_map = new int[4][4];

		for (int i = 0; i < copy_map.length; i++) {
			for (int j = 0; j < copy_map.length; j++) {
				copy_map[i][j] = map[i][j];
			}
		}

		Fish[] copy_fish = new Fish[17];

		for (int i = 1; i < copy_fish.length; i++) {
			copy_fish[i] = new Fish(fish[i].dir, fish[i].alive, fish[i].y, fish[i].x);
		}

		// 물고기 이동하기
		moveFish(copy_map, copy_fish);

		// 상어 움직이기 - 상태 변경
		for (int i = 1; i < 4; i++) {
			int iy = dy[dir] * i + y;
			int ix = dx[dir] * i + x;

			if (iy >= 0 && iy < 4 && ix >= 0 && ix < 4 && copy_map[iy][ix] != 0) {
				int fish_num = copy_map[iy][ix];
				copy_fish[fish_num].alive = false;
				copy_map[y][x] = 0;
				copy_map[iy][ix] = -1;

				moveShark(copy_map, iy, ix, copy_fish[fish_num].dir, copy_fish, sum + fish_num);

				// 상어가 움직이기 전 상태로 돌려주기
				copy_fish[fish_num].alive = true;
				copy_map[y][x] = -1;
				copy_map[iy][ix] = fish_num;

			}
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
