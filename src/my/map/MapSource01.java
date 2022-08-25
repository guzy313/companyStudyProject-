package my.map;

import java.util.*;

public class MapSource01 {

    public static void main(String[] args) throws Exception{
        /*
        Map map = new HashMap();
        map.put("ff","FF");
        map.put("aa","AA");
        map.put("xx","XX");
        map.put("xx","XXx");

        map.remove("xx");
        System.out.println(map);
        System.out.println("===="+map.get("ff"));
        System.out.println(map);
        Object xx = map.get("ff");

        System.out.println("map.size() "+map.size());
        System.out.println("map.isEmpty() "+map.isEmpty());

        map.clear();

        System.out.println(" map.isEmpty() after map.clear()" +map.isEmpty());
        map.put("aaxx","xx");
        System.out.println(map);


        TreeSet treeSet = new TreeSet(new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                return ((String)o2).compareTo((String)o1);
            }
        });
        treeSet.add("xx");
        treeSet.add("aa");
        treeSet.add("AA");

        System.out.println(treeSet);
*/

        HashMap hashMap = new HashMap();
        hashMap.put("xx","yy");
        hashMap.put("y","x");

        Set hsSet = hashMap.entrySet();
        Iterator hsIt = hsSet.iterator();
        while (hsIt.hasNext()) {
            Map.Entry  next = (Map.Entry) hsIt.next();
            System.out.println(next.getValue());

        }





    }

}
