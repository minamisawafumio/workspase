package jp.co.fm.businessLogic.common;

import java.io.FileNotFoundException;

interface XmlUtil {

	/**
	 * Objectの配列をxmlファイルに出力する。
	 * (オブジェクトの配列をXMLにエンコードする）
	 * @param objArray Objectの配列
	 * @param fileName xmlファイル
	 * @throws FileNotFoundException
	 */
	void encodeBeanArray(Object beanArray[], String fileName) throws FileNotFoundException;

	/**
	 * xmlファイルをオブジェクトの配列で取得する。
	 * (encodeBeanArrayでエンコードしたXMLをデコードする）
	 * @param fileName
	 * @return
	 * @throws FileNotFoundException
	 */
	Object[] decodeBeanArray(String fileName) throws FileNotFoundException;

	XmlUtil xmlUtil = new XmlUtilImpl();


	public static XmlUtil getInstancce() {
		return xmlUtil;
	}
}