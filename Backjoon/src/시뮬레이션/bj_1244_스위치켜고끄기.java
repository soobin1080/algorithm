package 시뮬레이션;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class bj_1244_스위치켜고끄기 {
	static int N;
	static int[] switches;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		switches = new int[N + 1];

		for (int i = 1; i < N + 1; i++) {
			switches[i] = sc.nextInt();
		}
		// 학생의 성별, 학생이 받은 수
		// 남학생은 1로, 여학생은 2

		int student = sc.nextInt();

		for (int i = 0; i < student; i++) {
			int sex = sc.nextInt();
			int number = sc.nextInt();

			if (sex == 1) { // 남학생
				boy(number);
			} else { // 여학생
				girl(number);
			}
		}

		for (int i = 1; i < N + 1; i++) {
			if (i % 20 == 0) {
				System.out.println(switches[i] + " ");
			} else
				System.out.print(switches[i] + " ");

		}
	}

	private static void girl(int number) {
		Queue<Integer> index = new LinkedList<Integer>();
		index.add(number);
		int distance = 1;
		while (number - distance > 0 && number + distance <= N) {
			int left = number - distance;
			int right = number + distance;

			if (switches[left] == switches[right]) {
				index.add(left);
				index.add(right);
				distance++;
			} else
				break;
		}

		while (!index.isEmpty()) {
			int i = index.poll();
			if (switches[i] == 1)
				switches[i] = 0;
			else
				switches[i] = 1;
		}
	}

	private static void boy(int number) {
		for (int i = number; i < switches.length; i += number) {
			if (switches[i] == 1)
				switches[i] = 0;
			else
				switches[i] = 1;
		}
	}
}
