package 삼성역량기출;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class bj_17143_낚시왕 {

	static int R, C, M;

	static Shark[][] map;
	static Queue<Point> sharks;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		R = sc.nextInt();
		C = sc.nextInt();
		M = sc.nextInt();
		map = new Shark[R][C];
		sharks = new LinkedList<Point>();

		for (int i = 0; i < M; i++) {
			int y = sc.nextInt() - 1;
			int x = sc.nextInt() - 1;
			int speed = sc.nextInt();
			int dir = sc.nextInt();
			int size = sc.nextInt();
			map[y][x] = new Shark(speed, dir, size);
			sharks.add(new Point(y, x));
		}

		int fishing = 0;// 잡은 상어 size 합;

		for (int king = 0; king < C; king++) {
			for (int i = 0; i < R; i++) {
				if (map[i][king] != null) {
					fishing += map[i][king].size;
					map[i][king] = null;
					break;
				}
			}
			moveShark();
		}

		System.out.println(fishing);

		sc.close();
	}

	private static void moveShark() {
		Shark[][] copy = new Shark[R][C];
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				copy[i][j] = map[i][j];
			}
		}
		// 상어 움직인 위치 저장하기 위한 초기화
		map = new Shark[R][C];
		int size = sharks.size();
		for (int k = 0; k < size; k++) {
			Point point = sharks.poll();
			int i = point.y;
			int j = point.x;
			if (copy[i][j] != null) {
				int y = i, x = j;
				int dir = copy[i][j].dir;
				//시간 초과 안하기 위해서 필요함!! 
				int speed = copy[i][j].speed;
				if (dir == 1 || dir == 2)
					speed = speed % ((R - 1) * 2);
				else
					speed = speed % ((C - 1) * 2);
				for (int s = 0; s < speed; s++) {
					if (dir == 1 && y == 0)
						dir = 2;
					else if (dir == 2 && y == R - 1)
						dir = 1;
					else if (dir == 4 && x == 0)
						dir = 3;
					else if (dir == 3 && x == C - 1)
						dir = 4;

					if (dir == 1) {
						y--;
					} else if (dir == 2) {
						y++;
					} else if (dir == 3) {
						x++;
					} else if (dir == 4) {
						x--;
					}
				}
				// copy에서 움직이고 map에 움직인거 체크하기
				if (map[y][x] == null) {
					map[y][x] = new Shark(copy[i][j].speed, dir, copy[i][j].size);
				} else {
					if (map[y][x].size < copy[i][j].size) {
						map[y][x] = new Shark(copy[i][j].speed, dir, copy[i][j].size);
					}
				}
				sharks.add(new Point(y, x));
			}
		}
	}

	static class Shark {
		int speed;
		int dir;
		int size;

		Shark(int speed, int dir, int size) {
			this.speed = speed;
			this.dir = dir;
			this.size = size;
		}
	}

	static class Point {
		int y;
		int x;

		Point(int y, int x) {
			this.y = y;
			this.x = x;
		}
	}
}
