import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WordAppController{

   private WordAppModel model;
   private WordAppView view;
   private boolean gameOver;
   private boolean firstGame;
   
   public WordAppController(WordAppModel m){
      model = m;
      view = new WordAppView(model.words);
      initView();  
      firstGame=true; 
   }
   
   // resets view and score    
   public void initView(){
      model.score.resetScore();
   }
   
 
    // initialises GameStatus
    // starts WordRecord[] threads
   public void initController(){
        view.getStartB().addActionListener(e->startgame());
        view.getEndB().addActionListener(e->endgame());
        view.getQuitB().addActionListener(e->quitGame());
        view.getAimB().addActionListener(e->aimOfGame());
        view.gettextEntry().addActionListener(e ->getTextInput());
   }
   
      // sets done booleans to false inmodel and view
    // initialises GameStatus
    // starts WordRecord[] threads
     // sets done booleans to false inmodel and view
   public void startgame(){
      initView();
      model.words[0].setRunning(true);
      model.setDone(false);
      view.getWordPanel().setDone(false);
      model.score.resetScore();
      view.getStartB().setEnabled(false);
      Thread GameStatus = new Thread(new WordAppController.GameStatus(model,view)); 
      GameStatus.start();

      for (int i =0;i<model.noWords;i++){
         Thread t = new Thread(view.words[i]);
         t.start();
         }
      view.setStart();
      view.gettextEntry().requestFocus(); 
    } 
    
    // ends game
    // calls GameOver pop up with score
    // 
    public void endgame(){
      view.getStartB().setEnabled(true);
      model.words[0].setRunning(false);
      model.setDone(true);
      view.getWordPanel().setDone(true);
      firstGame=false;
      for (int i =0;i<model.noWords;i++){
        model.words[i].resetWord();
      }
        view.popUpEndOfGame(model.score.toString());
    } 
    
    // disposes of swing GUI
    private void quitGame(){
      System.out.println("quitting");  
      view.frame.dispose();
      System.exit(0);
    }
    
      
    private void aimOfGame(){
      view.showAim(model.aim);
    }
    
    // recieve text input
    // check if word is a duplicate
    // if duplicate, reset lowest word    
    private void getTextInput(){
         int dupCount=0;
         int[] indexArray = new int[10];
         for(int i =0; i < model.noWords;i++){
            if(model.words[i].matchWord(view.gettextEntry().getText())){
               indexArray[dupCount]=i;
               dupCount++;
            }
         } 
         if(dupCount==1){
            model.score.caughtWord(model.words[indexArray[0]].getWord().length());
            boolean b=model.words[indexArray[0]].toReset();
            //model.words[indexArray[0]].resetWord();
         }      
         else if(dupCount >1){
            int y_cord=0;
            int indexDup=0;
            for(int i = 0; i<dupCount;i++){
               if(model.words[indexArray[i]].getY()>y_cord){
                  y_cord=model.words[indexArray[i]].getY();
                  indexDup=indexArray[i];
               }
            }
            model.score.caughtWord(model.words[indexDup].getWord().length());
            boolean b=model.words[indexDup].toReset();
            //model.words[indexDup].resetWord();
        }
	     view.gettextEntry().setText("");
	     view.gettextEntry().requestFocus();
    }
    

   public class GameStatus implements Runnable{
   WordAppModel m;
   WordAppView v;
   
   GameStatus(WordAppModel m, WordAppView v){
   this.m=m;
   this.v=v;   
   }
   
   public void run(){
      while(!m.done){
         updateScore();
         if(m.words[0].getRunning()==false){// if threads are done running

            updateScore();
            endgame(); // finish game
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
}
