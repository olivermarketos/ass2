import javax.swing.*;
import java.util.*;


public class WordRecord implements Runnable {

   static volatile boolean running;
   static  int finished;
   private String text;
	private  int x;
	private int y;
	private int maxY;
	private boolean dropped;
   private int noWords;
   private int totalWords;
	
	private int fallingSpeed;
	private static int maxWait=800;
	private static int minWait=100;
   public static Score score;
	public static WordDictionary dict;
	
	public void run(){
      
      dropped = false;
      while(running) {
            if (dropped) {
             score.missedWord();
               if(!toReset()){
                  break;
               }
            }  
          else {
            drop(2);}
          try { Thread.currentThread().sleep(fallingSpeed); }
          catch (InterruptedException e) {System.out.println(e);}  
         
          if(getFinished()==noWords){
               setRunning(false);
          } 
         }
   }

	WordRecord() {
		text="";
		x=0;
		y=0;	
		maxY=300;
		dropped=false;
		fallingSpeed=(int)(Math.random() * (maxWait-minWait)+minWait); 
	}
		WordRecord(String text) {
		this();
		this.text=text;
	}
	
	WordRecord(String text,int x, int maxY, int n, int t) {
		this(text);
		this.x=x;
		this.maxY=maxY;
      dropped=false;
      finished=0;
      totalWords=t;
      noWords= n;
		fallingSpeed=(int)(Math.random() * (maxWait-minWait)+minWait); 

	}
   
   public boolean toReset(){
      if(score.getCaught()+score.getMissed()+noWords-1 < totalWords){
         resetWord();
         return true;
      }
      else{
          setWord(null);
          finished();
          if(getFinished()==noWords){
           setRunning(false);
        }  
          return false;
      }
   }
   
	public void passScore(Score score){
    this.score = score; 
   }
    public synchronized  void finished(){
      finished++;
    }
     
    public synchronized  int getFinished(){
        return finished;
    }  
      
	public synchronized  void setY(int y) {
		if (y>maxY) {
			y=maxY;
			dropped=true;
		}
		this.y=y;
	}
	
	public synchronized  void setX(int x) {
		this.x=x;
	}
	
	public synchronized  void setWord(String text) {
		this.text=text;
	}

	public synchronized  String getWord() {
		return text;
	}
	
	public synchronized  int getX() {
		return x;
	}	
	
	public synchronized  int getY() {
		return y;
	}
	
	public synchronized  int getSpeed() {
		return fallingSpeed;
	}
   public synchronized void setDict(WordDictionary d){
    dict = d;
   }
	public synchronized void setPos(int x, int y) {
		setY(y);
		setX(x);
	}
	public synchronized void resetPos() {
		setY(0);
	}

	public synchronized void resetWord() {
		resetPos();
		text=dict.getNewWord();
		dropped=false;
		fallingSpeed=(int)(Math.random() * (maxWait-minWait)+minWait); 

	}
	
	public synchronized boolean matchWord(String typedText) {
		//System.out.println("Matching against: "+text);
		if (typedText.equals(this.text)) {     
			//resetWord();
			return true;
		}
		else
			return false;
	}
	

	public synchronized  void drop(int inc) {
      setY(y+inc);
      if(this.y>=maxY){
         dropped = true;
      }
	}
	
	public synchronized  boolean dropped() {
		
      return dropped;
	}
	
	public synchronized  void setRunning(boolean b) {
		this.running=b;
      if(b){
         finished=0;
      }
	}
	public synchronized  boolean getRunning() {
      return running;
	}

}
