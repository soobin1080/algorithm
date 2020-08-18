
import java.util.Scanner;

public class bj_10809 {
	
	private static Scanner sc;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		sc = new Scanner(System.in);

		String s = sc.next();

		int[] alphabet = new int[26];
		char[] input = s.toCharArray();

		for (int i = 0; i < input.length; i++) {
			int idx = input[i] - 'a';
			if (alphabet[idx] == 0) {
				alphabet[idx] = i + 1;
			}
		}

		for (int i = 0; i < alphabet.length; i++) {
			System.out.print(alphabet[i] - 1 + " ");
		}

	}

}
