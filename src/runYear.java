import utils.DateUtilsForSTP;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class runYear {

    public static void main(String[] args) throws Exception{

        String startReport = "2022-2-30",endReport = "2022-6-13" ;//开始填报周期 + 结束周期(几号)
        String  startTime= "2022-3-12",endTime="2025-12-31";
        String taskid = "";int periodType = 0;
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");

        if(endTime == "" || endTime == null) {//处理任务结束日期为空的情况
            String thisYearLastDay = DateUtilsForSTP.getYearByDate(startTime)+"-12-31";//当年最后一天 12月31号
            endTime = thisYearLastDay;
        }


        String startTimePeriod="",endTimePeriod="";//当前周期开始结束时间  --周期
        String startTimeFinal="",endTimeFinal="";//本周期开始(几号)对应的年月日-以下为计算过程 以及本周期结束.... --填报


        int countPeriod = 0;//一共跨过多少个周期

        if(Integer.parseInt(DateUtilsForSTP.getYearByDate(startReport)) != Integer.parseInt(DateUtilsForSTP.getYearByDate(endReport))){
            throw new NullPointerException("填报日期不能跨年");
        }
        if(DateUtilsForSTP.daysBetween(sdf1.parse(startReport),sdf1.parse(endReport)) < 0){
            throw new NullPointerException("填报开始日期不能在填报结束日期之后");
        }

        int allPeriod = 0;
        allPeriod = Integer.parseInt(DateUtilsForSTP.getYearByDate(endTime)) - Integer.parseInt(DateUtilsForSTP.getYearByDate(startTime)) + 1;

        //闰年和非闰年处理
        startReport = DateUtilsForSTP.leapOrNotTransformDate(startReport);
        endReport = DateUtilsForSTP.leapOrNotTransformDate(endReport);

        String runtime = startTime;
        for (int i = 0; i < allPeriod; i++) {
            countPeriod ++;
            String thisYearFirstDay = DateUtilsForSTP.getYearByDate(runtime)+"-1-1";//当年第一天 1月1号
            String thisYearLastDay = DateUtilsForSTP.getYearByDate(runtime)+"-12-31";//当年最后一天 12月31号
            startTimeFinal = Integer.parseInt(DateUtilsForSTP.getYearByDate(runtime))+"-"+Integer.parseInt(DateUtilsForSTP.getMonthByDate(startReport))+"-"+Integer.parseInt(DateUtilsForSTP.getDayByDate(startReport));//填报开始 日期String
            endTimeFinal = Integer.parseInt(DateUtilsForSTP.getYearByDate(runtime))+"-"+Integer.parseInt(DateUtilsForSTP.getMonthByDate(endReport))+"-"+Integer.parseInt(DateUtilsForSTP.getDayByDate(endReport));//填报结束 日期String

            //闰年和非闰年处理
            startTimeFinal = DateUtilsForSTP.leapOrNotTransformDate(startTimeFinal);
            endTimeFinal = DateUtilsForSTP.leapOrNotTransformDate(endTimeFinal);

            if(DateUtilsForSTP.daysBetween(sdf1.parse(thisYearFirstDay),sdf1.parse(runtime)) >0 ){//第一个周期的 非完整的开始年的情况
                startTimePeriod = runtime;//周期开始 日期String
            }else{
                startTimePeriod = runtime;//周期开始 日期String
            }
            if(DateUtilsForSTP.daysBetween(sdf1.parse(thisYearLastDay),sdf1.parse(endTime)) < 0 ){
                endTimePeriod = endTime;
            }else{
                endTimePeriod = thisYearLastDay;
            }
            runtime = DateUtilsForSTP.getDayAfter(endTimePeriod,1);
            System.out.println("startTimeFinal填报开始"+startTimeFinal);
            System.out.println("endTimeFinal填报结束"+endTimeFinal);
            System.out.println("startTimePeriod周期开始"+startTimePeriod);
            System.out.println("endTimePeriod周期结束"+endTimePeriod);
            System.out.println("第"+countPeriod+"周期");
        }





        System.out.println(DateUtilsForSTP.getYearByDate(endReport));





    }
}
