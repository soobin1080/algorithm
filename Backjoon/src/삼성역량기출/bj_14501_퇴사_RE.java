package 삼성역량기출;

import java.util.Scanner;

public class bj_14501_퇴사_RE {
	static int[][] arr;
	static int MAX = 0;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int N = sc.nextInt();

		arr = new int[N + 1][2];

		for (int i = 1; i < N + 1; i++) {
			for (int j = 0; j < 2; j++) {
				arr[i][j] = sc.nextInt();
			}
		}

		consult(1, N, 0);
		System.out.println(MAX);

	}

	private static void consult(int r, int n, int sum) {
		if (r > n) {
			MAX = (MAX < sum) ? sum : MAX;
			return;
		}

		int t = r + arr[r][0]; // 상담기간
		int p = arr[r][1]; // 상담금액
		if (t - 1 <= n) { // 퇴사일전까지 상담가능한지 체크
			consult(t, n, sum + p);
		}
		consult(r + 1, n, sum);

	}

}
