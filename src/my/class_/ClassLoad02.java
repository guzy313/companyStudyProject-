package my.class_;

import org.junit.jupiter.api.Test;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ClassLoad02 {

    @Test
    public void test() throws ClassNotFoundException, IllegalAccessException, InstantiationException, InvocationTargetException {
        Class<?> aClass = Class.forName("my.class_.Yu");
        System.out.println("全类名:"+aClass.getName());
        System.out.println("简单类名:"+aClass.getSimpleName());
        System.out.println("所有声明的字段:");
        for (Field f:aClass.getDeclaredFields()) {
            System.out.println(f.getName());
        }
        System.out.println("public字段:");
        for (Field f:aClass.getFields()) {
            System.out.println(f.getName());
        }
        System.out.println("public方法:");
        for (Method m:aClass.getMethods()) {
            System.out.println(m.getName());
        }
        System.out.println("父类信息:");
        Class<?> superclass = aClass.getSuperclass();
        System.out.println(superclass);
        System.out.println("接口信息:");
        for (Class c:aClass.getInterfaces()) {
            System.out.println(c.getName());
        }
        System.out.println("注解信息:");
        for (Annotation a:aClass.getAnnotations() ) {
            System.out.println(a);
        }

        System.out.println("构造器:");
        Constructor<?>[] constructors = aClass.getConstructors();
        for (Constructor c:constructors) {
            System.out.println(c);
        }
        System.out.println("声明构造器:");

        Constructor<?>[] declaredConstructors = aClass.getDeclaredConstructors();
        for (Constructor c:declaredConstructors ) {
            System.out.println(c.getName());
        }


    }
}

interface Ia{

}
interface Ib{

}
class Yy{
    public String hobby;
}
class Yu extends Yy implements Ia,Ib{
    public String name;
    protected int age;
    char sex;
    private String job;


    public Yu() {
    }


    public Yu(String job) {
        this.job = job;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public char getSex() {
        return sex;
    }

    public void setSex(char sex) {
        this.sex = sex;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }
}
