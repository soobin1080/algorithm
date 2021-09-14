import java.util.Scanner;

public class sw_11545_틱택톰2 {

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

			int count = 0;
			char bingo;
			// 가로
			for (int i = 0; i < map.length; i++) {
				char pCh = map[i][0];
				if (pCh != '.')
				for (int j = 0; j < map.length; j++) {
					if (map[i][j] == pCh)
						count++;
					else
						break;
				}
				if (count == 4) {
					bingo = pCh;
					break;
				}
			}

			// 세로
			if (count != 4) {
				count = 0;
				for (int i = 0; i < map.length; i++) {
					char pCh = map[0][i];
					if (pCh != '.')
						for (int j = 0; j < map.length; j++) {
							if (map[j][i] == pCh || map[j][i] == 'T')
								count++;
							else
								break;
						}
					if (count == 4) {
						bingo = pCh;
						break;
					}
				}
			}
			// 대각선
			if (count != 4) {
				count = 0;

			}
		}
	}
}
