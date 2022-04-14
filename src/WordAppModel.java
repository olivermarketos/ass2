import java.util.*;
import java.io.FileInputStream;
import java.io.IOException;


public class WordAppModel{


	static int noWords;
	static int totalWords;
   static final String aim= "The aim is to type all the words before they reach the red box \n\n'start' will cause a specified number of words to start falling down the screen \n'end' will reset the game \n'quit' will end the program";
	static WordDictionary dict = new WordDictionary(); //use default dictionary, to read from file eventually
	static WordRecord[] words;
	static volatile boolean done;  //must be volatile
	static Score score = new Score();

    public WordAppModel(String fileName, int noWords, int totalWords){
        this.noWords= noWords;
        this.totalWords=totalWords;
        
        String[] tmpDict=getDictFromFile(fileName); //file of words
        if (tmpDict!=null){
		  dict= new WordDictionary(tmpDict);}
    
        WordRecord.dict=dict;
        words = new WordRecord[noWords];

		   int x_inc=(int)1000/noWords;
		   for (int i=0;i<noWords;i++) {
			   words[i]=new WordRecord(dict.getNewWord(),i*x_inc,480,noWords,totalWords);
            words[i].passScore(score);
            words[i].setDict(dict);
		   }
    }


   // sets dictionary of words
   public static String[] getDictFromFile(String f) {
   
		String [] dictStr = null;
		try {
            String filename= f;
			Scanner dictReader = new Scanner(new FileInputStream(filename));
			int dictLength = dictReader.nextInt();
			dictStr=new String[dictLength];
         
			for (int i=0;i<dictLength;i++) {
				dictStr[i]=new String(dictReader.next());
			}
			dictReader.close();
		} catch (IOException e) {
	        System.err.println("Problem reading file " + f + " default dictionary will be used");
	    }
		return dictStr;
	}
   public void setDone(boolean b){
      this.done=b;
   }
}
