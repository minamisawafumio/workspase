package jp.co.fm.businessLogic.table;

import java.sql.Timestamp;
import java.util.Date;

public class T_0990 {

	private String corp_cd;

	private String user_id;

	private Date test_ymd;

	private Timestamp test_ymd2;

	private Double kingaku2;

	private String del_flg;

	private String make_ymdhms;

	private String updt_ymdhms;

	private String make_usr;

	private String updt_usr;

	public String []primaryKey = {"corp_cd", "user_id"};

	public String getCorp_cd() {
		return corp_cd;
	}

	public void setCorp_cd(String corp_cd) {
		this.corp_cd = corp_cd;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public Date getTest_ymd() {
		return test_ymd;
	}

	public void setTest_ymd(Date test_ymd) {
		this.test_ymd = test_ymd;
	}

	public Timestamp getTest_ymd2() {
		return test_ymd2;
	}

	public void setTest_ymd2(Timestamp test_ymd2) {
		this.test_ymd2 = test_ymd2;
	}

	public Double getKingaku2() {
		return kingaku2;
	}

	public void setKingaku2(Double kingaku2) {
		this.kingaku2 = kingaku2;
	}

	public String getDel_flg() {
		return del_flg;
	}

	public void setDel_flg(String del_flg) {
		this.del_flg = del_flg;
	}

	public String getMake_ymdhms() {
		return make_ymdhms;
	}

	public void setMake_ymdhms(String make_ymdhms) {
		this.make_ymdhms = make_ymdhms;
	}

	public String getUpdt_ymdhms() {
		return updt_ymdhms;
	}

	public void setUpdt_ymdhms(String updt_ymdhms) {
		this.updt_ymdhms = updt_ymdhms;
	}

	public String getMake_usr() {
		return make_usr;
	}

	public void setMake_usr(String make_usr) {
		this.make_usr = make_usr;
	}

	public String getUpdt_usr() {
		return updt_usr;
	}

	public void setUpdt_usr(String updt_usr) {
		this.updt_usr = updt_usr;
	}


}
