package utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateUtilsForSTP {


    /**
     * 获得指定日期的后一天
     * @param specifiedDay,n
     * @return
     */
    public static String getDayAfter(String specifiedDay,int n){
        Calendar c = Calendar.getInstance();
        Date date=null;
        try {
            date = new SimpleDateFormat("yy-MM-dd").parse(specifiedDay);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        c.setTime(date);
        int day=c.get(Calendar.DATE);
        c.set(Calendar.DATE,day+n);
        String dayAfter=new SimpleDateFormat("yyyy-MM-dd").format(c.getTime());
        return dayAfter;
    }

    /**
     * 获得指定日期的前n天
     * @param specifiedDay,n
     * @return
     */
    public static String getDayBefore(String specifiedDay,int n){
        Calendar c = Calendar.getInstance();
        Date date=null;
        try {
            date = new SimpleDateFormat("yy-MM-dd").parse(specifiedDay);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        c.setTime(date);
        int day=c.get(Calendar.DATE);
        c.set(Calendar.DATE,day - n);
        String dayBefore=new SimpleDateFormat("yyyy-MM-dd").format(c.getTime());
        return dayBefore;
    }


    //判断星期先后用
    public static int getWeekNum(String n){
        char x = '0';
        if(n.length() == 3){
            x = n.charAt(2);
        }else if(n.length() == 2){
            x = n.charAt(1);
        }else{
            //throw new ApplicationException("星期长度不正确");
        }

        switch (x){
            case '一':
                return 1;
            case '二':
                return 2;
            case '三':
                return 3;
            case '四':
                return 4;
            case '五':
                return 5;
            case '六':
                return 6;
            case '天':
                return 7;
            case '日':
                return 7;
            case '七':
                return 7;
            default:
                return -1;
        }
    }

    public static String getWeek(Date date){

        SimpleDateFormat sdf = new SimpleDateFormat("EEEE");

        String week = sdf.format(date);

        return week;

    }


    public static String getYearByDate(String date) throws Exception{
        Calendar ca = Calendar.getInstance();
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
        ca.setTime(sdf1.parse(date));
        int year = ca.get(Calendar.YEAR);
        return year+"";
    }


    public static String getMonthByDate(String date) throws Exception{
        Calendar ca = Calendar.getInstance();
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
        ca.setTime(sdf1.parse(date));
        int month = ca.get(Calendar.MONTH) + 1;
        return month+"";
    }

    /**
     * 通过日期获取 日
     * @param date
     * @return
     * @throws Exception
     */
    public static String getDayByDate(String date) throws Exception{
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return String.format("%td",sdf.parse(date));
    }

    /**
     * 计算两个日期之间相差的天数
     * @param smdate 较小的时间
     * @param bdate 较大的时间
     * @return 相差天数
     * @throws ParseException
     */
    public static int daysBetween(Date smdate,Date bdate) throws ParseException
    {
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        smdate=sdf.parse(sdf.format(smdate));
        bdate=sdf.parse(sdf.format(bdate));
        Calendar cal = Calendar.getInstance();
        cal.setTime(smdate);
        long time1 = cal.getTimeInMillis();
        cal.setTime(bdate);
        long time2 = cal.getTimeInMillis();
        long between_days=(time2-time1)/(1000*3600*24);

        return Integer.parseInt(String.valueOf(between_days));
    }


    /**
     *计算两个日期之间相差的月数
     * @param smdate 较小的时间
     * @param bdate 较大的时间
     * @return 相差月数
     * @throws Exception
     */
    public static int monthsBetween(Date smdate,Date bdate) throws Exception{
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String str1 = sdf.format(smdate);
        String str2 = sdf.format(bdate);
        Calendar bef = Calendar.getInstance();
        Calendar aft = Calendar.getInstance();
        bef.setTime(sdf.parse(str1));
        aft.setTime(sdf.parse(str2));
        int result = aft.get(Calendar.MONTH) - bef.get(Calendar.MONTH);
        int month = (aft.get(Calendar.YEAR) - bef.get(Calendar.YEAR)) * 12;
        System.out.println(Math.abs(month + result));
        return Math.abs(month + result);
    }




    /**
     * 获取某月最后一天
     * @return
     */
    public static Date getMonthLastDay(Date date){
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        do {
            cal.add(Calendar.DATE, 1);
        }while (cal.get(Calendar.DATE) != 1);
        cal.add(Calendar.DATE, -1);
        return cal.getTime();
    }

    /**
     * 2月份最后一天是否闰年处理
     * @param date
     * @return
     * @throws Exception
     */
    public static String  leapOrNotTransformDate(String date) throws Exception{
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        date = String.format(date,"yyyy-MM-dd");
        String[] arr = date.split("-");
        System.out.println(arr[1]);
        if(Integer.parseInt(arr[1]) == 2){//2月份
             String lastDayStr = sdf.format(getMonthLastDay(sdf.parse(arr[0]+"-"+ arr[1] +"-"+ "1")));
             int lastDay = Integer.parseInt(getDayByDate(lastDayStr));
             if(lastDay <= Integer.parseInt(arr[2])){
                 return lastDayStr;
             }else{
                 return date;
             }
        }else{
            return date;
        }
    }

    public static int getQuarterByMonth(int month){
        if(1 <=  month && month < 4){
            return 1;
        }else if(4 <= month && month < 7){
            return 2;
        }else if(7 <= month && month < 9){
            return 3;
        }else if(9 <= month && month <= 12){
            return 4;
        }else{
            return -1;
        }
    }


}
