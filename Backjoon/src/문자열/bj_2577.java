package 문자열;

import java.util.Scanner;

public class bj_2577 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc=new Scanner(System.in);
		
		int A=sc.nextInt();
		int B=sc.nextInt();
		int C=sc.nextInt();
		
		int result=A*B*C;
		
		String answer=String.valueOf(result);
		
		int number[]=new int[10];
		
		for (int i = 0; i < answer.length(); i++) {
			int n=answer.charAt(i)-'0';
			number[n]++;
		}
		
		for (int i = 0; i < number.length; i++) {
			System.out.println(number[i]);
		}
		
	}

}
