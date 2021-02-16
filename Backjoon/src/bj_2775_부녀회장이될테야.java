import java.util.Scanner;

public class bj_2775_부녀회장이될테야 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int TC = sc.nextInt();

		for (int tc = 0; tc < TC; tc++) {
			int k = sc.nextInt();
			int n = sc.nextInt();

			int answer = function(k, n);
			System.out.println(answer);
		}
		sc.close();
	}

	private static int function(int k, int n) {

		if (k == 0) {
			return n;
		} else if (n == 1) {
			return 1;
		} else
			return function(k, n - 1) + function(k - 1, n);
	}

}
