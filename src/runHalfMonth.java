import entity.SuperviseTaskPeriod;
import utils.DateUtilsForSTP;
import utils.NumToCn;

import java.text.SimpleDateFormat;
import java.util.Date;

public class runHalfMonth {
    public static void main(String[] args) throws Exception{


        String startReport = "3",endReport = "13" ;//开始填报周期 + 结束周期(几号)
        String  startTime= "2022-3-12",endTime="";
        String taskid = "";int periodType = 0;

        int startReportInt = Integer.parseInt(startReport),endReportInt = Integer.parseInt(endReport);//开始周期 + 结束周期(几号)转int便于计算
        int countPeriod = 0;//一共跨过多少个周期

        Date date = new Date();
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");


        if(endTime == "" || endTime == null){
            if(Integer.parseInt(DateUtilsForSTP.getDayByDate(startTime)) <= 15){//开始日期在上半月
                String PeriodendThisDate = DateUtilsForSTP.getDayAfter(startTime,15 - Integer.parseInt(DateUtilsForSTP.getDayByDate(startTime)));
                endTime = PeriodendThisDate;
            }else{//开始日期在下半月
                String PeriodendThisDate = DateUtilsForSTP.getDayAfter(startTime,
                        Integer.parseInt(DateUtilsForSTP.getDayByDate(sdf1.format(DateUtilsForSTP.getMonthLastDay(sdf1.parse(startTime))))) - Integer.parseInt(DateUtilsForSTP.getDayByDate(startTime)) );//第一个周期月末
                endTime = PeriodendThisDate;

            }
        }



        String startTimePeriod="",endTimePeriod="";//当前周期开始结束时间  --周期
        String startTimeFinal="",endTimeFinal="";//本周期开始(几号)对应的年月日-以下为计算过程 以及本周期结束.... --填报


        if(endReportInt < startReportInt){
            throw new NullPointerException("填报结束周期必须在开始周期之后");
        }else if(startReportInt == endReportInt){

        }else{

        }
        if(startReportInt > 15){
            throw new NullPointerException("填报开始周期只能小于等于15");
        }
        if(endReportInt > 16){
            throw new NullPointerException("填报开始周期只能小于等于16");
        }

        String runtime = startTime;
        //第一个周期:
        if(Integer.parseInt(DateUtilsForSTP.getDayByDate(runtime)) <= 15){//开始日期在上半月
            int endReportIntIf = endReportInt;
            if(endReportIntIf > 15){//最后一天处理
                endReportIntIf = 15;
            }
            startTimePeriod = runtime;//周期开始时间赋值 时间 1号  (计算方法:工具类传入任务开始日期 以及开始日期距1号差的天数（工具类获取任务开始日期的日对应int - 1） )
            //如果任务结束日期在第一个周期内

            //第一个周期15号的日期
            String PeriodendThisDate = DateUtilsForSTP.getDayAfter(runtime,15 - Integer.parseInt(DateUtilsForSTP.getDayByDate(runtime)));
            if(DateUtilsForSTP.daysBetween(sdf1.parse(PeriodendThisDate),sdf1.parse(endTime)) > 0 ){//结束日期在半月末之后
                endTimePeriod = PeriodendThisDate;//周期结束日期取半月末  周期结束时间赋值 时间
            }else{
                endTimePeriod = endTime;//周期结束日期取endtime（任务结束日期）周期结束时间赋值 时间
            }
            startTimeFinal =  DateUtilsForSTP.getDayAfter(startTimePeriod,startReportInt - Integer.parseInt(DateUtilsForSTP.getDayByDate(startTimePeriod)));//填报开始时间赋值
            endTimeFinal =  DateUtilsForSTP.getDayAfter(startTimePeriod,endReportIntIf - Integer.parseInt(DateUtilsForSTP.getDayByDate(startTimePeriod)));//填报结束时间赋值
            countPeriod ++;

            SuperviseTaskPeriod superviseTaskPeriod = SuperviseTaskPeriod.getUpdateSuperviseTaskPeriod(taskid,periodType,"第"+ NumToCn.numberToChinese(countPeriod)+"周",countPeriod,sdf1.parse(startTimePeriod),sdf1.parse(endTimePeriod),
                    sdf1.parse(startTimeFinal),sdf1.parse(endTimeFinal), date,date );
            //this.insert(superviseTaskPeriod);


            runtime = DateUtilsForSTP.getDayAfter(runtime,16 - Integer.parseInt(DateUtilsForSTP.getDayByDate(runtime)));
            if(DateUtilsForSTP.daysBetween(sdf1.parse(runtime),sdf1.parse(endTime)) <= 0){//防止多运行一次
                runtime = endTime;
            }
            System.out.println("startTimeFinal填报开始"+startTimeFinal);
            System.out.println("endTimeFinal填报结束"+endTimeFinal);
            System.out.println("startTimePeriod周期开始"+startTimePeriod);
            System.out.println("endTimePeriod周期结束"+endTimePeriod);
            System.out.println("第"+countPeriod+"周期");
        }



        if(Integer.parseInt(DateUtilsForSTP.getDayByDate(runtime)) > 15){//开始日期在下半月
            int endReportIntIf = endReportInt;
            startTimePeriod = runtime;//周期开始时间赋值 时间 16号

            String PeriodendThisDate = DateUtilsForSTP.getDayAfter(runtime,
                    Integer.parseInt(DateUtilsForSTP.getDayByDate(sdf1.format(DateUtilsForSTP.getMonthLastDay(sdf1.parse(runtime))))) - Integer.parseInt(DateUtilsForSTP.getDayByDate(runtime)) );//第一个周期月末
            if(DateUtilsForSTP.daysBetween(sdf1.parse(PeriodendThisDate),sdf1.parse(endTime)) > 0){//任务结束日期在这个月末之后
                endTimePeriod = PeriodendThisDate;//周期结束时间赋值 时间 月末
            }else{//任务结束日期在这个月末之前
                endTimePeriod = endTime;//周期结束时间赋值 时间 任务结束时间
            }
            startTimeFinal =  DateUtilsForSTP.getDayAfter(startTimePeriod,startReportInt - 1);//填报开始时间赋值
            if(endReportIntIf > 15){//最后一天处理
                endTimeFinal = endTimePeriod;//
            }else{
                endTimeFinal =  DateUtilsForSTP.getDayAfter(startTimePeriod,endReportInt - 1);//填报结束时间赋值
            }
            countPeriod ++;

            SuperviseTaskPeriod superviseTaskPeriod = SuperviseTaskPeriod.getUpdateSuperviseTaskPeriod(taskid,periodType,"第"+ NumToCn.numberToChinese(countPeriod)+"周",countPeriod,sdf1.parse(startTimePeriod),sdf1.parse(endTimePeriod),
                    sdf1.parse(startTimeFinal),sdf1.parse(endTimeFinal), date,date );
            //this.insert(superviseTaskPeriod);

            runtime = DateUtilsForSTP.getDayAfter(runtime,Integer.parseInt(DateUtilsForSTP.getDayByDate(PeriodendThisDate)) - Integer.parseInt(DateUtilsForSTP.getDayByDate(runtime)) + 1);//当月最后一天 - runtime + 1 (+1为了获取下一周期第一天给runtime)

            if(DateUtilsForSTP.daysBetween(sdf1.parse(runtime),sdf1.parse(endTime)) <= 0){//防止多运行一次
                runtime = endTime;
            }
            System.out.println("startTimeFinal填报开始"+startTimeFinal);
            System.out.println("endTimeFinal填报结束"+endTimeFinal);
            System.out.println("startTimePeriod周期开始"+startTimePeriod);
            System.out.println("endTimePeriod周期结束"+endTimePeriod);
            System.out.println("第"+countPeriod+"周期");

        }



        //任务开始日期所在月份 与 任务结束日期所在月份单独算 其他月份一起循环算
        int countMiddlePeriod = 0;//去除开始、结束日期所在月之后中间完整的月份的周期数量 上半月下半月(每个月2个周期)
        countMiddlePeriod = DateUtilsForSTP.monthsBetween(sdf1.parse(startTime),sdf1.parse(endTime));



       //runtime运行中的开始日期和结束日期——随运行到每个地方而改变
        for (int i = 0; i < countMiddlePeriod; i++) {
            int countThis = i+1;
            if(countThis % 2 !=0){//上半月
                int endReportIntIf = endReportInt;
                if(endReportIntIf > 15){//最后一天处理
                    endReportIntIf = 15;
                }
                startTimePeriod = runtime;//周期开始时间赋值 时间 1号

                //第一个周期15号的日期
                String PeriodendThisDate = DateUtilsForSTP.getDayAfter(runtime,15 - Integer.parseInt(DateUtilsForSTP.getDayByDate(runtime)));
                if(DateUtilsForSTP.daysBetween(sdf1.parse(PeriodendThisDate),sdf1.parse(endTime)) > 0 ){//结束日期在半月末之后
                    endTimePeriod = PeriodendThisDate;//周期结束日期取半月末  周期结束时间赋值 时间
                }else{
                    endTimePeriod = endTime;//周期结束日期取endtime（任务结束日期）周期结束时间赋值 时间
                }

                startTimeFinal =  DateUtilsForSTP.getDayAfter(startTimePeriod,startReportInt - 1);//填报开始时间赋值
                endTimeFinal =  DateUtilsForSTP.getDayAfter(startTimePeriod,endReportIntIf - 1);//填报结束时间赋值

                runtime = DateUtilsForSTP.getDayAfter(endTimePeriod,1);//赋值运行开始时间 时间
                countPeriod ++;

                SuperviseTaskPeriod superviseTaskPeriod = SuperviseTaskPeriod.getUpdateSuperviseTaskPeriod(taskid,periodType,"第"+ NumToCn.numberToChinese(countPeriod)+"周",countPeriod,sdf1.parse(startTimePeriod),sdf1.parse(endTimePeriod),
                        sdf1.parse(startTimeFinal),sdf1.parse(endTimeFinal), date,date );
                //this.insert(superviseTaskPeriod);

                System.out.println("startTimeFinal填报开始"+startTimeFinal);
                System.out.println("endTimeFinal填报结束"+endTimeFinal);
                System.out.println("startTimePeriod周期开始"+startTimePeriod);
                System.out.println("endTimePeriod周期结束"+endTimePeriod);
                System.out.println("第"+countPeriod+"周期");
            }else{//下半月
                int endReportIntIf = endReportInt;
                startTimePeriod = runtime;//周期开始时间赋值 时间 16号

                String PeriodendThisDate = DateUtilsForSTP.getDayAfter(runtime,
                        Integer.parseInt(DateUtilsForSTP.getDayByDate(sdf1.format(DateUtilsForSTP.getMonthLastDay(sdf1.parse(runtime))))) - Integer.parseInt(DateUtilsForSTP.getDayByDate(runtime)) );//第一个周期月末
                if(DateUtilsForSTP.daysBetween(sdf1.parse(PeriodendThisDate),sdf1.parse(endTime)) > 0){//任务结束日期在这个月末之后
                    endTimePeriod = PeriodendThisDate;//周期结束时间赋值 时间 月末
                }else{//任务结束日期在这个月末之前
                    endTimePeriod = endTime;//周期结束时间赋值 时间 任务结束时间
                }

                startTimeFinal =  DateUtilsForSTP.getDayAfter(startTimePeriod,startReportInt - 1);//填报开始时间赋值
                if(endReportIntIf > 15){//最后一天处理
                    endTimeFinal = endTimePeriod;//
                }else{
                    endTimeFinal =  DateUtilsForSTP.getDayAfter(startTimePeriod,endReportInt - 1);//填报结束时间赋值
                }
                countPeriod ++;

                SuperviseTaskPeriod superviseTaskPeriod = SuperviseTaskPeriod.getUpdateSuperviseTaskPeriod(taskid,periodType,"第"+ NumToCn.numberToChinese(countPeriod)+"周",countPeriod,sdf1.parse(startTimePeriod),sdf1.parse(endTimePeriod),
                        sdf1.parse(startTimeFinal),sdf1.parse(endTimeFinal), date,date );
                //this.insert(superviseTaskPeriod);

                runtime = DateUtilsForSTP.getDayAfter(endTimePeriod,1);//赋值运行开始时间 时间
                System.out.println("startTimeFinal填报开始"+startTimeFinal);
                System.out.println("endTimeFinal填报结束"+endTimeFinal);
                System.out.println("startTimePeriod周期开始"+startTimePeriod);
                System.out.println("endTimePeriod周期结束"+endTimePeriod);
                System.out.println("第"+countPeriod+"周期");
            }
        }

        /*
        //最后一个周期:
        if(Integer.parseInt(DateUtilsForSTP.getDayByDate(runtime)) <= 15 && DateUtilsForSTP.daysBetween(sdf1.parse(runtime),sdf1.parse(endTime)) >= 0){//开始日期在上半月 --并且结束时间在运行时间之后（防止多计算）
            int endReportIntIf = endReportInt;
            if(endReportIntIf > 15){//最后一天处理
                endReportIntIf = 15;
            }
            startTimePeriod = runtime;//周期开始时间赋值 时间 1号  (计算方法:工具类传入任务开始日期 以及开始日期距1号差的天数（工具类获取任务开始日期的日对应int - 1） )
            //如果任务结束日期在第一个周期内

            //第一个周期15号的日期
            String PeriodendThisDate = DateUtilsForSTP.getDayAfter(runtime,15 - Integer.parseInt(DateUtilsForSTP.getDayByDate(runtime)));
            if(DateUtilsForSTP.daysBetween(sdf1.parse(PeriodendThisDate),sdf1.parse(endTime)) > 0 ){//结束日期在半月末之后
                endTimePeriod = PeriodendThisDate;//周期结束日期取半月末  周期结束时间赋值 时间
            }else{
                endTimePeriod = endTime;//周期结束日期取endtime（任务结束日期）周期结束时间赋值 时间
            }
            startTimeFinal =  DateUtilsForSTP.getDayAfter(startTimePeriod,startReportInt - Integer.parseInt(DateUtilsForSTP.getDayByDate(startTimePeriod)));//填报开始时间赋值
            endTimeFinal =  DateUtilsForSTP.getDayAfter(startTimePeriod,endReportIntIf - Integer.parseInt(DateUtilsForSTP.getDayByDate(startTimePeriod)));//填报结束时间赋值
            countPeriod ++;

            SuperviseTaskPeriod superviseTaskPeriod = SuperviseTaskPeriod.getUpdateSuperviseTaskPeriod(taskid,periodType,"第"+ NumToCn.numberToChinese(countPeriod)+"周",countPeriod,sdf1.parse(startTimePeriod),sdf1.parse(endTimePeriod),
                    sdf1.parse(startTimeFinal),sdf1.parse(endTimeFinal), date,date );
            //this.insert(superviseTaskPeriod);

            runtime = DateUtilsForSTP.getDayAfter(runtime,16 - Integer.parseInt(DateUtilsForSTP.getDayByDate(runtime)));
            System.out.println("startTimeFinal填报开始"+startTimeFinal);
            System.out.println("endTimeFinal填报结束"+endTimeFinal);
            System.out.println("startTimePeriod周期开始"+startTimePeriod);
            System.out.println("endTimePeriod周期结束"+endTimePeriod);
            System.out.println("第"+countPeriod+"周期");
        }



        if(Integer.parseInt(DateUtilsForSTP.getDayByDate(runtime)) > 15 && DateUtilsForSTP.daysBetween(sdf1.parse(runtime),sdf1.parse(endTime)) >= 0){//开始日期在下半月 --并且结束时间在运行时间之后（防止多计算）
            int endReportIntIf = endReportInt;
            startTimePeriod = runtime;//周期开始时间赋值 时间 16号

            String PeriodendThisDate = DateUtilsForSTP.getDayAfter(runtime,
                    Integer.parseInt(DateUtilsForSTP.getDayByDate(sdf1.format(DateUtilsForSTP.getMonthLastDay(sdf1.parse(runtime))))) - Integer.parseInt(DateUtilsForSTP.getDayByDate(runtime)) );//第一个周期月末
            if(DateUtilsForSTP.daysBetween(sdf1.parse(PeriodendThisDate),sdf1.parse(endTime)) > 0){//任务结束日期在这个月末之后
                endTimePeriod = PeriodendThisDate;//周期结束时间赋值 时间 月末
            }else{//任务结束日期在这个月末之前
                endTimePeriod = endTime;//周期结束时间赋值 时间 任务结束时间
            }
            startTimeFinal =  DateUtilsForSTP.getDayAfter(startTimePeriod,startReportInt - 1);//填报开始时间赋值
            if(endReportIntIf > 15){//最后一天处理
                endTimeFinal = endTimePeriod;//
            }else{
                endTimeFinal =  DateUtilsForSTP.getDayAfter(startTimePeriod,endReportInt - 1);//填报结束时间赋值
            }
            countPeriod ++;

            SuperviseTaskPeriod superviseTaskPeriod = SuperviseTaskPeriod.getUpdateSuperviseTaskPeriod(taskid,periodType,"第"+ NumToCn.numberToChinese(countPeriod)+"周",countPeriod,sdf1.parse(startTimePeriod),sdf1.parse(endTimePeriod),
                    sdf1.parse(startTimeFinal),sdf1.parse(endTimeFinal), date,date );
            //this.insert(superviseTaskPeriod);

            runtime = DateUtilsForSTP.getDayAfter(runtime,Integer.parseInt(DateUtilsForSTP.getDayByDate(PeriodendThisDate)) - Integer.parseInt(DateUtilsForSTP.getDayByDate(runtime)) + 1);//当月最后一天 - runtime + 1 (+1为了获取下一周期第一天给runtime)

            System.out.println("startTimeFinal填报开始"+startTimeFinal);
            System.out.println("endTimeFinal填报结束"+endTimeFinal);
            System.out.println("startTimePeriod周期开始"+startTimePeriod);
            System.out.println("endTimePeriod周期结束"+endTimePeriod);
            System.out.println("第"+countPeriod+"周期");

        }*/




    }






}
