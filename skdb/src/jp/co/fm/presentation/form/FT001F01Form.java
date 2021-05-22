package jp.co.fm.presentation.form;

public class FT001F01Form {
    private String id;
    private String password;
    private String message;

    private Integer count;

    private String testData;

    private String testNo;

	public FT001F01Form(){
		id       = "";
		password = "";
       	message  = "";

       	testNo = "";



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

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public String getTestData() {
		return testData;
	}

	public void setTestData(String testData) {
		this.testData = testData;
	}

    public String getTestNo() {
		return testNo;
	}

	public void setTestNo(String testNo) {
		this.testNo = testNo;
	}



}
