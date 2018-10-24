package org.ccnu.nercel.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
* 试卷类
* @author xiaotong
* @version 2018年8月1日 下午1:26:07
*/

@Entity
public class Paper {
	
	//自增主键id
	@Id
	@Column(name="id")
	@GeneratedValue
	private int id;
	
	//试卷编号
	@Column(name="paperid")
	private String paperid;
	
	//主题编号
	@Column(name="themeid")
	private int themeid;
	
	//问题1
	@Column(name="questiona")
	private String questiona;

	//问题2
	@Column(name="questionb")
	private String questionb;

	//问题3
	@Column(name="questionc")
	private String questionc;

	//试卷名称
	@Column(name="papername")
	private String papername;

	//试卷日期
	@Column(name="date")
	private String date;


	public Paper() {
		
	}

	public Paper(String paperid, int themeid, String questiona, String questionb, String questionc, String papername, String date) {
		this.paperid = paperid;
		this.themeid = themeid;
		this.questiona = questiona;
		this.questionb = questionb;
		this.questionc = questionc;
		this.papername = papername;
		this.date = date;
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

	public int getThemeid() {
		return themeid;
	}

	public void setThemeid(int themeid) {
		this.themeid = themeid;
	}

	public String getQuestiona() {
		return questiona;
	}

	public void setQuestiona(String questiona) {
		this.questiona = questiona;
	}

	public String getQuestionb() {
		return questionb;
	}

	public void setQuestionb(String questionb) {
		this.questionb = questionb;
	}

	public String getQuestionc() {
		return questionc;
	}

	public void setQuestionc(String questionc) {
		this.questionc = questionc;
	}

	public String getPapername() {
		return papername;
	}

	public void setPapername(String papername) {
		this.papername = papername;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "Paper{" +
				"id=" + id +
				", paperid='" + paperid + '\'' +
				", themeid=" + themeid +
				", questiona='" + questiona + '\'' +
				", questionb='" + questionb + '\'' +
				", questionc='" + questionc + '\'' +
				", papername='" + papername + '\'' +
				", date='" + date + '\'' +
				'}';
	}
}
