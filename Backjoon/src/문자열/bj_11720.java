package 문자열;

import java.util.Scanner;

public class bj_11720 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);

		int n = sc.nextInt();
		String input = sc.next();

		char[] number = input.toCharArray();
		int answer = 0;
		for (int i = 0; i < n; i++) {
			answer+=Integer.parseInt(number[i]+"");
		}

		System.out.println(answer);
		sc.close();
	}

}