package kdu;


import kdu.backend5.Logging;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MultiFactorThreads {
    public static int factorial(int number){
        if(number==0){
            return 1;
        }
        int fact=2;
        for(int i=2;i<number;i++){
            fact*=i;
        }
        return fact;
    }

    public static List<Integer> calculatefactors(int number){
        List<Integer> factors=new ArrayList<>();
        for(int i=1;i<=number;i++){
            if(number%i==0){
                factors.add(i);
            }
        }
        return factors;
    }
    public static void main(String[] args) {
        int number=8;
        Logging logger=new Logging();

        logger.logInfo("Input number is 8");
        Thread factorial=new Thread(()-> logger.logInfo(Integer.toString(factorial(number))));
        factorial.start();

        Thread factors=new Thread(()->logger.logInfo(calculatefactors(number).stream().map(Object::toString).collect(Collectors.joining(","))));
        factors.start();
    }
}
