package jp.co.fm.businessLogic.common;

public interface PropertyUtil {

	/**
	 * プロパティファイルで定義された情報を任意�?�キーで検索して�?字�?�で返す
	 * @param propertyName
	 * @param sqlId
	 * @return
	 */
	String getStringValue(String propertyName, String sqlId);

	PropertyUtil propertyUtil = new PropertyUtilImpl();

	public static PropertyUtil getInstance() {
		return propertyUtil;
	}
}