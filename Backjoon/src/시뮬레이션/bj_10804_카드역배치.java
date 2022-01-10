package 시뮬레이션;

import java.util.Scanner;

public class bj_10804_카드역배치 {

	static int[] cards = new int[21];

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		for (int i = 0; i < cards.length; i++) {
			cards[i] = i;
		}

		for (int tc = 0; tc < 10; tc++) {

			int start = sc.nextInt();
			int end = sc.nextInt();

			while (start < end) {

				int temp = cards[start];
				cards[start] = cards[end];
				cards[end] = temp;
				start++;
				end--;

			}
		}

		for (int i = 1; i < cards.length; i++) {
			System.out.print(cards[i] + " ");
		}
	}

}
