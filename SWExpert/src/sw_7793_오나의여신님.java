import java.util.LinkedList;
import java.util.Scanner;

public class sw_7793_오나의여신님 {
	static char[][] map;
	static boolean[][] visited;
	static int N, M;

	static Node D;
	static LinkedList<Node> devil = null;
	static LinkedList<Node> suyeon = null;

	static int[] dy = { -1, 1, 0, 0 };
	static int[] dx = { 0, 0, -1, 1 };

	static boolean finish;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int TC = sc.nextInt();

		for (int tc = 1; tc <= TC; tc++) {

			N = sc.nextInt();
			M = sc.nextInt();

			map = new char[N][M];
			visited = new boolean[N][M];

			devil = new LinkedList<Node>();
			suyeon = new LinkedList<Node>();

			// 값 입력받기
			for (int i = 0; i < N; i++) {
				map[i] = sc.next().toCharArray();
				for (int j = 0; j < M; j++) {
					if (map[i][j] == 'D')
						D = new Node(i, j);
					else if (map[i][j] == 'S') {
						suyeon.add(new Node(i, j));
						visited[i][j] = true;
					} else if (map[i][j] == '*') {
						devil.add(new Node(i, j));
					}
				}
			}

			finish = false;
			int count = 0;
			while (!finish) {
				bfs();
				count++;
			}

			System.out.print("#" + tc + " ");
			if (suyeon.size() != 0) {
				System.out.println(count);
			} else {
				System.out.println("GAME OVER");
			}
		}
		sc.close();
	}

	private static void bfs() {

		// 수연이 이동
		int size = suyeon.size();

		for (int i = 0; i < size; i++) {
			boolean flag = false;
			Node now = suyeon.poll();
			for (int j = 0; j < 4; j++) {
				int nowy = now.y + dy[j];
				int nowx = now.x + dx[j];

				if (nowy >= 0 && nowy < N && nowx >= 0 && nowx < M && !visited[nowy][nowx]) {
					if (map[nowy][nowx] == '.') {
						map[nowy][nowx] = 'S';
						suyeon.add(new Node(nowy, nowx));
						visited[nowy][nowx] = true;
						flag = true;
					} else if (map[nowy][nowx] == 'D') {
						suyeon.add(new Node(nowy, nowx));
						finish = true;
						return;
					}
				}
			}
			if (flag)
				map[now.y][now.x] = '.';
		}

		// 악마 이동
		size = devil.size();

		for (int i = 0; i < size; i++) {
			Node now = devil.poll();
			for (int j = 0; j < 4; j++) {
				int nowy = now.y + dy[j];
				int nowx = now.x + dx[j];
				if (nowy >= 0 && nowy < N && nowx >= 0 && nowx < M) {
					if (map[nowy][nowx] == '.') {
						map[nowy][nowx] = '*';
						devil.add(new Node(nowy, nowx));
					} else if (map[nowy][nowx] == 'S') {
						// 수연제거
						for (int k = 0; k < suyeon.size(); k++) {
							if (suyeon.get(k).y == nowy && suyeon.get(k).x == nowx) {
								suyeon.remove(k);
								break;
							}
						}

						map[nowy][nowx] = '*';
						// 수연개수 0이면 게임오버
						if (suyeon.size() == 0) {
							finish = true;
							return;
						}
					}
				}
			}
		}
	}

	private static void print() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				System.out.print(map[i][j]);
			}
			System.out.println();
		}
	}

	static class Node {
		int y;
		int x;

		Node(int y, int x) {
			this.y = y;
			this.x = x;
		}
	}

}