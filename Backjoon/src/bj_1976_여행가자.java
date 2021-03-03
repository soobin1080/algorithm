import java.util.Scanner;

public class bj_1976_여행가자 {
	static int[][] map;
	static int[] route;
	static int[] arr;

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		int n = sc.nextInt();
		int m = sc.nextInt();

		map = new int[n + 1][n + 1];
		for (int i = 1; i < n + 1; i++) {
			for (int j = 1; j < n+1; j++) {
				map[i][j] = sc.nextInt();
			}
		}

		arr = new int[n + 1];
		for (int i = 1; i < n + 1; i++) {
			arr[i] = i;
		}

		route = new int[m];
		for (int i = 0; i < m; i++) {
			route[i] = sc.nextInt();
		}

		for (int i = 1; i < n + 1; i++) {
			for (int j = 1; j < n + 1; j++) {
				if (map[i][j] == 1) {
					union(i, j);
				}
			}
		}

		int connect = route[0];
		for (int i = 1; i < m; i++) {
			if (find(connect) != find(route[i])) {
				System.out.println("NO");
				return;
			}
		}
		System.out.println("YES");

		sc.close();

	}

	private static void union(int i, int j) {
		int p1 = find(i);
		int p2 = find(j);

		if (p1 != p2) {
			arr[p1] = p2;
		}
	}

	static int find(int city) {
		if (arr[city] == city)
			return city;

		arr[city] = find(arr[city]);
		return arr[city];
	}

}
