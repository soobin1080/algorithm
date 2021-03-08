package 백트래킹;

import java.util.Scanner;

public class bj_15649_N과M {
	static int[] arr;
	static boolean[] used;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		StringBuilder sb=new StringBuilder();
		int N = sc.nextInt();
		int M = sc.nextInt();

		arr = new int[N + 1];
		used = new boolean[N + 1];

		for (int i = 1; i < arr.length; i++) {
			arr[i] = i;
		}

		perm(sb,0, M);

	}

	static void perm(StringBuilder sb,int cnt, int m) {
		if (cnt == m) {
			System.out.println(sb);
			return;
		}

		for (int i = 1; i < arr.length; i++) {
			if (!used[i]) {
				used[i] = true;
				sb.append(arr[i]+" ");
				perm(sb,cnt + 1, m);
				sb.delete(sb.length()-2,sb.length());
				used[i] = false;
			}
		}
	}

}
