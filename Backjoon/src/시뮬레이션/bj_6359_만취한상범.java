package 시뮬레이션;

import java.util.Scanner;

public class bj_6359_만취한상범 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int TC = sc.nextInt();

		for (int tc = 0; tc < TC; tc++) {

			int n = sc.nextInt();

			boolean[] room = new boolean[n + 1];

			for (int round = 1; round < n+1; round++) {
				for (int i = 1; i*round < n+1; i++) {
					int idx=i*round;
					if(room[idx])
						room[idx]=false;
					else
						room[idx]=true;
				}
			}
			
			int count=0;
			for (int i = 1; i < room.length; i++) {
				if(room[i]) count++;
			}

			System.out.println(count);
		}

	}

}
