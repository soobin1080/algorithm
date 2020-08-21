import java.util.LinkedList;
import java.util.Queue;
//다시
public class pg_단어변환 {

	public static int main(String[] args) {
		// TODO Auto-generated method stub
		String begin = "hit";
		String target = "cog";
		String[] words = { "hot", "dot", "dog", "lot", "log", "cog" };

		int answer = 0;//몇번째에 완료되는지 확인하기 위해서

		boolean used[] = new boolean[words.length];

		Queue<String> que = new LinkedList<String>();

		que.add(begin);

		while (!que.isEmpty()) {
			
			int size = que.size();
			for (int s = 0; s < size; s++) {

				String now = que.poll();

				for (int i = 0; i < used.length; i++) {
					if (!used[i]) {
						int diff = 0;
						for (int j = 0; j < now.length(); j++) {
							if (now.charAt(j) != words[i].charAt(j)) {
								diff++;
							}
						}
						if (diff == 1) {
							que.add(words[i]);
							used[i] = true;
						}
						
						if(diff==0) {
							return answer+1;
						}
					}
				}
			}
			
			if(answer==words.length) {
				return 0;
			}
			answer++;
		}

		return answer;
	}

}
