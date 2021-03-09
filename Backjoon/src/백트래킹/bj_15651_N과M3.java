package 백트래킹;

import java.util.Scanner;

public class bj_15651_N과M3 {
//출력 간소화 통한 시간 단축
	static int[] arr;
	static boolean[] used;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int N = sc.nextInt();
		int M = sc.nextInt();

		arr = new int[N + 1];
		used = new boolean[N + 1];

		for (int i = 1; i < arr.length; i++) {
			arr[i] = i;
		}

		perm("", 0, M);
		System.out.println(sb);
	}

	static void perm(String s, int cnt, int m) {
		if (cnt == m) {
			sb.append(s.trim() + "\n");
			return;
		}

		for (int i = 1; i < arr.length; i++) {
			perm(s + " " + i, cnt + 1, m);
		}
	}

}
