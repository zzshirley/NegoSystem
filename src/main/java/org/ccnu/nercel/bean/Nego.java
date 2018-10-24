package org.ccnu.nercel.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * @author Xiaotong
 * @createTime 20181016 下午9:09
 * @description 协商
 */
@Entity
public class Nego {

    // 自增主键id
    @Id
    @Column(name = "id")
    @GeneratedValue
    private int id;

    // 试卷编号
    @Column(name = "paperid")
    private String paperid;

    // 学生ID
    @Column(name = "stuid")
    private String stuid;

    //能力面向/自评主题
    @Column(name = "ab")
    private String ability;

    //协商选项
    @Column(name="negopt")
    private String negopt;

    //提交理由主题/重新测试题目
    @Column(name="marka")
    private String marka;

    //提交主题内容/提交测试答案/自评选项
    @Column(name="markb")
    private String markb;

    //考试分数
    @Column(name="markc")
    private String markc;

    //时间
    @Column(name="endtime")
    private String endtime;

    public Nego() {
    }

    public Nego(String paperid, String stuid, String ability, String negopt, String marka, String markb, String markc, String endtime) {
        this.paperid = paperid;
        this.stuid = stuid;
        this.ability = ability;
        this.negopt = negopt;
        this.marka = marka;
        this.markb = markb;
        this.markc = markc;
        this.endtime = endtime;
    }

    @Override
    public String toString() {
        return "Nego{" +
                "id=" + id +
                ", paperid='" + paperid + '\'' +
                ", stuid='" + stuid + '\'' +
                ", ability='" + ability + '\'' +
                ", negopt='" + negopt + '\'' +
                ", marka='" + marka + '\'' +
                ", markb='" + markb + '\'' +
                ", markc='" + markc + '\'' +
                ", endtime='" + endtime + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPaperid() {
        return paperid;
    }

    public void setPaperid(String paperid) {
        this.paperid = paperid;
    }

    public String getStuid() {
        return stuid;
    }

    public void setStuid(String stuid) {
        this.stuid = stuid;
    }

    public String getAbility() {
        return ability;
    }

    public void setAbility(String ability) {
        this.ability = ability;
    }

    public String getNegopt() {
        return negopt;
    }

    public void setNegopt(String negopt) {
        this.negopt = negopt;
    }

    public String getMarka() {
        return marka;
    }

    public void setMarka(String marka) {
        this.marka = marka;
    }

    public String getMarkb() {
        return markb;
    }

    public void setMarkb(String markb) {
        this.markb = markb;
    }

    public String getMarkc() {
        return markc;
    }

    public void setMarkc(String markc) {
        this.markc = markc;
    }

    public String getEndtime() {
        return endtime;
    }

    public void setEndtime(String endtime) {
        this.endtime = endtime;
    }
}
