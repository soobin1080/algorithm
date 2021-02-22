import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class bj_2606_바이러스 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int N = sc.nextInt();
		int M = sc.nextInt();

		int[][] arr = new int[N + 1][N + 1];
		boolean[] computer = new boolean[N + 1];

		for (int i = 0; i < M; i++) {
			int y=sc.nextInt();
			int x=sc.nextInt();
			arr[y][x] = 1;
			arr[x][y] = 1;
		}
		

		Queue<Integer> qu = new LinkedList<Integer>();

		computer[1] = true;
		for (int i = 1; i < N + 1; i++) {
			if (arr[1][i] == 1 && !computer[i]) {
				qu.add(i);
			}
		}

		while (!qu.isEmpty()) {
			int n = qu.poll();
			computer[n] = true;

			for (int i = 1; i < N + 1; i++) {
				if (arr[n][i] == 1 && !computer[i]) {
					qu.add(i);
				}
			}
		}

		int sum = 0;
		for (int i = 2; i < computer.length; i++) {
			if (computer[i])
				sum++;
		}
		System.out.println(sum);

		sc.close();
	}

}
