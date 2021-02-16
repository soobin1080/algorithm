import java.util.Arrays;
import java.util.Scanner;

public class sw_2112_보호필름 {

	static int D, W, K, MIN;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int TC = sc.nextInt();

		for (int tc = 1; tc <= TC; tc++) {

			D = sc.nextInt();
			W = sc.nextInt();
			K = sc.nextInt();

			int film[][] = new int[D][W];
			for (int i = 0; i < D; i++) {
				for (int j = 0; j < W; j++) {
					film[i][j] = sc.nextInt();
				}
			}

			MIN = Integer.MAX_VALUE;

			dfs(film, 0, 0);
			System.out.println("#" + tc + " " + MIN);

		}
		sc.close();
	}

	private static void dfs(int[][] prev, int numOfInjection, int depth) {
		if (numOfInjection >= MIN)
			return;

		if (depth >= D) {
			if (check(prev)) {
				MIN = (MIN > numOfInjection) ? numOfInjection : MIN;
			}
			return;
		}

		int[][] temp = new int[D][W];
		for (int i = 0; i < D; i++) {
			temp[i] = Arrays.copyOf(prev[i], W);
		}

		// 끝까지 간 후
		dfs(temp, numOfInjection, depth + 1);

		Arrays.fill(temp[depth], 0);
		dfs(temp, numOfInjection + 1, depth + 1);

		Arrays.fill(temp[depth], 1);
		dfs(temp, numOfInjection + 1, depth + 1);
	}

	private static boolean check(int[][] film) {
		// 세로로
		for (int j = 0; j < W; j++) {
			int feature = film[0][j];
			int count = 0;
			for (int i = 0; i < D; i++) {
				if (feature == film[i][j]) {
					count++;
					if (count >= K)
						break;
				} else {
					feature = film[i][j];
					count = 1;
				}

			}

			if (count < K) {
				return false;
			}
		}
		return true;
	}

}
