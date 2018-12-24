package com.slp.demo.thread;

import java.util.Random;
import java.util.concurrent.*;

/**
 * @author sanglp
 * @create 2018-12-24 11:47
 * @desc Callable实现线程
 **/
public class CallableDemo  implements Callable<String> {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService es = Executors.newCachedThreadPool();

        CallableDemo cd = new CallableDemo();
        Future  f = es.submit(cd);
        System.out.print("return:"+f.get());
    }


    @Override
    public String call() throws Exception {
        Random radom = new Random(100);
       return String.valueOf(radom.nextInt());
    }
}
