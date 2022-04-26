import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class bj_15665_N과M11 {
	static int N, M;
	static ArrayList<Integer> list;
	static int[] answer;
	static String string="";

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st= new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		answer = new int[M];
		list = new ArrayList<>();

		st= new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			int number = Integer.parseInt(st.nextToken());
			if (!list.contains(number)) {
				list.add(number);
			}
		}

		Collections.sort(list);

		perm(N, M, 0);

	}

	private static void perm(int n, int r, int i) {
		if (r == i) {
			for (int j = 0; j < answer.length; j++) {
				string+=(answer[j] + " ");
			}
			System.out.println(string);
			string="";
			return;
		}

		for (int j = 0; j < list.size(); j++) {
			answer[i] = list.get(j);
			perm(n, r, i + 1);
		}
	}

}
