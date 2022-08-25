package my.map;


import java.util.*;

@SuppressWarnings("all")
public class MapFor {
    public static void main(String[] args){
        Map map = new HashMap();
        map.put("ff","FF");
        map.put("aa","AA");
        map.put("xx","XX");

        Set set = map.keySet();

        for (Object obj:set) {
            System.out.println( map.get(obj));
        }

        Iterator it = set.iterator();
        while (it.hasNext()){
            System.out.println("iterator: "+map.get(it.next()));
        }

        Set entrySet = map.entrySet();
        Iterator itx = entrySet.iterator();
        while (itx.hasNext()){
            System.out.println(itx.next());
        }
        for (Object obj:entrySet) {
            Map.Entry m = (Map.Entry) obj;
            System.out.println("m "+m.getValue());
        }

        /*
        Collection values = map.values();
        for (Object obj:values ) {
            System.out.println("collection "+obj);
        }
        Iterator itcollection = values.iterator();
        while (itcollection.hasNext()) {
            Object next =  itcollection.next();
            System.out.println("");

        }*/



    }
}
