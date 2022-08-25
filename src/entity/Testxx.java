package entity;

import java.io.Serializable;

public class Testxx implements Serializable {
    private String id;
    private String name;
    private int age;
    private static String color;
    private transient String nation;
    public int xxx;
    public static String eee = "e3";

    private static final long SerialVersionUID = 1L;

    public Testxx() {
    }

    public Testxx(int xxx) {
        this.xxx = xxx;
    }
    private Testxx(String name){
        this.name = name;
    }

    public Testxx(String id, String name, int age, String color, String nation) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.color = color;
        this.nation = nation;
    }

    public void tt(){
        System.out.println("ttt");
        System.out.println("xxx = "+xxx);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    private void testPrivate(){
        System.out.println("私有暴破");
    }

    @Override
    public String toString() {
        return this.getId()+"\t"+this.getName()+"\t"+this.getAge()+"\t"+this.color+"\t"+this.nation + "\t"+this.xxx;
    }
}
