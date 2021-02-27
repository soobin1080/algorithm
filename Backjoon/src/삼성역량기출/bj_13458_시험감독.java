package 삼성역량기출;

import java.util.Scanner;

public class bj_13458_시험감독 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int N = sc.nextInt();

		int[] TestSite = new int[N];

		for (int i = 0; i < TestSite.length; i++) {
			TestSite[i] = sc.nextInt();
		}

		int studentOfsuperIntendent = sc.nextInt();
		int studentOfdeputySuperIntendent = sc.nextInt();

		long total=0;
		
		for (int i = 0; i < TestSite.length; i++) {

			int candidate = TestSite[i] - studentOfsuperIntendent;
			double intendent = 1;
			if (candidate > 0) {
				intendent += Math.ceil((candidate * 1.0) / studentOfdeputySuperIntendent);
			}
			total+=intendent;
		}
		System.out.println(total);

		sc.close();
	}

}
