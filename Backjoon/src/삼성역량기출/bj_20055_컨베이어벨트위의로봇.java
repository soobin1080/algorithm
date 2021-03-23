package 삼성역량기출;

import java.util.Scanner;

public class bj_20055_컨베이어벨트위의로봇 {
	static int N, K;
	static int[] arr;
	static boolean[] robot;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();
		K = sc.nextInt();
		arr = new int[2 * N];
		robot = new boolean[2 * N];

		for (int i = 0; i < arr.length; i++) {
			arr[i] = sc.nextInt();
		}

		int start = 0, end = N - 1, count = 0, step = 0;

		while (count < K) {
			step++;

			start--;
			if (start < 0)
				start = 2 * N - 1;
			end--;
			if (end < 0)
				end = 2 * N - 1;

			int index = end + 1;
			while (index != start) {
				index--;
				if (index < 0)
					index = 2 * N - 1;

				if (robot[index]) {
					if (index == end)
						robot[index] = false;
					else {
						int idx = index + 1;
						if (idx == 2 * N)
							idx = 0;
						if (!robot[idx] && arr[idx] > 0) {
							robot[index] = false;
							
							if(idx==end)
								robot[idx]=false;
							else
								robot[idx] = true;
							
							arr[idx]--;
							if (arr[idx] == 0)
								count++;
						}
					}
				}

			}

			if (!robot[start] && arr[start] > 0) {
				robot[start] = true;
				arr[start]--;
				if (arr[start] == 0)
					count++;
			}

		}

		System.out.println(step);
		sc.close();
	}

}
