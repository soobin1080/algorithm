import java.util.Scanner;

public class bj_2003_수들의합2 {
	//투포인터 알고리즘
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int N = sc.nextInt();
		int M = sc.nextInt();

		int[] arr = new int[N];
		for (int i = 0; i < N; i++) {
			arr[i] = sc.nextInt();
		}

		int start = 0, end = 0, sum = 0, count = 0;

		while (true) {
			if (sum >= M) {
				sum -= arr[start++];
			} else
				sum += arr[end++];

			if (end == N)
				break;

			if (sum == M)
				count++;

		}
		System.out.println(count);

		sc.close();
	}

}
