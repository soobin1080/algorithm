package 시뮬레이션;

import java.util.Scanner;

public class bj_2947_나무조각 {
	static int[] arr = new int[5];

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		for (int i = 0; i < 5; i++) {
			arr[i] = sc.nextInt();
		}

		// 버블정렬
		boolean flag = false;
		while (!flag) {
			for (int i = 0; i < 4; i++) {
				if (arr[i] > arr[i + 1]) {
					int temp = arr[i];
					arr[i] = arr[i + 1];
					arr[i + 1] = temp;

					// 출력
					for (int j = 0; j < 5; j++) {
						System.out.print(arr[j] + " ");
					}
					System.out.println();
				}
			}

			// 정렬되면 종료
			for (int i = 0; i < 5; i++) {
				if (arr[i] == i + 1) {
					flag = true;
				} else {
					flag = false;
					break;
				}
			}
		}

	}

}
