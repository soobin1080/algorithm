package 삼성역량기출;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class bj_23290_마법사상어와복제_2차 {

	static ArrayList<Fish>[][] map = new ArrayList[4][4];
	static int checkSmell[][] = new int[4][4];
	static int M, S;
	static int sharky, sharkx;

	static int[] dx = { 0, -1, -1, 0, 1, 1, 1, 0, -1 };
	static int[] dy = { 0, 0, -1, -1, -1, 0, 1, 1, 1 };

	static int[] sx = { 0, 0, -1, 0, 1 }; // 상 좌 하 우
	static int[] sy = { 0, -1, 0, 1, 0 };

	static class Shark implements Comparable<Shark> {
		int y;
		int x;
		int route;
		int fishCount;

		Shark(int y, int x, int route, int fishCount) {
			this.y = y;
			this.x = x;
			this.route = route;
			this.fishCount = fishCount;
		}

		@Override
		public int compareTo(Shark o) {
			if (this.fishCount == o.fishCount)
				return this.route - o.route;
			else
				return o.fishCount - this.fishCount;
		}
	}

	static class Fish {
		int y;
		int x;
		int d;

		Fish(int y, int x, int d) {
			this.y = y;
			this.x = x;
			this.d = d;
		}
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		M = sc.nextInt();
		S = sc.nextInt();

		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				map[i][j] = new ArrayList<>();
			}
		}

		// M마리 물고기 좌표,방향 입력받기
		for (int i = 0; i < M; i++) {
			int fy = sc.nextInt() - 1;
			int fx = sc.nextInt() - 1;
			int fd = sc.nextInt();

			map[fy][fx].add(new Fish(fy, fx, fd));
		}

		// 상어좌표 입력받기
		sharky = sc.nextInt() - 1;
		sharkx = sc.nextInt() - 1;

		// S번 연습하기
		for (int i = 0; i < S; i++) {
			fishCopy();
		}

		// 물고기 수 체크
		int answer = 0;
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				answer += map[i][j].size();
			}
		}

		System.out.println(answer);
	}

	private static void fishCopy() {
		// 물고기랑 상어 이동 전 원래 값 복사해놓기
		ArrayList<Fish>[][] copyMap = new ArrayList[4][4];
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				copyMap[i][j] = new ArrayList<Fish>();
				for (int k = 0; k < map[i][j].size(); k++) {
					copyMap[i][j].add(new Fish(i, j, map[i][j].get(k).d));
				}
			}
		}

		// 물고기 이동시키기
		fishMove();

		// 상어 이동 경로 찾기
		sharkMove();

		// 물고기 냄새 제거하기
		removeSmell();

		// 물고기 복제하기
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				if (map[i][j] == null)
					map[i][j] = new ArrayList<>();
				int size = copyMap[i][j].size();
				for (int k = 0; k < size; k++) {
					Fish now = copyMap[i][j].get(k);
					map[i][j].add(new Fish(now.y, now.x, now.d));
				}
			}
		}

		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				System.out.print(map[i][j].size());
			}
			System.out.println();
		}
		System.out.println();

	}

	private static void removeSmell() {
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				if (checkSmell[i][j] != 0)
					checkSmell[i][j]--;
			}
		}
	}

	private static void sharkMove() {
		boolean visited[][] = new boolean[4][4];
		List<Shark> qu = new LinkedList<>();
		qu.add(new Shark(sharky, sharkx, 0, 0));
		for (int i = 2; i >= 0; i--) {
			int size = qu.size();
			for (int j = 0; j < size; j++) {
				Shark now = qu.get(0);
				int y = now.y;
				int x = now.x;
				for (int k = 1; k < 5; k++) {
					int ny = now.y + sy[k];
					int nx = now.x + sx[k];
					if (ny >= 0 && ny < 4 && nx >= 0 && nx < 4 && !visited[ny][nx]) {
						int rt = (int) Math.pow(10, i) * k;
						qu.add(new Shark(ny, nx, now.route + rt, now.fishCount + map[ny][nx].size()));
					}
				}

				visited[y][x] = true;
				if (i == 2 && y == sharky && x == sharkx)
					visited[y][x] = false;
				qu.remove(0);
			}
		}

		Collections.sort(qu);
		int finalroute = qu.get(0).route;

		System.out.println(finalroute + ",물고기수:" + qu.get(0).fishCount);

		// 물고기 삭제 후 냄새 남기기
		for (int i = 1; i <= 3; i++) {
			int rt = 0;
			if (i == 1)
				rt = finalroute / 100;
			else if (i == 2)
				rt = (finalroute % 100) / 10;
			else
				rt = finalroute % 10;
			int ny = sharky + sy[rt];
			int nx = sharkx + sx[rt];
			if (map[ny][nx].size() > 0) {
				checkSmell[ny][nx] = 3;
			}
			map[ny][nx].clear(); // 배열다시선언해줘야하나?
			System.out.println(rt + "방향으로:");
			sharky = ny;
			sharkx = nx;
			System.out.println(sharky + "," + sharkx);

		}
	}

	private static void fishMove() {

		ArrayList<Fish>[][] copyMap = new ArrayList[4][4];
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				copyMap[i][j] = new ArrayList<Fish>();
			}
		}

		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				// 물고기 있으면 이동시킨다
				if (map[i][j].size() != 0) {
					// 칸에 있는 물고기 수만큼 움직여주기
					int size = map[i][j].size();
					for (int f = 0; f < size; f++) {
						Fish now = map[i][j].get(f);
						int d = now.d;
						boolean move = false;
						a: for (int s = 0; s < 8; s++) {
							int ny = dy[d] + now.y;
							int nx = dx[d] + now.x;
							// 이동할 수 있으면 이동!!
							if (ny >= 0 && ny < 4 && nx >= 0 && nx < 4 && checkSmell[ny][nx] == 0) {
								if (ny != sharky || nx != sharkx) {
									copyMap[ny][nx].add(new Fish(ny, nx, d));
									move = true;
									break a;
								}
							}
							d--;
							if (d < 1) {
								d = 8;
							}
						}
						if (!move) {
							copyMap[now.y][now.x].add(new Fish(now.y, now.x, now.d));
						}
					}
				}
			}
		}

		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				map[i][j].clear();
				for (int k = 0; k < copyMap[i][j].size(); k++) {
					map[i][j].add(new Fish(copyMap[i][j].get(k).y, copyMap[i][j].get(k).x, copyMap[i][j].get(k).d));
				}
			}
		}
	}
}
