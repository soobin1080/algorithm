package 시뮬레이션;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class bj_1966_프린터큐 {

	static Queue<Point> qu;
	static LinkedList<Integer> importanceList;

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		int TC = sc.nextInt();

		for (int tc = 0; tc < TC; tc++) {
			qu = new LinkedList<Point>();
			importanceList = new LinkedList<Integer>();
			// 입력
			int N = sc.nextInt();
			int M = sc.nextInt();
			for (int i = 0; i < N; i++) {
				int imp = sc.nextInt();
				qu.add(new Point(i, imp));
				importanceList.add(imp);
			}

			Collections.sort(importanceList, new Comparator<Integer>() {

				@Override
				public int compare(Integer o1, Integer o2) {
					// TODO Auto-generated method stub
					return o2 - o1;
				}
			});
			int count = 0;
			while (!qu.isEmpty()) {

				// 현재 Queue의 가장 앞에 있는 문서의 ‘중요도’를 확인
				int nowImp = qu.peek().importance;
				int maxImp = importanceList.peek();
				// 나머지 중에 중요도가 높은 문서 하나라도 있으면 문서 인쇄하지 않고 queue의 가장 뒤에 재배치.
				Point p = qu.poll();
				if (nowImp < maxImp) {
					qu.add(new Point(p.order, p.importance));
				} else {
					// 그렇지 않으면 인쇄!
					count++;
					importanceList.poll();
					if (p.order == M)
						System.out.println(count);
				}
			}
		}

	}

	static class Point {
		int order;
		int importance;

		Point(int order, int importance) {
			this.order = order;
			this.importance = importance;
		}
	}

}
