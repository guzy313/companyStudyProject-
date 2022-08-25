package entity;


import java.io.Serializable;
import java.util.Date;

/**
 * ClassName: SuperviseTaskPeriod <br/>
 * Function: (督办任务周期) <br/>
 * Detail: (督办任务周期) <br/>
 * date: 2022年3月4日 上午9:50:36 <br/>
 *
 * @author Gzy
 * @version
 * @since JDK 1.8
 */

public class SuperviseTaskPeriod implements Serializable
{
    private static final long serialVersionUID = 7934989358618731356L;


    private String id;

    private String task_id;

    private int period_type;

    /** 周期序号 **/
    private  int period_num;

    /** 周期名称 **/
    private String period_name;

    /** 周期开始时间 **/
    private Date period_start_time;

    /** 周期结束时间 **/
    private Date period_end_time;

    /** 填报开始时间 **/
    private Date report_start_time;

    /** 填报截止时间 **/
    private Date report_end_time;

    /** 数据创建时间 **/
    private Date create_time;

    /** 数据更新时间 **/
    private Date update_time;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTask_id() {
        return task_id;
    }

    public void setTask_id(String task_id) {
        this.task_id = task_id;
    }

    public int getPeriod_type() {
        return period_type;
    }

    public void setPeriod_type(int period_type) {
        this.period_type = period_type;
    }

    public int getPeriod_num() {
        return period_num;
    }

    public void setPeriod_num(int period_num) {
        this.period_num = period_num;
    }

    public String getPeriod_name() {
        return period_name;
    }

    public void setPeriod_name(String period_name) {
        this.period_name = period_name;
    }

    public Date getPeriod_start_time() {
        return period_start_time;
    }

    public void setPeriod_start_time(Date period_start_time) {
        this.period_start_time = period_start_time;
    }

    public Date getPeriod_end_time() {
        return period_end_time;
    }

    public void setPeriod_end_time(Date period_end_time) {
        this.period_end_time = period_end_time;
    }

    public Date getReport_start_time() {
        return report_start_time;
    }

    public void setReport_start_time(Date report_start_time) {
        this.report_start_time = report_start_time;
    }

    public Date getReport_end_time() {
        return report_end_time;
    }

    public void setReport_end_time(Date report_end_time) {
        this.report_end_time = report_end_time;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }

    public Date getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(Date update_time) {
        this.update_time = update_time;
    }


    /**
     *
     * @param taskid
     * @param periodType
     * @param periodName
     * @param countPeriod
     * @param startTimePeriod
     * @param endTimePeriod
     * @param startTimeFinal
     * @param endTimeFinal
     * @param createTime
     * @param endTime
     * @return SuperviseTaskPeriod
     */
    public final static SuperviseTaskPeriod getUpdateSuperviseTaskPeriod(String taskid,int periodType,String periodName,int countPeriod,Date startTimePeriod,Date endTimePeriod,Date startTimeFinal,Date endTimeFinal,
                                                                   Date createTime,Date endTime ){
        SuperviseTaskPeriod superviseTaskPeriod = new SuperviseTaskPeriod();
        superviseTaskPeriod.setTask_id(taskid);
        superviseTaskPeriod.setPeriod_type(periodType);
        superviseTaskPeriod.setPeriod_num(countPeriod);
        superviseTaskPeriod.setPeriod_name(periodName);
        superviseTaskPeriod.setPeriod_start_time(startTimePeriod);
        superviseTaskPeriod.setPeriod_end_time(endTimePeriod);
        superviseTaskPeriod.setReport_start_time(startTimeFinal);
        superviseTaskPeriod.setReport_end_time(endTimeFinal);
        superviseTaskPeriod.setCreate_time(createTime);
        superviseTaskPeriod.setUpdate_time(endTime);
        return superviseTaskPeriod;
    }
}
