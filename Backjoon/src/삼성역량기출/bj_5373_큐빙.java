package 삼성역량기출;

import java.util.Scanner;

public class bj_5373_큐빙 {
	static char[] color = { 'w', 'y', 'r', 'o', 'g', 'b' };
	static char[][] U, D, F, B, L, R;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int TC = sc.nextInt();
		for (int tc = 0; tc < TC; tc++) {
			int n = sc.nextInt();

			U = new char[3][3];
			D = new char[3][3];
			F = new char[3][3];
			B = new char[3][3];
			L = new char[3][3];
			R = new char[3][3];

			fillColor(U, 0);
			fillColor(D, 1);
			fillColor(F, 2);
			fillColor(B, 3);
			fillColor(L, 4);
			fillColor(R, 5);

			for (int i = 0; i < n; i++) {
				String s = sc.next();
				rotation(s.charAt(0), s.charAt(1));
			}

			for (int i = 0; i < 3; i++) {
				for (int j = 0; j < 3; j++) {
					System.out.print(U[i][j]);
				}
				System.out.println();
			}
		}
		sc.close();
	}

	private static void rotation(char cube, char dir) {
		char[][] copy = new char[3][3];
		switch (cube) {
		case 'U':
			if (dir == '+') {
				// F->R->B->L
				char[] f = { L[0][0], L[0][1], L[0][2] };
				for (int i = 0; i < 3; i++) {
					L[0][i] = B[0][i];
				}
				for (int i = 0; i < 3; i++) {
					B[0][i] = R[0][i];
				}
				for (int i = 0; i < 3; i++) {
					R[0][i] = F[0][i];
				}
				for (int i = 0; i < 3; i++) {
					F[0][i] = f[i];
				}
			} else {
				// F->L->B->R
				char[] f = { R[0][0], R[0][1], R[0][2] };
				for (int i = 0; i < 3; i++) {
					R[0][i] = B[0][i];
				}
				for (int i = 0; i < 3; i++) {
					B[0][i] = L[0][i];
				}
				for (int i = 0; i < 3; i++) {
					L[0][i] = F[0][i];
				}
				for (int i = 0; i < 3; i++) {
					F[0][i] = f[i];
				}
			}
			move(U, copy);
			break;
		case 'D':
			if (dir == '+') {
				// F->L->B->R
				char[] f = { R[2][0], R[2][1], R[2][2] };
				for (int i = 0; i < 3; i++) {
					R[2][i] = B[2][i];
				}
				for (int i = 0; i < 3; i++) {
					B[2][i] = L[2][i];
				}
				for (int i = 0; i < 3; i++) {
					L[2][i] = F[2][i];
				}
				for (int i = 0; i < 3; i++) {
					F[2][i] = f[i];
				}
			} else {
				// F->R->B->L
				char[] f = { L[2][0], L[2][1], L[2][2] };
				for (int i = 0; i < 3; i++) {
					L[2][i] = B[2][i];
				}
				for (int i = 0; i < 3; i++) {
					B[2][i] = R[2][i];
				}
				for (int i = 0; i < 3; i++) {
					R[2][i] = F[2][i];
				}
				for (int i = 0; i < 3; i++) {
					F[2][i] = f[i];
				}
			}
			move(D, copy);
			break;
		case 'F':
			if (dir == '+') {
				// U->R->D->L
				char[] f = { L[0][2], L[1][2], L[2][2] };
				for (int i = 0; i < 3; i++) {
					L[i][2] = D[0][i];
				}
				for (int i = 0; i < 3; i++) {
					D[0][i] = R[2 - i][0];
				}
				for (int i = 0; i < 3; i++) {
					R[i][0] = U[2][i];
				}
				for (int i = 0; i < 3; i++) {
					U[2][2 - i] = f[i];
				}
			} else {
				// U->L->D->R
				char[] f = { R[0][0], R[1][0], R[2][0] };
				for (int i = 0; i < 3; i++) {
					R[2 - i][0] = D[0][i];
				}
				for (int i = 0; i < 3; i++) {
					D[0][i] = L[i][2];
				}
				for (int i = 0; i < 3; i++) {
					L[i][2] = U[2][2 - i];
				}
				for (int i = 0; i < 3; i++) {
					U[2][i] = f[i];
				}
			}
			move(F, copy);
			break;
		case 'B':
			if (dir == '+') {
				// U->L->D->R
				char[] f = { R[0][2], R[1][2], R[2][2] };
				for (int i = 0; i < 3; i++) {
					R[2 - i][2] = D[2][i];
				}
				for (int i = 0; i < 3; i++) {
					D[2][i] = L[i][0];
				}
				for (int i = 0; i < 3; i++) {
					L[i][0] = U[0][2 - i];
				}
				for (int i = 0; i < 3; i++) {
					U[0][i] = f[i];
				}
			} else {
				// U->R->D->L
				char[] f = { L[0][0], L[1][0], L[2][0] };
				for (int i = 0; i < 3; i++) {
					L[i][0] = D[2][i];
				}
				for (int i = 0; i < 3; i++) {
					D[2][i] = R[2 - i][2];
				}
				for (int i = 0; i < 3; i++) {
					R[i][2] = U[0][i];
				}
				for (int i = 0; i < 3; i++) {
					U[0][2 - i] = f[i];
				}
			}
			move(B, copy);
			break;
		case 'L':
			if (dir == '+') {
				// U->F->D->B
				char[] f = { B[0][2], B[1][2], B[2][2] };
				for (int i = 0; i < 3; i++) {
					B[2 - i][2] = D[i][0];
				}
				for (int i = 0; i < 3; i++) {
					D[i][0] = F[i][0];
				}
				for (int i = 0; i < 3; i++) {
					F[i][0] = U[i][0];
				}
				for (int i = 0; i < 3; i++) {
					U[2 - i][0] = f[i];
				}
			} else {
				// U->B->D->F
				char[] f = { F[0][0], F[1][0], F[2][0] };
				for (int i = 0; i < 3; i++) {
					F[i][0] = D[i][0];
				}
				for (int i = 0; i < 3; i++) {
					D[2 - i][0] = B[i][2];
				}
				for (int i = 0; i < 3; i++) {
					B[2 - i][2] = U[i][0];
				}
				for (int i = 0; i < 3; i++) {
					U[i][0] = f[i];
				}
			}
			move(L, copy);
			break;
		case 'R':
			if (dir == '+') {
				// U->B->D->F
				char[] f = { F[0][2], F[1][2], F[2][2] };
				for (int i = 0; i < 3; i++) {
					F[i][2] = D[i][2];
				}
				for (int i = 0; i < 3; i++) {
					D[2 - i][2] = B[i][0];
				}
				for (int i = 0; i < 3; i++) {
					B[2 - i][0] = U[i][2];
				}
				for (int i = 0; i < 3; i++) {
					U[i][2] = f[i];
				}
			} else {
				// U->F->D->B
				char[] f = { B[0][0], B[1][0], B[2][0] };
				for (int i = 0; i < 3; i++) {
					B[2 - i][0] = D[i][2];
				}
				for (int i = 0; i < 3; i++) {
					D[i][2] = F[i][2];
				}
				for (int i = 0; i < 3; i++) {
					F[i][2] = U[i][2];
				}
				for (int i = 0; i < 3; i++) {
					U[2 - i][2] = f[i];
				}
			}
			move(R, copy);
			break;
		}
	}

	private static void move(char[][] origin, char[][] copy) {
		for (int i = 0; i < copy.length; i++) {
			for (int j = 0; j < copy.length; j++) {
				copy[i][j] = origin[i][j];
			}
		}

		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				origin[i][j] = copy[j][2 - i];
			}
		}
	}

	private static void fillColor(char[][] cube, int c) {
		for (int i = 0; i < cube.length; i++) {
			for (int j = 0; j < cube.length; j++) {
				cube[i][j] = color[c];
			}
		}
	}
}
