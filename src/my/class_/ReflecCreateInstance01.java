package my.class_;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ReflecCreateInstance01 {

    @Test
    public void test() throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException, NoSuchFieldException {
        Class<?> cls = Class.forName("entity.Testxx");
        Object o = cls.newInstance();//
        Object o1 = cls.getConstructor().newInstance();
        Object o2 = cls.getConstructor(int.class).newInstance(3);
        System.out.println(o);
        System.out.println(o1);
        System.out.println(o2);

        Constructor<?> declaredConstructor = cls.getDeclaredConstructor(String.class);
        declaredConstructor.setAccessible(true);
        Object o3 = declaredConstructor.newInstance("测试暴破");
        System.out.println(o3);


        Method dm = cls.getDeclaredMethod("testPrivate");
        dm.setAccessible(true);
        dm.invoke(o);

        Field nation = cls.getDeclaredField("nation");
        nation.setAccessible(true);
        System.out.println(nation.get(o3));


    }


}
