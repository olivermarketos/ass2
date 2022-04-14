//package skeletonCodeAssgnmt2;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

import java.util.Scanner;
import java.util.concurrent.*;

public class WordApp {

	

	public static void main(String[] args) {
    	int totalWords;  //total words to fall
    	int noWords; 
      String file;
      
      if(args.length==3){
         totalWords=Integer.parseInt(args[0]);  //total words to fall
    	   noWords=Integer.parseInt(args[1]); // total words falling at any point
          file= args[2];       // file name of dictionary
       }  
      else{
        totalWords= 20;
        noWords=5;
          file="example_dict.txt";
      }
      if(noWords>10){
         System.out.println("For a better gameplay experience, the total words falling at once is capped at 10.");
         System.out.println("The game will play with 10 words falling at once");
         noWords=10;
      }
      WordAppModel m = new WordAppModel(file, noWords,totalWords); 
      WordAppController c = new WordAppController(m);
		c.initController();		
		}
}
