package 삼성역량기출;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class bj_21609_상어중학교 {
	static int[][] map;
	static int N;
	static int M;
	static int[] dy = { -1, 1, 0, 0 };
	static int[] dx = { 0, 0, -1, 1 };
	static int standardR, standardC, maxCount, rainbowCount;
	static int totalScore = 0;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();
		M = sc.nextInt();

		map = new int[N][N];
		// 입력받기
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				map[i][j] = sc.nextInt();
			}
		}
		maxCount = 1;
		while (true) {
			// 최대 그룹 찾기
			standardR = 0;
			standardC = 0;
			maxCount = 0;
			rainbowCount = 0;
			findBiggestGroup();
			// System.out.println("maxCount:" + maxCount + ",rainbowCount:" + rainbowCount
			// +",standardR:" + standardR+ ",standardC:" + standardC);

			if (standardR == 0 && standardC == 0 && maxCount == 0 && rainbowCount == 0)
				break;
			else {
				// 블록 점수 & 블록 제거하기
				removeBlock(standardR, standardC);
				totalScore += (maxCount * maxCount);
				// 중력 이동
				moveGravity();
				
				// 반시계 90 회전
				rotate();
				
				// 중력 이동
				moveGravity();
				
			}
		}

		System.out.println(totalScore);
	}

	private static void rotate() {
		int[][] copyMap = new int[N][N];

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				copyMap[i][j] = map[i][j];
			}
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				map[N - 1 - j][i] = copyMap[i][j];
			}
		}
	}

	private static void moveGravity() {
		for (int j = 0; j < N; j++) {
			// 내려갈 칸수
			int count = 0;
			for (int i = N - 1; i >= 0; i--) {
				if (map[i][j] == -9)
					count++;
				else if (map[i][j] >= 0 && count > 0) { // 블럭이동
					map[i + count][j] = map[i][j];
					map[i][j] = -9;
				} else if (map[i][j] == -1) {
					count = 0;
				}
			}
		}
	}

	private static void removeBlock(int standardR, int standardC) {
		// TODO Auto-generated method stub
		Queue<Integer> y = new LinkedList<>();
		Queue<Integer> x = new LinkedList<>();
		y.add(standardR);
		x.add(standardC);
		int number = map[standardR][standardC];
		map[standardR][standardC] = -9;
		// 블럭 제거하기
		while (!y.isEmpty() && !x.isEmpty()) {
			int iy = y.poll();
			int ix = x.poll();

			for (int k = 0; k < 4; k++) {
				int ny = iy + dy[k];
				int nx = ix + dx[k];
				if (ny >= 0 && ny < N && nx >= 0 && nx < N) {
					if (number == map[ny][nx]) { // 일반블록
						y.add(ny);
						x.add(nx);
						map[ny][nx] = -9;
					} else if (map[ny][nx] == 0) { // 무지개블록
						y.add(ny);
						x.add(nx);
						map[ny][nx] = -9;
					}
				}
			}
		}
	}

	private static void findBiggestGroup() {
		boolean[][] visited = new boolean[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				int sr = 0, sc = 0, mc = 0, rc = 0;
				// 일반블록이면 그룹찾기
				if (map[i][j] > 0 && !visited[i][j]) {

					sr = i;
					sc = j;

					Queue<Integer> y = new LinkedList<>();
					Queue<Integer> x = new LinkedList<>();
					y.add(i);
					x.add(j);
					visited[i][j] = true;
					mc++;

					// 인접블록찾기
					while (!y.isEmpty() && !x.isEmpty()) {
						int iy = y.poll();
						int ix = x.poll();

						for (int k = 0; k < 4; k++) {
							int ny = iy + dy[k];
							int nx = ix + dx[k];
							if (ny >= 0 && ny < N && nx >= 0 && nx < N && !visited[ny][nx]) {
								if (map[i][j] == map[ny][nx]) { // 일반블록
									visited[ny][nx] = true;
									mc++;
									y.add(ny);
									x.add(nx);
								} else if (map[ny][nx] == 0) { // 무지개블록
									visited[ny][nx] = true;
									mc++;
									rc++;
									y.add(ny);
									x.add(nx);
								}
							}
						}
					}
				}

				// 무지개 블럭 !visited 처리해주기
				for (int k = 0; k < N; k++) {
					for (int l = 0; l < N; l++) {
						if (map[k][l] == 0)
							visited[k][l] = false;
					}
				}

				if (mc >= 2) { // 그룹에 속한 블록수가 2개이상일때
					// 최대그룹인지 확인
					if (mc > maxCount) {
						maxCount = mc;
						rainbowCount = rc;
						standardR = sr;
						standardC = sc;
					} else if (mc == maxCount) {// 블록수 같으면
						if (rainbowCount < rc) { // 무지개 블록수 큰걸로
							rainbowCount = rc;
							standardR = sr;
							standardC = sc;
						} else if (rainbowCount == rc) { // 무지개 블록수 같으면
							if (standardR < sr) { // 행번호가 큰거
								standardR = sr;
								standardC = sc;
							} else if (standardR == sr) { // 행번호가 크면
								if (standardC < sc)
									standardC = sc; // 열번호가 큰게 기준 블록이 된다.
							}
						}
					}
				}
			}
		}
	}

	private static void printMap() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}

	}

}
