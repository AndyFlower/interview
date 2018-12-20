package com.slp.demo.interview;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author sanglp
 * @create 2018-12-20 16:55
 * @desc Array和ArrayList的区别
 **/
public class Array和ArrayList {


    public static void main(String[] args) {
        listToArray();
        arrayToList();
    }

    /**
     * List转换为array
     */
    private static void listToArray(){
        List<String> list = new ArrayList<String>();
        list.add("wang");
        list.add("zhang");
        int size = list.size();
        String[] array = list.toArray(new String[size]);
        for (int i=0 ;i<array.length;i++){
            System.out.println(array[i]);
        }
    }

    private static void arrayToList(){
        String[] array=new String[3];
        array[0]="王利虎";
        array[1]="张三";
        array[2]="李四";
        List<String> list= Arrays.asList(array);
        for(int i=0;i<list.size();i++){
            System.out.println(list.get(i));
        }
    }


    private static void what(){
        /**
         * 可以将ArrayList想象成一种"会自动扩增容量的Array"
         *
         * Array([]):最高效：但是其容量固定且无法动态改变
         * ArrayList:容量可动态增长；但牺牲效率
         *
         * 基于效率和类型校验，尽可能使用Array,无法确定数组大小时才有ArrayList
         *
         * Array可作为函数返回值，因为它本身就是对象的reference
         *
         * 对象数组与基本类型数组在运用上几乎一摸一样，唯一差别在于，前者持有得的reference,后者持有的是基本类型的值
         *
         * Array没有删除方法，而ArrayListremove方法
         *
         * 1）效率：
         * 数组扩容是对ArrayList效率影响比较大的一个因素。
         * 每当执行Add、AddRange、Insert、InsertRange等添加元素的方法，都会检查内部数组的容量是否不够了，如果是，它就会以当前容量的两倍来重新构建一个数组，将旧元素Copy到新数组中，然后丢弃旧数组，在这个临界点的扩容操作，应该来说是比较影响效率的。
         * ArrayList是Array的复杂版本
         * ArrayList内部封装了一个Object类型的数组，从一般的意义来说，它和数组没有本质的差别，甚至于ArrayList的许多方法，如Index、IndexOf、Contains、Sort等都是在内部数组的基础上直接调用Array的对应方法。
         *
         * 2）类型识别：
         * ArrayList存入对象时，抛弃类型信息，所有对象屏蔽为Object，编译时不检查类型，但是运行时会报错。
         * ArrayList与数组的区别主要就是由于动态增容的效率问题了
         *
         *
         */
    }
}
