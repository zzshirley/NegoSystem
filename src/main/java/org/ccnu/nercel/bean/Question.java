package org.ccnu.nercel.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
*试题实体（问卷题目） 
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
	
	//问题编号
	@Column(name="quesid",unique = true)
	private int quesid;
	
	//问题名称
	@Column(name="quesname")
	private String quesname;
	
	//选项A
	@Column(name="optiona")
	private String optiona;
	
	//选项B
	@Column(name="optionb")
	private String optionb;
	
	//选项C
	@Column(name="optionc")
	private String optionc;
	
	//选项D
	@Column(name="optiond")
	private String optiond;
	
	//选项E
	@Column(name="optione")
	private String optione;
	
	//标准答案
	@Column(name="answer")
	private String answer;
	
	//学生答案
	@Column(name="stuanswer")
	private String stuanswer;
	
	//对应文章
	@Column(name="paperid")
	private String paperid;
	
	//备注
	@Column(name="remark")
	private String remark;
	
	public Question() {
		
	}
	
	public Question(int id,int quesid,String quesname,String optiona,
			String optionb,String optionc,String optiond,String optione,
			String answer,String stuanswer,String paperid,String remark) {
		super();
		this.id=id;
		this.quesid=quesid;
		this.paperid=paperid;
		this.quesname=quesname;
		this.optiona=optiona;
		this.optionb=optionb;
		this.optionc=optionc;
		this.optiond=optiond;
		this.optione=optione;
		this.answer=answer;
		this.stuanswer=stuanswer;
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

	public String getOptiona() {
		return optiona;
	}

	public void setOptiona(String optiona) {
		this.optiona = optiona;
	}

	public String getOptionb() {
		return optionb;
	}

	public void setOptionb(String optionb) {
		this.optionb = optionb;
	}

	public String getOptionc() {
		return optionc;
	}

	public void setOptionc(String optionc) {
		this.optionc = optionc;
	}

	public String getOptiond() {
		return optiond;
	}

	public void setOptiond(String optiond) {
		this.optiond = optiond;
	}

	public String getOptione() {
		return optione;
	}

	public void setOptione(String optione) {
		this.optione = optione;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public String getStuanswer() {
		return stuanswer;
	}

	public void setStuanswer(String stuanswer) {
		this.stuanswer = stuanswer;
	}

	public String getPaperid() {
		return paperid;
	}

	public void setPaperid(String paperid) {
		this.paperid = paperid;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Override
	public String toString() {
		return "Question [id=" + id + ", quesid=" + quesid + ", quesname=" + quesname + ", optiona=" + optiona
				+ ", optionb=" + optionb + ", optionc=" + optionc + ", optiond=" + optiond + ", optione=" + optione
				+ ", answer=" + answer + ", stuanswer=" + stuanswer + ", paperid=" + paperid + ", remark=" + remark
				+ "]";
	}
}
