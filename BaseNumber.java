public class BaseNumber {

    private int val;
    private boolean assigned;
    private boolean crossedOut;

    public BaseNumber(int val) {
        this.val = val;
        this.assigned = false;
        this.crossedOut = false;
    }

    public void setVal(int val) {
        this.val = val;
    }

    public int getVal() {
        return this.val;
    }

    public void setAssigned(boolean assigned) {
        this.assigned = assigned;
    }

    public boolean getAssigned() {
        return this.assigned;
    }

    public void setCrossedOut(boolean crossedOut) {
        this.crossedOut = crossedOut;
    }

    public boolean getCrossedOut() {
        return this.crossedOut;
    }

    public String toString() {
        return "(" + this.val
            + ", " + this.assigned
            + ", " + this.crossedOut + ")\n";
    }
    
}