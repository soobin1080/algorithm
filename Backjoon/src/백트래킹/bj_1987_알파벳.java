package 백트래킹;

import java.util.Scanner;

public class bj_1987_알파벳 {
	static int[] dy = { -1, 1, 0, 0 };
	static int[] dx = { 0, 0, -1, 1 };
	static boolean[] alpabet = new boolean[26];
	static char[][] map;
	static int R, C, MAX = 0;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		R = sc.nextInt();
		C = sc.nextInt();

		map = new char[R][C];
		for (int i = 0; i < R; i++) {
			map[i] = sc.next().toCharArray();
		}

		alpabet[map[0][0] - 'A'] = true;
		dfs(0, 0, 1);

		System.out.println(MAX);
		sc.close();
	}

	private static void dfs(int y, int x, int depth) {
		if (MAX < depth)
			MAX = depth;
		
		for (int i = 0; i < 4; i++) {
			int iy = y + dy[i];
			int ix = x + dx[i];
			if (iy < R && iy >= 0 && ix < C && ix >= 0 && !alpabet[map[iy][ix] - 'A']) {
				alpabet[map[iy][ix]-'A'] = true;
				dfs(iy, ix, depth + 1);
				alpabet[map[iy][ix]-'A'] = false;
			}
		}
	}

}
