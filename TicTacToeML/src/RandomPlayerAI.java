import java.util.List;
import java.util.Random;

public class RandomPlayerAI extends BasePlayerAI {

	public RandomPlayerAI() {
	}
	public RandomPlayerAI(char c) {
		playerChar = c;
	}

	@Override
	public void move(Board board) {
		List<Integer> emptyPositions = board.getEmptyPositions();
		Random rand = new Random();
	    int randomElement = emptyPositions.get(rand.nextInt(emptyPositions.size()));
		board.placeChar(playerChar, randomElement);
	}

}
