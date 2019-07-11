import java.util.List;
import java.util.Random;

public class MLPlayerAI extends BasePlayerAI {
	
	Random rand = new Random();
	
	int[] inputLayer = new int[9];
	double[] hiddenNodes = new double[3];
	double[] outputLayer = new double[9];
	int hiddenLayerNodes = 3;
	double[][] hiddenInputWeights = new double[3][9]; 		//[nodes][inputs]
	double[][] hiddenOutputWeights = new double[3][9];		//[nodes][outputs]
	
	int boardSize = 3;
	int numSquares = 9;
	
	
	public MLPlayerAI() {
	}
	public MLPlayerAI(char c, boolean a){
		playerChar = c;
	}
	public MLPlayerAI(char c) {
		//the plan is when I create this char, I'll do the training and return the result
		//create 1k players, have them play 500 games in serial
		//take the 500 winners/tiers and dup/modify them
		//do this 100 times
		playerChar = c;
		
		//initialize the input layer
		inputLayer = new int[numSquares];
		
		//initialize the weights from input to hidden layer
		hiddenInputWeights = new double[hiddenLayerNodes][numSquares];
		for(int i = 0; i<hiddenLayerNodes; i++){
			for(int j = 0; j<numSquares; j++){
				hiddenInputWeights[i][j] = rand.nextDouble();	
			}
		}
		
		//initialize the weights from hidden layer to output
		hiddenOutputWeights = new double[hiddenLayerNodes][numSquares];
		for(int i = 0; i<hiddenLayerNodes; i++){
			for(int j = 0; j<numSquares; j++){
				hiddenOutputWeights[i][j] = rand.nextDouble();	
			}
		}
		
		//initialize the output layer
		outputLayer = new double[numSquares];
	}

	@Override
	public void move(Board board) {
		
		//calculate input layer
		for(int i = 0; i<3; i++){
			for(int j = 0; j<3; j++){
				inputLayer[i*3+j] = board.board[i][j]==playerChar?1:board.board[i][j]==board.emptyChar?0:-1;
			}
		}
		
		//reset hidden layer
		for(int i = 0; i<hiddenNodes.length; i++){
			hiddenNodes[i] = 0;
		}
		//calcualte hidden layer
		for(int i = 0; i<hiddenNodes.length; i++){
			for(int j = 0; j<numSquares; j++){
				hiddenNodes[i] += hiddenInputWeights[i][j]*inputLayer[j];
			}
		}
		
		//reset output layer
		for(int i = 0; i<outputLayer.length; i++){
			outputLayer[i] = 0;
		}
		//calcualte output layer
		for(int i = 0; i<hiddenNodes.length; i++){
			for(int j = 0; j<numSquares; j++){
				outputLayer[j] += hiddenOutputWeights[i][j]*hiddenNodes[i];
			}
		}
		
		
		List<Integer> emptyPositions = board.getEmptyPositions();
		int pos = emptyPositions.get(0);
		double max = outputLayer[pos];
		
		for(int i = 0; i<emptyPositions.size(); i++){
			int position = emptyPositions.get(i);
			if(outputLayer[position]>max){
				pos = emptyPositions.get(i);
				max = outputLayer[position];
			}
		}
//		System.out.println(emptyPositions.toString()+" "+pos);
		board.placeChar(playerChar, pos);
	}
	
	public void augment() {
		double magnitude = 0.01;

		for(int i = 0; i<hiddenInputWeights.length; i++){
			for(int j = 0; j<hiddenInputWeights[0].length; j++){
				hiddenInputWeights[i][j] += (Math.random() * (magnitude * 2) - magnitude);
			}
		}
		
		for(int i = 0; i<hiddenOutputWeights.length; i++){
			for(int j = 0; j<hiddenOutputWeights[0].length; j++){
				hiddenOutputWeights[i][j] += (Math.random() * (magnitude * 2) - magnitude);
			}
		}
	}
	

	
	/*
		how i'm planning it to work
		
	  each tile	--->		hidden layer	  ----> 	ouput (each tile will have a percentage, highest percent gets played(unless its already taken, then ill play the next one)
		1										0
		2										0.4
		3					0					0.9		<- pick this one
		4										0
		5					0					0.1
		6										0.2
		7					0					0.2
		8										0
		9										0.6
	 		iput weights			output weights
	 
	 
	 
	 
	 */

}
