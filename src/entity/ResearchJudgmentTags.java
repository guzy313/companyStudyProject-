package entity;


import java.io.Serializable;
import java.util.Date;

/**
 * ClassName: ResearchJudgmentTags <br/>
 * Function: (研判标签表实体) <br/>
 * Detail: (研判标签表实体) <br/>
 * date: 2022年4月1日 上午10:59:35 <br/>
 *
 * @author Gzy
 * @version
 * @since JDK 1.8
 */
public class ResearchJudgmentTags implements Serializable
{

    private static final long serialVersionUID = -8102987645017092165L;


    private String id;


    /*
    序号	字段名	数据类型	主键	非空	描述
1		id	nvarchar(50)	是	是	主键
2		tag_code	nvarchar(50)	否	否	标签编码
3		tag_name	nvarchar(50)	否	否	标签名称
4		tag_describe	nvarchar(500)	否	否	标签描述
5		create_time	datetime	否	否	数据创建时间
6		update_time	datetime	否	否	数据更新时间

     */


    private String tag_code;


    private String tag_name;


    private String tag_describe;


    private Date create_time;


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

    public String getTag_code() {
        return tag_code;
    }

    public void setTag_code(String tag_code) {
        this.tag_code = tag_code;
    }

    public String getTag_name() {
        return tag_name;
    }

    public void setTag_name(String tag_name) {
        this.tag_name = tag_name;
    }

    public String getTag_describe() {
        return tag_describe;
    }

    public void setTag_describe(String tag_describe) {
        this.tag_describe = tag_describe;
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
}
