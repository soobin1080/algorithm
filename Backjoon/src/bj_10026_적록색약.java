import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class bj_10026_적록색약 {
	static int[] dy = { -1, 1, 0, 0 };
	static int[] dx = { 0, 0, -1, 1 };
	static boolean visited[][];
	static char arr[][];
	static int N;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		arr = new char[N][N];

		for (int i = 0; i < N; i++) {
			String st = br.readLine();
			for (int j = 0; j < N; j++) {
				arr[i][j] = st.charAt(j);
			}
		}

		visited = new boolean[N][N];

		int count = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (!visited[i][j]) {
					count++;
					dfs(i, j, arr[i][j]);
				}
			}
		}

		visited = new boolean[N][N];
		int rgcount = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (!visited[i][j]) {
					rgcount++;
					dfs_rg(i, j, arr[i][j]);
				}
			}
		}

		System.out.println(count + " " + rgcount);
	}

	private static void dfs_rg(int i, int j, char c) {
		if (!visited[i][j]) {
			visited[i][j] = true;

			for (int k = 0; k < 4; k++) {
				int ny = dy[k] + i;
				int nx = dx[k] + j;

				if (ny >= 0 && ny < N && nx >= 0 && nx < N && !visited[ny][nx]) {
					if (c == 'R' && (arr[ny][nx] == 'R' || arr[ny][nx] == 'G')) {
						dfs_rg(ny, nx, arr[ny][nx]);
					} else if (c == 'G' && (arr[ny][nx] == 'R' || arr[ny][nx] == 'G')) {
						dfs_rg(ny, nx, arr[ny][nx]);
					} else if (c == 'B' && arr[ny][nx] == 'B') {
						dfs_rg(ny, nx, arr[ny][nx]);
					}
				}
			}
		}
	}

	private static void dfs(int i, int j, char c) {
		if (!visited[i][j]) {
			visited[i][j] = true;

			for (int k = 0; k < 4; k++) {
				int ny = dy[k] + i;
				int nx = dx[k] + j;

				if (ny >= 0 && ny < N && nx >= 0 && nx < N && !visited[ny][nx] && arr[ny][nx] == c) {
					dfs(ny, nx, arr[ny][nx]);
				}
			}
		}
	}
}
