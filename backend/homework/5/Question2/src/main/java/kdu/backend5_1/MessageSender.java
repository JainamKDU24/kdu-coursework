package kdu.backend5_1;

public class MessageSender implements Runnable{
    private final MessageQueue queue;

    public MessageSender(MessageQueue queue) {
        this.queue = queue;
    }
    @Override
    public void run() {
        String message = String.format("Message from %s",Thread.currentThread().getName());
        queue.addMessage(message);
    }
}
