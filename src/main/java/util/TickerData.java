package util;


public class TickerData {
    private double l_cur = 0.0;     //l_cur
    private String name;  //name
    private double hi = 0.0; //hi
    private double lo = 0.0; //lo

    public TickerData(){
                     //another test 1 qq  1
    }

    public double getL_cur() {
        return l_cur;
    }

    public void setL_cur(double l_cur) {
        this.l_cur = l_cur;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getHi() {
        return hi;
    }

    public void setHi(double hi) {
        this.hi = hi;
    }

    public double getLo() {
        return lo;
    }

    public void setLo(double lo) {
        this.lo = lo;
    }



}
