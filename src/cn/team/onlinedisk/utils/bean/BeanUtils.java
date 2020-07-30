package cn.team.onlinedisk.utils.bean;

import org.jetbrains.annotations.NotNull;

import java.lang.reflect.*;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.*;

/**
 * @ClassName BeanUtils
 * @Description pack request&response information
 * @Author luoyanze
 * @Date 2020/7/29 10:44 下午
 * @Version 1.0
 */


public class BeanUtils<K>{
    /**
     *  {@code cls}为要封装的目的对象，应该和{@code k}保持一致
     */
    private Class cls;

    public BeanUtils(Class cls) {
        this.cls = cls;
    }

    /**
     * TODO : 该方法未测试！
     * TODO : 该方法未测试！
     * TODO : 该方法未测试！
     * TODO : 该方法未测试！
     * TODO : 该方法未测试！
     * TODO : 该方法未测试！
     * @description 将数据库查询的结果封装成{@code List}对象
     * @param res:
     * @return: java.util.List<K>
     */
    public List<K> pack(@NotNull ResultSet res){
        String[] heads = null;
        List<K> returnList = new ArrayList<>();
        try {
            ResultSetMetaData metaData = res.getMetaData();
            int count = metaData.getColumnCount();
            heads = new String[count];

            for (int i = 0; i < count; i++) {
                String setterName = metaData.getColumnName(i);
                char[] chars = setterName.toCharArray();
                chars[0] -= 32;
                heads[i] = "set" + new String(chars);

            }
            while(res.next()){
                Object o = cls.getConstructor().newInstance();
                for (int i = 0; i < count; i++) {
                    Method[] methods = cls.getMethods();
                    Method executeMethod = null;
                    for (Method method : methods) {
                        if (method.getName() == heads[i]){
                            executeMethod = method;
                            break;
                        }
                    }
                    executeMethod.invoke(o,res.getObject(i));
                }
                returnList.add((K) o);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                res.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

            return returnList;
        }
    }

    /**
     * @description 将request获得的map集合封装成一个{@code List}集合;
     * @param map:  request请求的map形式;
     * @return: java.util.List<K>
     */
    public List<K> pack(@NotNull Map< ? extends String, ? extends String[]> map){
        Iterator<? extends Map.Entry<? extends String, ? extends String[]>> iterator = map.entrySet().iterator();
        int count = iterator.next().getValue().length;

        ArrayList<K> arrayList = new ArrayList<>();
        try {
            for (int i = 0; i < count; i++) {
                Object o = cls.getConstructor().newInstance();
                for (Map.Entry<? extends String, ? extends String[]> entry : map.entrySet()) {
                    String key = entry.getKey();
                    String[] param = entry.getValue();

                    Field field = cls.getDeclaredField(key);
                    String classType = field.getGenericType().toString();

                    char[] chars = key.toCharArray();
                    chars[0] -= 32;
                    String methodName = "set" + new String(chars);
                    Method method = cls.getMethod(methodName, Class.forName(classType.split(" ")[1]));
                    method.invoke(o, param[i]);
                }
                arrayList.add((K) o);
            }
        } catch (InstantiationException | NoSuchFieldException | ClassNotFoundException | NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }finally {
            return arrayList;
        }

    }



}









