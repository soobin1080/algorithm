package 백트래킹;

import java.util.Arrays;
import java.util.Scanner;

public class bj_1759_암호만들기 {
	static char[] arr;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int L = sc.nextInt();
		int C = sc.nextInt();
		arr = new char[C];
		for (int i = 0; i < C; i++) {
			arr[i] = sc.next().charAt(0);
		}

		Arrays.sort(arr);

		combi("", 0, 0, 0, 0, L);// 자음개수,모음개수,선택개수,총개수
		System.out.println(sb);
		sc.close();
	}

	private static void combi(String s, int index, int cCnt, int vCnt, int cnt, int L) {
		if (cnt == L) {
			if (cCnt >= 2 && vCnt >= 1)
				sb.append(s + "\n");
			return;
		}

		for (int i = index; i < arr.length; i++) {
			if (arr[i] == 'a' || arr[i] == 'e' || arr[i] == 'i' || arr[i] == 'o' || arr[i] == 'u') {
				combi(s + arr[i], i + 1, cCnt, vCnt + 1, cnt + 1, L);
			} else
				combi(s + arr[i], i + 1, cCnt + 1, vCnt, cnt + 1, L);

		}
	}

}
