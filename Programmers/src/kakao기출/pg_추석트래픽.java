package kakao기출;

public class pg_추석트래픽 {

	public static void main(String[] args) {
		String[] lines = { "2016-09-15 01:00:04.001 2.0s", "2016-09-15 01:00:04.001 0s" };

		int[] startTimes = new int[lines.length];
		int[] endTimes = new int[lines.length];

		for (int i = 0; i < lines.length; i++) {

			String[] log = lines[i].split(" ");
			String[] responseTime = log[1].split(":");

			int processingTime = (int) (Double.parseDouble(log[2].substring(0, log[2].length() - 1)) * 1000);

			int startTime = 0;
			int endTime = 0;
			endTime += (Integer.parseInt(responseTime[0]) * 60 * 60 * 1000
					+ Integer.parseInt(responseTime[1]) * 60 * 1000
					+ (int) (Double.parseDouble(responseTime[2]) * 1000));

			startTime = endTime - processingTime + 1;

			startTimes[i] = startTime;
			endTimes[i] = endTime;
		}

		int answer = 0;
		for (int i = 0; i < lines.length; i++) {
			
			//초당 최대 처리량을 구하기 위해서 1초범위를 정한다 
			int start = startTimes[i];
			int end = startTimes[i] + 1000;

			int s_cnt = 0, e_cnt = 0;

			for (int j = 0; j < lines.length; j++) {
				
				//1초 범위안에 처리시작시간이나 처리끝시간이 포함될 경우 +1, 처리시작시간과 처리 끝시간 사이에 1초범위가 포함 될때 +1
				
				if ((startTimes[j] >= start && startTimes[j] < end) || (endTimes[j] >= start && endTimes[j] < end)
						|| (startTimes[j] <= start && endTimes[j] >= end))
					s_cnt++;
			}

			//초당 최대 처리량을 구하기 위해서 1초범위를 정한다 
			start = endTimes[i];
			end = endTimes[i] + 1000;

			for (int j = 0; j < lines.length; j++) {
				if ((startTimes[j] >= start && startTimes[j] < end) || (endTimes[j] >= start && endTimes[j] < end)
						|| (startTimes[j] <= start && endTimes[j] >= end))
					e_cnt++;
			}

			answer = (answer < s_cnt) ? s_cnt : answer;
			answer = (answer < e_cnt) ? e_cnt : answer;
		}

		System.out.println(answer);

	}
}
