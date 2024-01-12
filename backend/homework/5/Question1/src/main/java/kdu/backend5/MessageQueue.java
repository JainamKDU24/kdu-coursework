package kdu.backend5;

import java.util.*;

public class MessageQueue {
    Logging logger=new Logging();
    private final Queue<String> messages=new LinkedList<>();
    public synchronized void addMessage(String message){
        messages.add(message);
        notifyAll();
    }
    public synchronized String getMessage() throws InterruptedException{
        while(messages.isEmpty()){
            logger.logInfo("Please Wait");
            wait();
        }
        return messages.remove();
    }
}
