package 삼성역량기출;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class bj_21611_마법사상어와블리자드 {
	static int N, M;
	static int map[][];
	static int sy, sx;
	static int dy[] = { -1, 1, 0, 0 };
	static int dx[] = { 0, 0, -1, 1 };
	static int blizardDirection[] = { 2, 1, 3, 0 };
	static int score = 0;
	static int numberScore[] = { 0, 0, 0, 0 };

	public static void main(String[] args) {

		// 입력받기
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		map = new int[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				map[i][j] = sc.nextInt();
			}
		}
		sy = N / 2;
		sx = N / 2;

		// 블리자드 시전
		for (int m = 0; m < M; m++) {

			int d = sc.nextInt() - 1;// 얼음파편방향
			int s = sc.nextInt();// 얼음파편거리

			// 얼음파편
			for (int i = 1; i <= s; i++) {
				int ny = sy + dy[d] * i;
				int nx = sx + dx[d] * i;
				map[ny][nx] = 0;
			}
			// 빈공간채우기
			fillEmptyspace(sy, sx);
			boolean isBomb = true;
			// 구슬 안 터질때까지 반복
			while (isBomb) {
				// 구슬 4개 이상인곳 없애기
				isBomb = bomb(sy, sx);
				// 터진 구슬 없으면 스탑 bomb=false;}
				if (isBomb)
					fillEmptyspace(sy, sx);
			}
			// 맵 바꾸기
			changeMap(sy, sx);
		}

		// 1*number[1]+2*number[2]+3*number[3]
		for (int i = 0; i < numberScore.length; i++) {
			score += (i * numberScore[i]);
		}
		System.out.println(score);
	}

	private static void printMap(String s) {
		System.out.println("ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ " + s + " ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ");
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
	}

	private static void changeMap(int sy, int sx) {
		Queue<Integer> newMap = new LinkedList<>();
		Queue<Node> beads = new LinkedList<Node>();
		int move = 0, d = 0, preNum = 0;
		boolean out = false;
		a: while (!out) {
			move++;
			for (int i = 0; i < 2; i++) {
				for (int j = 0; j < move; j++) {
					int iy = sy + dy[blizardDirection[d]];
					int ix = sx + dx[blizardDirection[d]];
					if (iy >= 0 && iy < N && ix >= 0 && ix < N) {
						sy = iy;
						sx = ix;
						if (map[sy][sx] > 0) { // 구슬이 있는 칸일때
							if (beads.size() == 0) {// 구슬비교할게 없으면 (처음시작할때)
								preNum = map[sy][sx];
								beads.add(new Node(sy, sx));
							} else { // 앞에 구슬이랑 번호 같을때
								if (preNum == map[sy][sx]) {
									beads.add(new Node(sy, sx));
								} else { // 앞에 구슬이랑 번호 다를때
									newMap.add(beads.size());
									newMap.add(preNum);

									beads.clear();
									preNum = map[sy][sx];
									beads.add(new Node(sy, sx));
								}
							}
						} else {
							if (beads.size() > 0) {
								newMap.add(beads.size());
								newMap.add(preNum);
								beads.clear();
							}
						}
					} else {
						out = true;
						break a;
					}
				}
				d = (d + 1) % 4;
			}
		}

		map = new int[N][N];
		move = 0;
		d = 0;
		out = false;
		sy = N / 2;
		sx = N / 2;
		// 새로운 정보 넣어주기
		a: while (!out) {
			move++;
			for (int i = 0; i < 2; i++) {
				for (int j = 0; j < move; j++) {
					int iy = sy + dy[blizardDirection[d]];
					int ix = sx + dx[blizardDirection[d]];
					if (iy >= 0 && iy < N && ix >= 0 && ix < N) {
						sy = iy;
						sx = ix;
						if (!newMap.isEmpty())
							map[sy][sx] = newMap.poll();
					} else {
						out = true;
						break a;
					}
				}
				d = (d + 1) % 4;
			}
		}
	}

	private static boolean bomb(int sy, int sx) {
		Queue<Node> beads = new LinkedList<Node>();
		boolean isBomb = false;
		int move = 0, d = 0, preNum = 0;
		boolean out = false;
		a: while (!out) {
			move++;
			for (int i = 0; i < 2; i++) {
				for (int j = 0; j < move; j++) {
					int iy = sy + dy[blizardDirection[d]];
					int ix = sx + dx[blizardDirection[d]];
					if (iy >= 0 && iy < N && ix >= 0 && ix < N) {
						sy = iy;
						sx = ix;
						if (map[sy][sx] > 0) { // 구슬이 있는 칸일때
							if (beads.size() == 0) {// 구슬비교할게 없으면
								preNum = map[sy][sx];
								beads.add(new Node(sy, sx));
							} else { // 앞에 구슬이랑 번호 같을때
								if (preNum == map[sy][sx]) {
									beads.add(new Node(sy, sx));
								} else { // 앞에 구슬이랑 번호 다를때
									if (beads.size() >= 4) { // 같은 구슬 4개이상인 그룹 존재할때
										numberScore[preNum] += beads.size();
										while (!beads.isEmpty()) {
											Node node = beads.poll();
											map[node.y][node.x] = 0;
										}
										isBomb = true;
									}
									beads.clear();
									preNum = map[sy][sx];
									beads.add(new Node(sy, sx));
								}
							}
						}else {
							if (beads.size() >= 4) { // 같은 구슬 4개이상인 그룹 존재할때
								numberScore[preNum] += beads.size();
								while (!beads.isEmpty()) {
									Node node = beads.poll();
									map[node.y][node.x] = 0;
								}
								isBomb = true;
							}
						}
					} else {
						out = true;
						break a;
					}
				}
				d = (d + 1) % 4;
			}
		}
		return isBomb;
	}

	// 빈공간 채우기
	private static void fillEmptyspace(int sy, int sx) {
		Queue<Node> zero = new LinkedList<Node>();
		int move = 0, d = 0;
		boolean out = false;
		a: while (!out) {
			move++;
			for (int i = 0; i < 2; i++) {
				for (int j = 0; j < move; j++) {
					int iy = sy + dy[blizardDirection[d]];
					int ix = sx + dx[blizardDirection[d]];
					if (iy >= 0 && iy < N && ix >= 0 && ix < N) {
						sy = iy;
						sx = ix;
						if (map[sy][sx] == 0) {
							zero.add(new Node(sy, sx));
						} else if (map[sy][sx] != 0 && zero.size() > 0) {
							Node z = zero.poll();
							map[z.y][z.x] = map[sy][sx];
							map[sy][sx] = 0;
							zero.add(new Node(sy, sx));
						}
					} else {
						out = true;
						break a;
					}
					// printMap("빈공간을 채워보자아아아!!");
				}
				d = (d + 1) % 4;
			}
		}
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
