package navy_hw1.demo;
import java.io.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import entities.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;


@Controller
@EnableAutoConfiguration
public class JottoController {

    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private PlaysRepository playsRepository;

    //ends all the symbols

    @RequestMapping(value = "/game", method = RequestMethod.GET)
    public String jotto(@RequestParam(name="username") String username, ModelMap map) throws IOException {
        File file;
        file = new File("words.txt");////put your own url
        BufferedReader br = new BufferedReader(new FileReader(file));
        new PrintWriter("wordlist.txt").close();
        FileWriter fw = new FileWriter("wordlist.txt");
        BufferedWriter bufw = new BufferedWriter(fw);
        String st;
        while ((st = br.readLine()) != null) {
            bufw.write(st);
            bufw.newLine();}
        bufw.flush();
        bufw.close();
        br.close();
        //单个数据
        UserForm user = new UserForm();
        user.setUsername(username);
        map.put("Guestword", user);
        return "jotto";
    }

    @RequestMapping(value="/jottogame",method={RequestMethod.POST,RequestMethod.GET})//
    public String add(ModelMap model, @ModelAttribute UserForm user) throws IOException {

        String Userword=user.getguessword();
        String username = user.getUsername();
        int valid=validWord(Userword);
        if (valid ==0){
            user = new UserForm();
            user.setUsername(username);
            model.put("Guestword", user);
            return "jotto";
        }
        else
        {
            ArrayList<String> wordlist = new ArrayList<String>();
            File file;
            file = new File("wordlist.txt");////put your own url
            BufferedReader br = new BufferedReader(new FileReader(file));
            String st;
            while ((st = br.readLine()) != null) {
                wordlist.add(st);}
            int random = (int)(Math.random() * 5683 + 1);
            String compWord=wordlist.get(random);
            /*FileWriter fw = new FileWriter("choosewordList.txt");// the list of word choose by computer or user
            BufferedWriter bufw = new BufferedWriter(fw);
            bufw.write(Userword);
            bufw.newLine();
            bufw.write(compWord);
            bufw.newLine();
            bufw.flush();
            bufw.close();*/
            br.close();
            UserForm user2 = new UserForm();

            // create game
            Game game = new Game();
            game.setPlayerID(username);
            game.setDateStarted(new Timestamp(System.currentTimeMillis()));
            game.setPlayerWord(Userword);
            game.setComputerWord(compWord);
            int gameID = gameRepository.save(game).getGameID();
            // game stored in database

            user2.setUsername(username);
            user2.setGameID(gameID);
            user2.setTurnNum(1);
            if (!gameRepository.findByGameID(user.getGameID()).isEmpty()) {
                model.put("username", username);
                model.put("gameID", gameID);
                model.put("turnNum", 1);
            }

            String newGuess="please guess word";
            model.put("userword",Userword);
            model.put("word",newGuess);
            model.put("Guestword", user2);

            return "JottoGame";}
    }
    @RequestMapping(value="/Gottogame",method={RequestMethod.POST,RequestMethod.GET})//
    public String jottogame(ModelMap model, @ModelAttribute UserForm user) throws IOException {
        String Userword=user.getguessword();
        String username = user.getUsername();
        int gameID = user.getGameID();
        int turnNum = user.getTurnNum();
// all the list we need
        //File file;
        //file = new File("choosewordList.txt");
        //BufferedReader br = new BufferedReader(new FileReader(file));
        String userWordO = gameRepository.findByGameID(gameID).get(0).getPlayerWord();
        String compWordO = gameRepository.findByGameID(gameID).get(0).getComputerWord();
        //br.close();
        ArrayList<String> userGuessWordList = new ArrayList<String>();
        ArrayList<String> compGuessWordList = new ArrayList<String>();
        ArrayList<String> userrightLetter = new ArrayList<String>();
        ArrayList<String> comprightLetter = new ArrayList<String>();
        ArrayList<Character> comprightLetterC = new ArrayList<Character>();
        ArrayList<Character> rightChar = new ArrayList<Character>();
// end of all the list we need
//get all the list file
        //userGuessWordList=BuRead1("userGuessWordList.txt");//get guess list user
        List<Plays> turns = playsRepository.findByPlaysIDGameID(gameID);
        for (Plays turn : turns) {
            userGuessWordList.add(turn.getPlayerGuess());//get guess list user
            compGuessWordList.add(turn.getComputerGuess());//get computer guess list
            for (int i = 0; i < turn.getComputerLettersCorrect().length(); i++) {
                if (comprightLetterC.indexOf(turn.getComputerLettersCorrect().charAt(i)) == -1) {
                    comprightLetterC.add(turn.getComputerLettersCorrect().charAt(i));
                }
            }
            comprightLetter.add(Integer.toString(turn.getComputerLettersCorrect().length()));// get the number of letter computer is right
            userrightLetter.add(Integer.toString(turn.getPlayerLettersCorrect().length()));// get the list of number user right
        }
        int valid=validWord(Userword);
      //ArrayList<ArrayList> sepeartletterUser = new ArrayList<ArrayList>();
        ArrayList<ArrayList> sepeartletterComputer = new ArrayList<ArrayList>();
        //for (int i=0;i<userGuessWordList.size();i++){
        //      sepeartletterUser.add(castwordsTochar(userGuessWordList.get(i))); }
        for (int i=0;i<compGuessWordList.size();i++){
            sepeartletterComputer.add(castwordsTochar(compGuessWordList.get(i))); }
        ArrayList<Character> userwordscharater = new ArrayList<Character>();
        userwordscharater=castwordsTochar(userWordO);

        if (valid ==0){//invalid word, no track
            String invalid="Invalid word";
          
            user = new UserForm();

            user.setUsername(username);
            user.setGameID(gameID);
            user.setTurnNum(turnNum);

            model.put("userword",userwordscharater);
            model.put("userguessword", userGuessWordList);
            model.put("numberuser",userrightLetter);
            model.put("compguessword", sepeartletterComputer);
            model.put("numbercomp",comprightLetter);
            model.put("Guestword", user);
            model.put("error",invalid);
            return "JottoGame";
        }
        //load all the file for game compare


        userGuessWordList.add(Userword);
        //BuWriter1("userGuessWordList.txt",userGuessWordList);// set guess list user
        ArrayList<String> wordlist = new ArrayList<String>();
        wordlist=BuRead1("wordlist.txt");
        int random = (int)(Math.random() * wordlist.size());
        String compWord=wordlist.get(random);//computer guess letter
        compGuessWordList.add(compWord);
        //BuWriter1("compGuessWordList.txt",compGuessWordList);// set computer guess list

        int compRightUser=guessWord(compWord,userWordO,comprightLetterC);// figure out the world is right this time computer
        if (compRightUser==-1){
            model.put("gameEnd", "Sorry you lose");
            model.put("wordname",compWordO );
            return "winOrlose";}
        comprightLetter.add(Integer.toString(compRightUser));
        //BuWriter1("comprightLetterC.txt",castTostring(comprightLetterC));// set the letter computer back
        //BuWriter1("comprightLetter.txt",comprightLetter);// set the number of letter computer back

        int userRightUser=guessWord(Userword,compWordO,rightChar);//figure out the number is right this time user
        if (userRightUser==-1){
            model.put("gameEnd", "Congratulations, you win");
            model.put("wordname",compWordO );
            return "winOrlose";}
        userrightLetter.add(Integer.toString(userRightUser));
        //BuWriter1("userrightLetter.txt",userrightLetter);//write the number back user
        // all the list are finish
        // delete from the word list
        int foundletter=comprightLetterC.size();
        //	  System.out.println("rightCharCompsize: "+foundletter);
        int compareletter=0;
        for (int i=0;i<wordlist.size();i++) {
            compareletter=0;
            String x=wordlist.get(i);
            if (x.equals(compWord)) {
                wordlist.remove(i);
                i--;}
            else {
                for (int k=0;k<x.length();k++) {
                    char c1=x.charAt(k);
                    for (int b=0;b<comprightLetterC.size();b++) {
                        char z= comprightLetterC.get(b);
                        if (c1==z) {
                            compareletter++;
                            //b=rightCharComp.size();
                        }}}
                if (foundletter!=0&&compareletter!=foundletter) {
                    wordlist.remove(i);
                    i--;}
            }}
        BuWriter1("wordlist.txt",wordlist);

        // create turn
        String playerCorrect = "";
        for (char c : rightChar) {
            playerCorrect = playerCorrect + c;
        }
        String computerCorrect = "";
        for (char c : comprightLetterC) {
            computerCorrect = computerCorrect + c;
        }
        Plays turn = new Plays();
        turn.setTurnNumber(turnNum);
        turn.setGameID(gameID);
        turn.setPlayerID(username);
        turn.setPlayerGuess(Userword);
        turn.setComputerGuess(compWord);
        turn.setPlayerLettersCorrect(playerCorrect);
        turn.setComputerLettersCorrect(computerCorrect);
        playsRepository.save(turn);
        // turn stored in database


        user = new UserForm();

        user.setUsername(username);
        user.setGameID(gameID);
        user.setTurnNum(turnNum + 1);

        String newGuess="please guess word";
      
        sepeartletterComputer.add(castwordsTochar(compGuessWordList.get(compGuessWordList.size()-1)));

        model.put("userword",userwordscharater);
        model.put("userguessword", userGuessWordList);
        model.put("numberuser",userrightLetter);
        model.put("compguessword", sepeartletterComputer);
        model.put("numbercomp",comprightLetter);

        model.put("word",newGuess);
        model.put("Guestword", user);

        return "JottoGame";
    }

