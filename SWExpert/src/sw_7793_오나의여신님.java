import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class sw_7793_오나의여신님 {

	static Queue<Node> devil;
	static Queue<Node> location;

	static int[] dy = { -1, 1, 0, 0 };
	static int[] dx = { 0, 0, -1, 1 };
	static int M;
	static int N;
	static char map[][];
	static boolean safe;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);

		int TC = sc.nextInt();

		for (int tc = 1; tc <= TC; tc++) {

			N = sc.nextInt();
			M = sc.nextInt();

			map = new char[N][M];

			devil = new LinkedList<Node>();
			location = new LinkedList<Node>();

			for (int i = 0; i < N; i++) {
				String s = sc.next();
				for (int j = 0; j < M; j++) {
					map[i][j] = s.charAt(j);
					if (map[i][j] == 'S') {
						location.add(new Node(i, j));
					}
					if (map[i][j] == '*') {
						devil.add(new Node(i, j));
					}
				}
			}

			int count = 0;

			safe = false;

			a: while (!devil.isEmpty() && !location.isEmpty()) {
				count++;
				// 수연 이동
				int size = location.size();
				for (int i = 0; i < size; i++) {
					Node point = location.poll();
					System.out.println("수연이 위치" + point.y + ", " + point.x);
					move(point.y, point.x);
					// 여신 만남
					if (safe) {
						break a;
					}
				}

				// 악마의 손아귀
				size = devil.size();
				for (int i = 0; i < size; i++) {
					Node point = devil.poll();
					devil(point.y, point.x);
					// 수연 잡힘..
					if (!safe) {
						break a;
					}
				}
			}

			if (safe)
				System.out.println("#" + tc + " " + count);
			else
				System.out.println("#" + tc + " GAME OVER");
		}
	}

	private static void devil(int y, int x) {
		// TODO Auto-generated method stub
		for (int i = 0; i < 4; i++) {
			int iy = y + dy[i];
			int ix = x + dx[i];
			if (iy >= 0 && iy < N && ix >= 0 && ix < M && (map[iy][ix] == '.' || map[iy][ix] == 'S')) {
				if (map[iy][ix] != 'S') {
					map[iy][ix] = '*';
				} else {
					safe = false;
					return;
				}

				devil.add(new Node(iy, ix));
			}
		}
	}

	private static void move(int y, int x) {
		for (int i = 0; i < 4; i++) {
			int iy = y + dy[i];
			int ix = x + dx[i];
			if (iy >= 0 && iy < N && ix >= 0 && ix < M && (map[iy][ix] == '.' || map[iy][ix] == 'D')) {
				if (map[iy][ix] != 'D') {
					map[iy][ix] = 'S';
					map[y][x] = '.';
				} else {
					safe = true;
					return;
				}
				System.out.println("갈수잇는 위치" + iy + ", " + ix);

				location.add(new Node(iy, ix));
			}
		}
	}

	private static class Node {
		int y;
		int x;

		Node(int y, int x) {
			this.y = y;
			this.x = x;
		}
	}
}
