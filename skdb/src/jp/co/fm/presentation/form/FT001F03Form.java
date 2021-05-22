package jp.co.fm.presentation.form;

import java.awt.print.Book;
import java.util.List;

public class FT001F03Form {

    private String back;
    private String doExecute;


    private String id;
    private String name;

    private String[] sampleCheckArray;

    private String[] testData;

    private List<Book> books;

    private String selectedIsbn;


    private String testData2;


    public FT001F03Form(){
    	id   = "";
    	name = "";
    	selectedIsbn= "";
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

	public String[] getSampleCheckArray() {
		return sampleCheckArray;
	}

	public void setSampleCheckArray(String[] sampleCheckArray) {
		this.sampleCheckArray = sampleCheckArray;
	}

	public String[] getTestData() {
		return testData;
	}

	public void setTestData(String[] testData) {
		this.testData = testData;
	}

	public List<Book> getBooks() {
		return books;
	}

	public void setBooks(List<Book> books) {
		this.books = books;
	}

	public String getSelectedIsbn() {
		return selectedIsbn;
	}

	public void setSelectedIsbn(String selectedIsbn) {
		this.selectedIsbn = selectedIsbn;
	}

	public String getTestData2() {
		return testData2;
	}

	public void setTestData2(String testData2) {
		this.testData2 = testData2;
	}
}
