import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

public class bj_11279_최대힙 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		PriorityQueue<Integer> pq = new PriorityQueue<>(new Comparator<Integer>() {

			@Override
			public int compare(Integer o1, Integer o2) {
				return o2 - o1;
			}
		});

		int N = sc.nextInt();
		for (int i = 0; i < N; i++) {
			int n = sc.nextInt();
			if (n != 0) {
				pq.add(n);
			} else {
				if (pq.size() != 0) {
					System.out.println(pq.poll());
				} else
					System.out.println(0);
			}
		}

		sc.close();
	}

}
