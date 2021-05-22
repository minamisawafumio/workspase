package jp.co.fm.presentation.form;

public class ERROR001Form {
    private String id;
    private String name;
    private String msg;

    public ERROR001Form(){
    	id   = "";
    	name = "";
    	msg  = "";
    }

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
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
