package 백트래킹;

import java.util.Scanner;

public class bj_15650_N과M2 {
	static int[] arr;
	static boolean[] used;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int N = sc.nextInt();
		int M = sc.nextInt();

		arr = new int[N + 1];
		used = new boolean[arr.length];

		for (int i = 1; i < arr.length; i++) {
			arr[i] = i;
		}

		combi(1, 0, M);

	}

	static void combi(int index, int cnt, int m) {
		if (cnt == m) {
			for (int i = 1; i < arr.length; i++) {
				if (used[i])
					System.out.print(arr[i] + " ");
			}
			System.out.println();
			return;
		}
		for (int i = index; i < arr.length; i++) {
			used[i] = true;
			combi(i + 1, cnt + 1, m);
			used[i] = false;
		}
	}

}
