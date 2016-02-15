
/**
 * Write a description of DistanceFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class DistanceFilter implements Filter {
    
    private Location loc;
    private double max_d;
    
    public DistanceFilter(Location loc0, double d0) {
        loc = new Location(loc0);
        max_d = d0;
    }
    
    public boolean satisfies(QuakeEntry qe) {
        return (loc.distanceTo(qe.getLocation()) < max_d);
    }

}
