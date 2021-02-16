import java.util.Scanner;

public class bj_9095 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int TC = sc.nextInt();

		int[] input = new int[TC];

		int n = 0;
		for (int i = 0; i < input.length; i++) {
			input[i] = sc.nextInt();
			n = (n < input[i]) ? input[i] : n;
		}

		int[] arr = new int[n + 1];

		arr[0] = 0;
		arr[1] = 1;
		arr[2] = 2;
		arr[3] = 4;

		for (int i = 4; i < arr.length; i++) {
			arr[i] = arr[i - 1] + arr[i - 2] + arr[i - 3];
		}

		// 결과 리턴하기
		for (int i = 0; i < input.length; i++) {
			System.out.println(arr[input[i]]);
		}
		sc.close();
	}

}