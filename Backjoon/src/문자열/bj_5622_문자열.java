package 문자열;

import java.util.HashMap;
import java.util.Scanner;

public class bj_5622_문자열 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		String s = sc.next();

		HashMap<Integer, Integer> h = new HashMap<>();
		int arr[] = { 3, 3, 3, 3, 3, 4, 3, 4 };
		
		int a=0;
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < arr[i]; j++) {
				h.put(a++, i);
			}
		}

		int sum = 0;
		for (int i = 0; i < s.length(); i++) {
			int number = h.get(s.charAt(i) - 'A');
			sum += (number + 3); // 2부터 알파벳 들어가니깐 +2, 1초씩 더 걸리니깐 +1
		}

		System.out.println(sum);
		sc.close();
	}

}
