import com.sun.deploy.util.StringUtils;
import utils.NumToCn;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class run1 {
    public static void main(String[] args) throws Exception{


        Date date = new Date();
        int countPeriod = 0;//一共跨过多少个周期

        String startTimePeriod="",endTimePeriod="";//当前周期开始结束时间  --周期
        String startTimeFinal="",endTimeFinal="";//本周期开始(周几)对应的年月日-以下为计算过程 以及本周期结束.... --填报
        String  startTime= "2022-3-5",endTime="";
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
        String startPeriod = "星期三",endPeriod = "星期六" ;//开始周期 +结束周期

        //endTime = startTime;
        if(endTime == "" || endTime == null){
            endTime = getDayAfter(startTime,7 - getWeekNum(getWeek(sdf1.parse(startTime))));//任务结束日期为空的情况（给定时任务用）
        }


        if(getWeekNum(endPeriod) < getWeekNum(startPeriod)){
            throw new NullPointerException("填报结束周期必须在开始周期之后");
        }else if(getWeekNum(endPeriod) == getWeekNum(startPeriod)){

        }else{

        }
        //如果endtime超过第一周周日
        int weekendPastdayFirst = getWeekNum("星期天") - getWeekNum(getWeek(sdf1.parse(startTime)));
        String weekendFirst = getDayAfter(startTime,weekendPastdayFirst);
        System.out.println("本周日"+weekendFirst);

        System.out.println(daysBetween(sdf1.parse(weekendFirst),sdf1.parse(endTime)));


        //int betweenDay = daysBetween(sdf1.parse(startTime),sdf1.parse(endTime));//计算结束日期和开始日期差多少天
        if(daysBetween(sdf1.parse(weekendFirst),sdf1.parse(endTime)) > 0){//如果结束日期在本周日之后  即跨到下周之后
            int pastDay = daysBetween(sdf1.parse(weekendFirst),sdf1.parse(endTime));
            //remainder余数
            int firstAfterWeekNums = pastDay / 7;//第一周之后剩余多少周
            int endWeekRemainder = pastDay % 7;//最后一周剩余多少天

            String lastWeekend = weekendFirst;//上一周周末 = 第一周周末


            //第一周期计算--必须执行
            startTimePeriod = startTime;//周期开始 第一周期

            endTimePeriod = getDayAfter(startTimePeriod,7 - getWeekNum(getWeek(sdf1.parse(startTime))));//周期结束 （7 - 周期开始日期距本周周日的天数）
            startTimeFinal = getDayBefore(endTimePeriod,7 - getWeekNum(startPeriod));//填报开始 startPeriod(周几)
            endTimeFinal = getDayAfter(startTimeFinal,getWeekNum(endPeriod) - getWeekNum(startPeriod));//填报结束 endPeriod(周几)
            countPeriod ++;//计算第几周期

            System.out.println("第一周:");
            System.out.println("taskid"+"periodType"+"第"+ NumToCn.numberToChinese(countPeriod)+"周"+"countPeriod:"+countPeriod+"\n startTimePeriod:"+sdf1.parse(startTimePeriod)+"\n endTimePeriod:"+sdf1.parse(endTimePeriod)+
                    "\n startTimeFinal:"+sdf1.parse(startTimeFinal)+"\n endTimeFinal"+sdf1.parse(endTimeFinal)+"\n date:"+date);

            System.out.println("startTimeFinal填报开始"+startTimeFinal);
            System.out.println("endTimeFinal填报结束"+endTimeFinal);
            System.out.println("startTimePeriod周期开始"+startTimePeriod);
            System.out.println("endTimePeriod周期结束"+endTimePeriod);


            //完整的周
            for (int i = 0; i < firstAfterWeekNums; i++) {
                if(i == 0){
                    lastWeekend = weekendFirst;
                    countPeriod ++;//计算第几周期
                }else{
                    lastWeekend = getDayAfter(lastWeekend,7);//lastWeekend + 7;
                    countPeriod ++;//计算第几周期
                }
                startTimePeriod = getDayAfter(lastWeekend,1);//周期开始 周一
                endTimePeriod = getDayAfter(startTimePeriod,6);//周期结束 周日
                int pastDay_lastWeekend_nextPeriodstartDay = getWeekNum(startPeriod) - getWeekNum("星期一") + 1;//填报开始距离上周日的日期
                startTimeFinal = getDayAfter(startTimePeriod,getWeekNum(startPeriod) - getWeekNum("星期一"));//填报开始 startPeriod(周几)
                endTimeFinal = getDayAfter(startTimeFinal,getWeekNum(endPeriod) - getWeekNum(startPeriod));//填报结束 endPeriod(周几)

                System.out.println("taskid"+"periodType"+"第"+ NumToCn.numberToChinese(countPeriod)+"周"+"countPeriod:"+countPeriod+"\n startTimePeriod:"+sdf1.parse(startTimePeriod)+"\n endTimePeriod:"+sdf1.parse(endTimePeriod)+
                        "\n startTimeFinal:"+sdf1.parse(startTimeFinal)+"\n endTimeFinal"+sdf1.parse(endTimeFinal)+"\n date:"+date);
                 /*
                SuperviseTaskPeriod superviseTaskPeriod = SuperviseTaskPeriod.getUpdateSuperviseTaskPeriod(taskid,Integer.parseInt(params.get("periodType")),"第"+NumToCn.numberToChinese(countPeriod)+"周",countPeriod,sdf1.parse(startTimePeriod),sdf1.parse(endTimePeriod),
                        sdf1.parse(startTimeFinal),sdf1.parse(endTimeFinal), date,date );
                this.insert(superviseTaskPeriod);*/
                System.out.println("startTimeFinal填报开始"+startTimeFinal);
                System.out.println("endTimeFinal填报结束"+endTimeFinal);
                System.out.println("startTimePeriod周期开始"+startTimePeriod);
                System.out.println("endTimePeriod周期结束"+endTimePeriod);
            }
            //不完整的最后一周
            if(endWeekRemainder > 0){
                countPeriod ++;//计算第几周期
                startTimePeriod = getDayAfter(lastWeekend,1);//周期开始 周一
                endTimePeriod = endTime;//周期结束 任务结束日期 endTime
                int pastDay_lastWeekend_nextPeriodstartDay = getWeekNum(startPeriod) - getWeekNum("星期一") + 1;//填报开始距离上周日的日期
                startTimeFinal = getDayAfter(startTimePeriod,getWeekNum(startPeriod) - getWeekNum("星期一"));//填报开始 startPeriod(周几)
                endTimeFinal = getDayAfter(startTimeFinal,getWeekNum(endPeriod) - getWeekNum(startPeriod));//填报结束 endPeriod(周几)

                System.out.println("taskid"+"periodType"+"第"+ NumToCn.numberToChinese(countPeriod)+"周"+"countPeriod:"+countPeriod+"\n startTimePeriod:"+sdf1.parse(startTimePeriod)+"\n endTimePeriod:"+sdf1.parse(endTimePeriod)+
                        "\n startTimeFinal:"+sdf1.parse(startTimeFinal)+"\n endTimeFinal"+sdf1.parse(endTimeFinal)+"\n date:"+date);
                  /*
                SuperviseTaskPeriod superviseTaskPeriod = SuperviseTaskPeriod.getUpdateSuperviseTaskPeriod(taskid,Integer.parseInt(params.get("periodType")),"第"+NumToCn.numberToChinese(countPeriod)+"周",countPeriod,sdf1.parse(startTimePeriod),sdf1.parse(endTimePeriod),
                        sdf1.parse(startTimeFinal),sdf1.parse(endTimeFinal), date,date );
                this.insert(superviseTaskPeriod);*/
                System.out.println("startTimeFinal填报开始"+startTimeFinal);
                System.out.println("endTimeFinal填报结束"+endTimeFinal);
                System.out.println("startTimePeriod周期开始"+startTimePeriod);
                System.out.println("endTimePeriod周期结束"+endTimePeriod);
            }
        }else{//如果结束日期小于等于本周日
            Date startTimeDate = sdf1.parse(startTime);//任务开始时间
            String startTimeWeek = getWeek(startTimeDate);
            //计算周期开始日期(周几) 与任务开始日期(几几年几月几号)差的天数  -目的 用任务开始日期算出 本周期第一天的日期
            int startpastday = getWeekNum(getWeek(sdf1.parse(startTime))) - getWeekNum(startPeriod) ;
            //计算周期开始日期(周几) 与周期结束日期(周几)差的天数
            int endpastday =  getWeekNum(endPeriod) - getWeekNum(startPeriod);
            int betweenDay = daysBetween(sdf1.parse(startTime),sdf1.parse(endTime));//计算结束日期和开始日期差多少天
            System.out.println(betweenDay);
            startTimeFinal = getDayBefore(startTime,startpastday);//填报
            endTimeFinal = getDayAfter(startTimeFinal,endpastday);//填报
            startTimePeriod = startTime;
            endTimePeriod = endTime;
            countPeriod ++;


            System.out.println("taskid"+"periodType"+"第"+ NumToCn.numberToChinese(countPeriod)+"周"+"countPeriod:"+countPeriod+"\n startTimePeriod:"+sdf1.parse(startTimePeriod)+"\n endTimePeriod:"+sdf1.parse(endTimePeriod)+
                    "\n startTimeFinal:"+sdf1.parse(startTimeFinal)+"\n endTimeFinal"+sdf1.parse(endTimeFinal)+"\n date:"+date);
            /*
            SuperviseTaskPeriod superviseTaskPeriod = SuperviseTaskPeriod.getUpdateSuperviseTaskPeriod(taskid,Integer.parseInt(params.get("periodType")),"第"+NumToCn.numberToChinese(countPeriod)+"周",countPeriod,sdf1.parse(startTimePeriod),sdf1.parse(endTimePeriod),
                    sdf1.parse(startTimeFinal),sdf1.parse(endTimeFinal), date,date );
            this.insert(superviseTaskPeriod);*/

            System.out.println("startTimeFinal填报开始"+startTimeFinal);
            System.out.println("endTimeFinal填报结束"+endTimeFinal);
            System.out.println("startTimePeriod周期开始"+startTimePeriod);
            System.out.println("endTimePeriod周期结束"+endTimePeriod);
        }














        /*
        if(betweenDay <= 7){
            //计算第一周周日日期
            int weekendPastday = getWeekNum("星期天") - getWeekNum(getWeek(sdf1.parse(startTime)));
            String weekendFirst = getDayAfter(startTime,weekendPastday);
            System.out.println("本周日"+weekendFirst);
            if((sdf1.parse(endTime)).before(sdf1.parse(weekendFirst))){//如果任务结束日期在本周末之前
                if((sdf1.parse(endTime)).before(sdf1.parse(endTimeFinal))){//如果任务结束日期在本周周期结束日期之前
                    endTimePeriod = endTime;//本周期任务结束时间= 任务周期结束日期
                }else{
                    endTimePeriod = endTimeFinal;//本周期任务结束时间= 本周周期结束日期
                }
            }else{//如果任务结束日期在本周末之后
                endTimePeriod = endTimeFinal;//本周期任务结束时间= 本周周期结束日期
            }
        }else{
        }

        */





    }






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
}
