package 삼성역량기출;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class bj_13460_구슬탈출2_다시 {

	static int[] dy = { -1, 1, 0, 0 };
	static int[] dx = { 0, 0, -1, 1 };
	static char[][] map;
	static boolean[][] visited;
	static int hy, hx;
	static int N, M;
	static int count = Integer.MAX_VALUE;

	public static void main(String[] args) throws NumberFormatException, IOException {

		// 입력받기
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new char[N][M];
		visited = new boolean[N][M];
		Ball start = new Ball(0, 0, 0, 0, 0);

		for (int i = 0; i < N; i++) {
			String s = br.readLine();
			for (int j = 0; j < M; j++) {
				map[i][j] = s.charAt(j);
				if (map[i][j] == 'R') {
					start.ry = i;
					start.rx = j;
				} else if (map[i][j] == 'B') {
					start.by = i;
					start.bx = j;
				} else if (map[i][j] == 'O') {
					hy = i;
					hx = j;
				}
			}
		} 

		// 구슬움직이기
		dfs(start);

		if (count > 10)
			count = -1;
		System.out.println(count);

	}

	
	private static void dfs(Ball start) {
		// TODO Auto-generated method stub
		
	}


	private static void print(char[][] map2) {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				System.out.print(map2[i][j]);
			}
			System.out.println();
		}
		System.out.println("ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ");
	}

	static class Ball {
		int ry;
		int rx;
		int by;
		int bx;
		int count;

		Ball(int ry, int rx, int by, int bx, int count) {
			this.ry = ry;
			this.rx = rx;
			this.by = by;
			this.bx = bx;
			this.count = count;
		}
	}

}
