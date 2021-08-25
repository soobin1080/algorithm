import java.util.LinkedList;
import java.util.StringTokenizer;

public class solution3 {

	public static void main(String[] args) {
		String[] cmd = { "D 500", "C", "U 3", "C", "D 4", "C", "U 2", "Z", "Z" };
		LinkedList<Integer> removelList = new LinkedList<>();
		int n = 502;
		boolean[] number = new boolean[n];
		int cursor = 2; // cursor=k;
		int end = number.length;
		for (int i = 0; i < number.length; i++) {
			number[i] = true;
		}

		for (int i = 0; i < cmd.length; i++) {
			StringTokenizer st = new StringTokenizer(cmd[i]);
			String c = st.nextToken();
			if (c.equals("D")) {
				int move = Integer.parseInt(st.nextToken());
				System.out.println(move + "아래로 움직임");
				int m = 0;
				while (m != move) {
					cursor++;
					if (number[cursor]) {
						m++;
					}
				}
			} else if (c.equals("U")) {
				int move = Integer.parseInt(st.nextToken());
				System.out.println(move + "위로 움직임");
				int m = 0;
				while (m != move) {
					cursor--;
					if (number[cursor]) {
						m++;
					}
				}
			} else if (c.equals("C")) {
				System.out.println(cursor + "번째 삭제");
				removelList.add(cursor);
				number[cursor] = false;
				if (cursor == end) {
					int m = 0;
					while (m != 1) {
						cursor--;
						if (number[cursor]) {
							m++;
						}
					}
					end = cursor;
				} else {
					int m = 0;
					while (m != 1) {
						cursor++;
						if (number[cursor]) {
							m++;
						}
					}
				}
			} else if (c.equals("Z")) {
				int index = removelList.getLast();
				removelList.removeLast();
				number[index] = true;
				if (index > end)
					end = index;
				System.out.println(index + "번째 복구");
			}
		}

		// 비교
		String answer = "";
		for (int i = 0; i < n; i++) {
			if (number[i])
				answer += "O";
			else
				answer += "X";
		}
		System.out.println(answer);
	}

}
