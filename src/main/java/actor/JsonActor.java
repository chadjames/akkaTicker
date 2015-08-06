package actor;

import akka.actor.UntypedActor;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import messages.JsonMsg;
import messages.PriceMsg;
import util.TickerData;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

/**
 * Created by cjames on 8/2/2015.
 */
public class JsonActor extends UntypedActor {


    @Override
    public void onReceive(Object o) throws Exception {

        if(o instanceof JsonMsg){
            PriceMsg priceMsg = null;
            Gson gson = new Gson();
            try{
                JsonParser parser = new JsonParser();
                JsonObject jo = (JsonObject)parser.parse(((JsonMsg) o).getJson());
                JsonObject list = (JsonObject) jo.get("list");
                JsonArray resources = list.getAsJsonArray("resources");
                JsonObject first = (JsonObject) resources.get(0);
                JsonObject resource = (JsonObject)first.get("resource");
                JsonObject fields = (JsonObject)resource.get("fields");
                priceMsg = gson.fromJson(fields, PriceMsg.class);

            } catch (Exception e){

            }
            if(priceMsg != null){
               getSender().tell(priceMsg,self());
            } else {
               getSender().tell(new Exception("could not parse"),self());
            }



        }

    }
}
