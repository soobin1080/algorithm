import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class sw_12052_부서진타일 {
	static int[] dy = { 0, 1, 1, 0 };
	static int[] dx = { 1, 1, 0, 0 };
	static boolean available;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int TC = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= TC; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());

			char[][] map = new char[N][M];
			for (int i = 0; i < N; i++) {
				map[i] = br.readLine().toCharArray();
			}

			available = true;
			a: for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if (map[i][j] == '#') {
						// 2*2만큼 확인
						for (int k = 0; k < 3; k++) {
							int y = dy[k] + i;
							int x = dx[k] + j;
							if (y < 0 || y >= N || x < 0 || x >= M || map[y][x] != '#') {
								available = false;
								break a;
							}
						}
						for (int k = 0; k < 4; k++) {
							int y = dy[k] + i;
							int x = dx[k] + j;
							map[y][x] = '.';
						}
					}
				}
			}
			if (available)
				System.out.println("#" + tc + " YES");
			else
				System.out.println("#" + tc + " NO");

		}
	}

}
