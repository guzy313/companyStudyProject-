package my.file_.homework_;

import java.io.Serializable;

public class Dogg1 implements Serializable{
    private String name;
    private int age;
    private String color;

    private static final long serialVersionUID = 1L;

    public Dogg1() {
    }

    public Dogg1(String name, int age, String color) {
        this.name = name;
        this.age = age;
        this.color = color;
    }

    @Override
    public String toString() {
        return name + "\t" + age + "\t" + color;
    }
}
