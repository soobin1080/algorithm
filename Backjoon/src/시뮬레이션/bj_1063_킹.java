package 시뮬레이션;

import java.util.Scanner;

public class bj_1063_킹 {

	static int map[][] = new int[8][8];
	static String king;
	static String stone;
	static int N = 0;
	static int ky = 0, kx = 0;
	static int sy = 0, sx = 0;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		king = sc.next();
		stone = sc.next();

		kx = king.charAt(0) - 'A';
		ky = king.charAt(1) - '1';

		sx = stone.charAt(0) - 'A';
		sy = stone.charAt(1) - '1';

		map[ky][kx] = 1;
		map[sy][sx] = 2;

		N = sc.nextInt();
 
		for (int i = 0; i < N; i++) {
			String order = sc.next();
			move(order);
		}
		char kingx=(char)(kx+65);
		int kingy=ky+1;
		String k=""+kingx+kingy;
		char stonex=(char)(sx+65);
		int stoney=sy+1;
		String s=""+stonex+stoney;
		
		System.out.println(k);
		System.out.println(s);
	}

	private static void move(String order) {
		int my = ky, mx = kx;
		int ny = sy, nx = sx;

		boolean flag = false;
		boolean sflag = false;
		switch (order) {
		case "R":
			mx = kx + 1;
			if (boundary(my, mx)) {
				if (map[my][mx] == 2) {
					nx = sx + 1;
					if (boundary(ny, nx)) {
						sflag = true;
						flag = true;
					}
				} else if (map[my][mx] == 0) {
					flag = true;
				}
			}
			break;
		case "L":
			mx = kx - 1;
			if (boundary(my, mx)) {
				if (map[my][mx] == 2) {
					nx = sx - 1;
					if (boundary(ny, nx)) {
						sflag = true;
						flag = true;
					}
				} else if (map[my][mx] == 0) {
					flag = true;
				}
			}
			break;
		case "T":
			my = ky + 1;
			if (boundary(my, mx)) {
				if (map[my][mx] == 2) {
					ny = sy + 1;
					if (boundary(ny, nx)) {
						sflag = true;
						flag = true;
					}
				} else if (map[my][mx] == 0) {
					flag = true;
				}
			}
			break;
		case "B":
			my = ky - 1;
			if (boundary(my, mx)) {
				if (map[my][mx] == 2) {
					ny = sy - 1;
					if (boundary(ny, nx)) {
						sflag = true;
						flag = true;
					}
				} else if (map[my][mx] == 0) {
					flag = true;
				}
			}
			break;
		case "RB":
			my = ky - 1;
			mx = kx + 1;
			if (boundary(my, mx)) {
				if (map[my][mx] == 2) {
					ny = sy - 1;
					nx = sx + 1;
					if (boundary(ny, nx)) {
						sflag = true;
						flag = true;
					}
				} else if (map[my][mx] == 0) {
					flag = true;
				}
			}
			break;
		case "LB":
			mx = kx - 1;
			my = ky - 1;
			if (boundary(my, mx)) {
				if (map[my][mx] == 2) {
					ny = sy - 1;
					nx = sx - 1;
					if (boundary(ny, nx)) {
						sflag = true;
						flag = true;
					}
				} else if (map[my][mx] == 0) {
					flag = true;
				}
			}
			break;
		case "RT":
			mx = kx + 1;
			my = ky + 1;
			if (boundary(my, mx)) {
				if (map[my][mx] == 2) {
					ny = sy + 1;
					nx = sx + 1;
					if (boundary(ny, nx)) {
						sflag = true;
						flag = true;
					}
				} else if (map[my][mx] == 0) {
					flag = true;
				}
			}
			break;
		case "LT":
			mx = kx - 1;
			my = ky + 1;
			if (boundary(my, mx)) {
				if (map[my][mx] == 2) {
					ny = sy + 1;
					nx = sx - 1;
					if (boundary(ny, nx)) {
						sflag = true;
						flag = true;
					}
				} else if (map[my][mx] == 0) {
					flag = true;
				}
			}
			break;
		}

		if (flag) {
			if (sflag) {
				map[ny][nx] = 2;
				sy = ny;
				sx = nx;
			}
			map[my][mx] = 1;
			map[ky][kx] = 0;
			ky = my;
			kx = mx;
		}
	}

	private static boolean boundary(int y, int x) {
		if (y >= 0 && y < 8 && x >= 0 && x < 8) {
			return true;
		} else
			return false;
	}

}
