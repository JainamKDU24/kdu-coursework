package kdu.backend5_1;

import kdu.backend5.Logging;

public class MessageReceiver implements Runnable{
    Logging logger=new Logging();

    private final MessageQueue queue;

    public MessageReceiver(MessageQueue queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            String message = queue.getMessage();
            logger.logInfo("Received: " + message);
        } catch (InterruptedException e) {
            logger.logInfo(e.getMessage());
            Thread.currentThread().interrupt();
        }
    }
}
