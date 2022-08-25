package my.collection_;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;

@SuppressWarnings("all")
public class Collection02 {
    public static void main(String[] args){
        ArrayList arrayList = new ArrayList();
        arrayList.add(new Bookss("测试书名"+1,"测试作者名"+1,12));
        arrayList.add(new Bookss("测试书名"+2,"测试作者名"+2,3));
        arrayList.add(new Bookss("测试书名"+3,"测试作者名"+3,55));
       /* arrayList.sort(new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                double i1 = 0;
                double i2 = 0;
                if(o1 instanceof Bookss){
                     i1 = ((Bookss)o1).getPrice();
                }
                if(o2 instanceof Bookss){
                     i2 = ((Bookss)o2).getPrice();
                }
                if((i1 - i2) >= 0){//调整正反序
                    return -1;
                }else{
                    return 1;
                }
            }
        });*/

       sortBu(arrayList);

        Iterator iterator = arrayList.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }
        System.out.println("-----------------------");
        for (int i = 0; i < arrayList.size(); i++) {
            System.out.println(arrayList.get(i));
        }
        System.out.println("-----------------------");
        for (Object obj:arrayList ) {
            System.out.println(obj);
        }
        System.out.println("-----------------------");

        System.out.println(arrayList);


    }


    public static void sortBu(ArrayList arrayList){
        for (int i = 0; i < arrayList.size() - 1; i++) {
            Object obj = arrayList.get(i);
            if(obj instanceof Bookss){
                if(((Bookss)arrayList.get(i)).getPrice() > ((Bookss)arrayList.get(i + 1)).getPrice()){
                    arrayList.set(i,arrayList.get(i + 1));
                    arrayList.set(i + 1,obj);
                }
            }

        }
    }


}
@SuppressWarnings("all")
class Bookss{
    private String name;
    private String author;
    private double price;

    public Bookss(String name, String author, double price) {
        this.name = name;
        this.author = author;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return name+"\t"+author+"\t"+price;
    }
}
