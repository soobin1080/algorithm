import java.util.Scanner;

public class sw_1210 {

	static boolean[][] visited;
	static int[] dy = { 0, 0, -1 }; // 좌, 우, 상
	static int[] dx = { -1, 1, 0 };

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);

		for (int TC = 0; TC < 10; TC++) {

			int tc = sc.nextInt();

			int map[][] = new int[100][100];

			for (int i = 0; i < 100; i++) {
				for (int j = 0; j < 100; j++) {
					map[i][j] = sc.nextInt();
				}
			}

			int x = 0, y = 0;
			for (int i = 0; i < 100; i++) {
				if (map[99][i] == 2) {
					x = i;
					y = 99;
					break;
				}
			}

			visited = new boolean[100][100];

			while (y != 0) {

				for (int i = 0; i < 3; i++) {
					int ix = x + dx[i];
					int iy = y + dy[i];

					if (ix >= 0 && ix < 100 && iy >= 0 && iy < 100 && map[iy][ix] == 1 && !visited[iy][ix]) {
						visited[iy][ix] = true;
						y = iy;
						x = ix;
						break;
					}
				}
			}

			System.out.println("#" + tc + " " + x);

		}
		sc.close();
	}

}