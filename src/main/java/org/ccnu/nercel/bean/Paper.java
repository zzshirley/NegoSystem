package org.ccnu.nercel.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
* 试卷（包括问卷调查）
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
	@Column(name="paperid",unique = true)
	private int paperid;
	
	//主题编号
	@Column(name="themeid")
	private int themeid;
	
	//学生学号
	@Column(name="stuid")
	private int stuid;
	
	//问题编号
	@Column(name="questionid")
	private int questionid;
	
	//试卷名称
	@Column(name="papername")
	private String papername;
	
	
	
	public Paper() {
		
	}
	
	public Paper(int id,int paperid,int themeid,int stuid,
			int questionid,String papername) {
		super();
		this.id=id;
		this.paperid=paperid;
		this.themeid=themeid;
		this.stuid=stuid;
		this.questionid=questionid;
		this.papername=papername;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getPaperid() {
		return paperid;
	}

	public void setPaperid(int paperid) {
		this.paperid = paperid;
	}

	public int getThemeid() {
		return themeid;
	}

	public void setThemeid(int themeid) {
		this.themeid = themeid;
	}

	public int getStuid() {
		return stuid;
	}

	public void setStuid(int stuid) {
		this.stuid = stuid;
	}

	public int getQuestionid() {
		return questionid;
	}

	public void setQuestionid(int questionid) {
		this.questionid = questionid;
	}

	public String getPapername() {
		return papername;
	}

	public void setPapername(String papername) {
		this.papername = papername;
	}

	@Override
	public String toString() {
		return "Paper [id=" + id + ", paperid=" + paperid + ", themeid=" + themeid + ", stuid=" + stuid
				+ ", questionid=" + questionid + ", papername=" + papername + "]";
	}

	
}
