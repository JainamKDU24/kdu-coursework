package kdu.backend5_1;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) {
        MessageQueue queue = new MessageQueue();

        ExecutorService senderThreadPool = Executors.newFixedThreadPool(3);
        ExecutorService receiverThreadPool = Executors.newFixedThreadPool(3);

        for (int i = 0; i < 3; i++) {
            senderThreadPool.execute(new MessageSender(queue));
            receiverThreadPool.execute(new MessageReceiver(queue));
        }

        senderThreadPool.shutdown();
        receiverThreadPool.shutdown();
    }
}
