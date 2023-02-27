//Programmer: T. Jake Holmes II
//Assignment: Word Search
//Extra Credit: word cross (see sample words)

import java.util.*;
import java.io.*;

public class Assignment1WordSearch {

   // VARIABLES:
   private static int aLength = 20;
   private static String[][] wordSearch = new String[aLength][aLength]; // word search array
   private static String[][] wordSearchKey = new String[aLength][aLength]; // answer key array
   private static ArrayList<String> words = new ArrayList<String>(); // array to collect words
   private static Random random = new Random(); // randome # generator
   private static String currentWord = ""; // words to collect
   private static String reversed = ""; // string for reverse of current word
   private static int V = 0; // starting array position verticle for previous string
   private static int H = 0; // starting array position horizontal for previous string
   private static int tempV = 0; // starting array position verticle for next string
   private static int tempH = 0; // starting array position horizontal for next string
   private static boolean placement; // to see if line has been placed
   
   // for displaying a smaller word search
   private static int minH = 100;
   private static int maxH = 0;
   private static int minV = 100;
   private static int maxV = 0;

   public static void main(String[] args) {
   
     /* SAMPLE WORDS TO CHECK THAT THEY CAN CROSS OVER:
     1. hello
     2. world
     3. computer
     4. monitor
     5. method
     */
   
      System.out.println("This Program Will Allow You To Chose Five Words To Make Your Own Word Search");
      menu();
   } // end of main

   public static void menu() {
      Scanner input = new Scanner(System.in);
      String menu;
   
      do { 
         System.out.println("Please Select An Option: ");
         System.out.println("Create A New Word Search(g)");
         System.out.println("Print Your Created Word Search(p)");
         System.out.println("Show The Solution To Your Word Search(s)");
         System.out.println("Quit Program(q)");
         menu = input.next().toLowerCase();
      } // end do
         while(!menu.equals("g")&&!menu.equals("p")&&!menu.equals("s")&&!menu.equals("q"));
      if(menu.equals("g")){
         generateWS();
      }
      else if(menu.equals("p")){
         printWS();
      }
      else if(menu.equals("s")){
         printAK();
      }
      else {
         System.out.println("Thank You For Playing.");
         System.exit(0);
      }
   } // end menu

    // method to build user word search
   public static void generateWS() {
      Scanner input = new Scanner(System.in);
   
      words.clear();
      wordSearch = new String[aLength][aLength];
      wordSearchKey = new String[aLength][aLength];
   
      for(int i = 0; i < 5; i++) { // prompts for words and adds to words array
         if(i == 0) {
            System.out.print("Please Choose A Word With 8 Or Less Characters \t");
         }
         else {System.out.print("Please Choose Another Word With 8 Or Less Characters\t"); }
         currentWord = input.next();
         while(currentWord.length() > 8) {
            System.out.println("I'm Sorry, That Word Is Too Long.");
            System.out.print("Please Choose A Word With 8 Or Less Characters \t");
            currentWord = input.next();
         }
      
         currentWord = currentWord.toLowerCase();
         words.add(currentWord); // adds word to list for display purposes
         
         switch(i) {
         
            case 0: // first word added to the middle of the array, horizontal
               wordOne();
               break;
         
            case 1: // second word verticle top left
               wordTwo();
               break;
         
            case 2: // word 3 reversed horizontal bottom right
               wordThree();
               break;
         
            case 3: // reversed verticle bottom right
               wordFour();
               break;
         
            case 4: // diag top right
               wordFive();
               break;
         }
      
        // setting display range for wordsearch
         for(int x = 0; x < aLength; x++) {
            for(int y = 0; y < aLength; y++) {
               if(wordSearch[x][y] != null) {
                  if( y > maxH) { maxH = y; }
                  if( y < minH) { minH = y; }
                  if( x > maxV) { maxV = x; }
                  if( x < minV) { minV = x; }
               }
            }
         }
      } 
         
            // for-loop to add random letters
      for(int i = 0; i < aLength; i++) {
         for(int j = 0; j < aLength; j ++) {
            int number = random.nextInt(26);
            char letter = (char) (number + 97);
            String sLetter = "" + letter;
            if(wordSearch[i][j] == null) {
               wordSearch[i][j] = sLetter;
               wordSearchKey[i][j] = "|";
            }
         } // end of for-loop j
      } // end of for-loop i
      System.out.println("\nYour Word Search Has Been Generated.\n");
      menu();
   } // end of generator
    
