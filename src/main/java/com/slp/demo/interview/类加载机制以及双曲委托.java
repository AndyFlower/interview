package com.slp.demo.interview;

/**
 * @author sanglp
 * @create 2018-12-19 16:23
 * @desc 类的加载机制，为什么要用双亲委托？如何打破双亲委托加载机制
 **/
public class 类加载机制以及双曲委托 {

    private void what(){
        /**
         * JDK自带的ClassLoader类：
         *     JDK中提了三个ClassLoader,根据层级从高到低为：
         *     Bootstrap ClassLoader:主要加载JVM自身工作需要的类
         *     Extension ClassLoader:主要加载%JAVA_HOME%\lib\ext目录下的库类
         *     Application ClassLoader：主要加载Classpath指定的库类，一般情况下这个是默认的类加载器，也是ClassLoader.getSystemClassLoader()的返回值
         *
         *什么是双曲委托模型：
         *    JVM加载类的实现方式，我们称为双亲委托模型：
         *    如果一个类加载器收到了类加载器的请求，他首先不会自己取尝试加载这个类，而是把这个请求委托给自己的父加载器，每一层的类加载器都是如此因此所有的类加载器最终都应该传送到顶层的Bootstrap ClassLoader中，
         *    只有当父加载器反馈自己无法完成加载请求时，子加载器才会尝试自己加载

         */
    }

    private void why(){
        /**
         * 双亲委托模型的重要用途是为了解决类载入过程中的安全性问题。
         * 假设有一个开发者自己编写了一个名为java.lang.Object的类，想借此欺骗JVM。现在他要使用自定义ClassLoader来加载自己编写的java.lang.Object类。然而幸运的是，双亲委托模型不会让他成功。因为JVM会优先在Bootstrap ClassLoader的路径下找到java.lang.Object类，并载入它。
         */
    }

    private void how(){
        /**
        *过程：
         *    1、自定义ClassLoader向自己的上层（Application ClassLoader）请求
         *    2、Application ClassLoader继续向上层（Extension ClassLoader）请求
         *    3、Extension ClassLoader继续向上层（Bootstrap ClassLoader）请求
         *    4、Bootstrap ClassLoader是最上层类加载器，所以它尝试在自己的路径中查找要加载类，如果查找到了就加载类，否则向下层（Extension ClassLoader）反馈自己无法加载类。
         *    5、Extension ClassLoader从自己的路径中寻找要加载类，找到则加载，找不到则继续向下返回。
         *    6、Application ClassLoader从自己的路径中寻找要加载类，找到则加载，找不到则继续向下返回。
         *   7、自定义ClassLoader从自己的路径中寻找要加载类，找到则加载。由于类加载请求是自定义ClassLoader发起的，所以当它自己也找不到要加载的类时会终止加载并抛出
         *    ClassNotFoundException。
         */
    }

    private void howBreak(){
        /**
         * Java的类加载是否一定遵循双亲委托模型
         *
         * 这个答案是否定的。
         * 双亲委托模型只是JDK提供的ClassLoader类的实现方式。
         * 在实际开发中，我们可以通过自定义ClassLoader，并重写父类的loadClass方法，来打破这一机制。
         */
    }
}
