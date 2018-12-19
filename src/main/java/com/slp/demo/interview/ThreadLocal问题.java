package com.slp.demo.interview;

/**
 * @author sanglp
 * @create 2018-12-19 15:02
 * @desc ThreadLocal有什么缺陷？如果是线程池里的线程用ThreadLocal会有什么问题？
 **/
public class ThreadLocal问题 {

    private void what(){
        /**
         * ThreadLocal是一个本地线程副本变量工具类。主要用于将私有线程和该线程存放的副本对象做一个映射，
         * 各个线程之间的变量互不干扰，在高并发场景下，可以实现无床头的调用，特别适用于各个线程依赖不同的变量值完成操作的场景。
         */

    }



   /*******************use start*******************************/
    ThreadLocal<Long> longLocal = new ThreadLocal<Long>();
    ThreadLocal<String> stringLocal = new ThreadLocal<String>();

    public void set(){
        longLocal.set(Thread.currentThread().getId());
        stringLocal.set(Thread.currentThread().getName());
    }

    public  long getLong(){
        return longLocal.get();
    }

    public String getString(){
        return stringLocal.get();
    }

    /**
     * 运行结果：
     * 1=run5
     * main=run6
     * 12=run1
     * Thread-0=run2
     * 1=run3
     * main=run4
     * @param args
     * @throws Exception
     * 从执行结果可以看出main线程中和thread1线程中localLong和localString保存的副本值是不一样的
     */
    public static void main(String[] args) throws Exception{
        final ThreadLocal问题 t = new ThreadLocal问题();
        t.set();
        System.out.println(t.getLong()+"=run5");
        System.out.println(t.getString()+"=run6");

        Thread thread1 = new Thread(){
            @Override
            public void run(){
                t.set();
                System.out.println(t.getLong()+"=run1");
                System.out.println(t.getString()+"=run2");
            };
        };

        thread1.start();
        thread1.join();

        System.out.println(t.getLong()+"=run3");
        System.out.println(t.getString()+"=run4");
    }
    /*******************use end*******************************/


   private void problem(){
       /**
        * 由于ThreadLocalMap的key是弱引用，而Value是强引用，这就导致了一个问题，ThreadLocal在没有外部对象强引用时，发生GC时弱引用Key会被回收，而Value不会回收，如果创建
        * ThreadLocal的线程一直持续运行，那么这个Entry对象中的value就有可能一直得不到回收，发生内存泄漏。
        * 避免泄漏：
        * 既然Key是弱引用，就是在调用ThreadLocal的get() set()方法时完成后再调用remove方法，将Entry节点和Map的引用关系移除，这样整个Entry对象在GCRoots分析后就变成不可达了，下次GC的时候就可以被回收
        * 如果使用ThreadLocal的set方法之后，没有显示调用remove方法，就有可能发生内存泄漏。
        */
   }


    private void end(){
        /**
         * 每个Thread线程内部都有一个Map
         * Map里面存储线程本地对象(key)和线程的变量副本(value)
         * 但是Thread内部的Map是由ThreadLocal维护的，由ThreadLocal负责向map获取和设置线程的变量值
         * 所以对于不同的线程，每次获取副本值时，别的线程并不能获取到当前线程的副本值，形成了副本的隔离，互不干扰。
         * static class Entry extends WeakReference<ThreadLocal<?>> {
         *             /** The value associated with this ThreadLocal.
         *Object value;
         *
         *Entry(ThreadLocal < ? > k, Object v){
         *super(k);
         *value = v;
         *}
         *}
         * Entry继承自WeakReference(弱引用，生命周期只能存活到下次GC前)但是只有key是弱引用的，value并非弱引用
         */

    }
}
