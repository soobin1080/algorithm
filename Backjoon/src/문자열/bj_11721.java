package 문자열;

import java.util.Scanner;

public class bj_11721 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc=new Scanner(System.in);
		
		String input=sc.next();
		
		int n=input.length()/10;
		for (int i = 0; i < n; i++) {
			int a=i*10;
			System.out.println(input.substring(a, a+10));
		}
		
		System.out.println(input.substring(n*10,input.length()));
	}

}
