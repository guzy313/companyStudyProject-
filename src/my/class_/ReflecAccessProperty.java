package my.class_;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;

public class ReflecAccessProperty {


    @Test
    public void test() throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchFieldException {
        Class<?> cls = Class.forName("entity.Testxx");
        Object o = cls.newInstance();
        Field eee = cls.getDeclaredField("eee");
        System.out.println(eee.get(o));


    }

}
