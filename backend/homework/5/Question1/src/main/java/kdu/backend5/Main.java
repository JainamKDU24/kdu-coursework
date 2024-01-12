package kdu.backend5;


public class Main {
    public static void main(String[] args) {
        MessageQueue queue = new MessageQueue();
        Thread[] senders=new Thread[3];

        for (int i = 0; i < 2; i++) {
            senders[i] = new Thread(new MessageSender(queue));
            String name = String.format("Sender %d",i);
            senders[i].setName(name);
            senders[i].start();
        }

        Thread[] receivers=new Thread[3];
        for (int i = 0; i < 3; i++) {
            receivers[i] = new Thread(new MessageReceiver(queue));
            String name = String.format("Receiver %d",i);
            receivers[i].setName(name);
            receivers[i].start();
        }
        senders[2]=new Thread(new MessageSender(queue),"Sender 2");
        senders[2].start();
    }
}