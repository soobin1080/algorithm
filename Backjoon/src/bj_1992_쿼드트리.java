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

		QuadTree(0, 0, N, true);
		System.out.println(sb);
	}

	static void QuadTree(int y, int x, int n, boolean square) {

		int number = map[y][x];
		a: for (int i = y; i < y + n; i++) {
			for (int j = x; j < x + n; j++) {
				if (number != map[i][j]) {
					square = false;
					break a;
				}
			}
		}

		if (square) {
			sb.append(number);
			return;
		}
		sb.append("(");
		QuadTree(y, x, n / 2, true);
		QuadTree(y, x + n / 2, n / 2, true);
		QuadTree(y + n / 2, x, n / 2, true);
		QuadTree(y + n / 2, x + n / 2, n / 2, true);
		sb.append(")");
	}

}
