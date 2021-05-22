package jp.co.fm.presentation.form;

public class FT091F01Form {
    private String id;
    private String password;
    private String name;
    private String msg;

    public FT091F01Form(){
       	id       = "";
       	password = "";
       	name     = "";
       	msg      = "";
    }

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
}
