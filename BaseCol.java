public class BaseCol implements RowColInterface {

    public BaseNumber[] col;
    private boolean tick;
    private boolean line;

    public BaseCol() {
        this.tick = false;
        this.line = false;
    }

    public void setTick(boolean tick) {
        this.tick = tick;
    }

    public boolean getTick() {
        return this.tick;
    }

    public void setLine(boolean line) {
        this.line = line;
    }

    public boolean getLine() {
        return this.line;
    }

    public String toString() {
        String str = "";
        for (int i = 0; i < col.length; i++) {
            str += i + " " + this.col[i];
        }
        return str;
    }
    
}