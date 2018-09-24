package org.ccnu.nercel.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
*问卷题目
*@author xiaotong
*@version 2018年8月1日 下午7:37:44
*/
@Entity
public class Question {
	
	//自增主键id
	@Id
	@Column(name = "id")
	@GeneratedValue
	private int id;

	//试题类别
	private int classic;

	//问题编号
	@Column(name="quesid")
	private int quesid;
	
	//问题名称
	@Column(name="quesname")
	private String quesname;

	//对应文章
	@Column(name="paperid")
	private String paperid;
	
	//备注
	@Column(name="remark")
	private String remark;
	
	public Question() {
		
	}

	public Question(int id, int quesid, String quesname, String paperid, String remark) {
		super();
		this.id=id;
		this.quesid=quesid;
		this.paperid=paperid;
		this.quesname=quesname;
		this.remark=remark;
		
	}
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getQuesid() {
		return quesid;
	}

	public void setQuesid(int quesid) {
		this.quesid = quesid;
	}

	public String getQuesname() {
		return quesname;
	}

	public void setQuesname(String quesname) {
		this.quesname = quesname;
	}

	public String getPaperid() {
		return paperid;
	}

	public void setPaperid(String paperid) {
		this.paperid = paperid;
	}

	public int getClassic() {
		return classic;
	}

	public void setClassic(int classic) {
		this.classic = classic;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Override
	public String toString() {
		return "Question{" +
				"id=" + id +
				", quesid=" + quesid +
				", quesname='" + quesname + '\'' +
				", paperid='" + paperid + '\'' +
				", remark='" + remark + '\'' +
				'}';
	}
}
