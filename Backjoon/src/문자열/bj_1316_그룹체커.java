package 문자열;

import java.util.Scanner;

public class bj_1316_그룹체커 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int N = sc.nextInt();
		int count = 0;

		for (int i = 0; i < N; i++) {
			String s = sc.next();
			boolean[] alpha = new boolean[26];
			alpha[s.charAt(0) - 'a'] = true;

			for (int j = 1; j < s.length(); j++) {
				if (s.charAt(j) != s.charAt(j - 1)) {
					int n = s.charAt(j) - 'a';
					if (alpha[n]) {
						count--;
						break;
					}
					alpha[n] = true;
				}
			}
			count++;
		}
		System.out.println(count);

		sc.close();
	}

}
