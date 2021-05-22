package manga.form;

public class FT001F0734Form {

	private String id;
    private String name;

	private String name_exe07_txt1;
    private String name_exe07_txt2;

    private String back;
    private String doExecute;
    private String doExecute3;

    public String getName_exe07_txt1() {
		return name_exe07_txt1;
	}

	public void setName_exe07_txt1(String name_exe07_txt1) {
		this.name_exe07_txt1 = name_exe07_txt1;
	}

	public String getName_exe07_txt2() {
		return name_exe07_txt2;
	}

	public void setName_exe07_txt2(String name_exe07_txt2) {
		this.name_exe07_txt2 = name_exe07_txt2;
	}

    public String getDoExecute3() {
		return doExecute3;
	}

	public void setDoExecute3(String doExecute3) {
		this.doExecute3 = doExecute3;
	}


    public FT001F0734Form(){
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
