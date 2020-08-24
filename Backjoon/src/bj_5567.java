import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class bj_5567 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);

		int n = sc.nextInt();
		int m = sc.nextInt();

		int relationship[][] = new int[m][2];
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < 2; j++) {
				relationship[i][j] = sc.nextInt();
			}
		}

		boolean invited[] = new boolean[n + 1];
		invited[0] = true;
		invited[1] = true;

		Queue<Integer> qu = new LinkedList<Integer>();
		qu.add(1);

		int turn = 0;
		int count = 0;

		while (!qu.isEmpty()) {
			int size=qu.size();
			for (int s = 0; s < size; s++) {

				int now = qu.poll();

				for (int i = 0; i < relationship.length; i++) {
					if (relationship[i][0] == now && !invited[relationship[i][1]]) {
						qu.add(relationship[i][1]);
						invited[relationship[i][1]] = true;
						count++;
					}
					
					if (relationship[i][1] == now && !invited[relationship[i][0]]) {
						qu.add(relationship[i][0]);
						invited[relationship[i][0]] = true;
						count++;
					}
				}

			}
			turn++;
			if(turn==2) break;
		}
		System.out.println(count);
	}

}
