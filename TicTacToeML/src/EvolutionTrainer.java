import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class EvolutionTrainer {
	
	public MLPlayerAI train(){
		MLPlayerAI[] players = new MLPlayerAI[100];
		for(int i = 0; i<players.length; i++){
			players[i] = new MLPlayerAI((char)(1000+i));
		}
		
		Board board = new Board();
		for(int run = 0; run < 100000; run++){
			List p = new ArrayList();
			Collections.addAll(p, players);
			Collections.shuffle(p);
			p.toArray(players);
			for(int i = 0; i<50; i++){
				board.reset();
				MLPlayerAI p1 = players[i*2];
				MLPlayerAI p2 = players[i*2+1];
				char winner = Utils.playAGame(board, p1, p2);
				MLPlayerAI[] children;
				if(winner == p1.playerChar){
					children = breed(p1);
				}
				else{
					p2.playerChar = p1.playerChar;
					children = breed(p2);	
				}
				children[0].augment();
				children[1].augment();
				players[i*2] = children[0];
				players[i*2+1] = children[1];				
			}	
		}
		for(int i = 0; i<players[0].hiddenInputWeights.length; i++){
			for(int j = 0; j<players[0].hiddenInputWeights[0].length; j++){
				System.out.print(players[0].hiddenInputWeights[i][j]);
			}
			System.out.println();
		}

		return players[0];

	}
	
	public MLPlayerAI[] breed(MLPlayerAI player){
		MLPlayerAI[] children = new MLPlayerAI[2];
		children[0] = new MLPlayerAI(player.playerChar);
		children[1] = new MLPlayerAI((char)(player.playerChar+1));
		for(int i = 0; i<player.hiddenInputWeights.length; i++){
			for(int j = 0; j<player.hiddenInputWeights[0].length; j++){
				children[0].hiddenInputWeights[i][j] = player.hiddenInputWeights[i][j];
				children[1].hiddenInputWeights[i][j] = player.hiddenInputWeights[i][j];
			}
		}		
		for(int i = 0; i<player.hiddenInputWeights.length; i++){
			for(int j = 0; j<player.hiddenInputWeights[0].length; j++){
				children[0].hiddenOutputWeights[i][j] = player.hiddenOutputWeights[i][j];
				children[1].hiddenOutputWeights[i][j] = player.hiddenOutputWeights[i][j];
			}
		}		
		children[0].augment();
		children[1].augment();
		
		return children;
	}
}
