import com.sun.xml.internal.ws.api.server.SDDocument;
import entity.SuperviseTaskPeriod;
import utils.DateUtilsForSTP;
import utils.NumToCn;

import java.text.SimpleDateFormat;
import java.util.Date;

public class runMonth {

    public static void main(String[] args) throws Exception{

        String startReport = "3",endReport = "13" ;//开始填报周期 + 结束周期(几号)
        String  startTime= "2022-3-12",endTime="2022-5-31";
        String taskid = "";int periodType = 0;

        int startReportInt = Integer.parseInt(startReport),endReportInt = Integer.parseInt(endReport);//开始周期 + 结束周期(几号)转int便于计算
        int countPeriod = 0;//一共跨过多少个周期

        Date date = new Date();
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");


        if(endTime == "" || endTime == null) {//处理任务结束日期为空的情况
            String PeriodendThisDate =  sdf1.format(DateUtilsForSTP.getMonthLastDay(sdf1.parse(startTime)));//月末
            endTime = PeriodendThisDate;
        }



        String startTimePeriod="",endTimePeriod="";//当前周期开始结束时间  --周期
        String startTimeFinal="",endTimeFinal="";//本周期开始(几号)对应的年月日-以下为计算过程 以及本周期结束.... --填报


        String runtime = startTime;

        //任务开始日期所在月份 与 任务结束日期所在月份单独算 其他月份一起循环算
        int countMiddlePeriod = 0;//去除开始、结束日期所在月之后中间完整的月份的周期数量 上半月下半月(每个月2个周期)
        countMiddlePeriod = DateUtilsForSTP.monthsBetween(sdf1.parse(startTime),sdf1.parse(endTime)) + 2;

        for (int i = 0; i < countMiddlePeriod; i++) {
            if(DateUtilsForSTP.daysBetween(sdf1.parse(runtime),sdf1.parse(endTime)) < 0 ){//防止endtime是这个月最后一天导致多运行一次
                break;
            }
            int endReportIntIf = endReportInt;
            startTimePeriod = runtime;//周期开始时间赋值 时间 1号

            String PeriodendThisDate =  sdf1.format(DateUtilsForSTP.getMonthLastDay(sdf1.parse(runtime)));//月末

            if(DateUtilsForSTP.daysBetween(sdf1.parse(PeriodendThisDate),sdf1.parse(endTime)) > 0){//任务结束日期在这个月末之后
                endTimePeriod = PeriodendThisDate;//周期结束时间赋值 时间 月末
            }else{//任务结束日期在这个月末之前
                endTimePeriod = endTime;//周期结束时间赋值 时间 任务结束时间
            }
            startTimeFinal =  DateUtilsForSTP.getDayAfter(runtime,startReportInt - Integer.parseInt(DateUtilsForSTP.getDayByDate(runtime)));//填报开始时间赋值
            endTimeFinal =  DateUtilsForSTP.getDayAfter(runtime,endReportInt - Integer.parseInt(DateUtilsForSTP.getDayByDate(runtime)));
            countPeriod ++;

            runtime = DateUtilsForSTP.getDayAfter(endTimePeriod,1);//赋值运行开始时间 时间

            if(DateUtilsForSTP.daysBetween(sdf1.parse(PeriodendThisDate),sdf1.parse(runtime)) > 1){//如果月末和 周期结束时间+1 的差距> 1 即周期结束时间不为月末 则跳出循环（防止多运行一次）
                break;
            }
            System.out.println("startTimeFinal填报开始"+startTimeFinal);
            System.out.println("endTimeFinal填报结束"+endTimeFinal);
            System.out.println("startTimePeriod周期开始"+startTimePeriod);
            System.out.println("endTimePeriod周期结束"+endTimePeriod);
            System.out.println("第"+countPeriod+"周期");
        }

    }
}
