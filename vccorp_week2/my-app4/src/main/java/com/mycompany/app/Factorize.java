package com.mycompany.app;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import com.google.common.cache.*;
public class Factorize {
    private static LoadingCache<Long, ArrayList<Long>> cache;
    static {
        cache = CacheBuilder.newBuilder()
                .maximumSize(100) //set size
                .expireAfterWrite(20, TimeUnit.SECONDS)
                .expireAfterAccess(10,TimeUnit.SECONDS)
                .build(
                        new CacheLoader<Long,ArrayList<Long>>() {
                            @Override
                            public ArrayList<Long> load(Long id) throws Exception {
                                return FactorizeNumber(id);
                            }
                        }
                );
    }
    public static LoadingCache<Long, ArrayList<Long>> getLoadingCache() {
        return cache;
    }
    public static ArrayList<Long> FactorizeNumber(long input){
        System.out.println("computing----------");
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
