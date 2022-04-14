
public class Score {
	private int missedWords;
	private int caughtWords;
	private int gameScore;
   private int highScore;
	
	Score() {
		missedWords=0;
		caughtWords=0;
		gameScore=0;
      highScore=0;
	}
		
	// all getters and setters must be synchronized
   // all methods self explanatory
	
	public synchronized int getMissed() {
		return missedWords;
	}

	public synchronized int getCaught() {
		return caughtWords;
	}
	
	public synchronized int getTotal() {
		return (missedWords+caughtWords);
	}

	public synchronized int getScore() {
		return gameScore;
	}
	public synchronized void updateHighScore(){
      if (gameScore>highScore){
         highScore=gameScore;}
   }
	public synchronized void missedWord() {
		missedWords++;
	}

	public synchronized void caughtWord(int length) {
		caughtWords++;
		gameScore+=length;
      updateHighScore();
      	}

	public void resetScore() {
		caughtWords=0;
		missedWords=0;
		gameScore=0;
	}
   
   public String toString(){
      return "Caught: "+getCaught()+"\nMissed: "+getMissed()+"\nScore: "+getScore()+"\nHigh Score: "+highScore;
   }
}
