package org.ccnu.nercel.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * @author Xiaotong
 * @createTime 20181223 上午10:22
 * @description 协商过程需要实时更新能力
 */
@Entity
public class AbilityNew {
    // 自增主键id
    @Id
    @Column(name = "id")
    @GeneratedValue
    private int id;

    //学生学号
    @Column(name="stuid")
    private String stuid;

    //更新时间
    @Column(name="time")
    private String time;

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

    //月份
    @Column(name="paperid")
    private String paperid;

    public AbilityNew() {
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

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
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

    public String getPaperid() {
        return paperid;
    }

    public void setPaperid(String paperid) {
        this.paperid = paperid;
    }

    @Override
    public String toString() {
        return "AbilityNew{" +
                "id=" + id +
                ", stuid='" + stuid + '\'' +
                ", time='" + time + '\'' +
                ", abt1='" + abt1 + '\'' +
                ", abt2='" + abt2 + '\'' +
                ", abt3='" + abt3 + '\'' +
                ", abt='" + abt + '\'' +
                ", paperid='" + paperid + '\'' +
                '}';
    }
}
