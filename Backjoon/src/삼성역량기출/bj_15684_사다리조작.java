package 삼성역량기출;

import java.util.Scanner;

public class bj_15684_사다리조작 {
	static int N, M, H;
	static int[][] ladder;
	static int answer = 0;
	static boolean finish = false;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();
		M = sc.nextInt();
		H = sc.nextInt();

		ladder = new int[H + 1][N + 1];
		for (int i = 0; i < M; i++) {
			int a = sc.nextInt();
			int b = sc.nextInt();

			ladder[a][b] = 1;
			ladder[a][b + 1] = 2;
		}

		for (int i = 0; i <= 3; i++) {
			dfs(1, 0, i);
			if (finish)
				break;
		}

		System.out.println((finish) ? answer : -1);
		sc.close();
	}

	private static void dfs(int start, int cnt, int addLadder) {
		if (finish)
			return;
		if (addLadder == cnt) {
			for (int i = 1; i < N + 1; i++) {
				int location = i;
				for (int j = 0; j < H + 1; j++) {
					if (ladder[j][location] == 1)
						location++;
					else if (ladder[j][location] == 2)
						location--;
				}
				if (location != i)
					return;
			}
			answer = addLadder;
			finish = true;
			return;
		}

		for (int i = start; i < H + 1; i++) {
			for (int j = 1; j < N; j++) {
				if (ladder[i][j] == 0 && ladder[i][j + 1] == 0) {
					ladder[i][j] = 1;
					ladder[i][j + 1] = 2;
					dfs(i, cnt + 1, addLadder);
					ladder[i][j] = ladder[i][j + 1] = 0;
				}
			}
		}
	}
}
