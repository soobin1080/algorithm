package SkillCheck;
import java.util.Arrays;

public class solution1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] answer = {};
		long n = 12345;
		String s = String.valueOf(n);
		
		char[] number=s.toCharArray();
		answer=new int[number.length];
		for (int i = 0; i <number.length; i++) {
			int a=number.length-i-1;
			answer[i]=number[a]-'0';
		}
		
		System.out.println(Arrays.toString(answer));
	}

}
