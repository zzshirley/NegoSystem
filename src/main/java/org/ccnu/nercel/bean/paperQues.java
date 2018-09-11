package org.ccnu.nercel.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * 试卷问题/问题所属面向
 * @author xiaotong
 * @version 2018年9月2日 下午1:28:13
 */

@Entity
public class paperQues {

    // 自增主键id
    @Id
    @Column(name = "id")
    @GeneratedValue
    private int id;

    // 试卷编号
    @Column(name = "paperid")
    private String paperid;

    // 问题面向
    @Column(name = "quesclass")
    private String quesclass;

    //问题编号
    @Column(name = "question")
    private String question;

    //问题内容
    @Column(name="quescontent")
    private String quescontent;

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

    public String getQuesclass() {
        return quesclass;
    }

    public void setQuesclass(String quesclass) {
        this.quesclass = quesclass;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getQuescontent() {
        return quescontent;
    }

    public void setQuescontent(String quescontent) {
        this.quescontent = quescontent;
    }

    @Override
    public String toString() {
        return "paperQues{" +
                "id=" + id +
                ", paperid='" + paperid + '\'' +
                ", quesclass='" + quesclass + '\'' +
                ", question='" + question + '\'' +
                ", quescontent='" + quescontent + '\'' +
                '}';
    }
}


