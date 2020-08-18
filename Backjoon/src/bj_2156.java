

import java.util.Scanner;

public class bj_2156 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
        
        int n = sc.nextInt();
         
        int p[] = new int[n+1];
        int dp[] = new int[n+1];
         
        for(int i=1; i<=n; i++){
            p[i] = sc.nextInt();
        }
        //1,2번째는 초기화
        dp[1] = p[1];
        
        if(n > 1) // 1인 경우 터지기 때문에 예외처리
        dp[2] = p[1] + p[2];        
        
        for(int i=3; i<=n; i++){
            //연속 0번 마신경우   //연속 1번 마신경우 //연속 2번마신 경우
            dp[i] = Math.max(dp[i-1],Math.max(dp[i-2]+p[i],dp[i-3]+p[i-1]+p[i]));
 
        }
         
        System.out.println(dp[n]);
	}

}
