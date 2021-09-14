package 삼성역량기출;

import java.util.Scanner;

public class bj_21608_상어초등학교 {
	static int map[][];
	static int people[][];
	static int dy[] = { -1, 1, 0, 0 };
	static int dx[] = { 0, 0, -1, 1 };
	static int score[] = { 0, 1, 10, 100, 1000 };

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int countOfPeople = N * N;
		people = new int[countOfPeople + 1][4];
		map = new int[N][N];

		for (int i = 0; i < countOfPeople; i++) {
			int num = sc.nextInt();
			for (int j = 0; j < 4; j++) {
				people[num][j] = sc.nextInt();
			}

			int r = -1, c = -1, countOfLike = 0, countOfEmpty = 0;
			// 자리에 앉히기
			for (int j = 0; j < N; j++) {
				for (int k = 0; k < N; k++) {
					if (map[j][k] == 0) {
						// 상하좌우 좋아하는 사람 수, 빈자리 수
						int like = 0, empty = 0;
						for (int index = 0; index < 4; index++) {
							int y = j + dy[index];
							int x = k + dx[index];
							if (y >= 0 && y < N && x >= 0 && x < N) {
								// 좋아하는 사람 있는지 찾기
								if (map[y][x] == 0) {// 빈자리면 빈자리 카운트
									empty++;
								} else {
									for (int l = 0; l < 4; l++) {
										if (map[y][x] == people[num][l]) {
											like++;
										}
									}
								}
							}
						}
						//System.out.println("like수:" + like + ", empty수:" + empty);
						//System.out.println("countOfLike:" + countOfLike + ", countOfEmpty:" + countOfEmpty);
						// 1. 좋아하는 사람이 가장 많은 칸으로 좌표 바꾸기
						if (countOfLike < like) {
							countOfLike = like;
							countOfEmpty = empty;
							r = j;
							c = k;
						}
						// 2. 1만족 여러칸일 경우 가장 빈칸 많은 칸
						else if (countOfLike == like && countOfEmpty < empty) {
							countOfEmpty = empty;
							r = j;
							c = k;
						}
						// 3. 2만족 여러칸일 경우 행번호 작은 순 , 열번호 작은 순
						
						if(r==-1 && c==-1) {
							r=j; c=k;
						}
					}
					
				}
			}
			System.out.println("num:" + num + ", r:" + r + ", c:" + c);

			map[r][c] = num;
			for (int j = 0; j < N; j++) {
				for (int j2 = 0; j2 < N; j2++) {
					System.out.print(map[j][j2]);
				}
				System.out.println();
			}
			System.out.println("ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ");
		}

		for (int j = 0; j < N; j++) {
			for (int j2 = 0; j2 < N; j2++) {
				System.out.print(map[j][j2]);
			}
			System.out.println();
		}
		System.out.println("ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ");

		// 만족도 계산하기
		int answer = 0;
		for (int j = 0; j < N; j++) {
			for (int k = 0; k < N; k++) {
				// 상하좌우 좋아하는 사람 수 세기
				int count = 0;
				for (int index = 0; index < 4; index++) {
					int y = j + dy[index];
					int x = k + dx[index];
					if (y >= 0 && y < N && x >= 0 && x < N) {
						// 좋아하는 사람 있는지 찾기
						for (int l = 0; l < 4; l++) {
							if (map[y][x] == people[map[j][k]][l]) {
								count++;
								break;
							}
						}
					}
				}
				answer += score[count];
			}
		}
		System.out.println(answer);
	}

}
