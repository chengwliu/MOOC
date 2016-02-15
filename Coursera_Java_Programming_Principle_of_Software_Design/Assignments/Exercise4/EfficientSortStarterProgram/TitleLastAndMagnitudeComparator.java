
/**
 * Write a description of TitleLastAndMagnitudeComparator here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class TitleLastAndMagnitudeComparator implements Comparator<QuakeEntry>{
    public int compare(QuakeEntry q1, QuakeEntry q2) {
        String[] q1Splits = q1.getInfo().split(" ");
        String q1Last = q1Splits[q1Splits.length-1];
        String[] q2Splits = q2.getInfo().split(" ");
        String q2Last = q2Splits[q2Splits.length-1];
        
        if (q1Last.compareTo(q2Last)==0) {
            return Double.compare(q1.getDepth(), q2.getDepth());
        } else {
            return q1Last.compareTo(q2Last);
        }
    }
    
}
