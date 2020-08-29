package codingtest_0828;

public class solution3 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[][] office = { { 1, 0, 0, 0 }, { 0, 0, 0, 1 }, { 0, 0, 1, 0 }, { 0, 1, 1, 0 } };
		int k = 2;
		int answer = 0;

		for (int i = 0; i < office.length; i++) {
			for (int j = 0; j < office.length; j++) {

				int person = 0;

				for (int l = i; l < k + i; l++) {
					for (int l2 = j; l2 < k + j; l2++) {
						if (l < office.length && l2 < office.length) {
							if (office[l][l2] == 1) {
								person++;
							}
						}
					}
				}

				answer = (answer < person) ? person : answer;

			}
		}
		System.out.println(answer);

	}

}
