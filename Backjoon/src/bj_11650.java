import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class bj_11650 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc=new Scanner(System.in);
		
		int n=sc.nextInt();
		
		ArrayList<Node> list=new ArrayList<>();
		for (int i = 0; i < n; i++) {
			list.add(new Node(sc.nextInt(), sc.nextInt()));
		}
		
		Collections.sort(list);
		
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i).x+" "+list.get(i).y);
		}
		sc.close();
	}
	
	static class Node implements Comparable<Node>{
		int x;
		int y;
		Node(int x,int y){
			this.x=x;
			this.y=y;
		}
		@Override
		public int compareTo(Node o) {
			// TODO Auto-generated method stub
			if(this.x== o.x) {
				return this.y-o.y;
			}else
				return this.x-o.x;
		}		
	}
	
	

}
