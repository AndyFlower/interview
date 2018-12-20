package com.slp.demo.interview;

/**
 * @author sanglp
 * @create 2018-12-20 10:34
 * @desc JVM堆内存结构是怎样的？哪些情况会触发GC？会触发哪些GC？
 **/
public class JVM堆内存结构 {

    private void desc (){
        /**
         * 1、JVM堆内存内部结构
         *  所有通过new创建的对象的内存都在堆中分配，其大小可以通过-Xmx和-Xms来控制。堆划分为新生代和旧生代。
         *  新生代又被进一步划分为Eden和Survicor区，最后Survivor由FromSpace和ToSpace组成
         *
         *  新生代：新建的对象都是用新生代分配内存，Eden空间不足的时候，会把存活的对象转移到Survivor中，新生代大小可以由-Xmn来控制，也可以用-XX:SurvivorRator来控制Eden和Survivor的比例旧生代，用于存放新生代中经过多次垃圾回收仍然存活的对象。
         *
         *  分代收集算法是目前大部分JVM的垃圾收集器采用的算法。它的核心思想是根据对象存活的生命周期将内存划分为若干个不同的区域，一般情况下将堆区划分为；老年代和新生代，老年代的特点是每次垃圾收集时只有少量对象需要被回收，而新生代的特点是每次垃圾回收时都有大量的对象需要被回收，
         *  那么久需要根据不同代的特点采取最适合的收集算法。
         *
         *  目前大部分垃圾收集器对于新生代都采取Coping算法，因为新生代中每次垃圾回收都要回收大部分对象，也就是说需要复制的操作次数较少，
         *  但是实际中并不是安装1:1的比例来划分新生代的空间的，一般来说是将新生代划分为一块较大的Eden空间和两块较小的Survivor空间，每次使用Eden空间和其中一块Survivoe空间，当进行回收时，将Eden和Survivor中还存活的对象复制到另一块Survivor空间中，然后清理掉Eden和刚才使用过的Survivor空间。
         *
         *  而由于老年代的特点是每次回收都只回收少量对象，一般使用的是Mark-Compact算法
         *
         *
         *  对象的内存分配，往大方向上讲就是在堆上分配，对象主要分配在新生代的Eden Space和From Space，少数情况下会直接分配在老年代。如果新生代的Eden Space和From Space的空间不足，则会发起一次GC，如果进行了GC之后，Eden Space和From Space能够容纳该对象就放在Eden Space和From Space。在GC的过程中，
         *  会将Eden Space和From  Space中的存活对象移动到To Space，然后将Eden Space和From Space进行清理。如果在清理的过程中，To Space无法足够来存储某个对象，就会将该对象移动到老年代中。在进行了GC之后，使用的便是Eden space和To Space了，下次GC时会将存活对象复制到From Space，如此反复循环。
         *  当对象在Survivor区躲过一次GC的话，其对象年龄便会加1，默认情况下，如果对象年龄达到15岁，就会移动到老年代中。
         *  一般来说，大对象会被直接分配到老年代，所谓的大对象是指需要大量连续存储空间的对象，最常见的一种大对象就是大数组，比如：
         * 　　byte[] data = new byte[4*1024*1024]
         * 　　这种一般会直接在老年代分配存储空间。
         * 　　当然分配的规则并不是百分之百固定的，这要取决于当前使用的是哪种垃圾收集器组合和JVM的相关参数。
         *
         *
         *
         * 2、什么时候触发GC
         *   GC又分为minor GC和Full GC,java堆内存分为新生代和老年代，新生代中又分为一个Eden区域和两个Survivor区域。
         *   对于Minor GC的触发条件是：
         *       大多数情况下，直接在Eden区进行分配，如果Eden区域没有足够的空间，那么就会触发一次Minor GC;
         *       对于Full GC的触发条件：也就是如果老年代没有足够空间的话，那么就会进行一次Full GC
         *
         *   在发生Minor GC之前，虚拟机会先检查老年代最大可用的连续空间是否大于新生代所有对象的总空间。如果大于则进行Minor GC，如果小于则看HandlePromotionFailure设置是否允许担保失败（不允许则直接Full GC）。如果允许，那么会继续检查老年代最大可用的连续空间是否大于历次晋升到老年代对象的平均大小，
         *   如果大于则尝试Minor GC（如果尝试失败也会触发Full GC），如果小于则进行Full GC。
         */

    }
}
