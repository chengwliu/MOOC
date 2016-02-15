import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
 * Write a description of PhraseFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class PhraseFilter implements Filter {
    
    private String where;
    private String phrase;
    
    public PhraseFilter(String where0, String phrase0) {
        where = where0;
        phrase = phrase0;
    }
    
    public boolean satisfies(QuakeEntry qe) {
        switch(where) {
            case "start":
                return qe.getInfo().startsWith(phrase);
            case "end":
                return qe.getInfo().endsWith(phrase);
            case "any":
                Pattern compiled_pattern = Pattern.compile(phrase);
                Matcher m = compiled_pattern.matcher(qe.getInfo());
                return m.find();
            default:
                System.out.println("unrecognized : " + where);
                return false;
        }
    }

}
