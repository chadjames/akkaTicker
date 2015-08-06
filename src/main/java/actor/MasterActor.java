package actor;

import akka.actor.*;
import akka.actor.SupervisorStrategy.Directive;
import akka.japi.Function;
import akka.routing.RoundRobinPool;
import messages.*;

import scala.concurrent.duration.Duration;

import javax.xml.ws.http.HTTPException;
import java.time.Instant;
import java.util.concurrent.TimeUnit;


import static akka.actor.SupervisorStrategy.escalate;
import static akka.actor.SupervisorStrategy.stop;
import static akka.actor.SupervisorStrategy.restart;
import static akka.actor.SupervisorStrategy.resume;



/**
 * Created by cjames on 8/2/2015.
 */
public class MasterActor extends UntypedActor{
    int responses;
    Instant start;

    ActorRef httpActor =
            getContext().actorOf(new RoundRobinPool(5).props(Props.create(HttpActor.class)),
                    "httpActor");


    ActorRef jsonActor = getContext().actorOf(Props.create(JsonActor.class), "jsonActor");
    ActorRef formattorActor = getContext().actorOf(Props.create(FormattorActor.class), "formattorActor");



    private  SupervisorStrategy strategy = new OneForOneStrategy(10,
            Duration.create("1 minute"),
            new Function<Throwable, SupervisorStrategy.Directive>() {

                @Override
                public SupervisorStrategy.Directive apply(Throwable t) {
                    if (t instanceof ArithmeticException) {
                        responses--;
                        return resume();
                    } else if (t instanceof NullPointerException) {
                        return restart();
                    } else if (t instanceof IllegalArgumentException) {
                        return stop();
                    } else {
                        return escalate();
                    }
                }
            }
    );

    @Override
    public void onReceive(Object o) throws Exception {
        if(o instanceof SymbolMsg){
            start = Instant.now();
            SymbolMsg message = (SymbolMsg) o;
            responses = message.getSymbolList().size();
            int count = 1;
            for(String ticker: message.getSymbolList()){
                TickerCounterMsg t = new TickerCounterMsg(count, ticker);
                t.setSymbol(ticker);
                t.setCount(count);
                httpActor.tell(t, getSelf());
                count++;
            }
        } else if(o instanceof JsonMsg){
            jsonActor.tell(o, getSelf());
        }else if(o instanceof PriceMsg){
            formattorActor.tell(o, getSelf());
        } else if(o instanceof FormattedMsg){
            responses--;
            System.out.println(((FormattedMsg) o).getFormattedResult());
            if(responses == 0){
                shutdown("Formatted");
            }
        } else
            unhandled(0);
        }

    private void shutdown(String s){
        Instant end =  Instant.now();
        System.out.println("Total time : " + 5 + " " + " Shutdown by " + s );
        getContext().system().shutdown();

    }
}
