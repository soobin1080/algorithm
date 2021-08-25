import java.util.LinkedList;
import java.util.StringTokenizer;

public class solution4 {

	public static void main(String[] args) {
		String[] cmd = { "D 2", "C", "U 3", "C", "D 4", "C", "U 2", "Z", "Z" };
		LinkedList<Integer> list = new LinkedList<>();
		LinkedList<Row> removelList = new LinkedList<>();
		int n = 8;
		for (int i = 0; i < n; i++) {
			list.add(i);
		}
		int cursor = 2; // cursor=k;

		for (int i = 0; i < cmd.length; i++) {
			StringTokenizer st = new StringTokenizer(cmd[i]);
			String c = st.nextToken();
			if (c.equals("D")) {
				int move = Integer.parseInt(st.nextToken());
				cursor += move;
			} else if (c.equals("U")) {
				int move = Integer.parseInt(st.nextToken());
				cursor -= move;
			} else if (c.equals("C")) {
				removelList.addFirst(new Row(cursor, list.get(cursor)));
				if (cursor == list.size() - 1) {
                    list.remove(cursor);
                    cursor = list.size() - 1;
                } else
                    list.remove(cursor);
			} else if (c.equals("Z")) {
				Row z = removelList.getFirst();
				list.add(z.index, z.number);
                if(z.index<=cursor)
                    cursor+=1;
                removelList.removeFirst();
			}
		}
		// 비교
		char[] ch = new char[n];
		for (int i = 0; i < list.size(); i++) {
			ch[list.get(i)] = 'O';
		}
		String answer = "";
		for (int i = 0; i < n; i++) {
			if (ch[i] == 'O')
				answer += ch[i];
			else
				answer += 'X';
		}
		System.out.println(answer);
	}

	static class Row {
		int index;// 들어갈 곳 인덱스
		int number;// 원래 위치

		Row(int index, int number) {
			this.index = index;
			this.number = number;
		}
	}
}
