package actor;

import akka.actor.UntypedActor;
import messages.FormattedMsg;
import messages.PriceMsg;

import java.util.Random;


public class FormattorActor extends UntypedActor {
    @Override
    public void onReceive(Object o) throws Exception {
        if(o instanceof PriceMsg){
            PriceMsg priceMsg = (PriceMsg) o;
            FormattedMsg formattedMsg = new FormattedMsg();
            Double x = new Random().nextDouble();
            formattedMsg.setFormattedResult("Company: " + priceMsg.getIssuer_name() + " - " + "Current price: " + getPrice(priceMsg.getPrice()) + " - " + "High price: "  + getPrice(priceMsg.getDay_high()) + " - " + "Low price: " + getPrice(priceMsg.getDay_low()));
            getSender().tell(formattedMsg,self());

        }


    }
    private Double getPrice(Double in){
        return Math.round(((in + new Random().nextDouble()))*100)/100.0;


    }
}
