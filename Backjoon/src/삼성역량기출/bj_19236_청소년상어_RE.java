package 삼성역량기출;

import java.util.Scanner;

public class bj_19236_청소년상어_RE {
	static int dy[] = { -1, -1, 0, 1, 1, 1, 0, -1 };// 반시계방향으로 45도씩
	static int dx[] = { 0, -1, -1, -1, 0, 1, 1, 1 };
	static int MaxNumber = 0;
	static Fish fish[][] = new Fish[4][4];
	static Shark shark;
	static Fish fishLocation[] = new Fish[17];

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		// 입력받기 : x는 행, y는 열
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				int num = sc.nextInt();
				int direction = sc.nextInt() - 1;
				fish[i][j] = new Fish(i, j, num, direction);
				fishLocation[num] = new Fish(i, j, num, direction);
			}
		}

		// 상어 공간 입장
		shark = new Shark(0, 0, fish[0][0].direction);
		// 0,0에 있는 물고기 잡아먹음.
		int num = fish[0][0].number;
		fish[0][0] = null;
		fishLocation[num] = null;
		// 상어이동
		moveShark(fish, fishLocation, shark, num);
		System.out.println(MaxNumber);

	}

	static void moveShark(Fish fish[][], Fish fishLocation[], Shark shark, int fishNumberSum) {
		if (fishNumberSum > MaxNumber) {
			MaxNumber = fishNumberSum;
		}

		Fish copyFish[][] = new Fish[4][4];
		for (int i = 0; i < copyFish.length; i++) {
			for (int j = 0; j < copyFish.length; j++) {
				if (fish[i][j] != null) {
					copyFish[i][j] = new Fish(fish[i][j].y, fish[i][j].x, fish[i][j].number, fish[i][j].direction);
				}
			}
		}
		Fish copyFishLocation[] = new Fish[17];
		for (int i = 0; i < copyFishLocation.length; i++) {
			copyFishLocation[i] = fishLocation[i];
		}
		// 물고기이동
		moveFish(copyFish, copyFishLocation, shark);
		// printMap(copyFish);

		// 물고기 없는 칸으로 이동 불가
		int direction = shark.direction;
		int y = shark.y;
		int x = shark.x;

		for (int i = 1; i < 4; i++) {
			int ny = y + dy[direction] * i;
			int nx = x + dx[direction] * i;
			if (ny >= 0 && ny < 4 && nx >= 0 && nx < 4) {// 공간의 경계 안에 있을 때
				if (copyFish[ny][nx] != null) { // 물고기가 있을 때 // 상어 이동했으면 물고기잡아먹기
					Fish f = new Fish(copyFish[ny][nx].y, copyFish[ny][nx].x, copyFish[ny][nx].number,
							copyFish[ny][nx].direction);
					shark.y = ny;
					shark.x = nx;
					shark.direction = copyFish[ny][nx].direction;
					int number = copyFish[ny][nx].number;
					copyFish[ny][nx] = null;
					copyFishLocation[number] = null;
					moveShark(copyFish, copyFishLocation, shark, fishNumberSum + number);
					copyFish[ny][nx] = new Fish(f.y, f.x, f.number, f.direction);
					copyFishLocation[number] = new Fish(f.y, f.x, f.number, f.direction);
				}
			}
		}
	}

	static void printMap(Fish[][] copyFish) {
		for (int i = 0; i < copyFish.length; i++) {
			for (int j = 0; j < copyFish.length; j++) {
				if (copyFish[i][j] != null)
					System.out.print(copyFish[i][j].number + " ");
				else
					System.out.print("0 ");
			}
			System.out.println();
		}
		System.out.println("ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ");
	}

	static void moveFish(Fish fish[][], Fish fishLocation[], Shark shark) {
		// 이동할 수 있는 칸은 빈 칸과 다른 물고기가 있는 칸
		// 이동할 수 없는 칸은 상어가 있거나, 공간의 경계를 넘는 칸
		for (int k = 1; k < fishLocation.length; k++) {

			Fish now = fishLocation[k];
			if (now != null) {
				for (int i = 0; i < 8; i++) {
					int d = (now.direction + i) % 8;
					int ny = now.y + dy[d];
					int nx = now.x + dx[d];

					if (ny >= 0 && ny < 4 && nx >= 0 && nx < 4) { // 공간의 경계를 안넘고
						if (!(shark.y == ny && shark.x == nx)) { // 상어 있는 곳 아니고
							if (fish[ny][nx] == null) { // 빈곳이면 그대로
								fish[ny][nx] = new Fish(ny, nx, now.number, d);
								fishLocation[k] = new Fish(ny, nx, now.number, d);
								fish[now.y][now.x] = null;
							} else { // 빈곳아니면 위치 바꾸기
								Fish move = new Fish(ny, nx, fish[ny][nx].number, fish[ny][nx].direction);

								fish[ny][nx] = new Fish(ny, nx, now.number, d);
								fishLocation[now.number] = new Fish(ny, nx, now.number, d);

								fish[now.y][now.x] = new Fish(now.y, now.x, move.number, move.direction);
								fishLocation[move.number] = new Fish(now.y, now.x, move.number, move.direction);
							}
							break;
						}
					} else
						continue;
				}
			}
		}
	}

	static class Fish {
		int y;
		int x;
		int number;
		int direction;

		Fish(int y, int x, int number, int direction) {
			this.y = y;
			this.x = x;
			this.number = number;
			this.direction = direction;
		}

	}

	static class Shark {
		int y;
		int x;
		int direction;

		Shark(int y, int x, int direction) {
			this.y = y;
			this.x = x;
			this.direction = direction;
		}
	}
}
