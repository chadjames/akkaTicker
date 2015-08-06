package messages;

import java.io.Serializable;

/**
 * Created by cjames on 8/2/2015.
 */
public class FormattedMsg implements Serializable {
    String formattedResult;

    public String getFormattedResult() {
        return formattedResult;
    }

    public void setFormattedResult(String formattedResult) {
        this.formattedResult = formattedResult;
    }


}
