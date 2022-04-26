package 시뮬레이션;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class bj_16918_봄버맨 {
	static int map[][];
	static int R, C, N;
	static int dy[] = { -1, 1, 0, 0 };
	static int dx[] = { 0, 0, -1, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());

		map = new int[R][C];

		for (int i = 0; i < R; i++) {
			String s = br.readLine();
			for (int j = 0; j < C; j++) {
				if (s.charAt(j) == '.')
					map[i][j] = 0;
				else
					map[i][j] = 1; // 폭탄
			}
		}

		for (int i = 2; i <= N; i++) {
			// map에 폭탄설치하기
			for (int r = 0; r < R; r++) {
				for (int c = 0; c < C; c++) {
					if (map[r][c] == 0) {
						map[r][c] = 1;
					} else {
						map[r][c]++;
					}
				}
			}

			if (i % 2 == 1)
				// 폭탄터트리기
				for (int r = 0; r < R; r++) {
					for (int c = 0; c < C; c++) {
						if (map[r][c] >= 3) {
							map[r][c] = 0;

							for (int k = 0; k < 4; k++) {
								int ny = dy[k] + r;
								int nx = dx[k] + c;
								if (ny >= 0 && ny < R && nx >= 0 && nx < C) {
									if (map[ny][nx] < 3)
										map[ny][nx] = 0;
								}
							}
						}
					}
				}
		}

		printMap();

	}

	private static void printMap() {
		StringBuilder sb = new StringBuilder(); // 출력하기
		for (int r = 0; r < R; r++) {
			for (int c = 0; c < C; c++) {
				if (map[r][c] == 0)
					sb.append(".");
				else
					sb.append("O");
			}
			sb.append("\n");
		}

		System.out.println(sb);
	}
}
