
/**
 * Write a description of DepthFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class DepthFilter implements Filter {    
    private double min_depth;
    private double max_depth;
    
    public DepthFilter (double min0, double max0) {
        min_depth = min0;
        max_depth = max0;
    }
    
    public boolean satisfies(QuakeEntry qe) {
        return (qe.getDepth() >= min_depth & qe.getDepth() <= max_depth);
    }

}
