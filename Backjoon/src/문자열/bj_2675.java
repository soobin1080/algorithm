package 문자열;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class bj_2675 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N=Integer.parseInt(br.readLine());
		
		for (int i = 0; i < N; i++) {
			StringTokenizer st=new StringTokenizer(br.readLine());
			int n=Integer.parseInt(st.nextToken());
			char[] arr=st.nextToken().toCharArray();
			
			StringBuilder sb=new StringBuilder();
			for (int j = 0; j < arr.length; j++) {
				char c=arr[j];
				for (int k = 0; k < n; k++) {
					sb.append(c);
				}
			}
			System.out.println(sb);
		}
		
	}

}
