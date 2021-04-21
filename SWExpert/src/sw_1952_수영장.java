import java.util.Scanner;

public class sw_1952_수영장 {
	static int[] fee;
	static int[] month;
	static int[] ticket;
	static int answer;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int TC = sc.nextInt();
		for (int tc = 1; tc <= TC; tc++) {

			fee = new int[4];
			month = new int[12];
			ticket = new int[12];
			answer = Integer.MAX_VALUE;

			for (int i = 0; i < 4; i++) {
				fee[i] = sc.nextInt();
			}
			for (int i = 0; i < 12; i++) {
				month[i] = sc.nextInt();
			}

			perm(0, 12);

			System.out.println("#"+tc+" "+answer);
		}

		sc.close();
	}

	private static void perm(int index, int n) {
		if (index == n) {
			int sum = 0, idx = 0;
			while (idx < n) {
				int pass = ticket[idx];
				if (pass == 0) {
					sum += (fee[pass] * month[idx]);
					idx++;
				} else if (pass == 1) {
					sum += fee[pass];
					idx++;
				} else if (pass == 2) {
					sum += fee[pass];
					idx += 3;
				} else if (pass == 3) {
					sum += fee[pass];
					idx = n;
				}
			}

			answer = (sum < answer) ? sum : answer;
			return;
		}

		for (int i = 0; i < fee.length; i++) {
			if (i == 0 || i == 1) {
				ticket[index] = i;
				perm(index + 1, n);
			} else if (i == 2 && index < 10) {
				ticket[index] = i;
				perm(index + 3, n);
			} else if (i == 3 && index == 0) {
				ticket[index] = i;
				perm(n, n);
			}

		}

	}
}
