import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class sw_10966_물놀이를가자3 {

	 static int[] dy = { -1, 1, 0, 0 }; // 상하좌우
	    static int[] dx = { 0, 0, -1, 1 };
	    static int distance_sum;
	 
	    public static void main(String[] args) throws IOException {
	        // TODO Auto-generated method stub
	        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	 
	        int TC = Integer.parseInt(br.readLine());
	        for (int tc = 1; tc <= TC; tc++) {
	            StringTokenizer st = new StringTokenizer(br.readLine());
	            int N = Integer.parseInt(st.nextToken());
	            int M = Integer.parseInt(st.nextToken());
	 
	            char[][] map = new char[N][M];
	            for (int i = 0; i < N; i++) {
	                map[i] = br.readLine().toCharArray();
	            }
	 
	            distance_sum = 0;
	             
	            for (int i = 0; i < N; i++) {
	                for (int j = 0; j < M; j++) {
	                    if (map[i][j] == 'L') {
	                        bfs(N, M, i, j, map);
	                    }
	                }
	            }
	            System.out.println("#" + tc + " " + distance_sum);
	 
	        }
	    }
	 
	    private static void bfs(int N, int M, int y, int x, char[][] map) {
	        boolean visited[][] = new boolean[N][M];
	        Queue<Point> qu = new LinkedList<Point>();
	        qu.add(new Point(y, x));
	        visited[y][x] = true;
	        int distance = 0;
	        a: while (!qu.isEmpty()) {
	            distance++;
	            int size = qu.size();
	            for (int s = 0; s < size; s++) {
	 
	                Point now = qu.poll();
	 
	                for (int i = 0; i < 4; i++) {
	                    int ny = dy[i] + now.y;
	                    int nx = dx[i] + now.x;
	                    if (ny >= 0 && ny < N && nx >= 0 && nx < M && !visited[ny][nx]) {
	                        if (map[ny][nx] == 'W') {
	                            distance_sum += distance;
	                            break a;
	                        } else {
	                            visited[ny][nx] = true;
	                            qu.add(new Point(ny, nx));
	                        }
	                    }
	                }
	            }
	        }
	    }
	 
	    static class Point {
	        int y;
	        int x;
	 
	        Point(int y, int x) {
	            this.y = y;
	            this.x = x;
	        }
	    }
	}
