import java.util.Scanner;

public class bj_10870 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc=new Scanner(System.in);
		
		System.out.println(fibo(sc.nextInt()));
		sc.close();
	}

	private static int fibo(int n) {
		// TODO Auto-generated method stub
		if(n==1)
			return 1;
		if(n==0)
			return 0;
		return fibo(n-1)+fibo(n-2);
	}

}
