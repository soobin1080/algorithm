package kakao기출;

import java.util.Scanner;

public class pg_신규아이디추천 {
//...!@BaT#*..y.abcdefghijklm
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);

		String new_id = sc.next();

		// 1단계
		new_id = function1(new_id);
		// 2단계
		new_id = function2(new_id);
		// 3단계
		new_id = function3(new_id);
		// 4단계
		new_id = function4(new_id);
		// 5단계
		new_id = function5(new_id);
		// 6단계
		new_id = function6(new_id);
		// 7단계
		new_id = function7(new_id);

		System.out.println(new_id);
	}

	private static String function7(String new_id) {
		String st = new_id;
		if (st.length() == 1) {
			char c = st.charAt(st.length() - 1);
			st += c;
			st += c;
		} else if (st.length() == 2) {
			char c = st.charAt(st.length() - 1);
			st += c;
		}

		return st;
	}

	private static String function6(String new_id) {
		String st = new_id;
		if (new_id.length() > 15) {
			if (new_id.charAt(14) == '.')
				st = new_id.substring(0, 14);
			else
				st = new_id.substring(0, 15);
		}

		return st;
	}

	private static String function5(String new_id) {
		String st = new_id;
		if (new_id == "") {
			st = "a";
		}
		return st;
	}

	private static String function4(String new_id) {
		String st = new_id;
		int start = 0, end = new_id.length();

		if (new_id.length() == 1 && new_id.charAt(0) == '.') {
			st = "";
		} else {

			if (new_id.charAt(0) == '.') {
				start = 1;
			}

			if (new_id.charAt(new_id.length() - 1) == '.') {
				end = new_id.length() - 1;
			}

			st = new_id.substring(start, end);
		}
		return st;
	}

	private static String function3(String new_id) {
		String st = "";
		char[] check = new_id.toCharArray();

		boolean flag = true;
		for (int i = 0; i < check.length; i++) {
			if (check[i] == '.') {
				if (flag) {
					st += '.';
					flag = false;
				}
			} else {
				st += check[i];
				flag = true;
			}
		}

		return st;
	}

	private static String function2(String new_id) {
		String st = "";
		for (int i = 0; i < new_id.length(); i++) {
			// .=-2 , -=-3, _=47, a~z=49~74, 0~9=0~9
			int num = new_id.charAt(i) - '0';
			if (num == -2 || num == -3 || num == 47 || (num >= 49 && num <= 74) || (num >= 0 && num <= 9)) {
				st += new_id.charAt(i);
			} else {
				continue;
			}
		}

		return st;
	}

	private static String function1(String new_id) {
		String st = new_id.toLowerCase();
		return st;
	}

}
