package 삼성역량기출;

import java.util.Scanner;

public class bj_14501_퇴사 {

	static int N;
	static int[][] arr;
	static int MAX = 0;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();
		arr = new int[N + 1][2];

		for (int i = 1; i < N + 1; i++) {
			for (int j = 0; j < 2; j++) {
				arr[i][j] = sc.nextInt();
			}
		}

		profit(arr, 1, 0);
		System.out.println(MAX);
	}

	static void profit(int[][] arr, int day, int sum) {
		if (day == N + 1) {
			MAX = (MAX > sum) ? MAX : sum;
			return;
		}

		int t = arr[day][0];
		int p = arr[day][1];

		if (day + t - 1 < N + 1) {
			profit(arr, day + t, sum + p);
		}
		profit(arr, day + 1, sum);
	}
}
