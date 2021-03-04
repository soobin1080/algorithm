
public class Combination {
	static int arr[] = { 1, 2, 3, 4, 5, 6 };
	static boolean used[] = new boolean[arr.length];

	public static void main(String[] args) {
		int size = arr.length;
		combi(size, 0, 0, 3);
	}

	private static void combi(int size, int index, int r, int n) {
		if (r == n) {
			/*String s="";
			for (int i = 0; i < size; i++) {
				if(used[i])
					s+=arr[i]+" ";
			}
			System.out.println(s);*/
			return;
		}

		for (int i = index; i < size; i++) {
			used[i] = true;
			combi(size, i + 1, r + 1, n);
			used[i] = false;
		}
	}

}
