public class Utils {
	
	
	public static char playAGame(Board board, BasePlayerAI player1, BasePlayerAI player2){
		while(true){
			player1.move(board);
			if(board.gameEnded()){
				return board.winner();
			}
			player2.move(board);
			if(board.gameEnded()){
				return board.winner();
			}
		}
	}



}
