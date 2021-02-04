package 삼성역량기출;

import java.util.Scanner;

import 삼성역량기출.bj_19326_청소년상어.Fish;

public class test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);

		int[][] map = new int[4][4];
		Fish[] fish = new Fish[17];

		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				map[i][j] = sc.nextInt();// 물고기번호
				fish[map[i][j]] = new Fish(sc.nextInt() - 1, true, i, j);
			}
		}
		move(map,fish,0);

	}

	private static void move(int[][] map, Fish[] fish,int n) {
		if(n==3) {
		
			return;
		}
		// TODO Auto-generated method stub
		int[][] copy_map = map.clone();
		Fish[] copy_fish = fish.clone();
		
		System.out.println(copy_map);
		System.out.println(copy_fish);
		System.out.println("--------------");
		
		move(copy_map,copy_fish,n+1);
		
	}

}
