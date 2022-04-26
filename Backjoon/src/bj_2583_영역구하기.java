import java.util.Collections;
import java.util.LinkedList;
import java.util.Scanner;

public class bj_2583_영역구하기 {
	static boolean visited[][];
	static int map[][];
	static int dy[] = { -1, 1, 0, 0 };
	static int dx[] = { 0, 0, -1, 1 };
	static int M;
	static int N;
	static int count;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();
		M = sc.nextInt();
		int K = sc.nextInt();

		visited = new boolean[M][N];

		for (int i = 0; i < K; i++) {
			int y1 = sc.nextInt();
			int x1 = sc.nextInt();
			int y2 = sc.nextInt();
			int x2 = sc.nextInt();

			for (int y = y1; y < y2; y++) {
				for (int x = x1; x < x2; x++) {
					visited[y][x] = true;
				}
			}
		}

		LinkedList<Integer> area = new LinkedList<>();

		for (int i = 0; i < M; i++) {
			for (int j = 0; j < N; j++) {
				if (!visited[i][j]) {
					count = 1;
					dfs(i, j);
					area.add(count);
				}
			}
		}

		Collections.sort(area);
		int size = area.size();
		System.out.println(size);

		for (int i = 0; i < size; i++) {
			System.out.print(area.get(i)+" ");
		}
	}

	static void dfs(int i, int j) {
		visited[i][j] = true;

		for (int k = 0; k < 4; k++) {
			int ny = i + dy[k];
			int nx = j + dx[k];

			if (ny >= 0 && ny < M && nx >= 0 && nx < N && !visited[ny][nx]) {
				visited[ny][nx] = true;
				count++;
				dfs(ny, nx);
			}
		}
	}

}
