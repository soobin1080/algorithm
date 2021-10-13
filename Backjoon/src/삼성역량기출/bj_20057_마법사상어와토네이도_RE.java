package 삼성역량기출;

import java.util.Scanner;

public class bj_20057_마법사상어와토네이도_RE {
	static int N;
	static int map[][];
	static int dy[] = { 0, 1, 0, -1 }; // 좌 하 우 상
	static int dx[] = { -1, 0, 1, 0 };
	static int percent[] = { 1, 1, 2, 2, 5, 7, 7, 10, 10, 0 };
	static int move[][][] = { { { -1, 1, -2, 2, 0, -1, 1, -1, 1, 0 }, { 1, 1, 0, 0, -2, 0, 0, -1, -1, -1 } },
			{ { -1, -1, 0, 0, 2, 0, 0, 1, 1, 1 }, { -1, 1, -2, 2, 0, -1, 1, -1, 1, 0 } },
			{ { -1, 1, -2, 2, 0, -1, 1, -1, 1, 0 }, { -1, -1, 0, 0, 2, 0, 0, 1, 1, 1 } },
			{ { 1, 1, 0, 0, -2, 0, 0, -1, -1, -1 }, { -1, 1, -2, 2, 0, -1, 1, -1, 1, 0 } } };// 좌 하 우 상

	static int outSand = 0;
	static int ty, tx; // 토네이도 위치

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();
		map = new int[N][N];

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				map[i][j] = sc.nextInt();
			}
		}

		ty = N / 2;
		tx = N / 2;

		int distance = 1;// 같은방향으로 전진하는 칸수
		int direction = 0;
		int count=0; 
		// 토네이도 이동
		while (!(ty == 0 && tx == 0)) {

			if(count==2) {
				count=0;
				distance++;
			}
			count++;
			
			// 토네이도 전진!
			for (int i = 0; i < distance; i++) {
				int y = ty + dy[direction];
				int x = tx + dx[direction];
				if (y >= 0 && y < N && x >= 0 && x < N) {
					ty = y;
					tx = x;
					stackSand(direction);
				}
			}
			
			// 토네이도 방향바꾸기
			direction++;
			direction %= 4;
		}

		System.out.println(outSand);
	}

	private static void printMap() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println("ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ");
	}

	private static void stackSand(int direction) {
		int leftsand = map[ty][tx];
		// map 바깥으로 나가면 outSand에 +
		for (int i = 0; i < percent.length; i++) {
			int y = ty + move[direction][0][i];
			int x = tx + move[direction][1][i];
			int sand = ((map[ty][tx] * percent[i]) / 100);
			
			if (i != percent.length - 1) {
				if (y >= 0 && y < N && x >= 0 && x < N) {
					map[y][x] += sand;
				} else {
					outSand += sand;
				}
				leftsand -= sand;
			} else {
				if (y >= 0 && y < N && x >= 0 && x < N) {
					map[y][x] += leftsand;
				} else {
					outSand += leftsand;
				}
			}
		}
		map[ty][tx] = 0;
	}

}
