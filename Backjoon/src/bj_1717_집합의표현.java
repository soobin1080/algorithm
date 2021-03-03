import java.util.Scanner;

public class bj_1717_집합의표현 {
	static int[] arr;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int n = sc.nextInt();
		int m = sc.nextInt();

		arr = new int[n + 1];
		for (int i = 0; i < n + 1; i++) {
			arr[i] = i;
		}
		for (int i = 0; i < m; i++) {
			int operation = sc.nextInt();
			int a = sc.nextInt();
			int b = sc.nextInt();
			if (operation == 0) { // union
				union(a, b);
			} else { // find
				if (find(a) == find(b)) {
					System.out.println("YES");
				} else
					System.out.println("NO");
			}
		}

		sc.close();
	}

	private static int find(int a) {
		if (arr[a] == a)
			return a;

		arr[a] = find(arr[a]); // 대표값을 저장해주면 재귀 돌리지 않고 한번에 찾을 수 있다.
		return arr[a]; // return find(arr[a]);연산 돌릴때 마다 계속 재귀돌리면서 찾아줘야해! 시간 많이 걸림
	}

	private static void union(int a, int b) {
		int p1 = find(a);
		int p2 = find(b);

		if (p1 != p2) {
			arr[p1] = p2;
		}
	}

}
