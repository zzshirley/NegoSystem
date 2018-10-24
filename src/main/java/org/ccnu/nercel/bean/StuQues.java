package org.ccnu.nercel.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * @author Xiaotong
 * @createTime 20181023 下午3:12
 * @description 协商过程中，学生需要回答的问题
 */
@Entity
public class StuQues {

    @Id
    @Column(name = "id")
    @GeneratedValue
    private int id;

    // 试卷编号
    @Column(name = "paperid")
    private String paperid;

    //郑老师选择的学生问题
    @Column(name = "stuques")
    private String stuques;

    public StuQues() {
    }

    public StuQues(String paperid, String stuques) {
        this.paperid = paperid;
        this.stuques = stuques;
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

    public String getStuques() {
        return stuques;
    }

    public void setStuques(String stuques) {
        this.stuques = stuques;
    }

    @Override
    public String toString() {
        return "StuQues{" +
                "id=" + id +
                ", paperid='" + paperid + '\'' +
                ", stuques='" + stuques + '\'' +
                '}';
    }
}
