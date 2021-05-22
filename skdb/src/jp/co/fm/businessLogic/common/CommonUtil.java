package jp.co.fm.businessLogic.common;

import java.util.Map;
import java.util.Set;

public interface CommonUtil {

	/**
	 *
	 * @param map
	 * @param deleteKey
	 * @return
	 */
	Map<String, Object> removeStartWith(Map<String, Object> map, String deleteKey);

	/**
	 * 文字列配列をSetに変換する
	 * @param data
	 * @return
	 */
	Set<String> toSet(String[] data);

	/**
	 *
	 * @param objects
	 * @return
	 */
	String makeJsonSt(Object... objects);

	/**
	 * Map作成
	 * @param objects
	 * @return
	 */
	Map<String, Object> makeObjectMap(Object... objects);

	CommonUtil commonUtil = new CommonUtilImpl();

	public static CommonUtil getInstance() {
		return commonUtil;
	}

}