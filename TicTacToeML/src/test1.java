import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class test1 {
	public static void main(String[] args){

		Board board = new Board();
		
		EvolutionTrainer trainer = new EvolutionTrainer();
		
		BasePlayerAI player1 = trainer.train();
		BasePlayerAI player2 = new RandomPlayerAI();
		System.out.println("done training");
		//BasePlayerAI player2 = new RandomPlayerAI('q');
		
		
		for(int i = 0; i<1000000; i++){
			board.reset();
			char winner = Utils.playAGame(board, player1, player2);
			if(winner == board.emptyChar){
				player1.addTie();
				player2.addTie();
			}
			if(winner == player1.playerChar){
				player1.addWin();
				player2.addLose();
			}
			if(winner == player2.playerChar){
				player1.addLose();
				player2.addWin();
			}
		}
		
		System.out.println("Results");
		System.out.println("Player1 wins: "+player1.wins);
		System.out.println("Player1 ties: "+player1.ties);
		System.out.println("Player1 loses: "+player1.loses);

	}
	

}
