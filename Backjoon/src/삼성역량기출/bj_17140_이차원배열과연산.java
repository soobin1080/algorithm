package 삼성역량기출;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class bj_17140_이차원배열과연산 {
	static int[][] arr;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int r = sc.nextInt() - 1;
		int c = sc.nextInt() - 1;

		int k = sc.nextInt();

		arr = new int[3][3];
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				arr[i][j] = sc.nextInt();
			}
		}

		int second = 0;
		while (second <= 100) {
			int n = arr.length;
			int m = arr[0].length;

			if (r < n && c < m && arr[r][c] == k)
				break;

			// r 연산
			if (n >= m) {

				int R = 0, C = 0;
				List<List<Number>> list = new ArrayList<>();
				for (int i = 0; i < arr.length; i++) {
					List<Number> sublist = new ArrayList<>();
					list.add(sublist);
					int[] number = new int[101];
					for (int j = 0; j < arr[0].length; j++) {
						number[arr[i][j]]++;
					}
					for (int j = 1; j < number.length; j++) {
						if (number[j] != 0)
							sublist.add(new Number(j, number[j]));
					}
					Collections.sort(sublist);

					if (sublist.size() > C)
						C = sublist.size();
				}

				R = list.size();
				arr = new int[R][C * 2];
				for (int i = 0; i < R; i++) {
					for (int j = 0; j < list.get(i).size(); j++) {
						arr[i][2 * j] = list.get(i).get(j).number;
						arr[i][2 * j + 1] = list.get(i).get(j).count;
					}
				}
			}
			// c 연산
			else {
				int R = 0, C = 0;
				List<List<Number>> list = new ArrayList<>();
				for (int i = 0; i < arr[0].length; i++) {
					List<Number> sublist = new ArrayList<>();
					list.add(sublist);
					int[] number = new int[101];
					for (int j = 0; j < arr.length; j++) {
						number[arr[j][i]]++;
					}
					for (int j = 1; j < number.length; j++) {
						if (number[j] != 0)
							sublist.add(new Number(j, number[j]));
					}
					Collections.sort(sublist);

					if (sublist.size() > R)
						R = sublist.size();
				}

				C = list.size();
				arr = new int[R * 2][C];
				for (int i = 0; i < C; i++) {
					for (int j = 0; j < list.get(i).size(); j++) {
						arr[2 * j][i] = list.get(i).get(j).number;
						arr[2 * j + 1][i] = list.get(i).get(j).count;
					}
				}
			}

			second++;
		}
		if (second > 100)
			second = -1;
		System.out.println(second);

		sc.close();
	}

	static class Number implements Comparable<Number> {
		int number;
		int count;

		Number(int number, int count) {
			this.number = number;
			this.count = count;
		}

		@Override
		public int compareTo(Number o) {
			if (this.count == o.count)
				return this.number - o.number;
			return this.count - o.count;
		}
	}

}
