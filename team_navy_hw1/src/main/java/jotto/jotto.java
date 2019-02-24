package jotto;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;



public class jotto {
	public static void main(String[] args) throws IOException {
		//start all the symbols 
		boolean user =false;
		boolean comp =false;
		ArrayList<String> wordlist = new ArrayList<String>();
		File file = new File("src/jotto/words.txt"); 
		  BufferedReader br = new BufferedReader(new FileReader(file));   
		  String st; 
		  while ((st = br.readLine()) != null) {
			  wordlist.add(st);}
		//System.out.println("listsize: "+wordlist.size());
		  String userWord, compWord;
		  ArrayList<String> userGuess = new ArrayList<String>();
		  String userGuess1, compGuess1;
		  ArrayList<String> compGuess = new ArrayList<String>();
		  ArrayList<Character> rightChar = new ArrayList<Character>();
		  ArrayList<Character> rightCharComp = new ArrayList<Character>();
		  //ends all the symbols
		  
		    
		// start user input word
		Scanner scan = new Scanner(System.in);
		System.out.println("pleas type a valid 5 letter word with no repeating characters");
		String words = scan.next();
		//System.out.println(words.length());
		int valid=validWord(words);
		  while (valid ==0) {
			  System.out.println("invalid world");
			  System.out.println("pleas type a valid 5 letter word with no repeating characters: ");
			  words = scan.next();
			  valid=validWord(words);}
		  userWord=words;
		// ends user input word
		  
		  
		// computer input and first guess words
		  int random = (int)(Math.random() * 5683 + 1);
		  compWord=wordlist.get(random);
		  random = (int)(Math.random() * 5683 + 1);
		  //System.out.println(compWord);
	//ends computers 
while (user==false&&comp==false) {
		  //user guess word

		  System.out.println("Guess the words please: ");
		  words = scan.next();
		  valid=validWord(words);
		  while (valid ==0) {
			  System.out.println("invalid world");
			  System.out.println("Guess the words please: ");
			  words = scan.next();
			  valid=validWord(words);}
		  userGuess1=words;
		  userGuess.add(userGuess1);
		  user=guessWord( userGuess1, compWord, rightChar);
		  System.out.println("the word you guess: "+userGuess);
		  System.out.println("the letter you guess right: "+rightChar);
		  if (user==true) {
			  System.out.println("Congratulations you win!");
			  System.exit(0);
		  }
		  // end user guess
		  
		 // start com guess
		  random = (int)(Math.random() * wordlist.size());
		  compGuess1=wordlist.get(random);
		  compGuess.add(compGuess1);
		  comp=guessWord(compGuess1, userWord, rightCharComp);
		  System.out.println("the word computer guess: "+compGuess);
		  System.out.println("the letter computer guess right: "+rightCharComp);
		  if (comp==true) {
			  System.out.println("sorry you lose!");
			  System.exit(0);}
		  
		 // end computer guess
		  
		 //start delete 
		  int foundletter=rightCharComp.size();
	//	  System.out.println("rightCharCompsize: "+foundletter);
		  int compareletter=0;
		  for (int i=0;i<wordlist.size();i++) {
			  compareletter=0;
			  String x=wordlist.get(i);
			  if (x.equals(compGuess1)) {
				  wordlist.remove(i);
				  i--;}
			  else {
				  for (int k=0;k<x.length();k++) {
					  char c1=x.charAt(k);
					  for (int b=0;b<rightCharComp.size();b++) {
							char z= rightCharComp.get(b);
							if (c1==z) {
								compareletter++;
								//b=rightCharComp.size();
								}}}
				  if (foundletter!=0&&compareletter!=foundletter) {
					  wordlist.remove(i);
					  i--;}
				  }}
	//System.out.println("listsize: "+wordlist.size());
		  }
		  
	}
		  
		  
		  
		 		  
		  
		  
		
		
	public static boolean guessWord(String guess1, String compare, ArrayList<Character> rightChar) {
		boolean inList=false;
		boolean Boolean =false;
		if (guess1.equals(compare)) {
			Boolean=true;
			 // System.out.println("Congratulations you win!");
			 // System.exit(0);
			  }
		  else {
		  for (int i=0;i<guess1.length();i++) {
				char x= guess1.charAt(i);
				for (int b=0;b<rightChar.size();b++) {
					char z= rightChar.get(b);
					if (x==z) {
						inList=true;
						}}
				for (int k=0;k<compare.length();k++) {
					char y= compare.charAt(k);
					if (x==y) {
						if (inList==false)
						rightChar.add(x);}}
				inList=false;
				}}
		return Boolean;}
	
    
	public static int validWord( String words) {

		if (words.length()!=5) {
			//System.out.println("me wrong 1");
			return 0;}
	for (int i=0;i<words.length();i++) {
		char x= words.charAt(i);
		for (int k=i+1;k<words.length();k++) {
		char y= words.charAt(k);
		if (x==y) {
			//System.out.println("me wrong 2"+x);
			return 0;}}}
	return 1;}
	
	
	
//	public static ArrayList<String> InputDictionary() {
//		ArrayList<String> wordlist = new ArrayList<String>();
//		wordlist.addAll();
//		
//	}
}
