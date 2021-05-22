package jp.co.fm.presentation.form;

public class FT001F08Form {

    private String back;
    private String doExecute;


    private String id;
    private String name;

    public FT001F08Form(){
    	id   = "";
    	name = "";
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

	public String getBack() {
		return back;
	}

	public void setBack(String back) {
		this.back = back;
	}

	public String getDoExecute() {
		return doExecute;
	}

	public void setDoExecute(String doExecute) {
		this.doExecute = doExecute;
	}
}
