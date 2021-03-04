package 삼성역량기출;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class bj_15686_치킨배달 {
	static List<Point> home = new LinkedList<>();
	static List<Point> chicken = new LinkedList<>();
	static boolean[] used;
	static int MIN = Integer.MAX_VALUE;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int M = sc.nextInt();

		int[][] map = new int[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				map[i][j]=sc.nextInt();
				if (map[i][j] == 1)
					home.add(new Point(i, j));
				else if (map[i][j] == 2)
					chicken.add(new Point(i, j));

			}
		}
		used = new boolean[chicken.size()];
		combi(chicken.size(), 0, 0, M);
		System.out.println(MIN);

		sc.close();
	}

	private static void combi(int size, int index, int cnt, int r) {
		if (cnt == r) {
			//뽑힌 치킨집
			int[] subChicken = new int[r];
			int ix = 0;
			for (int i = 0; i < used.length; i++) {
				if (used[i])
					subChicken[ix++] = i;
			}
			// 치킨집이랑 집들 사이 거리구하기
			int cityChickenDistance = 0;
			for (int i = 0; i < home.size(); i++) {
				int chickenDistance = Integer.MAX_VALUE;
				for (int j = 0; j < subChicken.length; j++) {
					Point h = home.get(i);
					Point c = chicken.get(subChicken[j]);
					int distance = Math.abs(h.y - c.y) + Math.abs(h.x - c.x);
					chickenDistance = Math.min(chickenDistance, distance);
				}
				cityChickenDistance += chickenDistance;
			}

			MIN = (MIN > cityChickenDistance) ? cityChickenDistance : MIN;
			return;
		}

		for (int i = index; i < size; i++) {
			used[i] = true;
			combi(size, i+1, cnt + 1, r);
			used[i] = false;
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
