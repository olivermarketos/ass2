
public class GameStatus implements Runnable{
   WordAppModel m;
   Controller c;
   WordAppView v;
   
   GameStatus(WordAppModel m, WordAppView v, Controller c){
   this.m=m;
   this.v=v;
   this.c=c;
   
   }
   
   public void run(){
      while(!m.done){
         updateScore();
         if(m.words[0].getRunning()==false){
            updateScore();
            v.popUpEndOfGame(m.score.toString());
            c.endgame();
            m.done=true;

         }
      }
   }
   public void updateScore(){
      v.scr.setText("Score: "+m.score.getScore()+"    ");
      v.missed.setText("Missed: "+m.score.getMissed()+"    ");
      v.caught.setText("Caught: "+m.score.getCaught()+"    ");

   }
}