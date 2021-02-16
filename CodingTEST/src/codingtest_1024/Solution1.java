package codingtest_1024;
import java.util.Scanner;

public class Solution1 {

	private static void solution(int numOfAllPlayers, int numOfQuickPlayers, char[] namesOfQuickPlayers, int numOfGames, int[] numOfMovesPerGame){
	    // TODO: 이곳에 코드를 작성하세요. 추가로 필요한 함수와 전역변수를 선언해서 사용하셔도 됩니다.
			char[] circle=new char[numOfAllPlayers-1];
			for (int i = 0; i < circle.length; i++) {
				circle[i]=(char) ('A'+i+1);
			}
			int[] count=new int[numOfAllPlayers];
			char player='A';
			count[0]=1;
			int start=0, next=0;
			for (int i = 0; i < numOfGames; i++) {
				if(numOfMovesPerGame[i]>=0) {
					next=(start+numOfMovesPerGame[i])%(circle.length);
				}else{
					next=start-((Math.abs(numOfMovesPerGame[i]))%(circle.length));
					if(next<0) {
						next+=circle.length;
					}
				}
				boolean flag=false;
				for (int j = 0; j < namesOfQuickPlayers.length; j++) {
					if(namesOfQuickPlayers[j]==circle[next]) {
						flag=true;
						break;
					}
				}
				
				if(flag) {//술래가 못잡았으면
					count[player-'A']++;
					start=next;
				}else{//술래가 잡았으면
					char temp=player;
					player=circle[next];
					count[player-'A']++;
					circle[next]=temp;
					start=next;
				}
				
			}
			for (int i = 0; i < circle.length; i++) {
				System.out.println(circle[i] +" "+count[circle[i]-'A']);
			}
			System.out.println(player +" "+count[player-'A']);
	  }

	  private static class InputData {
	    int numOfAllPlayers;
	    int numOfQuickPlayers;
	    char[] namesOfQuickPlayers;
	    int numOfGames;
	    int[] numOfMovesPerGame;
	  }

	  private static InputData processStdin() {
	    InputData inputData = new InputData();

	    try (Scanner scanner = new Scanner(System.in)) {
	      inputData.numOfAllPlayers = Integer.parseInt(scanner.nextLine().replaceAll("\\s+", ""));

	      inputData.numOfQuickPlayers = Integer.parseInt(scanner.nextLine().replaceAll("\\s+", ""));
	      inputData.namesOfQuickPlayers = new char[inputData.numOfQuickPlayers];
	      System.arraycopy(scanner.nextLine().trim().replaceAll("\\s+", "").toCharArray(), 0, inputData.namesOfQuickPlayers, 0, inputData.numOfQuickPlayers);

	      inputData.numOfGames = Integer.parseInt(scanner.nextLine().replaceAll("\\s+", ""));
	      inputData.numOfMovesPerGame = new int[inputData.numOfGames];
	      String[] buf = scanner.nextLine().trim().replaceAll("\\s+", " ").split(" ");
	      for(int i = 0; i < inputData.numOfGames ; i++){
	        inputData.numOfMovesPerGame[i] = Integer.parseInt(buf[i]);
	      }
	    } catch (Exception e) {
	      throw e;
	    }

	    return inputData;
	  }

	  public static void main(String[] args) throws Exception {
		    InputData inputData = processStdin();

		    solution(inputData.numOfAllPlayers, inputData.numOfQuickPlayers, inputData.namesOfQuickPlayers, inputData.numOfGames, inputData.numOfMovesPerGame);
		  }
}
