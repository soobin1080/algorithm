import java.util.Arrays;

public class pg_카펫 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] answer = new int[2];
		int brown = 8, yellow = 1;
		
		// row*col=yellow, row+col=brown
		
		brown -= 4;
		brown /= 2;

		int row = 0, col = 0;
		for (int i = 0; i < brown / 2 + 1; i++) {
			col = i;
			row = brown - i;
			if (col * row == yellow) {
				row += 2;
				col += 2;
				answer[0] = row;
				answer[1] = col;
				break;
			}
		}
		
		System.out.println(Arrays.toString(answer));
		
	}

}
