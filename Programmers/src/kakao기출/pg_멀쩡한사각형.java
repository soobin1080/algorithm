package kakao기출;

import java.math.BigInteger;

public class pg_멀쩡한사각형 {

	public static void main(String[] args) {
		int w = 99999999, h = 99999999;
		long answer = 0;
		long total = (long) w * (long) h;
		answer = total - w + h - BigInteger.valueOf(w).gcd(BigInteger.valueOf(h)).longValue();

		System.out.println(answer);

	}

}
