import edu.duke.*;
import java.util.*;

/**
 * Write a description of Tester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Tester {
    
    public void testGetFollows() {
        MarkovOne markov = new MarkovOne();
        markov.setTraining("this is a test yes this is a test.");
        ArrayList<String> follows = markov.getFollows("es"); // t
        System.out.println("size of follows: " + follows.size());
        for (int k=0; k < follows.size(); k++) {
            System.out.println(follows.get(k));
        }
        System.out.println("");
    }
    
    
    public void testGetFollowsWithFile() {
		FileResource fr = new FileResource();
		String st = fr.asString();
		st = st.replace('\n', ' ');
		MarkovOne markov = new MarkovOne();
		markov.setTraining(st);
        ArrayList<String> follows = markov.getFollows("he"); 
        System.out.println("size of follows: " + follows.size());		        
    }
    
    
    //public static void main(String[] args) {
    //}

}
