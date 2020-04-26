# 第二周学习笔记

# HASHMAP总结
## 基本结构：
* 链表结构：
```
static class HashMapEntry<K, V> implements Entry<K, V> {
        final K key;
        V value;
        final int hash;
        HashMapEntry<K, V> next;
        ......
}
```
* 数组存储所有链表：
```
transient HashMapEntry<K, V>[] table;   //后面tab=table
```

* key的hash值的计算：
``` 
int hash = Collections.secondaryHash(key); 
```

* table中HashMapEntry位置的计算：
```
//通过key的hash值获得，因为HashMap数组的大小总是2^n，所以实际的运算就是 (0xfff...ff) & hash ，这里的tab.length-1相当于一个mask，滤掉了大于当前长度位的hash，使每个i都能插入到数组中。
int index = hash & (tab.length - 1); 
```

* 新增元素：
```
//放在链表的最前面，next = table[index]
table[index] = new HashMapEntry<K, V>(key, value, hash, table[index]);
```

* 取元素：
```
//找到key的hash对应的HashMapEntry，然后遍历链表，通过key.equals取值
for (HashMapEntry<K, V> e = tab[hash & (tab.length - 1)]; e != null; e = e.next) {
      K eKey = e.key;
      if (eKey == key || (e.hash == hash && key.equals(eKey))) {
           return e.value;
      }
}
```

## 常见问题：
 （其实了解上面的基本知识，下面的很多问题都好理解了）

* 当两个不同的键对象的hashcode相同时会发生什么？ 
>> 它们会储存在同一个bucket位置的HashMapEntry组成的链表中。
 

* 如果两个键的hashcode相同，你如何获取值对象？
>> 当我们调用get()方法，HashMap会使用键对象的hashcode找到bucket位置，找到bucket位置之后，会调用keys.equals()方法去找到链表中正确的节点。


* 什么是hash，什么是碰撞，什么是equals ？
>> Hash：是一种信息摘要算法，它还叫做哈希，或者散列。我们平时使用的MD5,SHA1都属于Hash算法，通过输入key进行Hash计算，就可以获取key的HashCode()，比如我们通过校验MD5来验证文件的完整性。
>> 对于HashCode，它是一个本地方法，实质就是地址取样运算

>> 碰撞：好的Hash算法可以出计算几乎出独一无二的HashCode，如果出现了重复的hashCode，就称作碰撞;
>> 就算是MD5这样优秀的算法也会发生碰撞，即两个不同的key也有可能生成相同的MD5。

HashCode，它是一个本地方法，实质就是地址取样运算；
==是用于比较指针是否在同一个地址；
equals与==是相同的。

* 如何减少碰撞？
>> 使用不可变的、声明作final的对象，并且采用合适的equals()和hashCode()方法的话，将会减少碰撞的发生，提高效率。不可变性使得能够缓存不同键的hashcode，这将提高整个获取对象的速度，使用String，Interger这样的wrapper类作为键是非常好的选择.

* 如果HashMap的大小超过了负载因子(load factor)定义的容量，怎么办？
>> 默认的负载因子大小为0.75，也就是说，当一个map填满了75%的bucket时候，和其它集合类(如ArrayList等)一样，将会创建原来HashMap大小的两倍的bucket数组，来重新调整map的大小，并将原来的对象放入新的bucket数组中。这个过程叫作rehashing，因为它调用hash方法找到新的bucket位置。


* 重新调整HashMap大小存在什么问题吗？
>> （当多线程的情况下，可能产生条件竞争(race condition)）
>> 当重新调整HashMap大小的时候，确实存在条件竞争，因为如果两个线程都发现HashMap需要重新调整大小了，它们会同时试着调整大小。在调整大小的过程中，存储在链表中的元素的次序会反过来，因为移动到新的bucket位置的时候，HashMap并不会将元素放在链表的尾部，而是放在头部，这是为了避免尾部遍历(tail traversing)。如果条件竞争发生了，那么就死循环了。这个时候，你可以质问面试官，为什么这么奇怪，要在多线程的环境下使用HashMap呢？

* 为什么String, Interger这样的wrapper类适合作为键？
>> 因为String是不可变的，也是final的，而且已经重写了equals()和hashCode()方法了。其他的wrapper类也有这个特点。不可变性是必要的，因为为了要计算hashCode()，就要防止键值改变，如果键值在放入时和获取时返回不同的hashcode的话，那么就不能从HashMap中找到你想要的对象。不可变性还有其他的优点如线程安全。如果你可以仅仅通过将某个field声明成final就能保证hashCode是不变的，那么请这么做吧。因为获取对象的时候要用到equals()和hashCode()方法，那么键对象正确的重写这两个方法是非常重要的。如果两个不相等的对象返回不同的hashcode的话，那么碰撞的几率就会小些，这样就能提高HashMap的性能。
  

* 可以使用自定义的对象作为键吗？
>> 当然你可能使用任何对象作为键，只要它遵守了equals()和hashCode()方法的定义规则，并且当对象插入到Map中之后将不会再改变了。如果这个自定义对象时不可变的，那么它已经满足了作为键的条件，因为当它创建之后就已经不能改变了。
 

