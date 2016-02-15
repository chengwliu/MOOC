
/**
 * Write a description of class MarkovZero here.
 * 
 * @author Duke Software
 * @version 1.0
 */

import java.util.*;
//import java.util.Random;

public class MarkovFour {
    private String myText;
    private Random myRandom;
    private int keyLength = 4;
    
    public MarkovFour() {
        myRandom = new Random();
        
    }
    
    public void setRandom(int seed){
        myRandom = new Random(seed);
    }
    
    public void setTraining(String s){
        myText = s.trim();
    }
    
    public ArrayList<String> getFollows(String key) {
        ArrayList<String> follows = new ArrayList<String>();
        int pos = 0;
        int L = myText.length();
        int W = key.length();
        while (true) {
            int index = myText.indexOf(key, pos);
            if (index==-1 | index >= L-W) {
                break;
            }
            follows.add(String.valueOf(myText.charAt(index+W)));
            pos = index + W;
        }
        return follows;
    }
    
    public String getRandomText(int numChars){
        if (myText == null){
            return "";
        }
        StringBuilder sb = new StringBuilder();
        int L = myText.length();
        int W = keyLength;
        int index = myRandom.nextInt(L-W);
        String key = myText.substring(index, index+W);
        sb.append(key);
        for(int k=1; k <= numChars-W; k++){
            ArrayList<String> follows = getFollows(key);
            if (follows.size() == 0) { break; }
            index = myRandom.nextInt(follows.size());
            String next = follows.get(index);
            sb.append(next);
            StringBuilder sb2 = new StringBuilder();
            key = sb2.append(key.substring(1, key.length())).append(next).toString(); 
        }
        
        return sb.toString();
    }
}
