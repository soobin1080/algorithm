import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class sw_10966_물놀이를가자 {

	static int[] dy = { -1, 1, 0, 0 }; // 상하좌우
	static int[] dx = { 0, 0, -1, 1 };
	static int distance_sum, distance_min;
	static boolean visited[][];
	static boolean find;

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
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

			/*
			 * for (int i = 0; i < N; i++) { for (int j = 0; j < M; j++) {
			 * System.out.print(map[i][j]); } System.out.println(); }
			 * System.out.println("ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ");
			 */

			distance_sum = 0;

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if (map[i][j] == 'L') {
						visited = new boolean[N][M];
						visited[i][j] = true;
						find = false;
						distance_min = N + M;
						System.out.println(i + "," + j + ": 시작");
						dfs(N, M, i, j, map, 1);
						distance_sum += distance_min;
					}
				}
			}
			System.out.println("#" + tc + " " + distance_sum);

		}
	}

	private static void dfs(int N, int M, int y, int x, char[][] map, int d) {

		if (distance_min == 1)
			return;
		if (distance_min <= d)
			return;

		for (int i = 0; i < 4; i++) {
			int ny = dy[i] + y;
			int nx = dx[i] + x;
			if (ny >= 0 && ny < N && nx >= 0 && nx < M && !visited[ny][nx]) {
				System.out.println(ny + "," + nx);
				if (map[ny][nx] == 'W') {
					System.out.println("w찾음");
					distance_min = (distance_min > d) ? d : distance_min;
					System.out.println("distance_min:" + distance_min);
					return;
				} else {
					visited[ny][nx] = true;
					dfs(N, M, ny, nx, map, d + 1);
					visited[ny][nx] = false;
				}
			}

		}
		return;
	}
}
