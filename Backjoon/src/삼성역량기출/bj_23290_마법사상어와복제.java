package 삼성역량기출;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class bj_23290_마법사상어와복제 {
	static int map[][] = new int[4][4]; // 상어위치 냄새기록
	static int fishCount[][]; // 물고기 마릿수 저장
	static int M, S;

	static int sharky, sharkx, maxEat = 0;
	static int sharkMove[] = new int[3];
	static boolean visited[][];
	static List<String> sharkDirection;
	static int dy[] = { 0, 0, -1, -1, -1, 0, 1, 1, 1 }; // 좌부터 시계방향으로 45도씩 방향
	static int dx[] = { 0, -1, -1, 0, 1, 1, 1, 0, -1 };

	static int sy[] = { -1, 0, 0, 1 }; // 상 좌 우 하
	static int sx[] = { 0, -1, 1, 0 };

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		M = sc.nextInt();
		S = sc.nextInt();

		List<Fish> fishes = new LinkedList<>();

		for (int i = 0; i < M; i++) {
			int y = sc.nextInt() - 1;
			int x = sc.nextInt() - 1;
			int d = sc.nextInt();
			fishes.add(new Fish(y, x, d));
		}

		sharky = sc.nextInt() - 1;
		sharkx = sc.nextInt() - 1;

		map[sharky][sharkx] = 9;

		for (int s = 0; s < S; s++) {

			List<Fish> movedFishes = new LinkedList<>();
			fishCount = new int[4][4];

			int size = fishes.size();

			// 물고기 이동시키기
			for (int i = 0; i < size; i++) {
				int fishy = fishes.get(i).y;
				int fishx = fishes.get(i).x;
				int fishd = fishes.get(i).d;

				boolean move = false;
				int dCount = 0;
				while (!move) {
					if (dCount >= 8) {
						fishCount[fishy][fishx]++;
						break;
					}
					int ny = fishy + dy[fishd];
					int nx = fishx + dx[fishd];
					if (ny >= 0 && ny < 4 && nx >= 0 && nx < 4 && map[ny][nx] == 0) {
						movedFishes.add(new Fish(ny, nx, fishd));
						fishCount[ny][nx]++;
						move = true;
					} else {
						fishd--;
						if (fishd <= 0) // 방향 배열 첫번째는 빈값이기 때문에
							fishd = 8;
						dCount++;
					}
				}
			}
			System.out.println("상어위치:" + sharky + "," + sharkx);
			System.out.println("상어이동전(복제된상태)");
			for (int i = 0; i < 4; i++) {
				for (int j = 0; j < 4; j++) {
					System.out.print(fishCount[i][j] + " ");
				}
				System.out.println();
			}
			System.out.println("map");

			for (int i = 0; i < 4; i++) {
				for (int j = 0; j < 4; j++) {
					System.out.print(map[i][j] + " ");
				}
				System.out.println();
			}
			System.out.println();

			// 상어 움직이기
			maxEat = 0;
			visited = new boolean[4][4];
			visited[sharky][sharkx] = true;
			findMaxFish(sharky, sharkx, 0, 0);

			sharkDirection = new LinkedList<>();
			findSharkDirection(sharky, sharkx, 0, 0);
			Collections.sort(sharkDirection);

			for (int i = 0; i < 3; i++) {
				sharkMove[i] = sharkDirection.get(0).charAt(i) - 48;
			}
			System.out.println(sharky + "," + sharkx);
			System.out.println(sharkDirection.get(0));
			// 물고기냄새남기기
			int sharky_org = sharky;
			int sharkx_org = sharkx;

			for (int i = 0; i < 3; i++) {
				int sharkd = sharkMove[i];
				int ny = sharky + sy[sharkd];
				int nx = sharkx + sx[sharkd];

				if (ny >= 0 && ny < 4 && nx >= 0 && nx < 4) {
					if (fishCount[ny][nx] > 0)
						map[ny][nx] = 3; // 냄새남김
					// 물고기삭제하기
					int deleteSize = movedFishes.size();
					for (int j = deleteSize - 1; j >= 0; j--) {
						if (movedFishes.get(j).y == ny && movedFishes.get(j).x == nx) {
							System.out.println("사라지는물고기:" + ny + "," + nx);
							movedFishes.remove(j);
							fishCount[ny][nx]--;
						}
					}

					sharky = ny;
					sharkx = nx;
				}
			}
			if (map[sharky][sharkx] > 0) {
				if (map[sharky_org][sharkx_org] == 9)
					map[sharky_org][sharkx_org] = 0;
			} else if (map[sharky][sharkx] == 0) {
				if (map[sharky_org][sharkx_org] == 9) {
					map[sharky_org][sharkx_org] = 0;
					map[sharky][sharkx]=9;
				}
			}

			System.out.println("상어이동후+물고기냄새");
			for (int k = 0; k < 4; k++) {
				for (int j = 0; j < 4; j++) {
					System.out.print(fishCount[k][j] + " ");
				}
				System.out.println();
			}
			System.out.println("map");
			for (int i = 0; i < 4; i++) {
				for (int j = 0; j < 4; j++) {
					System.out.print(map[i][j] + " ");
				}
				System.out.println();
			}
			System.out.println("ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ");

			// 냄새 마이너스해주기
			for (int i = 0; i < 4; i++) {
				for (int j = 0; j < 4; j++) {
					if (map[i][j] > 0 && map[i][j] <= 3)
						map[i][j]--;
				}
			}


			// 복제하기
			int movedSize = movedFishes.size();
			for (int i = 0; i < movedSize; i++) {
				fishes.add(new Fish(movedFishes.get(i).y, movedFishes.get(i).x, movedFishes.get(i).d));
			}

		}

		System.out.println(fishes.size());
	}

	private static void findSharkDirection(int sharky, int sharkx, int eat, int moveCount) {

		if (moveCount == 3) {
			if (maxEat == eat) {
				String s = "";
				for (int i = 0; i < 3; i++) {
					s += sharkMove[i];
				}
				sharkDirection.add(s);
			}
			return;
		}

		for (int i = 0; i < 4; i++) {
			int ny = sharky + sy[i];
			int nx = sharkx + sx[i];
			if (ny >= 0 && ny < 4 && nx >= 0 && nx < 4 && !visited[ny][nx]) {
				visited[ny][nx] = true;
				sharkMove[moveCount] = i;
				findSharkDirection(ny, nx, eat + fishCount[ny][nx], moveCount + 1);
				visited[ny][nx] = false;
			}
		}
	}

	private static void findMaxFish(int sharky, int sharkx, int eat, int moveCount) {

		if (moveCount >= 3) {
			if (eat > maxEat) {
				maxEat = eat;
			}
			return;
		}

		for (int i = 0; i < 4; i++) {
			int ny = sharky + sy[i];
			int nx = sharkx + sx[i];
			if (ny >= 0 && ny < 4 && nx >= 0 && nx < 4 && !visited[ny][nx]) {
				visited[ny][nx] = true;
				sharkMove[moveCount] = i;
				findMaxFish(ny, nx, eat + fishCount[ny][nx], moveCount + 1);
				visited[ny][nx] = false;
			}
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

}
