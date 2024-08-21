package kdu.backend4;


public class Main {
    public static void main(String[] args) {
        Logging logger = new Logging();

        String[] list={"a","b","c","d"};
        Integer[] list2={1,2,3,4,5};

        String[] updatedlist=Swap.swapping(list,1,3);
        Integer[] updatedlist2=Swap.swapping(list2,2,4);
        for(String i:updatedlist){
            logger.logInfo(i);
        }
        for(int i:updatedlist2){
            logger.logInfo(String.valueOf(i));
        }
    }
}