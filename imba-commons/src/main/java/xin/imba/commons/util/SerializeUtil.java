package xin.imba.commons.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Util - 序列化工具类
 * <p>
 * Created by xiepengcheng on 2017/5/20.
 */
public final class SerializeUtil {

    /**
     * 使用ObjectOutputStream将对象序列化，
     * 传入ByteArrayOutputStream中，
     * 最后输出到一个byte类型的数组中
     * 而这样的数组可以直接存入数据库
     *
     * @param t
     * @param <T>
     * @return
     */
    public static <T> byte[] serialize(T t) {
        try {
            ByteArrayOutputStream arrayOutputStream = new ByteArrayOutputStream();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(arrayOutputStream);
            objectOutputStream.writeObject(t);
            objectOutputStream.flush();
            byte[] data = arrayOutputStream.toByteArray();
            arrayOutputStream.close();
            objectOutputStream.close();
            return data;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 将数据库中的数据读到一个byte数组中
     * 通过ByteArrayInputStream传入ObjectInputStream中
     * 从ObjectInputStream读出object对象并强制转换成Task
     *
     * @param data
     * @return
     */
    public static <T> T deserialize(byte[] data) {
        try {
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(data);
            ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
            T t = (T) (objectInputStream.readObject());
            byteArrayInputStream.close();
            objectInputStream.close();
            return t;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
