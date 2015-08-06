package messages;

import java.io.Serializable;

/**
 * Created by cjames on 8/4/2015.
 */
public class TickerCounterMsg implements Serializable {
    String symbol;
    int count;

    public TickerCounterMsg(int count, String symbol){
        count = count;
        symbol = symbol;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }


}
