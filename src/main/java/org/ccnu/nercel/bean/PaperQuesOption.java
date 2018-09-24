package org.ccnu.nercel.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Objects;

/**
 * 三个题组的题目选项
 * @author xiaotong
 * @version 2018年9月2日 下午1:28:13
 */

@Entity
public class PaperQuesOption {

    // 自增主键id
    @Id
    @Column(name = "id")
    @GeneratedValue
    private int id;

    //问题
    @Column(name="question")
    private String question;

    //选项
    @Column(name="options")
    private String options;

    //是否是正确答案
    @Column(name="istrueoption")
    private String istrueoption;

    public PaperQuesOption(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getOptions() {
        return options;
    }

    public void setOptions(String options) {
        this.options = options;
    }

    public String getIstrueoption() {
        return istrueoption;
    }

    public void setIstrueoption(String istrueoption) {
        this.istrueoption = istrueoption;
    }

    @Override
    public String toString() {
        return "PaperQuesOption{" +
                "id=" + id +
                ", question='" + question + '\'' +
                ", options='" + options + '\'' +
                ", istrueoption=" + istrueoption +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, question, options, istrueoption);
    }
}
