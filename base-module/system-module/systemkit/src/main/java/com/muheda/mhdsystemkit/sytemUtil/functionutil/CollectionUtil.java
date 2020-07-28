package com.muheda.mhdsystemkit.sytemUtil.functionutil;


import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Set;

/**
 * @author zhangming
 * @Date 2019/5/20 9:53
 * @Description: 集合的工具类
 */
public class CollectionUtil {

    /**
     * 获取两个集合的并集
     *
     * @param a
     * @param b
     * @return
     */
    public static Collection union(final Collection a, final Collection b) {
        if (a == null && b == null) return new ArrayList();
        if (a == null) return new ArrayList<Object>(b);
        if (b == null) return new ArrayList<Object>(a);
        ArrayList<Object> list = new ArrayList<>();
        Map<Object, Integer> mapA = getCardinalityMap(a);
        Map<Object, Integer> mapB = getCardinalityMap(b);
        Set<Object> elts = new HashSet<Object>(a);
        elts.addAll(b);
        for (Object obj : elts) {
            for (int i = 0, m = Math.max(getFreq(obj, mapA), getFreq(obj, mapB)); i < m; i++) {
                list.add(obj);
            }
        }
        return list;
    }

    /**
     * 获取两个集合的交集
     *
     * @param a
     * @param b
     * @return
     */
    public static Collection intersection(final Collection a, final Collection b) {
        if (a == null || b == null) return new ArrayList();
        ArrayList<Object> list = new ArrayList<>();
        Map mapA = getCardinalityMap(a);
        Map mapB = getCardinalityMap(b);
        Set<Object> elts = new HashSet<Object>(a);
        elts.addAll(b);
        for (Object obj : elts) {
            for (int i = 0, m = Math.min(getFreq(obj, mapA), getFreq(obj, mapB)); i < m; i++) {
                list.add(obj);
            }
        }
        return list;
    }

    /**
     * 获取两个集合的差集
     *
     * @param a
     * @param b
     * @return
     */
    public static Collection subtract(final Collection a, final Collection b) {
        if (a == null) return new ArrayList();
        if (b == null) return new ArrayList<Object>(a);
        ArrayList<Object> list = new ArrayList<Object>(a);
        for (Object o : b) {
            list.remove(o);
        }
        return list;
    }

    /**
     * 集合转map
     *
     * @param coll
     * @return
     */
    public static Map<Object, Integer> getCardinalityMap(final Collection coll) {
        Map<Object, Integer> count = new HashMap<>();
        if (coll == null) return count;
        for (Object obj : coll) {
            Integer c = count.get(obj);
            if (c == null) {
                count.put(obj, 1);
            } else {
                count.put(obj, c + 1);
            }
        }
        return count;
    }

    /**
     * 是否是子集
     *
     * @param a
     * @param b
     * @return a是否是b的子集
     */
    public static boolean isSubCollection(final Collection a, final Collection b) {
        if (a == null || b == null) return false;
        Map mapA = getCardinalityMap(a);
        Map mapB = getCardinalityMap(b);
        for (Object obj : a) {
            if (getFreq(obj, mapA) > getFreq(obj, mapB)) {
                return false;
            }
        }
        return true;
    }

    /**
     * 两个集合是否相等
     *
     * @param a
     * @param b
     * @return
     */
    public static boolean isEqualCollection(final Collection a, final Collection b) {
        if (a == null || b == null) return false;
        if (a.size() != b.size()) {
            return false;
        } else {
            Map mapA = getCardinalityMap(a);
            Map mapB = getCardinalityMap(b);
            if (mapA.size() != mapB.size()) {
                return false;
            } else {
                for (Object obj : mapA.keySet()) {
                    if (getFreq(obj, mapA) != getFreq(obj, mapB)) {
                        return false;
                    }
                }
                return true;
            }
        }
    }

    /**
     * 获取集合中指定元素的个数
     *
     * @param obj
     * @param coll
     * @param <E>
     * @return
     */
    public static <E> int cardinality(E obj, final Collection<E> coll) {
        if (coll == null) return 0;
        if (coll instanceof Set) {
            return (coll.contains(obj) ? 1 : 0);
        }
        int count = 0;
        if (obj == null) {
            for (E e : coll) {
                if (e == null) {
                    count++;
                }
            }
        } else {
            for (E e : coll) {
                if (obj.equals(e)) {
                    count++;
                }
            }
        }
        return count;
    }

    /**
     * 删除原集合中不符合条件的元素
     *
     * @param collection
     * @param closure
     * @param <E>
     */
    public static <E> void forAllDo(Collection<E> collection, Closure<E> closure) {
        if (collection == null || closure == null) return;
        int index = 0;
        for (E e : collection) {
            closure.execute(index++, e);
        }
    }

    /**
     * 删除原集合中不符合条件的元素
     *
     * @param collection
     * @param predicate
     * @param <E>
     */
    public static <E> void filter(Collection<E> collection, Predicate<E> predicate) {
        if (collection == null || predicate == null) return;
        for (Iterator it = collection.iterator(); it.hasNext(); ) {
            if (!predicate.evaluate((E) it.next())) {
                it.remove();
            }
        }
    }

    /**
     * 查找出原集合中符合条件的元素并返回新的集合
     *
     * @param inputCollection
     * @param predicate
     * @param <E>
     * @return
     */
    public static <E> Collection<E> select(Collection<E> inputCollection, Predicate<E> predicate) {
        if (inputCollection == null || predicate == null) return new ArrayList<>();
        ArrayList<E> answer = new ArrayList<>(inputCollection.size());
        for (E o : inputCollection) {
            if (predicate.evaluate(o)) {
                answer.add(o);
            }
        }
        return answer;
    }

    /**
     * 对原集合进行转变
     *
     * @param collection
     * @param transformer
     * @param <E1>
     * @param <E2>
     */

    public static <E1, E2> void transform(Collection<E1> collection, Transformer<E1, E2> transformer) {
        if (collection == null || transformer == null) return;
        if (collection instanceof List) {
            List list = (List) collection;
            for (ListIterator it = list.listIterator(); it.hasNext(); ) {
                it.set(transformer.transform((E1) it.next()));
            }
        } else {
            Collection resultCollection = collect(collection, transformer);
            collection.clear();
            collection.addAll(resultCollection);
        }
    }

    /**
     * 对原集合进行转变并返回新的集合
     *
     * @param inputCollection
     * @param transformer
     * @param <E1>
     * @param <E2>
     * @return
     */
    public static <E1, E2> Collection<E2> collect(final Collection<E1> inputCollection,
                                                  final Transformer<E1, E2> transformer) {
        List<E2> answer = new ArrayList<>();
        if (inputCollection == null || transformer == null) return answer;
        for (E1 e1 : inputCollection) {
            answer.add(transformer.transform(e1));
        }
        return answer;
    }

    /**
     * 判断集合是否为空
     *
     * @param list
     * @return
     */
    public static boolean isEmpty(List list) {
        return list == null || list.size() == 0;
    }

    /**
     * 集合转为字符串
     *
     * @param collection
     * @return
     */
    public static String toString(Collection collection) {
        if (collection == null) return "null";
        return collection.toString();
    }


    private static int getFreq(final Object obj, final Map freqMap) {
        Integer count = (Integer) freqMap.get(obj);
        if (count != null) {
            return count;
        }
        return 0;
    }

    public interface Closure<E> {
        void execute(int index, E item);
    }

    public interface Transformer<E1, E2> {
        E2 transform(E1 input);
    }

    public interface Predicate<E> {
        boolean evaluate(E item);
    }
}
