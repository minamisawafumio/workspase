package jp.co.fm.presentation.form;

import java.util.HashMap;
import java.util.Map;

import jp.co.fm.businessLogic.common.Const;
import jp.co.fm.businessLogic.system.SystemInfo;

public class FT001F02Form {
    //チェックボックス
    private Boolean sampleCheck1;
    private Boolean sampleCheck2;
    private Boolean sampleCheck3;

    //ラジオボタン
    private String[] optionValues;

    private Map<String, String> menuMap = new HashMap<>();
    private String[] mapValues;

    private String selectedIsbn;

    private SystemInfo si = SystemInfo.getInstance();

    public FT001F02Form(){
        Map<String, String> countriesMap = (Map<String, String>) si.getValue(Const.RENDOU1);

        setMenuMap(countriesMap);
    }

	public void setMenuMap(Map<String, String> menuMap) {
		this.menuMap = menuMap;
	}

	public Boolean getSampleCheck1() {
		return sampleCheck1;
	}

	public void setSampleCheck1(Boolean sampleCheck1) {
		this.sampleCheck1 = sampleCheck1;
	}

	public Boolean getSampleCheck2() {
		return sampleCheck2;
	}

	public void setSampleCheck2(Boolean sampleCheck2) {
		this.sampleCheck2 = sampleCheck2;
	}

	public Boolean getSampleCheck3() {
		return sampleCheck3;
	}

	public void setSampleCheck3(Boolean sampleCheck3) {
		this.sampleCheck3 = sampleCheck3;
	}

	public String[] getOptionValues() {
		return optionValues;
	}

	public void setOptionValues(String[] optionValues) {
		this.optionValues = optionValues;
	}

	public String[] getMapValues() {
		return mapValues;
	}

	public void setMapValues(String[] mapValues) {
		this.mapValues = mapValues;
	}

	public String getSelectedIsbn() {
		return selectedIsbn;
	}

	public void setSelectedIsbn(String selectedIsbn) {
		this.selectedIsbn = selectedIsbn;
	}

	public Map<String, String> getMenuMap() {
		return menuMap;
	}
}
