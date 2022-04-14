import java.awt.*;
import javax.swing.*;
import java.awt.Color;


public class WordAppView{

   static int frameX=1000;
	static int frameY=600;
	static int yLimit=480;
	static WordPanel w;
   
    JFrame frame;
    JPanel g;
    JPanel txt;
    JLabel caught;
    JLabel missed;
    JLabel scr;
    JTextField textEntry;
    JPanel b;
    JButton startB;
    JButton endB;
    JButton quitB;
    JButton aimB;
   
    WordRecord[] words;
	
    WordAppView(WordRecord[] words){
         
      this.words=words;
      frame = new JFrame("WordGame"); 
    	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	frame.setSize(frameX, frameY);

      
        g = new JPanel();
        g.setLayout(new BoxLayout(g, BoxLayout.PAGE_AXIS)); 
        g.setSize(frameX,frameY);

        w = new WordPanel(words, yLimit);
		  w.setSize(frameX,yLimit+100);
		  g.add(w);
        txt = new JPanel();
        txt.setLayout(new BoxLayout(txt, BoxLayout.LINE_AXIS)); 
        caught =new JLabel("Caught: 0    ");
        missed =new JLabel("Missed: 0    ");
        scr =new JLabel("Score: 0    ");    
        txt.add(caught);
	    txt.add(missed);
	    txt.add(scr);   

	    textEntry = new JTextField("",20);
	   	   
	    txt.add(textEntry);
	    txt.setMaximumSize( txt.getPreferredSize() );
	    g.add(txt);
	    
	    b = new JPanel();
        b.setLayout(new BoxLayout(b, BoxLayout.LINE_AXIS)); 
	    startB = new JButton("Start");
		
		endB = new JButton("End");
			
	    quitB = new JButton("Quit");		
       aimB= new JButton("Aim");		
		b.add(startB);
		b.add(endB);
		b.add(quitB);
      b.add(aimB);
		g.add(b);
    	
        frame.setLocationRelativeTo(null);  // Center window on screen.
        frame.add(g); //add contents to window
        frame.setContentPane(g);     
        frame.setVisible(true);
   }
   public void setStart(){
      //System.out.println("made to set start");
      //for (int i =0;i<words.length;i++){
       //   System.out.println("start thread");
        // words[i].execute();
        // }
      Thread t = new Thread(w);
      //if(b){
     
      t.start();
      //}
      //else{
      // w.done=true;
      //}
   }
   
   public WordPanel getWordPanel(){
      return w;
   }
   public JButton getStartB(){
        return startB;
    }
   public JButton getEndB(){
        return endB;
    }
     public JButton getQuitB(){
        return quitB;
    }
    public JButton getAimB(){
        return aimB;
    }

    public JTextField gettextEntry(){
        return textEntry;
    }
    public void setWordPanel(WordRecord[] wr){
        w = new WordPanel(words, yLimit);
		  w.setSize(frameX,yLimit+100);
		  g.add(w);

    }
    public void popUpEndOfGame(String scoreString) { 
         JOptionPane.showMessageDialog(frame, scoreString ,"Game Over", JOptionPane.PLAIN_MESSAGE);
    }
    public void showAim(String aimString){
         JOptionPane.showMessageDialog(frame, aimString  ,"Aim of Game", JOptionPane.PLAIN_MESSAGE);

    }
}
