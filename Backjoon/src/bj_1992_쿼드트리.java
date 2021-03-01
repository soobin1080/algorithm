import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class bj_1992_쿼드트리 {
	static int[][] map;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		for (int i = 0; i < N; i++) {
			String s = br.readLine();
			for (int j = 0; j < N; j++) {
				map[i][j] = s.charAt(j) - '0';
			}
		}
		Quadtree(0, 0, N, true);
		System.out.println(sb);

	}

	private static void Quadtree(int y, int x, int n, boolean square) {

		int number = map[y][x];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				System.out.print(map[i][j]);
				if (number != map[i][j])
					square = false;
			}
			System.out.println();
		}

		if (square) {
			sb.append(number);
			return;
		} else {
			int[] pointY = { y, y, y + n / 2, y + n / 2 };
			int[] pointX = { x, x + n / 2, x, x + n / 2 };
			for (int i = 0; i < 4; i++) {
				Quadtree(pointY[i], pointX[i], n / 2, true);
			}
			sb.append(")");
		}

	}

}