 // Method to handle the first word from user
   public static void wordOne() {
      V = aLength / 2;
      H = V - currentWord.length() / 2;
   
      for(int j = 0; j < currentWord.length(); j++) { // adds words to arrays
         String character = "" + currentWord.charAt(j);
         wordSearch[V][H + j] = character; // adds word to wordSearch lower case
         wordSearchKey[V][H + j] = character.toUpperCase(); // adds the word all caps
      } // end of for-loop
   } // end of wordOne()

 // Method to handle the second word from user
   public static void wordTwo() {
      placement = false;
      for(int x = 0; x < words.get(words.size() - 2).length(); x++) {
         for(int y = 0; y < currentWord.length(); y++) { // goes through second word
            if(words.get(words.size() - 2).charAt(x) == currentWord.charAt(y)) {
               if(placement == false) {
                  placement = true;
                  V = V - y;
                  H = H + x;
                  for(int j = 0; j < currentWord.length(); j++) { // places on left side
                     String character = "" + currentWord.charAt(j);
                     wordSearch[V + j][H] = character; // adds word to wordSearch lower case
                     wordSearchKey[V + j][H] = character.toUpperCase(); // adds the word all caps
                  } // end of for
               } // end of if false
            } // end of if matching letter
         } // end of for-loop y
      } // end of  for-loop x
   
      if(placement == false) { // default position if no letters match
         
         V = V - 2;
         H = H - 2;
         for(int j = 0; j < currentWord.length(); j++) { // places on left side
            String character = "" + currentWord.charAt(j);
            wordSearch[V + j][H] = character; // adds word to wordSearch lower case
            wordSearchKey[V + j][H] = character.toUpperCase(); // adds the word all caps
         }
      } // end of if false
   } // end of wordTwo
   
   public static void wordThree() {
      placement = false;
      for(int x = 0; x < words.get(words.size() - 2).length(); x++) {
         for(int y = 0; y < currentWord.length(); y++) { // goes through second word
            if(words.get(words.size() - 2).charAt(x) == currentWord.charAt(y)) {
               if(placement == false) {
                  placement = true;
                  tempV = V + x;
                  tempH = H + y;
                  for(int i = 0; i < currentWord.length(); i++) {
                     String character = "" + currentWord.charAt(i);
                     if(wordSearch[tempV][tempH - i] != null && !wordSearch[tempV][tempH - i].equals(character)) {
                        placement = false;
                     }
                  }
                  if(placement) {
                     V = V + x;
                     H = H + y;
                     for(int i = 0; i < currentWord.length(); i++) { // places on left side
                        String character = "" + currentWord.charAt(i);
                        wordSearch[V][H - i] = character; // adds word to wordSearch lower case
                        wordSearchKey[V][H - i] = character.toUpperCase(); // adds the word all caps
                     }
                  }
                                
               } // end of if
            }
         } // end of y
      } // end of x   
         
      if(placement == false) { // default position if no match is found
      
         V = V + 4;
         H = V + 4;
      
         for(int i = 0; i < currentWord.length(); i++) { // places on left side
            String character = "" + currentWord.charAt(i);
            wordSearch[V][H - (currentWord.length() - 1) + i]  = character; // adds word to wordSearch lower case
            wordSearchKey[V][H - (currentWord.length() - 1) + i] = character.toUpperCase(); // adds the word all caps
         } // end for
      } // end if
   } // end of wordThree()
   
