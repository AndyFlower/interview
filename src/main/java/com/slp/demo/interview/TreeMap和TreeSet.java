package com.slp.demo.interview;

import java.util.*;

/**
 * @author sanglp
 * @create 2018-12-19 17:08
 * @desc TreeMap与TreeSet实现原理是什么
 **/
public class TreeMap和TreeSet {

    private void what(){
        /**
         *   TreeMap和TreeSet算是java集合类里面比较有难度的数据结构。和普通的HashMap不一样，
         *   普通的HashMap元素存取的时间复杂度一般是O(1)的范围。而TreeMap内部对元素的操作复杂度为O(logn)。
         *   虽然在元素的存取方面TreeMap并不占优，但是它内部的元素都是排序的，当需要查找某些元素以及顺序输出元素的时候它能够带来比较理想的结果。可以说，
         *   TreeMap是一个内部元素排序版的HashMap。
         *   这里会对TreeMap内部的具体实现机制和它所基于的红黑树做一个详细的介绍
         */
    }

    private void extend(){
        /**
         * 红黑树(Red-Black Tree)
         *
         *     红黑树本质上是一棵一定程度上相对平衡的二叉搜索树。为什么这么说呢？我们从前面讨论二叉搜索树的文章中可以看到。一棵二叉搜索树理想情况下的搜索和其他元素操作的时间复杂度是O(logn)。但是，这是基于一个前提，即二叉搜索树本身构造出来的树是平衡的。如果我们按照普通的插入一个元素就按照二叉树对应关系去摆的话，在一些极端的情况下会失去平衡。比如说我们通过插入一个顺序递增或者递减的一组元素，那么最后的结构就相当于一个双向链表。对其中元素的访问也不可能达到O(logn)这样的级别。
         *
         *     所以，在这样的情况下，我们就希望有那么一种机制或者数据结构能够保证我们既能构造出一棵二叉搜索树来，而且它天生就是平衡的。这样就有了红黑树。当然，为了同时达到这两个目标，红黑树设定了一些特定的属性限制，也使得它本身的实现比较复杂。我们在下面的定义中就可以看到。
         *
         *     红黑树的官方定义如下：
         *
         * 红黑树是一种二叉树，同时它还满足下列5个特性：
         *
         * 1. 每个节点是红色或者黑色的。
         *
         * 2. 根节点是黑色的。
         *
         * 3. 每个叶节点是黑色的。（这里将叶节点的左右空子节点作为一个特殊的节点对待，设定他们必须是黑色的。）
         *
         * 4. 如果一个节点是红色的，则它的左右子节点都必须是黑色的。
         *
         * 5. 对任意一个节点来说，从它到叶节点的所有路径必须包含相同数目的黑色节点。
         */
    }

    private void add(){
        /**
         * 添加元素的过程可以大致的分为两个步骤。和前面的二叉搜索树类似，我们添加元素也是需要通过比较元素的值，找到添加元素的地方。这部分基本上没有什么变化。第二步则是一个调整的过程。因为红黑树不一样，当我们添加一个新的元素之后可能会破坏它固有的属性。主要在于两个地方，一个是要保证新加入元素后，到所有叶节点的黑色节点还是一样的。另外也要保证红色节点的子节点为黑色节点。
         *
         *     还有一个就是，结合TreeMap的map特性，我们添加元素的时候也可能会出现新加入的元素key已经在数中间存在了，那么这个时候就不是新加入元素，而是要更新原有元素的值。
         *
         *     public V put(K key, V value) {
         *         Entry<K,V> t = root;
         *         ***********start********
         *         根节点为null的情况下，我们的put方法相当于直接创建一个节点并关联到根节点
         *         if (t == null) {
         *             compare(key, key); // type (and possibly null) check 为null的情况下compare里面的两个key是相同的
         *
         *             root = new Entry<>(key, value, null);
         *             size = 1;
         *             modCount++;
         *             return null;
         *         }
         *         **********end*************
         *         int cmp;
         *         Entry<K,V> parent;
         *         // split comparator and comparable paths
         *         Comparator<? super K> cpr = comparator;
         *         if (cpr != null) {
         *             do {
         *                 parent = t;
         *                 cmp = cpr.compare(key, t.key);
         *                 if (cmp < 0)
         *                     t = t.left;
         *                 else if (cmp > 0)
         *                     t = t.right;
         *                 else
         *                     return t.setValue(value);
         *             } while (t != null);
         *         }
         *         else {
         *             if (key == null)
         *                 throw new NullPointerException();
         *             @SuppressWarnings("unchecked")
         *                 Comparable<?  s uper  K> k = (Comparable<? super K>) key;
         *             do {
         *                 parent = t;
         *                 cmp = k.compareTo(t.key);
         *                 if (cmp < 0)
         *                     t = t.left;
         *                 else if (cmp > 0)
         *                     t = t.right;
         *                 else
         *                     return t.setValue(value);
         *             } while (t != null);
         *         }
         *         ********start******************
         *         如果在前面的循环块里面没有找到对应的Key值，则说明已经找到了需要插入元素的位置，这里则要在这个地方加入进去。添加了元素之后，基本上整个过程就结束了。
         *         Entry<K,V> e = new Entry<>(key, value, parent);
         *         if (cmp < 0)
         *             parent.left = e;
         *         else
         *             parent.right = e;
         *         fixAfterInsertion(e);每次当我们插入一个元素的时候，我们添加的元素会带有一个颜色，而这个颜色不管是红色或者黑色都可能会破坏红黑树定义的属性。所以，这里需要通过一个判断调整的过程来保证添加了元素后整棵树还是符合要求的
         *         size++;
         *         modCount++;
         *         return null;
         *         *********end*****************
         *     }
         */
    }

