package org.ccnu.nercel.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * @author Xiaotong
 * @createTime 20181010 下午3:00
 * @description 自我评价
 */
@Entity
public class Selfeva {

    // 自增主键id
    @Id
    @Column(name = "id")
    @GeneratedValue
    private int id;

    // 学生ID
    @Column(name = "stuid")
    private String stuid;

    //自我评价时间
    @Column(name = "endtime")
    private String endtime;

    //理论能力
    @Column(name="abt1")
    private String abt1;

    //应用能力
    @Column(name="abt2")
    private String abt2;

    //分析能力
    @Column(name="abt3")
    private String abt3;

    //记录次数(10-10第一次，10-17第二次)
    @Column(name="times")
    private String times;


    public Selfeva(){

    }

    public Selfeva(String stuid, String endtime, String abt1, String abt2, String abt3, String times) {
        this.stuid = stuid;
        this.endtime = endtime;
        this.abt1 = abt1;
        this.abt2 = abt2;
        this.abt3 = abt3;
        this.times = times;
    }

    @Override
    public String toString() {
        return "Selfeva{" +
                "id=" + id +
                ", stuid='" + stuid + '\'' +
                ", endtime='" + endtime + '\'' +
                ", abt1='" + abt1 + '\'' +
                ", abt2='" + abt2 + '\'' +
                ", abt3='" + abt3 + '\'' +
                ", times='" + times + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAbt3() {
        return abt3;
    }

    public void setAbt3(String abt3) {
        this.abt3 = abt3;
    }

    public String getStuid() {
        return stuid;
    }

    public void setStuid(String stuid) {
        this.stuid = stuid;
    }

    public String getEndtime() {
        return endtime;
    }

    public void setEndtime(String endtime) {
        this.endtime = endtime;
    }

    public String getAbt1() {
        return abt1;
    }

    public void setAbt1(String abt1) {
        this.abt1 = abt1;
    }

    public String getAbt2() {
        return abt2;
    }

    public void setAbt2(String abt2) {
        this.abt2 = abt2;
    }

    public String getTimes() {
        return times;
    }

    public void setTimes(String times) {
        this.times = times;
    }
}
