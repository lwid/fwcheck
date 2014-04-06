package fwcheck.pojo;

/**
 * Created with IntelliJ IDEA.
 * User: Common
 * Date: 2014-04-06
 * Time: 12:47
 * To change this template use File | Settings | File Templates.
 */
public class FwRule {

    // TBD: Use some CIDR class for these instead
    private String source;
    private String dest;
    private int sport;
    private int dport;

    // This is to be set by the rules engine
    private boolean valid=false;

    public FwRule(String source, int sport, String dest, int dport) {
        this.source = source;
        this.dest = dest;
        this.sport = sport;
        this.dport = dport;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getDest() {
        return dest;
    }

    public void setDest(String dest) {
        this.dest = dest;
    }

    public int getSport() {
        return sport;
    }

    public void setSport(int sport) {
        this.sport = sport;
    }

    public int getDport() {
        return dport;
    }

    public void setDport(int dport) {
        this.dport = dport;
    }

    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }
}
