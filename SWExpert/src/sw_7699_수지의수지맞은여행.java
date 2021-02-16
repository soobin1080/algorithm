import java.util.Scanner;

public class sw_7699_수지의수지맞은여행 {

	static int dx[] = { 0, 0, -1, 1 }; // 상 하 좌 우
	static int dy[] = { -1, 1, 0, 0 };
	static char map[][];
	static int alphabet[];
	static int R, C;
	static int MAX;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);

		int TC = sc.nextInt();

		for (int tc = 1; tc <= TC; tc++) {

			R = sc.nextInt();
			C = sc.nextInt();

			map = new char[R][C];

			for (int i = 0; i < map.length; i++) {
				map[i] = sc.next().toCharArray();
			}

			alphabet = new int[26];

			// dfs
			MAX = 0;
			alphabet[map[0][0] - 'A'] = 1;
			dfs(0, 0, 1);
			System.out.println("#" + tc + " " + MAX);

		}
		sc.close();
	}

	private static void dfs(int y, int x, int count) {

		for (int i = 0; i < 4; i++) {
			int iy = y + dy[i];
			int ix = x + dx[i];

			if (iy >= 0 && iy < R && ix >= 0 && ix < C && alphabet[map[iy][ix] - 'A'] == 0) {

				alphabet[map[iy][ix] - 'A']++;
				dfs(iy, ix, count + 1);
				alphabet[map[iy][ix] - 'A']--;
			}
		}

		MAX = (MAX < count) ? count : MAX;

		return;

	}

}
