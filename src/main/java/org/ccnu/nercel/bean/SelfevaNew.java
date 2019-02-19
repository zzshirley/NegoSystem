package org.ccnu.nercel.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * @author Xiaotong
 * @createTime 20181223 下午9:46
 * @description 协商中更新自我评价
 */
@Entity
public class SelfevaNew {

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
    private double abt1;

    //应用能力
    @Column(name="abt2")
    private double abt2;

    //分析能力
    @Column(name="abt3")
    private double abt3;

    //更新的能力
    @Column(name="abt")
    private String abt;

    //记录次数(10-10第一次，10-17第二次)
    @Column(name="times")
    private String times;

    public SelfevaNew() {
    }

    public SelfevaNew(String stuid, String endtime, double abt1, double abt2, double abt3, String abt, String times) {
        this.stuid = stuid;
        this.endtime = endtime;
        this.abt1 = abt1;
        this.abt2 = abt2;
        this.abt3 = abt3;
        this.abt = abt;
        this.times = times;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public double getAbt1() {
        return abt1;
    }

    public void setAbt1(double abt1) {
        this.abt1 = abt1;
    }

    public double getAbt2() {
        return abt2;
    }

    public void setAbt2(double abt2) {
        this.abt2 = abt2;
    }

    public double getAbt3() {
        return abt3;
    }

    public void setAbt3(double abt3) {
        this.abt3 = abt3;
    }

    public String getAbt() {
        return abt;
    }

    public void setAbt(String abt) {
        this.abt = abt;
    }

    public String getTimes() {
        return times;
    }

    public void setTimes(String times) {
        this.times = times;
    }

    @Override
    public String toString() {
        return "SelfevaNew{" +
                "id=" + id +
                ", stuid='" + stuid + '\'' +
                ", endtime='" + endtime + '\'' +
                ", abt1='" + abt1 + '\'' +
                ", abt2='" + abt2 + '\'' +
                ", abt3='" + abt3 + '\'' +
                ", abt='" + abt + '\'' +
                ", times='" + times + '\'' +
                '}';
    }
}
