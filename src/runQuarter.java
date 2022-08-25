package utils;

import my.interface_.DbInterface;

import java.text.SimpleDateFormat;

public class runQuarter {

    public static void main(String[] args) throws Exception{

        String startReport = "1-3",endReport = "3-6" ;//开始填报周期 + 结束周期(几号) （季度第 1/2/3 月的 第1-31天 超过本月最后一天的情况会自动处理 格式 1-1 1-2...1-31 2-1...3-31）
        String  startTime= "2022-1-12",endTime="2025-3-3";
        String taskid = "";int periodType = 0;


        String startTimePeriod="",endTimePeriod="";//当前周期开始结束时间  --周期
        String startTimeFinal="",endTimeFinal="";//本周期开始(几号)对应的年月日-以下为计算过程 以及本周期结束.... --填报

        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");

        if(endTime == "" || endTime == null) {//处理任务结束日期为空的情况
            int startMonth = Integer.parseInt(DateUtilsForSTP.getMonthByDate(startTime));
            switch (DateUtilsForSTP.getQuarterByMonth(startMonth)){
                case 1 :
                    String firstQuarterEnd = DateUtilsForSTP.getYearByDate(startTime)+"-3-31";
                    endTime = firstQuarterEnd;
                    break;
                case 2 :
                     firstQuarterEnd = DateUtilsForSTP.getYearByDate(startTime)+"-6-30";
                    endTime = firstQuarterEnd;
                    break;
                case 3 :
                     firstQuarterEnd = DateUtilsForSTP.getYearByDate(startTime)+"-9-30";
                    endTime = firstQuarterEnd;
                    break;
                case 4 :
                     firstQuarterEnd = DateUtilsForSTP.getYearByDate(startTime)+"-12-31";
                    endTime = firstQuarterEnd;
                    break;
                    default:
                        throw new NullPointerException("该日期季度不存在");
            }
        }



        int countPeriod = 0;//一共跨过多少个周期

        String [] startReportStringArr = startReport.split("-"),endReportStringArr = endReport.split("-");
        int startReportIntMonth = Integer.parseInt(startReportStringArr[0]),endReportIntMonth = Integer.parseInt(endReportStringArr[0]);//开始填报月 结束填报月
        int startReportIntDay =  Integer.parseInt(startReportStringArr[1]),endReportIntDay = Integer.parseInt(endReportStringArr[1]);//开始填报日 结束填报月日

        if(startReportIntMonth > 3 || startReportIntMonth < 1){
            throw new NullPointerException("按季填报的开始月份不能大于3或者小于1");
        }
        if(endReportIntMonth > 3 || endReportIntMonth < 1){
            throw new NullPointerException("按季填报的结束月份不能大于3或者小于1");
        }
        if(startReportIntMonth > endReportIntMonth){
            throw new NullPointerException("填报开始月份不能在填报结束月份之后");
        }else if(startReportIntMonth == endReportIntMonth){//月份相等的情况
            if(startReportIntDay > endReportIntDay){//日期比较
                throw new NullPointerException("填报开始日期不能在填报结束日期之后");
            }
        }

        int countPeriodAll = 0;//初始化所有的周期数
        //完整的年数--计算周期用
        int fullYear = Integer.parseInt(DateUtilsForSTP.getYearByDate(endTime)) - Integer.parseInt(DateUtilsForSTP.getYearByDate(startTime));

        int startMonth = Integer.parseInt(DateUtilsForSTP.getMonthByDate(startTime));//任务开始月
        int endMonth = Integer.parseInt(DateUtilsForSTP.getMonthByDate(endTime));//任务结束月

        if(fullYear == 0){//开始日期和结束日期在同一年
            int startQuarter = DateUtilsForSTP.getQuarterByMonth(startMonth);//开始季度
            int endQuarter = DateUtilsForSTP.getQuarterByMonth(endMonth);//结束季度
            countPeriodAll += endQuarter - startQuarter + 1;
        }
        if(fullYear != 0){//结束日期不会大于开始日期（已做限制）所以只剩下结束日期年在开始日期年之后
            int startQuarter = 1;
            int endQuarter = DateUtilsForSTP.getQuarterByMonth(endMonth);//结束季度
            countPeriodAll += endQuarter - startQuarter + 1;
        }
        countPeriodAll += fullYear * 4;

        int taskStartYear = Integer.parseInt(DateUtilsForSTP.getYearByDate(startTime));
        int taskEndYear = Integer.parseInt(DateUtilsForSTP.getYearByDate(endTime));


        String runtime = startTime;
        //第一年（残缺年情况）
        int runMonth = Integer.parseInt(DateUtilsForSTP.getMonthByDate(runtime));

        for (int i = 0; i < countPeriodAll; i++) {
            if(1 <= runMonth && runMonth < 4){
                startTimePeriod = runtime;//周期开始 日期字符串
                String firstQuarterEnd = DateUtilsForSTP.getYearByDate(runtime)+"-3-31";
                if(DateUtilsForSTP.daysBetween(sdf1.parse(firstQuarterEnd),sdf1.parse(endTime)) > 0){
                    endTimePeriod = firstQuarterEnd;//周期结束  日期字符串  任务结束时间在当前季最后一天之后的情况
                }else{
                    endTimePeriod = endTime;//周期结束  日期字符串 任务结束时间在当前季最后一天之前的情况
                }
                int thisStartReportMonth = startReportIntMonth;//填报开始月
                String thisStartReportMonthLastDay = sdf1.format(DateUtilsForSTP.getMonthLastDay(sdf1.parse(DateUtilsForSTP.getYearByDate(runtime)+"-"+thisStartReportMonth+"-1")));//获取当月最后一天
                int thisStartReportMonthLastDayInt = Integer.parseInt(DateUtilsForSTP.getDayByDate(thisStartReportMonthLastDay));//当月最后一天 日的int值
                if(startReportIntDay > thisStartReportMonthLastDayInt){//如果填报开始日期的日大于当月最大日 --防止超日期到下月
                    startTimeFinal = DateUtilsForSTP.getYearByDate(runtime)+"-"+thisStartReportMonth+"-"+thisStartReportMonthLastDayInt; //填报开始 日期字符串
                }else{
                    startTimeFinal = DateUtilsForSTP.getYearByDate(runtime)+"-"+thisStartReportMonth+"-"+startReportIntDay; //填报开始 日期字符串
                }

                int thisEndReportMonth = endReportIntMonth;//填报结束月
                String thisEndReportMonthLastDay = sdf1.format(DateUtilsForSTP.getMonthLastDay(sdf1.parse(DateUtilsForSTP.getYearByDate(runtime)+"-"+thisEndReportMonth+"-1")));//获取当月最后一天
                int thisEndReportMonthLastDayInt = Integer.parseInt(DateUtilsForSTP.getDayByDate(thisEndReportMonthLastDay));//当月最后一天 日的int值
                if(endReportIntDay > thisEndReportMonthLastDayInt) {//如果填报结束日期的日小于当月最大日 --防止超日期到下月
                    endTimeFinal = DateUtilsForSTP.getYearByDate(runtime)+"-"+thisEndReportMonth+"-"+thisEndReportMonthLastDayInt; //填报结束 日期字符串
                }else{
                    endTimeFinal =  DateUtilsForSTP.getYearByDate(runtime)+"-"+thisEndReportMonth+"-"+endReportIntDay; //填报结束 日期字符串
                }
                countPeriod ++;
                runtime = DateUtilsForSTP.getDayAfter(endTimePeriod,1);
                runMonth = Integer.parseInt(DateUtilsForSTP.getMonthByDate(runtime));
                sysout(startTimeFinal,endTimeFinal,startTimePeriod,endTimePeriod,countPeriod);//测试输出
            }else if(4 <= runMonth && runMonth < 7){

                startTimePeriod = runtime;//周期开始 日期字符串
                String firstQuarterEnd = DateUtilsForSTP.getYearByDate(runtime)+"-6-30";
                if(DateUtilsForSTP.daysBetween(sdf1.parse(firstQuarterEnd),sdf1.parse(endTime)) > 0){
                    endTimePeriod = firstQuarterEnd;//周期结束  日期字符串  任务结束时间在当前季最后一天之后的情况
                }else{
                    endTimePeriod = endTime;//周期结束  日期字符串 任务结束时间在当前季最后一天之前的情况
                }
                int thisStartReportMonth = startReportIntMonth + 3;//填报开始月
                String thisStartReportMonthLastDay = sdf1.format(DateUtilsForSTP.getMonthLastDay(sdf1.parse(DateUtilsForSTP.getYearByDate(runtime)+"-"+thisStartReportMonth+"-1")));//获取当月最后一天
                int thisStartReportMonthLastDayInt = Integer.parseInt(DateUtilsForSTP.getDayByDate(thisStartReportMonthLastDay));//当月最后一天 日的int值
                if(startReportIntDay > thisStartReportMonthLastDayInt){//如果填报开始日期的日大于当月最大日 --防止超日期到下月
                    startTimeFinal = DateUtilsForSTP.getYearByDate(runtime)+"-"+thisStartReportMonth+"-"+thisStartReportMonthLastDayInt; //填报开始 日期字符串
                }else{
                    startTimeFinal = DateUtilsForSTP.getYearByDate(runtime)+"-"+thisStartReportMonth+"-"+startReportIntDay; //填报开始 日期字符串
                }

                int thisEndReportMonth = endReportIntMonth + 3;//填报结束月
                String thisEndReportMonthLastDay = sdf1.format(DateUtilsForSTP.getMonthLastDay(sdf1.parse(DateUtilsForSTP.getYearByDate(runtime)+"-"+thisEndReportMonth+"-1")));//获取当月最后一天
                int thisEndReportMonthLastDayInt = Integer.parseInt(DateUtilsForSTP.getDayByDate(thisEndReportMonthLastDay));//当月最后一天 日的int值
                if(endReportIntDay > thisEndReportMonthLastDayInt) {//如果填报结束日期的日小于当月最大日 --防止超日期到下月
                    endTimeFinal = DateUtilsForSTP.getYearByDate(runtime)+"-"+thisEndReportMonth+"-"+thisEndReportMonthLastDayInt; //填报结束 日期字符串
                }else{
                    endTimeFinal =  DateUtilsForSTP.getYearByDate(runtime)+"-"+thisEndReportMonth+"-"+endReportIntDay; //填报结束 日期字符串
                }
                countPeriod ++;
                runtime = DateUtilsForSTP.getDayAfter(endTimePeriod,1);
                runMonth = Integer.parseInt(DateUtilsForSTP.getMonthByDate(runtime));

                sysout(startTimeFinal,endTimeFinal,startTimePeriod,endTimePeriod,countPeriod);//测试输出
            }else if(7 <= runMonth && runMonth < 9){
                startTimePeriod = runtime;//周期开始 日期字符串
                String firstQuarterEnd = DateUtilsForSTP.getYearByDate(runtime)+"-9-30";
                if(DateUtilsForSTP.daysBetween(sdf1.parse(firstQuarterEnd),sdf1.parse(endTime)) > 0){
                    endTimePeriod = firstQuarterEnd;//周期结束  日期字符串  任务结束时间在当前季最后一天之后的情况
                }else{
                    endTimePeriod = endTime;//周期结束  日期字符串 任务结束时间在当前季最后一天之前的情况
                }
                int thisStartReportMonth = startReportIntMonth + 6;//填报开始月
                String thisStartReportMonthLastDay = sdf1.format(DateUtilsForSTP.getMonthLastDay(sdf1.parse(DateUtilsForSTP.getYearByDate(runtime)+"-"+thisStartReportMonth+"-1")));//获取当月最后一天
                int thisStartReportMonthLastDayInt = Integer.parseInt(DateUtilsForSTP.getDayByDate(thisStartReportMonthLastDay));//当月最后一天 日的int值
                if(startReportIntDay > thisStartReportMonthLastDayInt){//如果填报开始日期的日大于当月最大日 --防止超日期到下月
                    startTimeFinal = DateUtilsForSTP.getYearByDate(runtime)+"-"+thisStartReportMonth+"-"+thisStartReportMonthLastDayInt; //填报开始 日期字符串
                }else{
                    startTimeFinal = DateUtilsForSTP.getYearByDate(runtime)+"-"+thisStartReportMonth+"-"+startReportIntDay; //填报开始 日期字符串
                }

                int thisEndReportMonth = endReportIntMonth + 6;//填报结束月
                String thisEndReportMonthLastDay = sdf1.format(DateUtilsForSTP.getMonthLastDay(sdf1.parse(DateUtilsForSTP.getYearByDate(runtime)+"-"+thisEndReportMonth+"-1")));//获取当月最后一天
                int thisEndReportMonthLastDayInt = Integer.parseInt(DateUtilsForSTP.getDayByDate(thisEndReportMonthLastDay));//当月最后一天 日的int值
                if(endReportIntDay > thisEndReportMonthLastDayInt) {//如果填报结束日期的日小于当月最大日 --防止超日期到下月
                    endTimeFinal = DateUtilsForSTP.getYearByDate(runtime)+"-"+thisEndReportMonth+"-"+thisEndReportMonthLastDayInt; //填报结束 日期字符串
                }else{
                    endTimeFinal =  DateUtilsForSTP.getYearByDate(runtime)+"-"+thisEndReportMonth+"-"+endReportIntDay; //填报结束 日期字符串
                }
                countPeriod ++;
                runtime = DateUtilsForSTP.getDayAfter(endTimePeriod,1);
                runMonth = Integer.parseInt(DateUtilsForSTP.getMonthByDate(runtime));
                sysout(startTimeFinal,endTimeFinal,startTimePeriod,endTimePeriod,countPeriod);//测试输出
            }else if(9 <= runMonth && runMonth <= 12){
                startTimePeriod = runtime;//周期开始 日期字符串
                String firstQuarterEnd = DateUtilsForSTP.getYearByDate(runtime)+"-12-31";
                if(DateUtilsForSTP.daysBetween(sdf1.parse(firstQuarterEnd),sdf1.parse(endTime)) > 0){
                    endTimePeriod = firstQuarterEnd;//周期结束  日期字符串  任务结束时间在当前季最后一天之后的情况
                }else{
                    endTimePeriod = endTime;//周期结束  日期字符串 任务结束时间在当前季最后一天之前的情况
                }
                int thisStartReportMonth = startReportIntMonth + 9;//填报开始月
                String thisStartReportMonthLastDay = sdf1.format(DateUtilsForSTP.getMonthLastDay(sdf1.parse(DateUtilsForSTP.getYearByDate(runtime)+"-"+thisStartReportMonth+"-1")));//获取当月最后一天
                int thisStartReportMonthLastDayInt = Integer.parseInt(DateUtilsForSTP.getDayByDate(thisStartReportMonthLastDay));//当月最后一天 日的int值
                if(startReportIntDay > thisStartReportMonthLastDayInt){//如果填报开始日期的日大于当月最大日 --防止超日期到下月
                    startTimeFinal = DateUtilsForSTP.getYearByDate(runtime)+"-"+thisStartReportMonth+"-"+thisStartReportMonthLastDayInt; //填报开始 日期字符串
                }else{
                    startTimeFinal = DateUtilsForSTP.getYearByDate(runtime)+"-"+thisStartReportMonth+"-"+startReportIntDay; //填报开始 日期字符串
                }

                int thisEndReportMonth = endReportIntMonth + 9;//填报结束月
                String thisEndReportMonthLastDay = sdf1.format(DateUtilsForSTP.getMonthLastDay(sdf1.parse(DateUtilsForSTP.getYearByDate(runtime)+"-"+thisEndReportMonth+"-1")));//获取当月最后一天
                int thisEndReportMonthLastDayInt = Integer.parseInt(DateUtilsForSTP.getDayByDate(thisEndReportMonthLastDay));//当月最后一天 日的int值
                if(endReportIntDay > thisEndReportMonthLastDayInt) {//如果填报结束日期的日小于当月最大日 --防止超日期到下月
                    endTimeFinal = DateUtilsForSTP.getYearByDate(runtime)+"-"+thisEndReportMonth+"-"+thisEndReportMonthLastDayInt; //填报结束 日期字符串
                }else{
                    endTimeFinal =  DateUtilsForSTP.getYearByDate(runtime)+"-"+thisEndReportMonth+"-"+endReportIntDay; //填报结束 日期字符串
                }
                countPeriod ++;
                runtime = DateUtilsForSTP.getDayAfter(endTimePeriod,1);
                runMonth = Integer.parseInt(DateUtilsForSTP.getMonthByDate(runtime));
                sysout(startTimeFinal,endTimeFinal,startTimePeriod,endTimePeriod,countPeriod);//测试输出
            }
        }



    }



    public static void sysout(String startTimeFinal,String endTimeFinal, String startTimePeriod,String endTimePeriod,int countPeriod){
        System.out.println("startTimeFinal填报开始"+startTimeFinal);
        System.out.println("endTimeFinal填报结束"+endTimeFinal);
        System.out.println("startTimePeriod周期开始"+startTimePeriod);
        System.out.println("endTimePeriod周期结束"+endTimePeriod);
        System.out.println("第"+countPeriod+"周期");
    }

}
