package 시뮬레이션;

import java.util.Scanner;

public class bj_1547_공 {
	static int cup[] = new int[4];

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		for (int i = 0; i < cup.length; i++) {
			cup[i] = i;
		}
		// 컵 위친 바꾼 횟수
		int TC = sc.nextInt();

		for (int tc = 1; tc <= TC; tc++) {
			int x = sc.nextInt();
			int y = sc.nextInt();

			swap(x, y);
		}
		for (int i = 1; i < cup.length; i++) {
			if(cup[i]==1)
			System.out.print(i);
		}
	}

	static void swap(int x, int y) {
		int temp1 = cup[x]; //x번컵의 인덱스값
		cup[x]=cup[y];
		cup[y]=temp1;
	}

}
