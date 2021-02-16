import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class bj_2468 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);

		int N = sc.nextInt();
		int dy[] = { -1, 1, 0, 0 };// 상하좌우
		int dx[] = { 0, 0, -1, 1 };
		int area[][] = new int[N][N];
		int max_height = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				area[i][j] = sc.nextInt();
				max_height = (max_height < area[i][j]) ? area[i][j] : max_height;
			}
		}

		if (max_height > 100) {
			max_height = 100;
		}

		int answer = 0;
		for (int height = 0; height <= max_height; height++) { // 물에 잠길 수 있는 최대 높이 =max
			boolean safe[][] = new boolean[N][N];
			int safezone = 0;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (!safe[i][j] && area[i][j] > height) {
						Queue<Node> qu = new LinkedList<Node>();
						safe[i][j] = true;
						qu.add(new Node(i, j));

						while (!qu.isEmpty()) {
							Node node = qu.poll();

							for (int k = 0; k < 4; k++) {
								int yy = node.y + dy[k];
								int xx = node.x + dx[k];

								if (yy >= 0 && yy < N && xx >= 0 && xx < N && !safe[yy][xx] && area[yy][xx] > height) {
									safe[yy][xx] = true;
									qu.add(new Node(yy, xx));
								}
							}
						}
						safezone++;
					}
				}

			}

			answer = (safezone > answer) ? safezone : answer;
		}

		System.out.println(answer);
		sc.close();
	}

	static class Node {
		int y;
		int x;

		Node(int y, int x) {
			this.y = y;
			this.x = x;
		}
	}

}
