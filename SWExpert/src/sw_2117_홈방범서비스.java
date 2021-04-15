import java.util.Scanner;

public class sw_2117_홈방범서비스 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int TC = sc.nextInt();

		for (int tc = 1; tc <= TC; tc++) {

			int N = sc.nextInt();
			int M = sc.nextInt();
			int MAX = 0;
			int map[][] = new int[N][N];

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					map[i][j] = sc.nextInt();
				}
			}

			for (int k = 1; k <= N+2; k++) {
				int operatingCost = k * k + (k - 1) * (k - 1);
				for (int i = 0; i < N; i++) {
					for (int j = 0; j < N; j++) {
						int houseCount = 0;
						// 마름모 만들기
						int lengthX = -1;
						boolean flag = false;
						for (int rhombusY = i - (k - 1); rhombusY <= i + (k - 1); rhombusY++) {
							if (lengthX == k - 1)
								flag = true;
							if (flag)
								lengthX--;
							else
								lengthX++;
							for (int rhombusX = j - lengthX; rhombusX <= j + lengthX; rhombusX++) {
								if (rhombusY >= 0 && rhombusY < N && rhombusX >= 0 && rhombusX < N
										&& map[rhombusY][rhombusX] == 1)
									houseCount++;
							}
						}

						int profit = houseCount * M - operatingCost;
						if (profit >= 0 && houseCount > MAX)
							MAX = houseCount;
					}
				}
			}

			System.out.println("#" + tc + " " + MAX);
		}

		sc.close();
	}

}
