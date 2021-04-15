package 삼성역량기출;

import java.util.Scanner;

public class bj_17779_게리멘더링2 {
	static int N;
	static int[][] map;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();
		map = new int[N][N];

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				map[i][j] = sc.nextInt();
			}
		}
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				//기준점 i,j
				//경계의 길이 d1, d2
				
			}
		}

		sc.close();
	}

}
