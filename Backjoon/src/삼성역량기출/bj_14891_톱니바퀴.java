package 삼성역량기출;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class bj_14891_톱니바퀴 {
	static int[][] wheel;
	static int dir[];
	static int[] right = { 2, 2, 2, 2 }; // 1번,2번,3번,4번
	static int[] left = { 6, 6, 6, 6 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		wheel = new int[4][8];
		for (int i = 0; i < 4; i++) {
			String s = br.readLine();
			for (int j = 0; j < s.length(); j++) {
				wheel[i][j] = s.charAt(j) - '0';
			}
		}

		int K = Integer.parseInt(br.readLine());

		dir = new int[4];

		for (int i = 0; i < K; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int number = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());

			dir[number - 1] = d;
			rotate(number);

			for (int j = 0; j < 4; j++) {
				if (dir[j] == 1) {
					right[j]--;
					if (right[j] < 0)
						right[j] = 7;
					left[j]--;
					if (left[j] < 0)
						left[j] = 7;
				} else if (dir[j] == -1) {
					right[j]++;
					if (right[j] > 7)
						right[j] = 0;
					left[j]++;
					if (left[j] > 7)
						left[j] = 0;
				}
			}

			Arrays.fill(dir, 0);
		}

		int sum = 0;
		int a = 1;
		for (int i = 0; i < 4; i++) {
			int j = right[i] - 2;
			if (j < 0)
				j = 8 + j;

			if (wheel[i][j] == 1)
				sum += (wheel[i][j] * a);
			a *= 2;
		}
		System.out.println(sum);
	}

	static void rotate(int number) {
		if (number == 1) {
			if (wheel[0][right[0]] != wheel[1][left[1]] && dir[0] != 0) {
				dir[1] = dir[0] * -1;
			}
			if (wheel[1][right[1]] != wheel[2][left[2]] && dir[1] != 0) {
				dir[2] = dir[1] * -1;
			}
			if (wheel[2][right[2]] != wheel[3][left[3]] && dir[2] != 0) {
				dir[3] = dir[2] * -1;
			}
		} else if (number == 2) {
			if (wheel[0][right[0]] != wheel[1][left[1]] && dir[1] != 0) {
				dir[0] = dir[1] * -1;
			}
			if (wheel[1][right[1]] != wheel[2][left[2]] && dir[1] != 0) {
				dir[2] = dir[1] * -1;
			}
			if (wheel[2][right[2]] != wheel[3][left[3]] && dir[2] != 0) {
				dir[3] = dir[2] * -1;
			}
		} else if (number == 3) {
			if (wheel[2][right[2]] != wheel[3][left[3]] && dir[2] != 0) {
				dir[3] = dir[2] * -1;
			}
			if (wheel[1][right[1]] != wheel[2][left[2]] && dir[2] != 0) {
				dir[1] = dir[2] * -1;
			}
			if (wheel[0][right[0]] != wheel[1][left[1]] && dir[1] != 0) {
				dir[0] = dir[1] * -1;
			}
		} else if (number == 4) {
			if (wheel[2][right[2]] != wheel[3][left[3]] && dir[3] != 0) {
				dir[2] = dir[3] * -1;
			}
			if (wheel[1][right[1]] != wheel[2][left[2]] && dir[2] != 0) {
				dir[1] = dir[2] * -1;
			}
			if (wheel[0][right[0]] != wheel[1][left[1]] && dir[1] != 0) {
				dir[0] = dir[1] * -1;
			}
		}
	}

}
