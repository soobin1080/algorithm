package 시뮬레이션;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class bj_14719_빗물 {
	static int[] map;
	static int H, W;
	static boolean[] used;
	static Queue<Integer> index;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		H = sc.nextInt();
		W = sc.nextInt();

		map = new int[W];
		used = new boolean[W];

		for (int i = 0; i < W; i++) {
			map[i] = sc.nextInt();
		}
		int answer = 0;
		int count = 0;
		int h = 0;
		index = new LinkedList<>();
		for (int i = 0; i < W; i++) {
			if (h > map[i]) {
				if (!used[i]) {
					count += (h - map[i]);
					index.add(i);
				}
			} else {
				h = map[i];
				answer += count;
				count = 0;
				while (!index.isEmpty()) {
					used[index.poll()] = true;
				}
			}
		}

		h = 0;
		count = 0;
		index = new LinkedList<>();
		for (int i = W - 1; i >= 0; i--) {
			if (h > map[i]) {
				if (!used[i]) {
					count += (h - map[i]);
					index.add(i);
				}
			} else {
				h = map[i];
				answer += count;
				count = 0;
				while (!index.isEmpty()) {
					used[index.poll()] = true;
				}
			}
		}
		System.out.println(answer);
	}

}
