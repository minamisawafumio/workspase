package jp.co.fm.presentation.form;

public class FT00ShogiForm {
    private String back;
    private String shogiBan;
    private String shogiBan2;

	private String shogiMsg;



	public FT00ShogiForm() {
	   	shogiBan = "あああああああ<br>いいいいい<br>ううううう<br>";
	   	shogiMsg = "";
	}




    public String getShogiBan() {
		return shogiBan;
	}

	public void setShogiBan(String shogiBan) {
		this.shogiBan = shogiBan;
	}

	public String getShogiMsg() {
		return shogiMsg;
	}

	public void setShogiMsg(String shogiMsg) {
		this.shogiMsg = shogiMsg;
	}

	public String getBack() {
		return back;
	}

	public void setBack(String back) {
		this.back = back;
	}




	public String getShogiBan2() {
		return shogiBan2;
	}




	public void setShogiBan2(String shogiBan2) {
		this.shogiBan2 = shogiBan2;
	}
}
