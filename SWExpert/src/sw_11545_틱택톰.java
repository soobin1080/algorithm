import java.util.Scanner;

public class sw_11545_틱택톰 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int TC = sc.nextInt();

		for (int tc = 1; tc <= TC; tc++) {

			char map[][] = new char[4][4];

			for (int i = 0; i < map.length; i++) {
				map[i] = sc.next().toCharArray();
			}

			boolean finish = true;
			a: for (int i = 0; i < map.length; i++) {
				for (int j = 0; j < map.length; j++) {
					if (map[i][j] == '.') {
						finish = false;
						break a;
					}
				}
			}

			char pCh = '.';
			boolean bingo = false;

			a: for (int i = 0; i < map.length; i++) {
				for (int j = 0; j < map.length; j++) {
					// o || x
					if (map[i][j] == 'X' || map[i][j] == 'O') {
						pCh = map[i][j];
						// 가로
						if (!bingo) {
							for (int index = 0; index < 4; index++) {
								if (pCh == map[i][index] || 'T' == map[i][index])
									bingo = true;
								else {
									bingo = false;
									break;
								}
							}
						} else
							break a;
						if (!bingo) {
							// 세로
							for (int index = 0; index < 4; index++) {
								if (pCh == map[index][j] || 'T' == map[index][j])
									bingo = true;
								else {
									bingo = false;
									break;
								}
							}
						} else
							break a;
					}
				}
			}
			// 대각선
			if (!bingo) {
				if (map[0][0] != '.') {
					pCh = map[0][0];
					if (pCh == 'T' && map[1][1] != '.')
						pCh = map[1][1];
					for (int i = 1; i < 4; i++) {
						if (pCh == map[i][i] || map[i][i] == 'T')
							bingo = true;
						else {
							bingo = false;
							break;
						}
					}
				}
			}
			if (!bingo) {
				if (map[0][3] != '.') {
					pCh = map[0][3];
					if (pCh == 'T' && map[1][2] != '.')
						pCh = map[1][2];
					for (int i = 1; i < 4; i++) {
						if (pCh == map[0 + i][3 - i] || map[0 + i][3 - i] == 'T')
							bingo = true;
						else {
							bingo = false;
							break;
						}
					}
				}
			}
			if (bingo) {
				if (pCh == 'X')
					System.out.println("#" + tc + " X won");
				else
					System.out.println("#" + tc + " O won");
			} else {
				if (finish)
					System.out.println("#" + tc + " Draw");

				if (!finish)
					System.out.println("#" + tc + " Game has not completed");
			}
		}
	}
}
