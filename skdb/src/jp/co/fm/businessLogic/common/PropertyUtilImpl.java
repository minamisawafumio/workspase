package jp.co.fm.businessLogic.common;

import java.util.ResourceBundle;

class PropertyUtilImpl implements PropertyUtil {

	/**
	 * プロパティファイルで定義された情報を任意�?�キーで検索して�?字�?�で返す
	 * @param propertyName
	 * @param sqlId
	 * @return
	 */
	@Override
	public String getStringValue(String propertyName, String sqlId){

		ResourceBundle applicationBundle = ResourceBundle.getBundle(propertyName);

		return applicationBundle.getString(sqlId);
	}
}
