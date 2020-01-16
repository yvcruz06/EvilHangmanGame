import java.util.Scanner;
import java.util.Arrays;
import java.io.File;
/**
 * Write a description of class EvilHangmanMain here.
 * 
 * @author Yvonne Cruz
 * @version 
 */
public class EvilHangmanMain
{
    public static void main(String []args) throws Exception  {
        boolean restart = true;
        while (restart) { 
            ArrayList<String> possibleWords = new ArrayList<String>();
            int wordSize;
            String matched = "-";
            String guess;
            String guessBank = "";
            boolean again = true;
            boolean showWL = false; 
            File file = new File("dictionary.txt"); 
            Scanner fs = new Scanner(file); 
            Scanner kb = new Scanner(System.in);
            
            System.out.println();
            System.out.println("Hello, Welcome to Hangman Game"); // start ask for word size, loop until correct input
            do {
                System.out.print("How many letters would you like in the word? ");
                wordSize = kb.nextInt();
            } while (wordSize < 2 || wordSize > 29 || wordSize == 26 || wordSize == 27);
            
            //find words with same length and store
            while (fs.hasNextLine()){
                String temp = fs.nextLine();
                if (temp.length() == wordSize) {
                    possibleWords.add(temp);
                }
            } 
            //create matched string 
            for (int j = 1; j < wordSize; j++) {
                matched = matched+"-";
            }
            
            
            System.out.print("How many guesses would you like? ");
            int guessCount = kb.nextInt();
            
            //ask if running total words in possible words should be displayed
            System.out.print("One more thing... want to see the running total of words in list? (y/n)");
            char w = kb.next().charAt(0);
            if (w == 'y')
                showWL = true;
            
            while (again) {
                System.out.println();
                System.out.println("Guess this word: "+matched);
                System.out.println("You have "+guessCount+" guesses left");
                System.out.println("Already guessed letters:"+guessBank);
                if (showWL)
                    System.out.println("Amount of words in possible word list: "+possibleWords.getLength());
                
                guess = guessCheck(guessBank);
                guessBank += " "+guess;
                
                String key = "";
                ArrayList<String> keys = new ArrayList<String>();
                Dictionary<String,ArrayList<String>> category = new Dictionary<String,ArrayList<String>>();
                for (int e = 1; e <= possibleWords.getLength(); e++) {
                    String word = possibleWords.getEntry(e);
                    key = pattern(word,guess,matched);
                    keys.add(key);
                    ArrayList<String> alist = category.getValue(key);    
                    if (alist == null){
                        ArrayList<String> blist = new ArrayList<String>();
                        blist.add(word);
                        category.add(key,blist);
                    }    
                    else {
                        alist.add(word);
                        category.add(key,alist);
                    }
            
                }    
                
                String bigKey = getBigKey(category,keys);//finds key with biggest array
                if (matched.equals(bigKey)) {  //if big key chosen did not have guess letter
                    guessCount--;               //then count goes down
                }
                //update possibleWords and delete words not in big key array value
                matched = bigKey;
                
                for (int z = 1; z <= possibleWords.getLength(); z++) {
                        ArrayList<String> temp = category.getValue(matched);
                        if (possibleWords.getEntry(z) != null && possibleWords.getLength() > 1) {
                            if (!temp.contains(possibleWords.getEntry(z))) {
                                possibleWords.remove(z);
                                z--;
                            }  
                        }   
                    }
                System.out.println();
                    //check to see if they won or are out of tries
                if (guessCount <= 0) {
                    again = false;
                    System.out.println("You ran out of tries! :(");
                    System.out.println("Answer was: "+possibleWords.getEntry(1));
                    System.out.print("You lost, better luck next time... Try Again? (y/n) ");
                    char response = kb.next().charAt(0);
                    if (response == 'n') 
                        restart = false;
                }
                if (matched.indexOf('-') < 0) {
                    again = false;
                    System.out.println("You guessed the word!!");
                    System.out.println(possibleWords.getEntry(1));
                    System.out.print("Wow you won! Congrats.. wanna play again? (y/n) ");
                    char response = kb.next().charAt(0);
                    if (response == 'n') 
                        restart = false;
                }
            }    
        }
        
    }
    
    public static String guessCheck(String bank) {
        Scanner kb = new Scanner(System.in);
        boolean alreadyGuessed = false;
        String guess;
        do { 
            alreadyGuessed = false;
            System.out.print("Guess a letter: ");
            guess = kb.next();
            for (int k = 0; k < bank.length(); k++) {
                if (bank.substring(k,k+1).equals(guess))
                    alreadyGuessed = true;
            }
        } while (guess.length() != 1 || alreadyGuessed || !Character.isLetter(guess.charAt(0)));
        return guess;
    }
    
    public static String pattern(String word, String guess, String matched) {
        String key = matched;
        for (int i = 0; i < word.length(); i++) {
            if (word.charAt(i) == guess.charAt(0))
                key = key.substring(0,i)+guess+key.substring(i+1);    
        }
        return key;
    }
    
    public static String getBigKey(Dictionary<String,ArrayList<String>> category, ArrayList<String> keysRef) {
        String bk = "";
        int length = 0;
        for (int i = 1; i <= keysRef.getLength(); i++) {
            if (keysRef.getEntry(i) != null) {
                if  (category.getValue(keysRef.getEntry(i)).getLength() > length) { //gets each key and finds the arraylist of it and gets the length
                    length = category.getValue(keysRef.getEntry(i)).getLength();   //stores largest length of array and the key it belongs to
                    bk = keysRef.getEntry(i);
                }
            }    
        }
        return bk;
    }
    
    
    
    
}
