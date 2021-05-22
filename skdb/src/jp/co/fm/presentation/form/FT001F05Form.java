package jp.co.fm.presentation.form;

public class FT001F05Form {

    private String back;
    private String doExecute;
    private String doExecuteAa;
    private String doExecuteBb;

    private String id;
    private String name;

    public FT001F05Form(){
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

	public String getDoExecuteAa() {
		return doExecuteAa;
	}

	public void setDoExecuteAa(String doExecuteAa) {
		this.doExecuteAa = doExecuteAa;
	}

	public String getDoExecuteBb() {
		return doExecuteBb;
	}

	public void setDoExecuteBb(String doExecuteBb) {
		this.doExecuteBb = doExecuteBb;
	}
}
