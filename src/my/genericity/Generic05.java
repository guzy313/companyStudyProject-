package my.genericity;



import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@SuppressWarnings("all")
public class Generic05 {
    public static void main(String[] args) throws Exception{

        Object o1 = new String("str");
        List<Object> list1 = new ArrayList<>();
        List<String> list2 = new ArrayList<>();
        List<AA1>  list3 = new ArrayList<>();
        List<BB1> list4 = new ArrayList<>();
        List<CC1> list5 = new ArrayList<>();

        printCollection1(list1);

        printCollection2(list3);

        printCollection3(list1);
        printCollection3(list3);



    }

    public static void printCollection1(List<?> c){
        for (Object obj:c) {
            System.out.println(obj);
        }
    }

    public static void printCollection2(List<? extends AA1> c){
        for (Object obj:c) {
            System.out.println(obj);
        }
    }


    public static void printCollection3(List<? super AA1> c){
        for (Object obj:c) {
            System.out.println(obj);
        }
    }

}

class AA1{

}
class BB1 extends AA1{

}

class CC1 extends BB1{

}