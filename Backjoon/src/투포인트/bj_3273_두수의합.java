package 투포인트;

import java.util.Arrays;
import java.util.Scanner;

public class bj_3273_두수의합 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int[] arr = new int[n];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = sc.nextInt();
		}
		int x = sc.nextInt();
		
		Arrays.sort(arr);
		
		int start = 0, end = n-1, sum = 0, count = 0;
		while (true) {

			if (start == end)
				break;

			sum = arr[start] + arr[end];
			
			if (sum > x)
				end--;
			else if (sum < x)
				start++;
			else {
				count++;
				start++;
			}

		}

		System.out.println(count);

		sc.close();
	}

}
