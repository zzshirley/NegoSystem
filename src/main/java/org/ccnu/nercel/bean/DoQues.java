package org.ccnu.nercel.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.List;

/**
*@author xiaotong
*@version 2018年8月31日 下午9:04:22
*/
@Entity
public class DoQues {
	
	// 自增主键id
	@Id
	@Column(name = "id")
	@GeneratedValue
	private int id;

	// 试卷编号
	@Column(name = "paperid")
	private int paperid;
	// 开始时间
	@Column(name = "begintime")
	private String begintime;

	// 结束时间
	@Column(name = "endtime")
	private String endtime;
	
	// 学生ID
	@Column(name = "stuid")
	private String stuid;
	
	//学生选择选项
	@Column(name = "stuanswer")
	private String stuanswer;

	public DoQues() {
		
	}

	@Override
	public String toString() {
		return "DoQues{" +
				"id=" + id +
				", paperid=" + paperid +
				", begintime='" + begintime + '\'' +
				", endtime='" + endtime + '\'' +
				", stuid='" + stuid + '\'' +
				", stuanswer=" + stuanswer +
				'}';
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

	public String getBegintime() {
		return begintime;
	}

	public void setBegintime(String begintime) {
		this.begintime = begintime;
	}

	public String getEndtime() {
		return endtime;
	}

	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}

	public String getStuid() {
		return stuid;
	}

	public void setStuid(String stuid) {
		this.stuid = stuid;
	}

	public String getStuanswer() {
		return stuanswer;
	}

	public void setStuanswer(String stuanswer) {
		this.stuanswer = stuanswer;
	}
}
