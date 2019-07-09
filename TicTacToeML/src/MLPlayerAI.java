import java.util.List;
import java.util.Random;

public class MLPlayerAI extends BasePlayerAI {
	
	Random rand = new Random();
	
	int[] inputLayer;
	int[] outputLayer;
	int hiddenLayerNodes = 3;
	double[] hiddenInputWeights;
	double[] hiddenOutputWeights;
	
	
	public MLPlayerAI() {
	}
	public MLPlayerAI(char c) {
		playerChar = c;
	}

	@Override
	public void move(Board board) {
		List<Integer> emptyPositions = board.getEmptyPositions();
		Random rand = new Random();
	    int randomElement = emptyPositions.get(rand.nextInt(emptyPositions.size()));
		board.placeChar(playerChar, randomElement);
	}
	
	public void initializeEverything(Board board){
		//initialize the input layer
		inputLayer = new int[board.size];
		for(int i = 0; i<board.boardSize; i++){
			for(int j = 0; j<board.boardSize; j++){
				inputLayer[i*board.boardSize+j] = board.board[i][j] == board.emptyChar?0:
												board.board[i][j] == playerChar?1:-1;
			}
		}
		
		//initialize the weights from input to hidden layer
		hiddenInputWeights = new double[board.size*hiddenLayerNodes];
		for(int i = 0; i<hiddenInputWeights.length; i++){
			hiddenInputWeights[i] = rand.nextDouble();
		}
		
		//initialize the weights from hidden layer to output
		hiddenOutputWeights = new double[board.size*hiddenLayerNodes];
		for(int i = 0; i<hiddenOutputWeights.length; i++){
			hiddenOutputWeights[i] = rand.nextDouble();
		}
		
		//initialize the output layer
		outputLayer = new int[board.size];
		
	}
	
	/*
		how i'm planning it to work
		
	  each tile	--->		hidden layer	  ----> 	ouput (each tile will have a percentage, highest percent gets played(unless its already taken, then ill play the next one)
		1										0
		2										0.4
		3					O					0.9		<- pick this one
		4										0
		5										0.1
		6					O					0.2
		7										0.2
		8										0
		9										0.6
	 		iput weights			output weights
	 
	 
	 
	 
	 */

}
