package codingtest_1024;
import java.util.Arrays;
import java.util.Scanner;

public class Solution2 {

	private static void solution(int day, int width, int[][] blocks) {
	    // TODO: 이곳에 코드를 작성하세요. 추가로 필요한 함수와 전역변수를 선언해서 사용하셔도 됩니다.
			//배열에 입력값 받아서 넣고
			//오른쪽으로 가면서 더 큰값 있으면 채울 수 있음
			//왼쪽으로 가면서 더 큰값 있으면 채울 수 있음
			//시멘트 채우기 - 시멘트 양 카운트 하기
			//공사 기간동안 반복
		for (int i = 0; i < blocks.length; i++) {
			
			System.out.println(Arrays.toString(blocks[i]));
		}
	  }
	  
	  private static class InputData {
	    int day;
	    int width;
	    int[][] blocks;
	  }

	  private static InputData processStdin() {
	    InputData inputData = new InputData();

	    try (Scanner scanner = new Scanner(System.in)) {
	      inputData.day = Integer.parseInt(scanner.nextLine().replaceAll("\\s+", ""));      
	      inputData.width = Integer.parseInt(scanner.nextLine().replaceAll("\\s+", ""));
	      
	      inputData.blocks = new int[inputData.day][inputData.width];
	      for (int i = 0; i < inputData.day; i++) {
	        String[] buf = scanner.nextLine().trim().replaceAll("\\s+", " ").split(" ");
	        for (int j = 0; j < inputData.width; j++) {
	          inputData.blocks[i][j] = Integer.parseInt(buf[j]);
	        }
	      }
	    } catch (Exception e) {
	      throw e;
	    }

	    return inputData;
	  }

	  public static void main(String[] args) throws Exception {
	    InputData inputData = processStdin();

	    solution(inputData.day, inputData.width, inputData.blocks);
	  }

}
