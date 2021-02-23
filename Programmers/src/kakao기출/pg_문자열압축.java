package kakao기출;

import java.util.Scanner;

public class pg_문자열압축 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		String s = sc.next();
		int min = 1000;
		for (int i = 1; i < s.length(); i++) { // 문자 단위
			String answer = "";
			String pre = "";
			int n = 1;
			for (int j = 0; j < s.length(); j += i) {
				String next = "";
				if (i + j > s.length())
					next = s.substring(j, s.length());
				else
					next = s.substring(j, i + j);

				if (next.equals(pre)) {
					n++;
				} else {
					if (n > 1)
						answer += (n + pre);
					else
						answer += pre;
					n = 1;
				}
				pre = next;
			}
			if (n > 1)
				answer += (n + pre);
			else
				answer += pre;

			System.out.println(answer);
			
			if (min > answer.length())
				min = answer.length();
		}
		
		if(s.length()==1)
			min=1;
		
		System.out.println(min);

		sc.close();
	}

}
