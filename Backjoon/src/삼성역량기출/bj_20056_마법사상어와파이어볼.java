package 삼성역량기출;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class bj_20056_마법사상어와파이어볼 {
	static ArrayList<Fireball>[][] map;
	static int N, M, K;
	static Queue<Fireball> fireball;
	static int[] dy = { -1, -1, 0, 1, 1, 1, 0, -1 };
	static int[] dx = { 0, 1, 1, 1, 0, -1, -1, -1 };
	static int[] d1 = { 0, 2, 4, 6 };
	static int[] d2 = { 1, 3, 5, 7 };

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();
		M = sc.nextInt();
		K = sc.nextInt();
		map = new ArrayList[N][N];

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				map[i][j] = new ArrayList<>();
			}
		}

		fireball = new LinkedList<>();
		// M개 파이어볼
		for (int i = 0; i < M; i++) {
			int y = sc.nextInt() - 1;
			int x = sc.nextInt() - 1;
			int m = sc.nextInt();
			int s = sc.nextInt();
			int d = sc.nextInt();
			fireball.add(new Fireball(y, x, m, s, d));
			map[y][x].add(new Fireball(y, x, m, s, d));
		}

		// K번 명령
		for (int i = 0; i < K; i++) {
			for (int r = 0; r < N; r++) {
				for (int c = 0; c < N; c++) {
					map[r][c].clear();
				}
			}

			int size = fireball.size();
			// 파이어볼 하나씩 이동시키기 이동시킴
			for (int j = 0; j < size; j++) {
				Fireball fb = fireball.poll();
				int y = fb.y;
				int x = fb.x;
				int m = fb.m;
				int s = fb.s;
				int d = fb.d;
				int n = s % N;
				for (int k = 0; k < n; k++) {
					y = y + dy[d];
					x = x + dx[d];
					if (y == -1)
						y = N - 1;
					if (y == N)
						y = 0;
					if (x == -1)
						x = N - 1;
					if (x == N)
						x = 0;
				}
				map[y][x].add(new Fireball(y, x, m, s, d));
			}
			// 2개 이상인 곳 계산하기
			for (int r = 0; r < N; r++) {
				for (int c = 0; c < N; c++) {
					int fireballcount = map[r][c].size();
					if (fireballcount >= 2) {
						int mass = 0, speed = 0, odd = 0, even = 0;

						for (int j = 0; j < fireballcount; j++) {
							mass += map[r][c].get(j).m;
							speed += map[r][c].get(j).s;
							int dir = map[r][c].get(j).d;
							if (dir % 2 == 0) {
								even++;
							} else
								odd++;
						}

						mass = mass / 5;
						speed = speed / fireballcount;
						map[r][c].clear();

						if (mass != 0) {
							if (even == fireballcount || odd == fireballcount) {
								// d1
								for (int j = 0; j < 4; j++) {
									Fireball fb = new Fireball(r, c, mass, speed, d1[j]);
									fireball.add(fb);
									map[r][c].add(fb);
								}
							} else {
								// d2
								for (int j = 0; j < 4; j++) {
									Fireball fb = new Fireball(r, c, mass, speed, d2[j]);
									fireball.add(fb);
									map[r][c].add(fb);
								}
							}
						}
					} else if (fireballcount == 1) {
						Fireball fb = map[r][c].get(0);
						fireball.add(new Fireball(fb.y, fb.x, fb.m, fb.s, fb.d));

					}
				}
			}
		}

		// 남은 질량 구하기
		int totalMass = 0;
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				if (map[r][c].size() > 0) {
					for (int j = 0; j < map[r][c].size(); j++) {
						totalMass += map[r][c].get(j).m;
					}
				}
			}
		}

		System.out.println(totalMass);

		sc.close();
	}

	static class Fireball {
		int y;
		int x;
		int m; // 질량
		int s; // 속력
		int d; // 방향

		Fireball(int y, int x, int m, int s, int d) {
			this.y = y;
			this.x = x;
			this.m = m;
			this.s = s;
			this.d = d;
		}
	}

}
