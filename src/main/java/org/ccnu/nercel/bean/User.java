package org.ccnu.nercel.bean;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

/**
* 学生用户实体
* @author xiaotong
* @version ${date} ${time}
*/

@Entity
public class User {

	//自增主键id
	@Id
	@Column(name = "id")
	@GeneratedValue
	private int id;
	
    @Column(name = "stuid",unique = true)
    @NotNull(message="学号不能为空！")
	private String stuid;
	
    @Column(name = "stuname")
    private String stuname;
    
    @Column(name = "password")
    @NotEmpty(message="密码不能为空！")
    private String password;
    
    //学生的性别
    @Column(name = "gender")
    private String  gender;
	
    @Column(name = "schoolid")
    private int schoolid;
    
    @Column(name = "major")
    private int major;
	
    @Column(name = "regdate")
    private Date regdate;
    
    //成绩
    @Column(name = "scoreStr")
    private String scoreStr;
    
	@Override
	public String toString() {
		return "User [id=" + id + ", stuid=" + stuid + ", stuname=" + stuname + ", password=" + password + ", gender="
				+ gender + ", schoolid=" + schoolid + ", major=" + major + ", regdate=" + regdate + ", scoreStr="
				+ scoreStr + "]";
	}

	public String getScoreStr() {
		return scoreStr;
	}

	public void setScoreStr(String scoreStr) {
		this.scoreStr = scoreStr;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@NotNull
	public String getStuid() {
		return stuid;
	}

	public void setStuid(@NotNull String stuid) {
		this.stuid = stuid;
	}

	public String getStuname() {
		return stuname;
	}

	public void setStuname(String stuname) {
		this.stuname = stuname;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public int getSchoolid() {
		return schoolid;
	}

	public void setSchoolid(int schoolid) {
		this.schoolid = schoolid;
	}

	public int getMajor() {
		return major;
	}

	public void setMajor(int major) {
		this.major = major;
	}

	public Date getRegdate() {
		return regdate;
	}

	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}
}
