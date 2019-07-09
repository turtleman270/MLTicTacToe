import java.util.ArrayList;
import java.util.List;

public class Board{
		char emptyChar = '_';
		int boardSize = 3;
		int size = boardSize*boardSize;
		char[][] board = new char[boardSize][boardSize];
		
		public Board(){
			reset();
		}
		
		public void reset(){
			for(int i = 0; i<board.length; i++){
				for(int j = 0; j<board[0].length; j++){
					board[i][j] = emptyChar;
				}
			}
		}
		
		public void print(){
			for(int i = 0; i<board.length; i++){
				for(int j = 0; j<board[0].length; j++){
					System.out.print(board[i][j]+(j<(board[0].length-1)?"|":""));
				}
				System.out.println();
			} 
			System.out.println();
		}
		
		public List<Integer> getEmptyPositions(){
			List<Integer> arr = new ArrayList<Integer>();
			for(int i = 0; i<board.length; i++){
				for(int j = 0; j<board[0].length; j++){
					if(board[i][j] == emptyChar){
						arr.add(i*boardSize+j);
					}
				}
			}
			return arr;
		}
		
		public boolean gameEnded(){
			if(winner()!=emptyChar || isFull()){
				return true;
			}
			return false;
		}
		
		public void printResult(){
			if(winner()!=emptyChar){
				System.out.println("Winner: "+winner());
				return;
			}
			System.out.println("Tie, full board");
		}
		
		public void placeChar(char c, int position){
			board[position/boardSize][position%boardSize] = c;
		}
		
		public boolean isFull(){
			boolean full = true;
			for(int i = 0; i<board.length; i++){
				for(int j = 0; j<board[0].length; j++){
					if(board[i][j] == emptyChar){
						full = false;
					}
				}
			}
			return full;
		}
		
		public char winner(){
			//returns the winning character, or emptyChar if there is no winner
			
			//rows
			if(board[0][0] == board[0][1] && board[0][1] == board[0][2] && board[0][0] != emptyChar){
				return board[0][0];
			}
			if(board[1][0] == board[1][1] && board[1][1] == board[1][2] && board[1][0] != emptyChar){
				return board[1][0];
			}
			if(board[2][0] == board[2][1] && board[2][1] == board[2][2] && board[2][0] != emptyChar){
				return board[2][0];
			}
			
			//cols
			if(board[0][0] == board[1][0] && board[0][0] == board[2][0] && board[0][0] != emptyChar){
				return board[0][0];
			}
			if(board[0][1] == board[1][1] && board[0][1] == board[2][1] && board[0][1] != emptyChar){
				return board[0][1];
			}
			if(board[0][2] == board[1][2] && board[0][2] == board[2][2] && board[0][2] != emptyChar){
				return board[0][2];
			}
			

			//diagonals
			if(board[0][0] == board[1][1] && board[1][1] == board[2][2] && board[0][0] != emptyChar){
				return board[0][0];
			}
			if(board[0][2] == board[1][1] && board[1][1] == board[2][0] && board[0][2] != emptyChar){
				return board[0][2];
			}
			return emptyChar;
		}
	}