import java.util.Scanner;

public class sw_10505 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		 
        int TC = sc.nextInt();
        for (int tc = 1; tc <= TC; tc++) {
 
            int answer = 0;
 
            int n = sc.nextInt();
            int[] arr = new int[n];
            for (int i = 0; i < n; i++) {
                arr[i] = sc.nextInt();
            }
             
            int sum = 0;
 
            for (int i = 0; i < n; i++) {
                sum += arr[i];
            }
 
            int avg = sum / n;
 
            for (int i = 0; i < n; i++) {
                if (avg >= arr[i])
                    answer++;
            }
 
            System.out.println("#" + tc + " " + answer);
        }
	}

}
