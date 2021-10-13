package 삼성역량기출;

import java.util.Scanner;

public class bj_20055_컨베이어벨트위의로봇_RE {

	public static void main(String[] args) {
		// 입력받기

		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int K = sc.nextInt();

		// 내구도
		int[] belt = new int[2 * N];

		for (int i = 0; i < 2 * N; i++) {
			belt[i] = sc.nextInt();
		}

		// robot
		boolean[] robot = new boolean[2 * N];

		int start = 0, end = N - 1;

		int count = 0, stage = 0;

		// 내구도가 0 인 칸이 K개 이상이면 종료
		while (count < K) {
			stage++;

			// 한칸 회전
			start--;
			if (start < 0)
				start = 2 * N - 1;
			end--;
			if (end < 0)
				end = 2 * N - 1;

			// 로봇이동
			int index = end + 1;

			while (index != start) {
				index--;
				if (index < 0)
					index = 2 * N - 1;

				// 내려가는곳일때
				if (index == end) {
					if (robot[index])
						robot[index] = false;
				} else {
					if (robot[index]) {
						int nidx = index + 1;
						if (nidx > 2 * N - 1)
							nidx = 0;
						if (!robot[nidx] && belt[nidx] > 0) {
							robot[index] = false;
							if (nidx == end) //내리는 위치에 도달하면 즉시 내린다.
								robot[nidx] = false;
							else
								robot[nidx] = true;
							belt[nidx]--;
							if (belt[nidx] == 0)
								count++;
						}
					}
				}
			}

			if (belt[start] > 0) {
				robot[start] = true;
				belt[start]--;
				if (belt[start] == 0)
					count++;
			}

		}

		System.out.println(stage);

	}

}
