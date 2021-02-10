package kakao기출;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

public class pg_메뉴리뉴얼 {
	static String[] orders = { "XYZ", "XWY", "WXA" };
	static int[] course = { 2, 3, 4 };
	static TreeMap<String, Integer> map = new TreeMap<>();

	public static void main(String[] args) {

		// 주문 하나에 코스에 들어있는 수만큼 다 조합 해본다.
		for (int i = 0; i < orders.length; i++) {
			char[] c = orders[i].toCharArray();
			boolean[] used = new boolean[c.length];

			for (int j = 0; j < course.length; j++) {
				if (course[j] <= c.length) {
					Arrays.sort(c);
					combi(c, used, 0, c.length, course[j]);
				}
			}
		}

		//treemap에 있는 조합 중에 가장 많이 나온 순으로 내림차순
		List<Map.Entry<String, Integer>> list = new LinkedList<>(map.entrySet());

		Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {

			@Override
			public int compare(Entry<String, Integer> o1, Entry<String, Integer> o2) {
				return o2.getValue() - o1.getValue();
			}
		});

		//가장 많이 나온 추천 메뉴 저장하기
		ArrayList<String> arr = new ArrayList<>();

		for (int i = 0; i < course.length; i++) {
			int max = 0;
			for (int j = 0; j < list.size(); j++) {
				if (course[i] == list.get(j).getKey().length()) {
					if (max <= list.get(j).getValue() && list.get(j).getValue()>1) {
						max = list.get(j).getValue();
						arr.add(list.get(j).getKey());
					}
				}
			}
		}

		//정답 출력하는 배열
		Collections.sort(arr);
		String answer[] = new String[arr.size()];
		for (int i = 0; i < arr.size(); i++) {
			answer[i] = arr.get(i);
		}


	}

	static void combi(char[] arr, boolean[] used, int start, int n, int r) {
		if (r == 0) {
			String s = "";
			for (int i = 0; i < used.length; i++) {
				if (used[i]) {
					s += arr[i];
				}
			}

			if (map.get(s) != null) {
				int count = map.get(s) + 1;
				map.put(s, count);
			} else {
				map.put(s, 1);
			}
			return;
		}
		for (int i = start; i < n; i++) {
			used[i] = true;
			combi(arr, used, i + 1, n, r - 1);
			used[i] = false;
		}
	}

}
