import java.util.LinkedList;
import java.util.List;

public class Permutation {

	static String[] arr = { "1", "2", "3", "4", "5" };
	static boolean[] used = new boolean[arr.length];
	static List<String> result = new LinkedList<>();

	public static void main(String[] args) {
		perm(0, 3);
		System.out.println(total + "개");
	}

	static int total = 0;

	static void perm(int cnt, int r) { // cnt 현재 뽑은 갯수, r 총 뽑아야 하는 카드수

		if (cnt == r) {
			total++;
			System.out.println(result);
			return;
		}
		for (int i = 0; i < arr.length; i++) {
			if (!used[i]) { // i번 카드가 사용 가능 상태이면(앞의 재귀들이 안쓴 상태이면)
				result.add(arr[i]); // i번 카드를 내가 사용할게
				used[i] = true;// 다음 재귀들은 쓰지마!
				perm(cnt + 1, r); // 다음 재귀한테 다음거 뽑아봐
				used[i] = false; // i 넣고 그 밑에 재귀들이 경우의 수 다 봤다네? 그럼 이제 다음거도 써봐야지
				result.remove(arr[i]);
			}
		}
	}
}
