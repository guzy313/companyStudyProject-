package my.collection_;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

public class Collection01 {
    public static void main(String[] args) throws Exception{
       /* long endTime = System.currentTimeMillis();
        //long time_consuming = endTime - startTime;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:SS");
        Date dt = new Date(endTime);
        System.out.println(dt);
        List list = new ArrayList();
        list.add("1212");

        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }*/
        SimpleDateFormat sdfWeek = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        System.out.println(sdfWeek.format(date));
    }
}
