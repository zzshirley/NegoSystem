package org.ccnu.nercel.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

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
	@Column(name = "paperid")
	private String paperid;
	
	// 学生ID
	@Column(name = "stuid")
	private String stuid;

	// 开始时间
	@Column(name = "begintime")
	private String begintime;

	// 结束时间
	@Column(name = "endtime")
	private String endtime;

	// 试卷分数
	@Column(name = "score")
	private int score;

	//能力a
	@Column(name="scorea")
	private int scorea;

	//能力b
	@Column(name="scoreb")
	private int scoreb;

	//能力c
	@Column(name="scorec")
	private int scorec;

	// 试卷状态
	@Column(name = "paperstate")
	private String paperstate;

	// 学生答案
	@Column(name = "paperanswer")
	private String paperanswer;

	//题目listid
	@Column(name="queslist")
	private String queslist;


	public DoPaper() {

	}
	
	public DoPaper(int id,String paperid,String stuid,String begintime,
			String endtime,int score,int scorea,int scoreb,int scorec,String paperstate,String paperanswer,String queslist) {
		super();
		this.id=id;
		this.paperid=paperid;
		this.stuid=stuid;
		this.begintime=begintime;
		this.endtime=endtime;
		this.score=score;
		this.scorea=scorea;
		this.scoreb=scoreb;
		this.scorec=scorec;
		this.paperstate=paperstate;
		this.paperanswer=paperanswer;
		this.queslist=queslist;
	}

	@Override
	public String toString() {
		return "DoPaper{" +
				"id=" + id +
				", paperid='" + paperid + '\'' +
				", stuid='" + stuid + '\'' +
				", begintime='" + begintime + '\'' +
				", endtime='" + endtime + '\'' +
				", score=" + score +
				", scorea=" + scorea +
				", scoreb=" + scoreb +
				", scorec=" + scorec +
				", paperstate='" + paperstate + '\'' +
				", paperanswer='" + paperanswer + '\'' +
				", queslist='" + queslist + '\'' +
				'}';
	}

	public int getScorea() {
		return scorea;
	}

	public void setScorea(int scorea) {
		this.scorea = scorea;
	}

	public int getScoreb() {
		return scoreb;
	}

	public void setScoreb(int scoreb) {
		this.scoreb = scoreb;
	}

	public int getScorec() {
		return scorec;
	}

	public void setScorec(int scorec) {
		this.scorec = scorec;
	}

	public String getStuid() {
		return stuid;
	}

	public void setStuid(String stuid) {
		this.stuid = stuid;
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

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getPaperstate() {
		return paperstate;
	}

	public void setPaperstate(String paperstate) {
		this.paperstate = paperstate;
	}

	public String getPaperanswer() {
		return paperanswer;
	}

	public void setPaperanswer(String paperanswer) {
		this.paperanswer = paperanswer;
	}

	public String getQueslist() {
		return queslist;
	}

	public void setQueslist(String queslist) {
		this.queslist = queslist;
	}
}
