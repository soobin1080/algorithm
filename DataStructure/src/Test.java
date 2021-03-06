import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

public class Test {

	public static void main(String[] args) {
		Set<Integer> set = new HashSet<>();
		set.add(2);
		Iterator<Integer> it = set.iterator();
		while (it.hasNext())
			System.out.println(it.next());
		Map<String, Integer> map = new HashMap<>();
		map.put("1", 5);
		map.put("2", 4);
		for (String s : map.keySet()) {
			System.out.println(map.get(s));
		}
		Stack<Integer> stack = new Stack<>();
		stack.add(3);
		System.out.println(stack.pop());
		List<Integer> list = new LinkedList<Integer>();
		list.add(12);
		list.add(20);
		Collections.sort(list, new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				return o2 - o1;
			}
		});
		System.out.println(list);

	}

}
