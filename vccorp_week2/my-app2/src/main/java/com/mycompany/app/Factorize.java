package com.mycompany.app;

import java.util.ArrayList;

public class Factorize {

    public static ArrayList<Long> FactorizeNumber(long input){
        ArrayList<Long> tmp = new ArrayList<>();
        for (long i = 1; i <= input ; i++) {
            if(isPrimeNumber(i))
                tmp.add(i);
        }
        return tmp;
    }
    private static boolean isPrimeNumber(long n){
        if(n <2 )
            return false;
        for (long i = 2; i < (n-1); i++) {
            if(n%i == 0)
                return false;
        }
        return true;
    }

}