    private void rotate(){
        /**
         *  private void fixAfterInsertion(Entry<K,V> x) {
         *         x.color = RED;
         *
         *         while (x != null && x != root && x.parent.color == RED) {
         *             if (parentOf(x) == leftOf(parentOf(parentOf(x)))) {
         *                 Entry<K,V> y = rightOf(parentOf(parentOf(x)));
         *                 if (colorOf(y) == RED) {
         *                     setColor(parentOf(x), BLACK);
         *                     setColor(y, BLACK);
         *                     setColor(parentOf(parentOf(x)), RED);
         *                     x = parentOf(parentOf(x));
         *                 } else {
         *                     if (x == rightOf(parentOf(x))) {
         *                         x = parentOf(x);
         *                         rotateLeft(x);
         *                     }
         *                     setColor(parentOf(x), BLACK);
         *                     setColor(parentOf(parentOf(x)), RED);
         *                     rotateRight(parentOf(parentOf(x)));
         *                 }
         *             } else {
         *                 Entry<K,V> y = leftOf(parentOf(parentOf(x)));
         *                 if (colorOf(y) == RED) {
         *                     setColor(parentOf(x), BLACK);
         *                     setColor(y, BLACK);
         *                     setColor(parentOf(parentOf(x)), RED);
         *                     x = parentOf(parentOf(x));
         *                 } else {
         *                     if (x == leftOf(parentOf(x))) {
         *                         x = parentOf(x);
         *                         rotateRight(x);
         *                     }
         *                     setColor(parentOf(x), BLACK);
         *                     setColor(parentOf(parentOf(x)), RED);
         *                     rotateLeft(parentOf(parentOf(x)));
         *                 }
         *             }
         *         }
         *         root.color = BLACK;
         *     }
         */
    }

    public static void main(String[] args) {
        TreeSet ts = new TreeSet();
        ts.add("123");
        ts.add("1");
        List<String> l = new ArrayList<String>();
        l.add("w");
        l.add(1,"3");
        ts.addAll(l);
    }


    private void doc(){

        /**
         * 1、构造函数
         *  1、1 public TreeSet() {
         *         this(new TreeMap<E,Object>());
         *     }
         *   1、2 public TreeSet(Comparator<? super E> comparator) {
         *         this(new TreeMap<>(comparator));
         *     }
         *   1、3 public TreeSet(Collection<? extends E> c) {
         *         this();
         *         addAll(c);
         *     }
         *  1、4  public TreeSet(SortedSet<E> s) {
         *         this(s.comparator());
         *         addAll(s);
         *     }
         *
         *  主要的构造函数有2个，一个是默认的无参数构造函数和另一个比较器构造函数，他们内部的实现都是使用的TreeMap,而其他的构造函数都是通过调用这两个实现的，故其底层使用的就是TreeMap,既然
         *  TreeSet只是TreeMap的一个马甲，我们就重点关注TreeMap的实现。
         *
         *  2、节点定义
         *   对于一个键值对来说，key的作用就是用来寻址的，在HashMap里面，key是通过hash函数运算直接映射到对于的slot,这里则是通过查找比较放到一颗二叉树里的合适的位置。这个位置相当于一个slot,所以我们的节点中必须有一个key,一个value.
         *   由于使用了红黑树，所以需要有一个保存节点颜色的属性。
         *   节点应该包含如下6部分：
         *    左子节点引用
         *    右子节点引用
         *    父节点引用
         *    key
         *    value
         *    color
         *    ---------------
         *     color   parent
         *    ----------------
         *     key     value
         *    -----------------
         *    left     right
         *    ----------------
         *
         * final class Entry<K,V> implements Map.Entry<K,V> {
         *             K key;
         *             V value;
         *             TreeMap.Entry<K,V> left;
         *             TreeMap.Entry<K,V> right;
         *             TreeMap.Entry<K,V> parent;
         *             boolean color = BLACK;
         *

         *Entry(K key, V value, TreeMap.Entry < K, V > parent) {
         *this.key = key;
         *this.value = value;
         *this.parent = parent;
         *}
         * }
         */


    }
}
