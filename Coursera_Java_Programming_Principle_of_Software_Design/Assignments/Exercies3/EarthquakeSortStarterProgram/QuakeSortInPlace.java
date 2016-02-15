
/**
 * Write a description of class QuakeSortInPlace here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;
import edu.duke.*;

public class QuakeSortInPlace {
    public QuakeSortInPlace() {
        // TODO Auto-generated constructor stub
    }
       
    
    public boolean checkInSortedOrder(ArrayList<QuakeEntry> quakes) {
        // check the array is sordted in magnitude from SMALLEST to LARGEST
        for (int i=0; i < quakes.size()-1; i++) {
            if (quakes.get(i).getMagnitude() > quakes.get(i+1).getMagnitude()) {
                return false;
            }
        }
        return true;
    }

    
    public void sortByMagnitudeWithBubbleSortWithCheck(ArrayList<QuakeEntry> quakes) {
        int numbSorted;
        for (numbSorted=0; numbSorted < quakes.size()-1; numbSorted++) {
            if (checkInSortedOrder(quakes)) {
                break;
            }
            onePassBubbleSort(quakes, numbSorted);
        }
        System.out.println(numbSorted + " number of passes performed");        
    }   
    
    public void onePassBubbleSort(ArrayList<QuakeEntry> quakes, int numbSorted) {
        for (int i=0; i <= quakes.size()-2-numbSorted; i++) {
            if (quakes.get(i).getMagnitude() > quakes.get(i+1).getMagnitude()) {
                QuakeEntry qi = quakes.get(i);
                QuakeEntry qi1 = quakes.get(i+1);
                quakes.set(i, qi1);
                quakes.set(i+1, qi);
            }
        }
    }

    
    public void sortByMagnitudeWithBubbleSort(ArrayList<QuakeEntry> quakes) {
        System.out.println("Initial:");
        for (QuakeEntry qe: quakes) { 
            System.out.println(qe);
        } 

        for (int numbSorted=0; numbSorted < quakes.size()-1; numbSorted++) {
            onePassBubbleSort(quakes, numbSorted);
            System.out.println("--");            
            for (QuakeEntry qe: quakes) { 
                System.out.println(qe);
            } 
            System.out.println("");
        }
    }

    
    public void sortByLargestDepth(ArrayList<QuakeEntry> in) {
        for (int i=0; i < 50; i++) {
            int largestIdx = getLargestDepth(in, i);
            QuakeEntry qi = in.get(i);
            QuakeEntry qL = in.get(largestIdx);
            in.set(i, qL);
            in.set(largestIdx, qi);            
        }

        /*
        for (int i=0; i < in.size(); i++) {
            int largestIdx = getLargestDepth(in, i);
            QuakeEntry qi = in.get(i);
            QuakeEntry qL = in.get(largestIdx);
            in.set(i, qL);
            in.set(largestIdx, qi);            
        }
        */
    }

    
    public int getLargestDepth(ArrayList<QuakeEntry> quakes, int from) {
        int largestIdx = from;
        for (int i=from+1; i < quakes.size(); i++) {
            if (quakes.get(i).getDepth() > quakes.get(largestIdx).getDepth()) {
                largestIdx = i;
            }
        }
        return largestIdx;
    }

    
    public int getSmallestMagnitude(ArrayList<QuakeEntry> quakes, int from) {
        int minIdx = from;
        for (int i=from+1; i< quakes.size(); i++) {
            if (quakes.get(i).getMagnitude() < quakes.get(minIdx).getMagnitude()) {
                minIdx = i;
            }
        }
        return minIdx;
    }

    
    public void sortByMagnitude(ArrayList<QuakeEntry> in) {
       
       for (int i=0; i< in.size(); i++) {
            int minIdx = getSmallestMagnitude(in,i);
            QuakeEntry qi = in.get(i);
            QuakeEntry qmin = in.get(minIdx);
            in.set(i,qmin);
            in.set(minIdx,qi);
        }
        
    }


    public void sortByMagnitudeWithCheck(ArrayList<QuakeEntry> in) {
        int i;
        for (i=0; i < in.size(); i++) {
            if (checkInSortedOrder(in)) {
                break;
            }
            int minIdx = getSmallestMagnitude(in,i);
            QuakeEntry qi = in.get(i);
            QuakeEntry qmin = in.get(minIdx);
            in.set(i,qmin);
            in.set(minIdx,qi);            
        }
        System.out.println(i + " number of passes performed");                
    }
    
    public void testSort() {
        EarthQuakeParser parser = new EarthQuakeParser(); 
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        //String source = "data/nov20quakedatasmall.atom";
        //String source = "data/earthquakeDataSampleSix2.atom";
        //String source = "data/nov20quakedata.atom";
        //String source = "data/earthQuakeDataWeekDec6sample2.atom";
        //String source = "data/earthquakeDataSampleSix2.atom";
        String source = "data/earthQuakeDataWeekDec6sample1.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);  
       
        System.out.println("read data for "+list.size()+" quakes");    
        //sortByMagnitude(list);
        //sortByLargestDepth(list);
        //sortByMagnitudeWithBubbleSort(list);
        sortByMagnitudeWithBubbleSortWithCheck(list);
        //sortByMagnitudeWithCheck(list);
        //for (QuakeEntry qe: list) { 
        //    System.out.println(qe);
        //} 
        
    }

    
    public void createCSV() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "data/nov20quakedata.atom";
        String source = "data/nov20quakedatasmall.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        dumpCSV(list);
        System.out.println("# quakes read: " + list.size());
    }

    
    public void dumpCSV(ArrayList<QuakeEntry> list) {
        System.out.println("Latitude,Longitude,Magnitude,Info");
        for(QuakeEntry qe : list){
            System.out.printf("%4.2f,%4.2f,%4.2f,%s\n",
                              qe.getLocation().getLatitude(),
                              qe.getLocation().getLongitude(),
                              qe.getMagnitude(),
                              qe.getInfo());
        }
        
    }
}
