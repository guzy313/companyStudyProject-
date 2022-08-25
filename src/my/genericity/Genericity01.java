package my.genericity;

import java.util.ArrayList;
import java.util.Iterator;

@SuppressWarnings({"all"})
public class Genericity01 {
    public static void main(String[] args){
        ArrayList arrayList = new ArrayList();
        arrayList.add(new Dogg("d1",1));
        arrayList.add(new Dogg("d2",2));
        Iterator it = arrayList.iterator();
        while (it.hasNext()){
            Dogg o = (Dogg)it.next();
            System.out.println(o.getName()+"\t"+o.getAge());
        }


        //
        ArrayList<Dogg> doggs = new ArrayList<>();
        doggs.add(new Dogg("d3",3));
        doggs.add(new Dogg("d4",4));
        Iterator<Dogg> doggIterator = doggs.iterator();
        while (doggIterator.hasNext()) {
            Dogg next =  doggIterator.next();
            System.out.println("Doggs "+next.getName()+"\t"+next.getAge());
        }




    }
}

class Dogg{
    private String name;
    private int age;

    public Dogg(String name, int age) {
        this.name = name;
        this.age = age;
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

    @Override
    public String toString() {
        return "name = "+name+"age = "+age;
    }
}
