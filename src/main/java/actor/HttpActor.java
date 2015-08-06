package actor;

import akka.actor.UntypedActor;
import messages.JsonMsg;
import messages.TickerCounterMsg;
import scala.util.parsing.json.JSON;

import javax.xml.ws.http.HTTPException;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Random;
import java.util.stream.Stream;

public class HttpActor extends UntypedActor {
    final String yahooUrl = "http://finance.yahoo.com/webservice/v1/symbols/";

    @Override
    public void onReceive(Object o) throws Exception {

        if(o instanceof TickerCounterMsg){
            TickerCounterMsg msg = (TickerCounterMsg) o;

            String constructedUrl = buildUrl(msg.getSymbol());
            String result = null;
            try {
               if (msg.getCount() != 0 && msg.getCount() % 20  == 0){
                   throw new ArithmeticException(msg.getSymbol());
               }else{
                   result = retreiveData(constructedUrl);
                   System.out.println(constructedUrl);
               }
            }catch (Exception e){
                throw e;
            }

            if(result != null){
                JsonMsg jsonMsg = new JsonMsg(result);
                getSender().tell(jsonMsg,self());
            }

        }
    }

    private String buildUrl(String string){

       String url = yahooUrl + string + "/quote?format=json&view=detail";
        return url;
    }

    private String retreiveData(String url) throws Exception{
        Thread.sleep(new Random().nextInt(((1000 - 100) + 1) + 100));
//        StringBuffer buffer = new StringBuffer();
//            URL tickerURL = new URL(url);
//            URLConnection tickerConnection = tickerURL.openConnection();
//            BufferedReader in = new BufferedReader(new InputStreamReader(
//                    tickerConnection.getInputStream()));
//            String inputLine;
//            while ((inputLine = in.readLine()) != null)
//                buffer.append(inputLine);
//            in.close();
       String content = new String(Files.readAllBytes(Paths.get("c:/tmp","data1.txt")));



        return content;
    }
}
