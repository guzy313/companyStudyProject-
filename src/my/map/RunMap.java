package my.map;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RunMap {
    public static void main(String[] args){
        Map<String, Object> data1 = new HashMap<>();
        data1.put("id1","123");
        data1.put("name1","NN");
        List<Map<String,Object>> list1 = new ArrayList<Map<String,Object>>() ;
        for (int i = 0; i < 3; i++) {
            list1.add(data1);
        }

        Map<String, Object> data = new HashMap<>();
        data.put("id","123");
        data.put("name","NN");

        List<Map<String,Object>> list = new ArrayList<Map<String,Object>>() ;
        for (int i = 0; i < 3; i++) {
            list.add(data);
        }

        System.out.println(list);


        for (Map<String,Object> l:list ) {
            l.put("x",list1);
        }

        System.out.println(list);



    }
}
