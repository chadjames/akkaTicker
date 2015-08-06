package messages;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class SymbolMsg implements Serializable {

    List<String> symbolList;

    public SymbolMsg(){
        symbolList = new ArrayList<String>();
    }

    public void addSymbol(String ticker) {
        symbolList.add(ticker);

    }

    public void addAll(List<String> listToAdd){
            symbolList.addAll(listToAdd);
    }
    public List<String> getSymbolList() {
        return symbolList;
    }
}
