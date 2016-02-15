import java.util.*;
import edu.duke.*;

public class EarthQuakeClient2
{
    public EarthQuakeClient2() {
        // TODO Auto-generated constructor stub
    }

    public ArrayList<QuakeEntry> filter(ArrayList<QuakeEntry> quakeData, Filter f) { 
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        for(QuakeEntry qe : quakeData) { 
            if (f.satisfies(qe)) { 
                answer.add(qe); 
            } 
        } 
        return answer;
    } 

    public void quakesWithFilter() { 
        EarthQuakeParser parser = new EarthQuakeParser(); 
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        //String source = "data/nov20quakedatasmall.atom";
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);         
        System.out.println("read data for "+list.size()+" quakes");

        EarthQuakeClient2 eqc = new EarthQuakeClient2(); 
        
        // Q1
        /*
        Filter f = new MinMagFilter(4.0); 
        Filter f1 = new MagnitudeFilter(4.0, 5.0);
        Filter f2 = new DepthFilter(-35000.0, -12000.0);
        ArrayList<QuakeEntry> res1  = eqc.filter(list, f1); 
        ArrayList<QuakeEntry> res2 = eqc.filter(res1, f2);
        
        for (QuakeEntry qe: res2) { 
            System.out.println(qe);
        }
        */
        
        // Q2
        /*
        Location Japan = new Location(35.42, 139.43);
        Filter f1 = new DistanceFilter(Japan, 10000000.0);
        Filter f2 = new PhraseFilter("end", "Japan");
        ArrayList<QuakeEntry> res1 = eqc.filter(list, f1);
        ArrayList<QuakeEntry> res2 = eqc.filter(res1, f2);
        for (QuakeEntry qe : res2) {
            System.out.println(qe);
        }
        */

        // Q3    
        Location Denver = new Location(39.7392, -104.9903);
        Filter f1 = new DistanceFilter(Denver, 1000000.0);
        Filter f2 = new PhraseFilter("end", "a");
        ArrayList<QuakeEntry> res1 = eqc.filter(list, f1);
        ArrayList<QuakeEntry> res2 = eqc.filter(res1, f2);
        for (QuakeEntry qe : res2) {
            System.out.println(qe);
        }
        System.out.println("matched : " + res2.size());
        
    }
    
    public void testMatchAllFilter() {
        EarthQuakeParser parser = new EarthQuakeParser(); 
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        //String source = "data/nov20quakedatasmall.atom";
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);         
        System.out.println("read data for "+list.size()+" quakes");
        
        // Q1
        /*
        MatchAllFilter maf = new MatchAllFilter();
        Filter f1 = new MagnitudeFilter(0.0, 2.0);
        Filter f2 = new DepthFilter(-100000.0, -10000.0);
        maf.addFilter(f1);
        maf.addFilter(f2);
        */
       
       // Q2
       /*
       MatchAllFilter maf = new MatchAllFilter();
       Filter f1 = new MagnitudeFilter(3.5, 4.5);
       Filter f2 = new DepthFilter(-55000.0, -20000.0);
       maf.addFilter(f1);
       maf.addFilter(f2);
        
       ArrayList<QuakeEntry> res = filter(list, maf);
       for (QuakeEntry qe : res) {
           System.out.println(qe);
       }  
       System.out.println("matched : " + res.size());
       */

       // Q3 
       MatchAllFilter maf = new MatchAllFilter();
       Filter f1 = new MagnitudeFilter(1.0, 4.0);
       Filter f2 = new DepthFilter(-180000.0, -30000.0);
       Filter f3 = new PhraseFilter("any", "o");
       maf.addFilter(f1);
       maf.addFilter(f2);
       maf.addFilter(f3);
        
       ArrayList<QuakeEntry> res = filter(list, maf);
       for (QuakeEntry qe : res) {
           System.out.println(qe);
       }  
       System.out.println("matched : " + res.size());

       
    }

    public void testMatchAllFilter2() {
        EarthQuakeParser parser = new EarthQuakeParser(); 
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        //String source = "data/nov20quakedatasmall.atom";
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);         
        System.out.println("read data for "+list.size()+" quakes");
        
        // Q1
        /*
        MatchAllFilter maf = new MatchAllFilter();
        Filter f1 = new MagnitudeFilter(0.0, 3.0);
        Location TULSA = new Location(36.1314, -95.9372);
        Filter f2 = new DistanceFilter(TULSA, 10000000.0);
        Filter f3 = new PhraseFilter("any", "Ca");
        maf.addFilter(f1);
        maf.addFilter(f2);
        maf.addFilter(f3);
        
        ArrayList<QuakeEntry> res = filter(list, maf);
        for (QuakeEntry qe : res) {
            System.out.println(qe);
        }                
        */
       
        // Q2    
        MatchAllFilter maf = new MatchAllFilter();
        Filter f1 = new MagnitudeFilter(0.0, 5.0);
        Location BILLUND = new Location(55.7308, 9.1153);
        Filter f2 = new DistanceFilter(BILLUND, 3000000.0);
        Filter f3 = new PhraseFilter("any", "e");
        maf.addFilter(f1);
        maf.addFilter(f2);
        maf.addFilter(f3);
        
        ArrayList<QuakeEntry> res = filter(list, maf);
        for (QuakeEntry qe : res) {
            System.out.println(qe);
        }                
        System.out.println("matched : " + res.size());
    }
        
    public void createCSV(){
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "../data/nov20quakedata.atom";
        String source = "data/nov20quakedatasmall.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        dumpCSV(list);
        System.out.println("# quakes read: "+list.size());
    }

    public void dumpCSV(ArrayList<QuakeEntry> list){
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
