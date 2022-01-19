import java.util.Scanner;

public class bj_11727_2n타일링2 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int n = sc.nextInt();

		int[] arr = new int[n + 1];

		arr[0] = 0;
		arr[1] = 1;

		for (int i = 1; i < n; i++) {
			if (i % 2 == 1) {
				arr[i + 1] = (arr[i] * 2 + 1) % 10007;
			} else {
				arr[i + 1] = (arr[i] * 2 - 1) % 10007;
			}
		}

		System.out.println(arr[n] % 10007);

	}
}
