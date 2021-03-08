package 백트래킹;

import java.util.Scanner;

public class bj_6603_로또 {
	static int[] arr;
	static boolean[] used;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int k;
		while ((k = sc.nextInt()) != 0) {
			arr = new int[k];
			used = new boolean[k];
			for (int i = 0; i < arr.length; i++) {
				arr[i] = sc.nextInt();
			}
			combi(0, 0, 6);
			System.out.println();
		}
		sc.close();
	}

	static void combi(int index, int cnt, int n) {
		if (cnt == n) {
			for (int i = 0; i < arr.length; i++) {
				if (used[i]) {
					System.out.print(arr[i] + " ");
				}
			}
			System.out.println();
			return;
		}
		for (int i = index; i < arr.length; i++) {
			used[i] = true;
			combi(i + 1, cnt + 1, n);
			used[i] = false;
		}
	}
}
