import java.util.PriorityQueue;
import java.util.Scanner;

public class bj_1655_가운데를말해요 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		PriorityQueue<Integer> pq = new PriorityQueue<>();

		int N = sc.nextInt();
		for (int i = 0; i < N; i++) {
			int n = sc.nextInt();
			pq.add(n);
			// Object[] arr = pq.toArray();
			// System.out.println(Arrays.toString(arr));
			/*
			 * if (pq.size() % 2 == 0) if (pq.size() == 0) System.out.println(arr[0]); else
			 * System.out.println(arr[pq.size() / 2 - 1]); else { if (pq.size() == 1)
			 * System.out.println(arr[0]); else System.out.println(arr[pq.size() / 2 - 1]);
			 * }
			 */
			System.out.println(pq.poll());
		}

		sc.close();
	}

}
