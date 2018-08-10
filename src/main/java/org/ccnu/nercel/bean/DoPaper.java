package org.ccnu.nercel.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * @author xiaotong
 * @version 2018年8月5日 下午8:03:13 做测试（问卷）
 */
@Entity
public class DoPaper {
	
	// 自增主键id
	@Id
	@Column(name = "id")
	@GeneratedValue
	private int id;

	// 试卷编号
	@Column(name = "paperid", unique = true)
	private int paperid;

	// 开始时间
	@Column(name = "begintime")
	private String begintime;

	// 结束时间
	@Column(name = "endtime")
	private String endtime;

	// 试卷分数
	@Column(name = "score")
	private String score;

	// 试卷状态
	@Column(name = "paperstate")
	private String paperstate;

	public DoPaper() {

	}
	
	public DoPaper(int id,int paperid,String begintime,
			String endtime,String score,String paperstate) {
		super();
		this.id=id;
		this.paperid=paperid;
		this.begintime=begintime;
		this.endtime=endtime;
		this.score=score;
		this.paperstate=paperstate;		
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

	public String getScore() {
		return score;
	}

	public void setScore(String score) {
		this.score = score;
	}

	public String getPaperstate() {
		return paperstate;
	}

	public void setPaperstate(String paperstate) {
		this.paperstate = paperstate;
	}

	@Override
	public String toString() {
		return "DoPaper [id=" + id + ", paperid=" + paperid + ", begintime=" + begintime + ", endtime=" + endtime
				+ ", score=" + score + ", paperstate=" + paperstate + ", getClass()=" + getClass() + ", hashCode()="
				+ hashCode() + ", toString()=" + super.toString() + "]";
	}

}
