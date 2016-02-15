
/**
 * Write a description of class MarkovZero here.
 * 
 * @author Duke Software
 * @version 1.0
 */

import java.util.*;
//import java.util.Random;

public class EfficientMarkovModel extends AbstractMarkovModel {
    private int keyLength; 
    private HashMap<String, ArrayList<String> > myHashMap;
    
    public EfficientMarkovModel(int N) {
        keyLength = N;
        myRandom = new Random();
        myHashMap = new HashMap<String, ArrayList<String> >();
        
    }
    
    public void setRandom(int seed){
        myRandom = new Random(seed);
    }
    
    public void setTraining(String s){
        myText = s.trim();
    }
    
    private void buildMap(String key, ArrayList<String> vals) {
        myHashMap.put(key, vals);
    }
    
    protected ArrayList<String> getFollows(String key) {
        if (myHashMap.containsKey(key)) {
            return myHashMap.get(key);
        }
        
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
        buildMap(key, follows);
        printHashMapInfo();
        return follows;
    }
    
    
    public void printHashMapInfo() {
        int sizeThreshold = 10;
        if (myHashMap.size() < sizeThreshold) {
            Iterator<Map.Entry<String, ArrayList<String>>> entries = myHashMap.entrySet().iterator();
            while (entries.hasNext()) {
                Map.Entry<String, ArrayList<String>> entry = entries.next();
                System.out.println("Key: " + entry.getKey() + ", val : " + entry.getValue());
            }
        } else {
            System.out.println("HashMap size > " + sizeThreshold + "\n");
            System.out.println("size of the map : " + myHashMap.size());
            String keyOfLongestArray = "";
            int largestSize;
            largestSize = 0;
            
            Iterator<Map.Entry<String, ArrayList<String>>> entries = myHashMap.entrySet().iterator();
            while (entries.hasNext()) {
                Map.Entry<String, ArrayList<String>> entry = entries.next();
                if (entry.getValue().size() > largestSize) {
                    largestSize = entry.getValue().size();
                    keyOfLongestArray = entry.getKey();
                }
            }
            System.out.println("length of the longest array: " + largestSize);
            System.out.println("key of the longest array: " + keyOfLongestArray);
            
        }
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
    
    public String toString() {
        return "MarkovModel of order " + keyLength;
    }
}
