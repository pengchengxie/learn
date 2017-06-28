package xin.imba.commons.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Util - 随机数工具类
 * <p>
 * Created by xiepengcheng on 2016/10/23.
 */
public final class RandomUtil {

    private final static Random random = new Random();

    /**
     * 获取end以内的随机数，如end=100贼返回0~99的随机数
     * @param end
     * @return
     */
    public static int randomInt(int end) {
        if (end < 1)
            return -1;
        int index = random.nextInt(end);;
        return index;
    }

    /**
     * 随机获取数组元素
     *
     * @param array
     * @param <T>
     * @return
     */
    public static <T> T randomArrayElement(T[] array) {
        if (array == null || array.length < 1)
            return null;
        return array[randomInt(array.length)];
    }

    /**
     * 随机获取 List元素
     *
     * @param list {@link List}
     * @return
     */
    public static <T> T randomListElement(List<? extends T> list) {
        if (list == null || list.size() < 1)
            return null;
        return list.get(
                randomInt(list.size()
                ));
    }

    /**
     * 随机获取 List元素
     *
     * @param list  {@link List}
     * @param count 获取随机元素数量
     * @param <T>   泛型约束
     * @return List 随机元素集合
     */
    public static <T> List<T> randomList(List<? extends T> list, int count) {
        List<T> results = new ArrayList<>(count);
        for (int i = 0; i < count; i++) {
            results.add(randomListElement(list));
        }
        return results;
    }

    /**
     * 随机获取 List元素
     *
     * @param list       {@link List}
     * @param count      随机元素总数
     * @param repeatable 是否可以重复
     * @param <T>        泛型
     * @return 随机元素集合
     */
    public static <T> List<T> randomList(List<? extends T> list, int count, boolean repeatable) {


        if (!repeatable && count >= list.size()) {
            throw new ArrayIndexOutOfBoundsException("It 's more count than list size.");
        }

        if (repeatable) {
            return randomList(list, count);
        } else {

            if (count > list.size() * 3 / 5) {
                List<T> temp = randomList(list, list.size() - count);
                List<T> results = new ArrayList<>(list);
                results.removeAll(temp);
                return results;
            } else {
                List<T> results = new ArrayList<>(count);
                T t = null;
                for (int i = 0; i < count; i++, t = randomListElement(list)) {
                    if (results.contains(t)) {
                        i--;
                        continue;
                    }
                    results.add(randomListElement(list));
                }
                return results;
            }

        }
    }

}
