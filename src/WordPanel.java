//package skeletonCodeAssgnmt2;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.CountDownLatch;

import javax.swing.JButton;
import javax.swing.JPanel;

public class WordPanel extends JPanel implements Runnable {
		public static volatile boolean done;
		private WordRecord[] words;
		private int noWords;
		private int maxY;
      static Score score;
		
      // paints words onto panel
		public void paintComponent(Graphics g) {
		    int width = getWidth();
		    int height = getHeight();
		    g.clearRect(0,0,width,height);
		    g.fillRect(0,maxY-10,width,height);
          g.setColor(Color.red);

		    g.setColor(Color.black);
		    g.setFont(new Font("Helvetica", Font.PLAIN, 26));
          
          // used for party mode (random color), couldnt implement properly 
          //g.setColor(new Color((float)Math.random(),(float)Math.random(),(float)Math.random()));
         		    
        for (int i=0; i < noWords; i++) {
            float c = (float) (words[i].getY()/(maxY*1.2));// changes color based on word position
            g.setColor(new Color(c,1-c,0));
            try {
              g.drawString(words[i].getWord(),words[i].getX(),words[i].getY()+20); }
            catch (java.lang.NullPointerException e) {} // catch if word is null because thread is dead
            }		   
		  }
             
		WordPanel(WordRecord[] words, int maxY) {
			this.words=words; 
			noWords = words.length;
			this.maxY=maxY;
         		
		}
		public void setScore(Score score){
         this.score=score;
      }
      public void setDone(boolean b){
         this.done=b;        
      }
      

		public void run() {
         while(!done){ // while game is ongoing animate 
            this.repaint();         
            }
      }
}

	


