public abstract class BasePlayerAI{
	char playerChar = 'x';
	int wins = 0;
	int loses = 0;
	int ties = 0;
	
	public void addWin(){
		wins++;
	}
	public void addLose(){
		loses++;
	}
	public void addTie(){
		ties++;
	}
	
	public abstract void move(Board board);
		
}