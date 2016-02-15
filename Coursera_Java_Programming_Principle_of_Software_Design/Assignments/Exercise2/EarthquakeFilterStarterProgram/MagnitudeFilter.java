
/**
 * Write a description of MagnitudeFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MagnitudeFilter implements Filter 
{
    private double min_magnitude;
    private double max_magnitude;
    
    public MagnitudeFilter(double min0, double max0) {
        min_magnitude = min0;
        max_magnitude = max0;
    }
    
    public boolean satisfies(QuakeEntry qe) {
        return (qe.getMagnitude() >= min_magnitude & qe.getMagnitude() <= max_magnitude);
    }    

}
