package 삼성역량기출;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class bj_16236_아기상어 {
	static int N;
	static int[][] map;
	static boolean[][] visited;
	static int[] dx = { 0, 0, -1, 1 };
	static int[] dy = { -1, 1, 0, 0 };

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();
		map = new int[N][N];

		int y = 0, x = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				map[i][j] = sc.nextInt();
				if (map[i][j] == 9) {
					y = i;
					x = j;
					map[i][j]=0;
				}
			}
		}

		Queue<Shark> qu = new LinkedList<Shark>();
		visited = new boolean[N][N];
		qu.add(new Shark(y, x));
		visited[y][x]=true;
		Queue<Shark> eat = new LinkedList<>();
		int time = 0,ans=0;
		int eat_count = 0;
		int shark_size = 2;

		while (!qu.isEmpty()) {
			int size = qu.size();
			time++;
			
			for (int s = 0; s < size; s++) {
				
				Shark shark = qu.poll();
				// 4방향 탐색
				for (int i = 0; i < 4; i++) {
					int sy = shark.y + dy[i];
					int sx = shark.x + dx[i];

					if (sy >= 0 && sy < N && sx >= 0 && sx < N && !visited[sy][sx]) {

						if (map[sy][sx] != 0) {
							if (map[sy][sx] < shark_size) {
								eat.add(new Shark(sy, sx));

							} else if (map[sy][sx] == shark_size) {
								qu.add(new Shark(sy, sx));
							}
							visited[sy][sx] = true;

						}else {
							qu.add(new Shark(sy, sx));
							visited[sy][sx] = true;
						}
					}
				}

			}

			if (eat.size() > 0) {
				Collections.sort((List<Shark>) eat);
				Shark shark = eat.poll();
				eat_count++;
				map[shark.y][shark.x] = 0;
				if (eat_count == shark_size) {
					eat_count = 0;
					shark_size++;
				}
				qu.clear();
				qu.add(new Shark(shark.y, shark.x));
				visited = new boolean[N][N];
				visited[shark.y][shark.x]=true;
				eat.clear();
				ans+=time;
				time=0;
			}
		}

		System.out.println(ans);

	}

	static class Shark implements Comparable<Shark> {
		int y;
		int x;

		Shark(int y, int x) {
			this.y = y;
			this.x = x;
		}

		@Override
		public int compareTo(Shark o) {
			if (this.y==o.y)
				return this.x-o.x;
			return this.y-o.y;
		}

	}

}
