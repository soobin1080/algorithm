package 삼성역량기출;

import java.util.Scanner;

public class bj_14890_경사로 {

	static int[][] map;
	static int N, L;
	static int pass = 0;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();
		L = sc.nextInt();

		map = new int[N][N];

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				map[i][j] = sc.nextInt();
			}
		}

		for (int i = 0; i < N; i++) {
			// 한줄씩
			if (go(i, 0, 0))
				pass++;
			if (go(0, i, 1))
				pass++;
		}
		System.out.println(pass);
	}

	// 행 d=0 / 열 d=1
	static boolean go(int y, int x, int d) {
		int[] height = new int[N];
		boolean[] visited = new boolean[N];

		// 한 줄씩 검사하기
		for (int i = 0; i < N; i++) {
			height[i] = (d == 0) ? map[y][x + i] : map[y + i][x];
		}

		for (int i = 0; i < N - 1; i++) {
			// 높이 같을 때
			if (height[i] == height[i + 1])
				continue;
			
			// 길 통과 할 수 없음
			if (Math.abs(height[i] - height[i + 1]) > 1)
				return false; 

			// 내려갈 때
			if (height[i] - height[i + 1] == 1) {
				for (int j = i + 1; j <= i + L; j++) {
					if (j >= N || height[i + 1] != height[j] || visited[j]) {
						return false;
					}
					visited[j] = true;
				}
			}
			// 올라갈 때
			else if(height[i] - height[i + 1] == -1) {
				for (int j = i; j > i - L; j--) {
					if (j < 0 || height[i] != height[j] || visited[j]) {
						return false;
					}
					visited[j] = true;
				}
			}
		}

		return true;
	}
}