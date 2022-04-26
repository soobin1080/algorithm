import java.util.ArrayList;
import java.util.Scanner;

public class bj_11725_트리의부모찾기 {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt() + 1;

		int parent[] = new int[n];

		ArrayList<Integer>[] list = new ArrayList[n + 1];

		for (int i = 1; i <= n; i++) {
			list[i] = new ArrayList<Integer>();
		}

		for (int i = 0; i < n - 2; i++) {
			int a = sc.nextInt();
			int b = sc.nextInt();
			list[a].add(b);
			list[b].add(a);
		}

		dfs(list, parent, n - 1, 1, 0);

		
		for (int i = 2; i < n; i++) {
			System.out.println(parent[i]);
		}
	}

	private static void dfs(ArrayList<Integer>[] list, int[] parent, int n, int s, int p) {
		parent[s] = p;
		for (int item : list[s]) {
			if (item != p)
				dfs(list, parent, n, item, s);
		}
	}

}
