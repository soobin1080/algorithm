package 삼성역량기출;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class bj_17142_연구소3 {
	static int N, M, ANSWER = Integer.MAX_VALUE;
	static int wall = 0;
	static int[] dy = { -1, 1, 0, 0 };
	static int[] dx = { 0, 0, -1, 1 };
	static int[][] map;
	static ArrayList<Point> activeVirus = new ArrayList<>();
	static ArrayList<Point> virus = new ArrayList<>();

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();
		M = sc.nextInt();

		map = new int[N][N];

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				map[i][j] = sc.nextInt();
				if (map[i][j] == 1) {
					map[i][j] = -2;
					wall++;
				} else if (map[i][j] == 2) {
					map[i][j] = -1;
					virus.add(new Point(i, j));
				}
			}
		}

		combi(virus, 0, 0);
		if (ANSWER == Integer.MAX_VALUE)
			ANSWER = -1;
		System.out.println(ANSWER);
		sc.close();
	}

	static void combi(ArrayList<Point> virus, int index, int cnt) {
		if (cnt == M) {
			// 바이러스 퍼트리는 작업, 시간 구하기
			spread_virus();
			return;
		}
		for (int i = index; i < virus.size(); i++) {
			activeVirus.add(virus.get(i));
			combi(virus, i + 1, cnt + 1);
			activeVirus.remove(activeVirus.size() - 1);
		}
	}

	private static void spread_virus() {
		int[][] spreadMap = new int[N][N];

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				spreadMap[i][j] = map[i][j];
			}
		}

		Queue<Point> spreadOfVirus = new LinkedList<>();
		for (int i = 0; i < activeVirus.size(); i++) {
			Point point = activeVirus.get(i);
			spreadOfVirus.add(point);
			spreadMap[point.y][point.x] = 1;
		}

		// 바이러스 퍼지는 시간
		int second = 0, count = virus.size() + wall;
		while (!spreadOfVirus.isEmpty()) {
			second++;

			if (second > ANSWER)
				return;

			if (count == N * N) {
				second--;
				ANSWER = second;
				break;
			}
			int size = spreadOfVirus.size();

			for (int i = 0; i < size; i++) {
				Point point = spreadOfVirus.poll();

				for (int j = 0; j < 4; j++) {
					int iy = point.y + dy[j];
					int ix = point.x + dx[j];
					if (iy < N && iy >= 0 && ix < N && ix >= 0) {
						if (spreadMap[iy][ix] == 0) {
							spreadMap[iy][ix] = second;
							spreadOfVirus.add(new Point(iy, ix));
							count++;
						}else if(spreadMap[iy][ix] == -1) {
							spreadMap[iy][ix] = second;
							spreadOfVirus.add(new Point(iy, ix));
						}
					}
				}
			}
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
