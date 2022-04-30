package 시뮬레이션;

import java.util.Scanner;

public class bj_1937_욕심쟁이판다 {
	static int MAX = 0;
	static int map[][];
	static int N;
	static int count[][];
	static int dy[] = { -1, 1, 0, 0 };
	static int dx[] = { 0, 0, -1, 1 };

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();
		map = new int[N][N];
		count = new int[N][N];

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				map[i][j] = sc.nextInt();
			}
		}

		// 판다 이동
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				// 시작 지점을 정한다.
				MAX = Math.max(MAX,dfs(i, j));
			}
		}

		// 이동할 수 있는 최대 칸수
		System.out.println(MAX);
	}

	private static void printMap() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				System.out.print(count[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println("ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ");
	}

	private static int dfs(int i, int j) {

		if (count[i][j] != 0)
			return count[i][j];

		count[i][j] = 1;

		for (int k = 0; k < 4; k++) {
			int ny = i + dy[k];
			int nx = j + dx[k];
			if (ny >= 0 && ny < N && nx >= 0 && nx < N) {
				if (map[ny][nx] > map[i][j]) {
					count[i][j] = Math.max(count[i][j], dfs(ny, nx) + 1);
					dfs(ny, nx);
				}
			}
		}
		return count[i][j];
	}

}
