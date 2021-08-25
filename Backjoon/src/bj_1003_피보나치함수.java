import java.util.Scanner;

public class bj_1003_피보나치함수 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int[] zero = new int[41];
		int[] one = new int[41];
		zero[0]=1;
		one[1]=1;
		for (int i = 0; i <= 38; i++) {
			zero[i + 2] = zero[i] + zero[i + 1];
			one[i + 2] = one[i] + one[i + 1];
		}

		int TC = sc.nextInt();
		for (int tc = 0; tc < TC; tc++) {

			int N = sc.nextInt();

			System.out.println(zero[N] + " " + one[N]);
		}
	}
}
