package my.class_;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ClassLoad_ {

    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException,
            NoSuchMethodException, InvocationTargetException {
        Class<?> cls = Class.forName("entity.Testxx");
        Object o = cls.newInstance();
        Method method = cls.getMethod("tt");
        method.invoke(o);

        Constructor<?> constructor = cls.getConstructor(int.class);
        Object o1 = constructor.newInstance(123);
        method.invoke(o1);

        Field[] fields  = cls.getDeclaredFields();
        for (Field f:fields ) {
            System.out.println(f.getName() +"-" + f.getType());
        }




    }



}
