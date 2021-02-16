import java.util.Scanner;

public class sw_6808_규영이와인영이의카드게임 {

	static int win, lose;

	static int[] card1;
	static int[] card2;
	static boolean[] used;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);

		int TC = sc.nextInt();

		for (int tc = 1; tc <= TC; tc++) {

			card1 = new int[9];
			card2 = new int[9];
			used = new boolean[9];

			int[] cardset = new int[19];

			for (int i = 0; i < card1.length; i++) {
				card1[i] = sc.nextInt();
				cardset[card1[i]] = 1;
			}

			int idx = 0;
			for (int i = 1; i < cardset.length; i++) {
				if (cardset[i] == 0)
					card2[idx++] = i;
			}



			win = 0;
			lose = 0;
			int[] card = new int[9];
			perm(card, 0);

			System.out.println("#" + tc + " " + win + " " + lose);

		}
		sc.close();
	}

	private static void perm(int[] card, int idx) {
		if (idx == 9) {
			// 점수 계산
			play(card1, card);
			return;
		}

		for (int i = 0; i < card2.length; i++) {
			if (used[i])
				continue;
			used[i] = true;
			card[idx] = card2[i];
			perm(card, idx + 1);
			used[i] = false;
		}

	}

	private static void play(int[] card1, int[] card2) {
		int score1 = 0, score2 = 0;

		for (int i = 0; i < card1.length; i++) {

			if (card1[i] > card2[i]) {
				score1 += (card1[i] + card2[i]);
			} else
				score2 += (card1[i] + card2[i]);
		}

		if (score1 > score2) {
			win++;
		}
		if (score1 < score2) {
			lose++;
		}
	}
}
