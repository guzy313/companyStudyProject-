package my.map;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

@SuppressWarnings("all")
public class MapExercise01 {
    public static void main(String[] args) throws Exception{
        Map map = new HashMap();
        map.put(1,new Employee001(1,"员工1",19000));
        map.put(2,new Employee001(2,"员工1",16000));
        map.put(3,new Employee001(3,"员工1",26000));

        Set set =   map.entrySet();
        Iterator itset = set.iterator();
        while (itset.hasNext()) {
            Object next = itset.next();
            //System.out.println(((Map.Entry)next).getValue());
            Employee001 e = (Employee001)((Map.Entry)next).getValue();
            if(e.getSalary() > 18000){
                System.out.println("e1"+ e);
            }
        }

        Set setkey = map.keySet();
        Iterator itkey = setkey.iterator();
        while (itkey.hasNext()) {
            Object next =  itkey.next();
            Employee001 e = (Employee001) map.get(next);
            if(e.getSalary() > 18000){
                System.out.println("e2"+ e);
            }
        }

        for (Object obj:map.values()) {
            Employee001 e = (Employee001) obj;
            if(e.getSalary() > 18000){
                System.out.println("e3:"+e);
            }
        }







    }
}

class Employee001{
    private int id;
    private String name;
    private double salary;

    public Employee001(int id, String name, double salary) {
        this.id = id;
        this.name = name;
        this.salary = salary;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getSalary() {
        return salary;
    }

    @Override
    public String toString() {
        return "id:"+id+"\tname:"+name+"\tsalary:"+salary;
    }
}
