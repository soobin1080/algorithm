import java.util.Scanner;

public class bj_11052_카드구매하기 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int n = sc.nextInt();

		int p[] = new int[n + 1]; // 카드팩
		int dp[] = new int[n + 1]; // 지불해야하는 금액의 최댓값

		for (int i = 1; i < n + 1; i++) {
			p[i] = sc.nextInt();
		}

		for (int i = 1; i < n + 1; i++) {
			for (int j = 1; j < i + 1; j++) {
				dp[i] = Math.max(dp[i], p[j] + dp[i - j]);
			}
		}

		System.out.println(dp[n]);
	}

}
