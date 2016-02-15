
/**
 * Write a description of LargestQuakes here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */


import java.util.*;

public class LargestQuakes
{
    public ArrayList<QuakeEntry> getLargest(ArrayList<QuakeEntry> quakeData0, int howMany) {
        ArrayList<QuakeEntry> quakeData = new ArrayList<QuakeEntry>(quakeData0);
        ArrayList<QuakeEntry> res = new ArrayList<QuakeEntry>();
        for (int j=0; j < howMany; j++) {
            if (quakeData.isEmpty()) break; 
            int index_of_largest = indexOfLargest(quakeData);
            res.add(quakeData.get(index_of_largest));
            quakeData.remove(index_of_largest);
        }
        return res;
    }
    
    
    public int indexOfLargest(ArrayList<QuakeEntry> data) {
        int answer = -1;
        double largest_magnitude = 0;
        for (int i=0; i < data.size(); i++) {
            QuakeEntry qe = data.get(i);
            if (qe.getMagnitude() > largest_magnitude) {
                largest_magnitude = qe.getMagnitude();
                answer = i;
            }
        }
       return answer;  
    }
    
    public void findLargestQuakes() {
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        
        System.out.println("read data for "+list.size()+" quakes");
        
        //int index_of_largest = indexOfLargest(list);
        //System.out.println("index_of_largest : " + index_of_largest);
        //System.out.println(list.get(index_of_largest));
        
        ArrayList<QuakeEntry> filtered = getLargest(list, 20);
        for (QuakeEntry qe : filtered) {
            System.out.println(qe);
        }
        
    }
    
}