    public static int guessWord(String guess1, String compare, ArrayList<Character> rightChar) {
        boolean inList=false;
        int Boolean =0;
        if (guess1.equals(compare)) {
            Boolean=-1;
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
                        Boolean++;
                        if (inList==false)
                            rightChar.add(x);}}

                inList=false;
            }}

        return Boolean;}


    public static int validWord( String words) throws IOException {// 0 invalid 1 valid
        ArrayList<String> wordlist = new ArrayList<String>();
        File file = new File("words.txt");//put your own url
        BufferedReader br = new BufferedReader(new FileReader(file));
        String st;
        while ((st = br.readLine()) != null) {
            wordlist.add(st);}
        int compareletter=0;;
        for (int i=0;i<wordlist.size();i++) {
            String x=wordlist.get(i);
            if (x.equals(words)) {
                compareletter=1;
            }}
        if (compareletter==0) {
            return 0;
        }

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

    public static ArrayList<String> BuRead1(String url) throws IOException {
        ArrayList<String> List = new ArrayList<String>();
        File file;
        file = new File(url);////put your own url
        BufferedReader br = new BufferedReader(new FileReader(file));
        String st;
        while ((st = br.readLine()) != null) {
            List.add(st);}
        br.close();

        return List;

    }
    public void BuWriter1(String url,ArrayList<String> List) throws IOException {
        FileWriter fw = new FileWriter(url);// the list of word choose by computer or user
        BufferedWriter bufw = new BufferedWriter(fw);
        for (int i=0;i<List.size();i++){
            bufw.write(List.get(i));
            bufw.newLine(); }
        bufw.flush();
        bufw.close();
    }
    public static ArrayList<Character> castTochar(ArrayList<String> List){
        ArrayList<Character> chara = new ArrayList<Character>();
        for (int i=0;i<List.size();i++){
            char x=List.get(i).charAt(0);
            chara.add(x);
        }
        return chara;
    }
    public static ArrayList<String> castTostring(ArrayList<Character> List){
        ArrayList<String> chara = new ArrayList<String>();
        for (int i=0;i<List.size();i++){
            String x=List.get(i).toString();
            chara.add(x);
        }
        return chara;
    }
    public static ArrayList<Character> castwordsTochar(String words){
        ArrayList<Character> chara = new ArrayList<Character>();
        for (int i=0;i<words.length();i++){
            char x=words.charAt(i);
            chara.add(x);
        }
        return chara;
    }







}