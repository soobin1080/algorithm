import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Scanner;

public class bj_2252_줄세우기 {

	static int n, m;
	static boolean visits[];
	static int indegree[];

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		m = sc.nextInt();
		indegree = new int[n + 1];
		visits = new boolean[n + 1];
		ArrayList<Integer> list[] = new ArrayList[n + 1];

		for (int i = 0; i <= n; i++) {
			list[i] = new ArrayList<Integer>();
		}

		for (int i = 1; i <= m; i++) {
			int tmp1 = sc.nextInt(); // 1 2
			int tmp2 = sc.nextInt(); // 3 3
			list[tmp1].add(tmp2);
			indegree[tmp2]++;
		}

		PriorityQueue<Integer> q = new PriorityQueue<Integer>();

		for (int i = 1; i <= n; i++) {
			if (indegree[i] == 0) {
				q.add(i); // 1, 2가 들어감
			}
		}

		while (!q.isEmpty()) {
			int out = q.poll(); // 1, 2가 나옴
			System.out.print(out + " ");
			for (int i = 0; i < list[out].size(); i++) {
				int next = list[out].get(i);
				indegree[next]--;
				if (indegree[next] == 0) {
					q.add(next);
				}
			}
		}
	}
}