* 可以使用CocurrentHashMap来代替Hashtable吗？
>>Hashtable是synchronized的，但是ConcurrentHashMap同步性能更好，因为它仅仅根据同步级别对map的一部分进行上锁。ConcurrentHashMap当然可以代替HashTable，但是HashTable提供更强的线程安全性。

 
* 能否让HashMap同步？
>> HashMap可以通过下面的语句进行同步：
>> Map m = Collections.synchronizeMap(hashMap);


* HashMap和Hashtable的区别：
>> 主要的不同：线程安全以及速度。仅在你需要完全的线程安全的时候使用Hashtable，而如果你使用Java 5或以上的话，请使用ConcurrentHashMap吧。
>> HashMap和Hashtable都实现了Map接口，但决定用哪一个之前先要弄清楚它们之间的分别。主要的区别有：线程安全性，同步(synchronization)，以及速度。
>> HashMap几乎可以等价于Hashtable，除了HashMap是非synchronized的，并可以接受null(HashMap可以接受为null的键值(key)和值(value)，而Hashtable则不行)。
>> HashMap是非synchronized，而Hashtable是synchronized，这意味着Hashtable是线程安全的，多个线程可以共享一个Hashtable；而如果没有正确的同步的话，多个线程是不能共享HashMap的。Java 5提供了ConcurrentHashMap，它是HashTable的替代，比HashTable的扩展性更好。
>> 另一个区别是HashMap的迭代器(Iterator)是fail-fast迭代器，而Hashtable的enumerator迭代器不是fail-fast的。所以当有其它线程改变了HashMap的结构（增加或者移除元素），将会抛出ConcurrentModificationException，但迭代器本身的remove()方法移除元素则不会抛出ConcurrentModificationException异常。但这并不是一个一定发生的行为，要看JVM。这条同样也是Enumeration和Iterator的区别。
>> 由于Hashtable是线程安全的也是synchronized，所以在单线程环境下它比HashMap要慢。如果你不需要同步，只需要单一线程，那么使用HashMap性能要好过Hashtable。
>> HashMap不能保证随着时间的推移Map中的元素次序是不变的。
>> 要注意的一些重要术语：
>> 1) sychronized意味着在一次仅有一个线程能够更改Hashtable。就是说任何线程要更新Hashtable时要首先获得同步锁，其它线程要等到同步锁被释放之后才能再次获得同步锁更新Hashtable。
>> 2) Fail-safe和iterator迭代器相关。如果某个集合对象创建了Iterator或者ListIterator，然后其它的线程试图“结构上”更改集合对象，将会抛出ConcurrentModificationException异常。但其它线程可以通过set()方法更改集合对象是允许的，因为这并没有从“结构上”更改集合。但是假如已经从结构上进行了更改，再调用set()方法，将会抛出IllegalArgumentException异常。
>> 3) 结构上的更改指的是删除或者插入一个元素，这样会影响到map的结构。


* HashMap和HashSet的区别：

>> 什么是HashSet?
>>>> HashSet实现了Set接口，它不允许集合中有重复的值，当我们提到HashSet时，第一件事情就是在将对象存储在HashSet之前，要先确保对象重写equals()和hashCode()方法，这样才能比较对象的值是否相等，以确保set中没有储存相等的对象。如果我们没有重写这两个方法，将会使用这个方法的默认实现。
>>>>  public boolean add(Object o)方法用来在Set中添加元素，当元素值重复时则会立即返回false，如果成功添加的话会返回true。

 

>> 什么是HashMap?
>>>>  HashMap实现了Map接口，Map接口对键值对进行映射。Map中不允许重复的键。Map接口有两个基本的实现，HashMap和TreeMap。TreeMap保存了对象的排列次序，而HashMap则不能。HashMap允许键和值为null。HashMap是非synchronized的，但collection框架提供方法能保证HashMap synchronized，这样多个线程同时访问HashMap时，能保证只有一个线程更改Map。
>>>>  public Object put(Object Key,Object value)方法用来将元素添加到map中。

 

* HashSet和HashMap的区别
>> HashMap实现了Map接口 HashSet实现了Set接口
>> HashMap储存键值对 HashSet仅仅存储对象
>> 使用put()方法将元素放入map中 使用add()方法将元素放入set中
>> HashMap中使用键对象来计算hashcode值 HashSet使用成员对象来计算hashcode值，对于两个对象来说hashcode可能相同，所以equals()方法用来判断对象的相等性，如果两个对象不同的话，那么返回false
>> HashMap比较快，因为是使用唯一的键来获取对象 HashSet较HashMap来说比较慢


* HashMap的复杂度
>> HashMap整体上性能都非常不错，但是不稳定，为O(N/Buckets)，N就是以数组中没有发生碰撞的元素。

               	获取            		查找         	添加/删除     		空间

ArrayList     	O(1)            	O(1)            O(N)        		O(N)

LinkedList   	O(N)            	O(N)            O(1)        		O(N)

HashMap   	O(N/Bucket_size)   	O(N/Bucket_size)   	O(N/Bucket_size) 	O(N)

注：发生碰撞实际上是非常稀少的，所以N/Bucket_size约等于1

 

*  对key进行Hash计算
>> 在JDK8中，由于使用了红黑树来处理大的链表开销，所以hash这边可以更加省力了，只用计算hashCode并移动到低位就可以了
```
static final int hash(Object key) {
    int h;
    //计算hashCode，并无符号移动到低位
    return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
}
```






  
