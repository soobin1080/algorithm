package 문자열;

import java.util.Scanner;

public class bj_2908 {
	private static Scanner sc;

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		sc = new Scanner(System.in);

		int first = sc.nextInt();
		int second = sc.nextInt();

		int a = first / 100;
		int b = first % 100;
		int c = b / 10;
		int d = b % 10;
		int ff = d * 100 + c * 10 + a;

		a = second / 100;
		b = second % 100;
		c = b / 10;
		d = b % 10;
		int ss = d * 100 + c * 10 + a;
		
		if(ff<ss) {
			System.out.println(ss);
		}else
			System.out.println(ff);
	}

}
