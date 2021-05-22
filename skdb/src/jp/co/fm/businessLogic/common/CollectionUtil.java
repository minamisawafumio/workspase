package jp.co.fm.businessLogic.common;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface CollectionUtil {

	/**
	 * 文字列配列をList配列に変換する
	 * @param data
	 * @return
	 */
	List<String> changeStringList(String[] data);

	/**
	 * 文字列配列をMapに変換する
	 * @param data
	 * @return
	 */
	Map<String, String> changeStringMap(String[] data);

	/**
	 *
	 * @param map
	 * @return
	 */
	List[] getMapKeyList(Map<String, Object> map);

	/**
	 * 文字列マップをタブ区切りのListで返却する。
	 * @param map
	 * @return
	 */
	List<String> getMapKeyList2(Map<String, String> map);

	/**
	 * Mapのキーをリストで返す
	 * @param map
	 * @return
	 */
	List<String> getKeyList(Map map);

	/**
	 * Mapのキーをリストで返す
	 * @param map
	 * @return
	 */
	List<Object> mapToKeyList(Map<Object, Object> map);

	/**
	 * 指定項目の位置を取得する
	 * @param key
	 * @param list
	 * @return
	 */
	Integer getListPoint(String key, List<String> list);

	/**
	 * リストの要素をトークンで連結して文字列で返す
	 * @param list
	 * @param token
	 * @param mode    要素を " で囲むかの設定
	 * @return
	 */
	String getStringUnitesByToken(List<String> list, String token, Boolean mode);

	/**
	 * Mapの値をリストで返す
	 * @param map
	 * @return
	 */
	List<String> getValueList(Map<String, String> map);

	/**
	* Listの内容を System.out に出力する
	*/
	void listPrintln(List<String> l);

	void mapPrintln(Map<String, Object> map);

	/**
	 * Mapの内容をリストで返す
	 * @param map
	 * @return
	 */
	List<Object> mapToList(Map<String, Object> map);

	/**
	 * Mapの内容（キーと値を）をタブで区切ったリストで返す
	 * @param map
	 * @return
	 */
	List<String> mapToTabList(Map<String, String> map);

	/**
	 * Mapのキーに先頭から指定文字を含む場合、Mapより削除して返す
	 * @param map
	 * @param deleteKey
	 * @return
	 */
	Map<String, Object> removeStartWith(Map<String, Object> map, String deleteKey);

	/**
	 * リストの要素（文字列）で重複している要素を削除して返す
	 * @param inList
	 * @return
	 */
	List<String> senbetuList(List<String> inList);

	/**
	 * 文字列リストを昇順ソートする
	 * @param _list
	 * @return
	 */
	List<String> sort(List<String> _list);

	/**
	 * 文字列配列をSetに変換する
	 * @param data
	 * @return
	 */
	Set<String> toSet(String[] data);

	CollectionUtil collectionUtil = new CollectionUtilImpl();

	public static CollectionUtil getInstance() {
		return collectionUtil;
	}

}