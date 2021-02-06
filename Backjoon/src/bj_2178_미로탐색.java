import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class bj_2178_미로탐색 {

	static int N;
	static int M;
	static int[][] map;
	static boolean[][] visited;
	static int[] dx = { 0, 0, -1, 1 };
	static int[] dy = { -1, 1, 0, 0 };
	static int MIN = Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new int[N][M];
		visited = new boolean[N][M];

		for (int i = 0; i < N; i++) {
			String s = br.readLine();
			for (int j = 0; j < M; j++) {
				map[i][j] = s.charAt(j) - '0';
			}
		}

		visited[0][0] = true;

		Queue<Node> qu = new LinkedList<>();
		visited[0][0] = true;
		qu.add(new Node(0, 0, 1));

		// bfs
		while (!qu.isEmpty()) {

			Node now = qu.poll();

			if (MIN < now.d) {
				continue;
			}
			if (now.y == N - 1 && now.x == M - 1) {
				MIN = (MIN > now.d) ? now.d : MIN;
			}

			for (int i = 0; i < 4; i++) {
				int iy = now.y + dy[i];
				int ix = now.x + dx[i];

				if (iy >= 0 && iy < N && ix >= 0 && ix < M && !visited[iy][ix] && map[iy][ix] == 1) {
					visited[iy][ix] = true;
					qu.add(new Node(iy, ix, now.d + 1));
				}
			}
		}

		System.out.println(MIN);

	}

	static class Node {
		int y;
		int x;
		int d;

		Node(int y, int x, int d) {
			this.y = y;
			this.x = x;
			this.d = d;
		}
	}

}