   //Method for fourth word
   public static void wordFour() {
   
      placement = false;
      for(int x = 0; x < words.get(words.size() - 2).length(); x++) {
         for(int y = 0; y < currentWord.length(); y++) { // goes through second word
            if(words.get(words.size() - 2).charAt(x) == currentWord.charAt(y)) {
               if(placement == false) {
                  placement = true;
                  tempV = V + y;
                  tempH = H - x;
                     
                  for(int i = 0; i < currentWord.length(); i++) {
                     String character = "" + currentWord.charAt(i);
                     if(wordSearch[tempV - i][tempH] != null && !wordSearch[tempV - i][tempH].equals(character)) {
                        placement = false;
                     }
                  }
                     
                  if(placement == true) {
                     V = V + y;
                     H = H - x;
                     for(int i = 0; i < currentWord.length(); i++) { // places on left side
                        String character = "" + currentWord.charAt(i);
                        wordSearch[V - i][H] = character; // adds word to wordSearch lower case
                        wordSearchKey[V - i][H] = character.toUpperCase(); // adds the word all caps
                     }
                  }             
               } // end of if
            }
         } // end of y
      } // end of x 
   
      if(placement == false) {
         V = 19;
         H = 0;
         for(int i = 0; i < currentWord.length(); i++) { // places on left side
            String character = "" + currentWord.charAt(i);
            wordSearch[V - i][H] = character; // adds word to wordSearch lower case
            wordSearchKey[V - i][H] = character.toUpperCase(); // adds the word all caps
         }
      }
   }
   
   public static void wordFive() {
      placement = false;
      for(int x = 0; x < words.get(words.size() - 2).length(); x++) {
         for(int y = 0; y < currentWord.length(); y++) { // goes through second word
            if(words.get(words.size() - 2).charAt(x) == currentWord.charAt(y)) {
               if(placement == false) {
                  placement = true;
                  tempV = V + y;
                  tempH = H - x;
                     
                  for(int i = 0; i < currentWord.length(); i++) {
                     String character = "" + currentWord.charAt(i);
                     if((tempV + i) < 0 || (tempV + i) > 19 || (tempH - i) < 0 || (tempH - i) > 19) {
                        placement = false;
                     }
                     else {
                     if(wordSearch[tempV + i][tempH - i] != null && !wordSearch[tempV + i][tempH - i].equals(character)) {
                        placement = false;
                     }
                     }
                  }
                     
                  if(placement == true) {
                     V = V + y;
                     H = H - x;
                     for(int i = 0; i < currentWord.length(); i++) { // places on left side
                        String character = "" + currentWord.charAt(i);
                        //wordSearch[V + i][H - i] = character; // adds word to wordSearch lower case
                        //wordSearchKey[V + i][H - i] = character.toUpperCase(); // adds the word all caps
                        System.out.println("V + i = " + (V + i));
                        System.out.println("H - i = " + (H - i));
                     }
                  }             
               } // end of if
            }
         } // end of y
      } // end of x 

      if(placement == false) {
         V = 0;
         H = 19;
         while(wordSearch[V][H] == null) {
            V++;
            H--;
         }
         
         V -= currentWord.length() + 1;
         H += currentWord.length() + 1;
   
         for(int i = 0; i < currentWord.length(); i++) { // places on left side
         String character = "" + currentWord.charAt(i);
         wordSearch[V + i][H - i] = character; // adds word to wordSearch lower case
         wordSearchKey[V + i][H - i] = character.toUpperCase(); // adds the word all caps
         } // end of for
      } // end of if
   }

    //Method to print word search
   public static void printWS() {
      if(wordSearch[0][0] == null) {
         System.out.println("\nPlease Generate A Word Search To Print.\n");
      }
      else {
         System.out.println("\nYOUR WORDS:");
         for(String word: words) {
            System.out.println(word);
         }
         System.out.println();
           // for loop to print wordsearch
         for(int i = minV; i <= maxV; i++) {
            for(int j = minH; j <= maxH; j ++) {
               System.out.print(" " + wordSearch[i][j] + " ");
            } // end of for-loop j
            System.out.println();
         } // end of for-loop i
         System.out.println();
      }
      menu();
   } // end print word search

    //Method to print answer key
   public static void printAK() {
      if(wordSearch[0][0] == null) {
         System.out.println("\nPlease Generate A Word Search To Print.\n");
      }
      else {
         System.out.println("\nYOUR WORD SEARCH ANSWER KEY:");
           // for loop to print wordsearch key
         for(int i = minV; i <= maxV; i++) {
            for(int j = minH; j <= maxH; j ++) {
               System.out.print(" " + wordSearchKey[i][j] + " ");
            } // end of for-loop j
            System.out.println();
         } // end of for-loop i
         System.out.println();
      }
      menu();
   } // end answer key
} // end of class
