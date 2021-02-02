package 삼성역량기출;

import java.util.Scanner;

public class bj_14499_주사위굴리기 {

	static int[][] map;
	static int[] dy= {1,-1,0,0};//동 1, 서2, 북3, 남4
	static int[] dx= {0,0,-1,1};
	static int dice[]=new int[7];
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Scanner sc=new Scanner(System.in);
		
		int n=sc.nextInt();
		int m=sc.nextInt();
		int x=sc.nextInt();
		int y=sc.nextInt();
		int k=sc.nextInt();
		
		map=new int[n][m];
		
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				map[i][j]=sc.nextInt();
			}
		}
		
		for (int i = 0; i < k; i++) {
			int dir=sc.nextInt();
			
			int ix=x+dx[dir-1];
			int iy=y+dy[dir-1];
			
			if(ix>=0 && ix<n && iy>=0 && iy<m) {
				moveDice(dir);
				
				if(map[ix][iy]==0) {
					map[ix][iy]=dice[6];
				}else {
					dice[6]=map[ix][iy];
					map[ix][iy]=0;
				}
				
				x=ix;
				y=iy;
				System.out.println(dice[1]);
			}
			
		}
		
	}

	private static void moveDice(int dir) {
		int[] temp = dice.clone();
		//동 1, 서2, 북3, 남4
		if(dir==1) {
			dice[1]=temp[4];
			dice[3]=temp[1];
			dice[4]=temp[6];
			dice[6]=temp[3];
		}else if(dir==2) {
			dice[1] = temp[3];
            dice[3] = temp[6];
            dice[4] = temp[1];
            dice[6] = temp[4];
		}else if(dir==3) {
			dice[1] = temp[5];
            dice[2] = temp[1];
            dice[5] = temp[6];
            dice[6] = temp[2];
		}else {
			dice[1] = temp[2];
            dice[2] = temp[6];
            dice[5] = temp[1];
            dice[6] = temp[5];
		}
	}

}
