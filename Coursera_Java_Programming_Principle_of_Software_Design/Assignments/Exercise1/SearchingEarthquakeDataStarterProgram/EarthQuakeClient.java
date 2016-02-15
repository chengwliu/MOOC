import java.util.*;
import edu.duke.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EarthQuakeClient
{

    public EarthQuakeClient() {
        // TODO Auto-generated constructor stub
    }

    public ArrayList<QuakeEntry> filterByMagnitude(ArrayList<QuakeEntry> quakeData,
    double magMin) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        for (QuakeEntry qe : quakeData) {
            if (qe.getMagnitude() > magMin) {
                answer.add(qe);
            }
        }

        return answer;
    }

    public ArrayList<QuakeEntry> filterByDistanceFrom(ArrayList<QuakeEntry> quakeData,
    double distMax,
    Location from) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        for (QuakeEntry qe : quakeData) {
            if (qe.getLocation().distanceTo(from) <= distMax) {
                answer.add(qe);
            }
        }

        return answer;
    }
    
    public ArrayList<QuakeEntry> filterByDepth(ArrayList<QuakeEntry> quakeData, 
    double minDepth, double maxDepth) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();    
        for (QuakeEntry qe : quakeData) {
           if (qe.getDepth() > minDepth & qe.getDepth() < maxDepth) {
               answer.add(qe);
           }
        }
        return answer;
    }

    public ArrayList<QuakeEntry> filterByPhrase(ArrayList<QuakeEntry> quakeData,
    String where, String phrase) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        switch(where) {
            case "start": 
                for (QuakeEntry qe : quakeData) {
                    if (qe.getInfo().startsWith(phrase)) {
                        answer.add(qe);
                    }
                }
                break;
            case "end":
                for (QuakeEntry qe : quakeData) {
                    if (qe.getInfo().endsWith(phrase)) {
                        answer.add(qe);
                    }
                }
                break;
            case "any":
                Pattern compiled_pattern = Pattern.compile(phrase);                
                for (QuakeEntry qe : quakeData) {
                    Matcher m = compiled_pattern.matcher(qe.getInfo());
                    if (m.find()) {
                        answer.add(qe);
                    }
                }
                break;
            default:
                System.out.println("unrecognized: " + where);
                break;                        
        }
        return answer;
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

    public void bigQuakes() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size()+" quakes");
        
        /*
        for (QuakeEntry qe : list) {
            if (qe.getMagnitude() > 5.0) {
                System.out.println(qe);
            }
        }
        */
       
        ArrayList<QuakeEntry> listBig = filterByMagnitude(list, 5.0);
        for (QuakeEntry qe : listBig) {
            System.out.println(qe);
        }

    }

    public void closeToMe(){
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        //ArrayList<QuakeEntry> list  = parser.read(source);
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        
        System.out.println("read data for "+list.size()+" quakes");

        // This location is Durham, NC
        //Location city = new Location(35.988, -78.907);

        // This location is Bridgeport, CA
        Location city =  new Location(38.17, -118.82);

       ArrayList<QuakeEntry> listClosest = filterByDistanceFrom(list, 50000.0, city);
       System.out.println("Size of the result: " + listClosest.size());
       for(QuakeEntry qe : listClosest){
            //System.out.printf("%4.2f,%s\n",
            //    qe.getLocation().distanceTo(city),
            //    qe.getInfo());
            System.out.println(qe);
        }
    }
    
    public void quakesOfDepth(){
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        
        System.out.println("read data for "+list.size()+" quakes");
        
        double minDepth = -4000.0;
        double maxDepth = -2000.0;
        System.out.println("Find quakes with depth between " + minDepth + "and" + maxDepth);
        ArrayList<QuakeEntry> listInDepths = filterByDepth(list, minDepth, maxDepth);
        for (QuakeEntry qe : listInDepths) {
            System.out.println(qe);
        }
        System.out.println("matches: " + listInDepths.size());
    }
    
    public void quakesByPhrase() {
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        
        System.out.println("read data for "+list.size()+" quakes");
        
        ArrayList<QuakeEntry> filtered = filterByPhrase(list, "any", "Can");
        for (QuakeEntry qe : filtered) {
            System.out.println(qe);
        }                
        System.out.println("matches : " + filtered.size());
    }

   public void createCSV(){
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedata.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        dumpCSV(list);
        System.out.println("# quakes read: "+list.size());
    }
}
