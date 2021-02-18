import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

public class bj_1655_가운데를말해요 {

	public static void main(String[] args) throws InterruptedException {
		Scanner sc = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();

		PriorityQueue<Integer> maxHeap = new PriorityQueue<>();
		PriorityQueue<Integer> minHeap = new PriorityQueue<>((o1, o2) -> o2 - o1); // new Comparator 안쓰고 이렇게
																					// 가능!!!!!!!대박스

		int N = sc.nextInt();

		for (int i = 0; i < N; i++) {
			int n = sc.nextInt();

			if (minHeap.size() == maxHeap.size()) {
				minHeap.add(n);
			} else
				maxHeap.add(n);

			if (!minHeap.isEmpty() && !maxHeap.isEmpty()) {
				if (minHeap.peek() > maxHeap.peek()) {
					maxHeap.add(minHeap.poll());
					minHeap.add(maxHeap.poll());
				}
			}

			sb.append(minHeap.peek() + "\n");
		}

		System.out.println(sb);
		sc.close();
	}

}
