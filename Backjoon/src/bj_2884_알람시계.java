import java.time.LocalTime;
import java.util.Scanner;

public class bj_2884_알람시계 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int H = sc.nextInt();
		int M = sc.nextInt();

		LocalTime lt = LocalTime.of(H, M);
		lt=lt.minusMinutes(45);
		
		System.out.println(lt.getHour() + " " + lt.getMinute());

		sc.close();
	}

}
