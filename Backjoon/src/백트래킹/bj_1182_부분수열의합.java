package 백트래킹;

import java.util.Scanner;

public class bj_1182_부분수열의합 {

	static int N;
	static int S;
	static int count = 0;
	static int[] arr;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();
		S = sc.nextInt();

		arr = new int[N];
		for (int i = 0; i < N; i++) {
			arr[i] = sc.nextInt();
		}

		// 부분 수열의 합
		for (int i = 0; i < arr.length; i++) {
			back_tracking(arr[i], i);
		}
		System.out.println(count);

	}

	private static void back_tracking(int sum, int depth) {
		if (depth == arr.length - 1 && sum == S)
			count++;
		depth++;
		if (depth < N) {
			back_tracking(sum + arr[depth], depth);
			back_tracking(sum, depth);
		}
	}
}
