
/**
 * Abstract class AbstractMarkovModel - write a description of the class here
 * 
 * @author (your name here)
 * @version (version number or date here)
 */

import java.util.*;

public abstract class AbstractMarkovModel implements IMarkovModel {
    protected String myText;
    protected Random myRandom;
    
    public AbstractMarkovModel() {
        myRandom = new Random();
    }
    
    public void setTraining(String s) {
        myText = s.trim();
    }
    
    protected ArrayList<String> getFollows(String key) {
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
    
 
    abstract public String getRandomText(int numChars);

}
