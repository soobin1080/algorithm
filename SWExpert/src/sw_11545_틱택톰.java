import java.util.Scanner;

public class sw_11545_틱택톰 {
	static boolean X, O, finish;

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		int TC = sc.nextInt();
		for (int tc = 1; tc <= TC; tc++) {
			X = false;
			O = false;
			finish = true;

			char map[][] = new char[4][4];

			// 입력받기
			for (int i = 0; i < 4; i++) {
				String st = sc.next();
				map[i] = st.toCharArray();
				if (st.contains("."))
					finish = false;
			}

			// 가로검사 - x,o 일때 t일때 .일때
			searchHori(map);
			// 세로검사 - x,o 일때 t일때 .일때
			searchVerti(map);
			// 대각선 검사 - x,o 일때 t일때 .일때
			searchDiagonal(map);

			if (finish) {
				if (X && O)
					System.out.println("#" + tc + " Draw");
				else if (!X && O)
					System.out.println("#" + tc + " O won");
				else if (X && !O)
					System.out.println("#" + tc + " X won");
				else if (!X && !O)
					System.out.println("#" + tc + " Draw");
			} else {
				System.out.println("#" + tc + " Game has not completed");
			}
		}
	}

	private static void searchDiagonal(char[][] map) {
		// 비교기준이 될 문자
		char ch = map[0][0];
		// ch가 x,o 일때
		if (ch == 'X' || ch == 'O')
			dFindXO(map, ch, 1);
		// ch가 t 일때
		else if (ch == 'T')
			dFindT(map, ch, 1);

		// 비교기준이 될 문자
		ch = map[0][3];
		// ch가 x,o 일때
		if (ch == 'X' || ch == 'O')
			dFindXO(map, ch, 2);
		// ch가 t 일때
		else if (ch == 'T')
			dFindT(map, ch, 2);
	}

	private static void dFindT(char[][] map, char ch, int dir) {
		ch = 'X';
		dFindXO(map, ch, dir);
		ch = 'O';
		dFindXO(map, ch, dir);
	}

	private static void dFindXO(char[][] map, char ch, int dir) {
		int count = 0;
		// 왼쪽위에서 오른쪽 아래로
		if (dir == 1) {
			for (int i = 0; i < map.length; i++) {
				if (map[i][i] == ch || map[i][i] == 'T') {
					count++;
				}
			}
			if (count == 4) {
				if (ch == 'X')
					X = true;
				else if (ch == 'O')
					O = true;
				finish = true;
			}
		} else {
			// 오른쪽 위에서 왼쪽 아래로
			for (int i = 0; i < map.length; i++) {
				if (map[i][3 - i] == ch || map[i][3 - i] == 'T') {
					count++;
				}
			}
			if (count == 4) {
				if (ch == 'X')
					X = true;
				else if (ch == 'O')
					O = true;
				finish = true;
			}
		}
	}

	private static void searchVerti(char[][] map) {
		for (int i = 0; i < map.length; i++) {
			// 비교기준이 될 문자
			char ch = map[0][i];
			// ch가 x,o 일때
			if (ch == 'X' || ch == 'O')
				vFindXO(map, ch, i);
			// ch가 t 일때
			else if (ch == 'T')
				vFindT(map, ch, i);
		}
	}

	private static void vFindT(char[][] map, char ch, int col) {
		ch = 'X';
		vFindXO(map, ch, col);
		ch = 'O';
		vFindXO(map, ch, col);
	}

	private static void vFindXO(char[][] map, char ch, int col) {
		int count = 0;
		for (int i = 0; i < map.length; i++) {
			if (map[i][col] == ch || map[i][col] == 'T') {
				count++;
			}
		}
		if (count == 4) {
			if (ch == 'X')
				X = true;
			else if (ch == 'O')
				O = true;
			finish = true;
		}
	}

	private static void searchHori(char[][] map) {
		for (int i = 0; i < map.length; i++) {
			// 비교기준이 될 문자
			char ch = map[i][0];
			// ch가 x,o 일때
			if (ch == 'X' || ch == 'O')
				hFindXO(map, ch, i);
			// ch가 t 일때
			else if (ch == 'T')
				hFindT(map, ch, i);
		}
	}

	private static void hFindT(char[][] map, char ch, int row) {
		ch = 'X';
		hFindXO(map, ch, row);
		ch = 'O';
		hFindXO(map, ch, row);
	}

	private static void hFindXO(char[][] map, char ch, int row) {
		int count = 0;
		for (int i = 0; i < map.length; i++) {
			if (map[row][i] == ch || map[row][i] == 'T') {
				count++;
			}
		}
		if (count == 4) {
			if (ch == 'X')
				X = true;
			else if (ch == 'O')
				O = true;
			finish = true;
		}
	}
}
