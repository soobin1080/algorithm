import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

public class bj_11286_절댓값힙 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		PriorityQueue<Node> pq = new PriorityQueue<>(new Comparator<Node>() {

			@Override
			public int compare(Node o1, Node o2) {
				if (o1.abs == o2.abs)
					return o1.n - o2.n;
				return o1.abs - o2.abs;
			}
		});

		int N = sc.nextInt();
		for (int i = 0; i < N; i++) {
			int n = sc.nextInt();
			if (n != 0) {
				pq.add(new Node(n, Math.abs(n)));
			} else {
				if (pq.size() != 0) {
					System.out.println(pq.poll().n);
				} else
					System.out.println(0);
			}
		}

		sc.close();
	}

	static class Node {
		int n;
		int abs;

		Node(int n, int abs) {
			this.n = n;
			this.abs = abs;
		}
	}

}
