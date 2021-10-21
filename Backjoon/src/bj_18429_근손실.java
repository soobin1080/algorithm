import java.util.Scanner;

public class bj_18429_근손실 {
	static int[] kit;
	static boolean[] used;
	static int COUNT = 0;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int N = sc.nextInt();
		int K = sc.nextInt();

		kit = new int[N];
		used = new boolean[N];

		for (int i = 0; i < N; i++) {
			kit[i] = sc.nextInt();
		}

		perm(0, N, 500, K);
		System.out.println(COUNT);
	}

	private static void perm(int r, int n, int weight, int k) {
		if (weight < 500)
			return;

		if (r == n) {
			COUNT++;
			return;
		}

		for (int i = 0; i < kit.length; i++) {
			if (!used[i]) {
				used[i] = true;
				int w = weight + (kit[i] - k);
				perm(r + 1, n, w, k);
				used[i] = false;
			}
		}
	}

}
