package messages;

import java.io.Serializable;

/**
 * Created by cjames on 8/2/2015.
 */
public class PriceMsg implements Serializable {
    private double price = 0.0;     //price
    private String issuer_name;  //issuer_name
    private double day_high = 0.0; //day_high
    private double day_low = 0.0; //day_low


    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getIssuer_name() {
        return issuer_name;
    }

    public void setIssuer_name(String issuer_name) {
        this.issuer_name = issuer_name;
    }

    public double getDay_high() {
        return day_high;
    }

    public void setDay_high(double day_high) {
        this.day_high = day_high;
    }

    public double getDay_low() {
        return day_low;
    }

    public void setDay_low(double day_low) {
        this.day_low = day_low;
    }



}
