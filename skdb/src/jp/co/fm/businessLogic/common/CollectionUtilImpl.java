package jp.co.fm.businessLogic.common;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;


public class CollectionUtilImpl implements CollectionUtil {

	/**
	 * 文字列配列をList配列に変換する
	 * @param data
	 * @return
	 */
	@Override
	public List<String> changeStringList(String []data){
		 List<String> rtnList = new ArrayList<>();

		 for(Integer i = 0; i < data.length; i++){
			 rtnList.add(data[i]);
		 }

		 return rtnList;
	}

	/**
	 * 文字列配列をMapに変換する
	 * @param data
	 * @return
	 */
	@Override
	public Map<String, String> changeStringMap(String []data){
		Map<String, String> rtnMap = new HashMap<>();

		 for(Integer i = 0; i < data.length; i++){
			 rtnMap.put(data[i], data[i]);
		 }

		return rtnMap;
	}

	/**
	 *
	 * @param map
	 * @return
	 */
	@Override
	public List[] getMapKeyList(Map<String, Object> map) {
		List<String> keyList   = new ArrayList<>();
		List<Object> valueList = new ArrayList<>();

		Iterator ite = map.keySet().iterator();
		while (ite.hasNext() ) {
			String key = (String)ite.next();
			keyList.add(key);
			valueList.add(map.get(key));
		}

		List []aaa = {keyList, valueList};

		return aaa;
	}

	/**
	 * 文字列マップをタブ区切りのListで返却する。
	 * @param map
	 * @return
	 */
	@Override
	public List<String> getMapKeyList2(Map<String, String> map) {
		List<String> rtnList = new ArrayList<>();

		Iterator<String> ite = map.keySet().iterator();

		while (ite.hasNext() ) {
			String key   = ite.next();
			String value = map.get(key);
			rtnList.add(key + "\t" + value);
		}

		rtnList = sort(rtnList);

		return rtnList;
	}

	/**
	 * Mapのキーをリストで返す
	 * @param map
	 * @return
	 */
	@Override
	public List<String> getKeyList(Map map){
    	return mapToKeyList(map);
    }

	/**
	 * Mapのキーをリストで返す
	 * @param map
	 * @return
	 */
	@Override
	public List<Object> mapToKeyList(Map<Object, Object> map) {
		List<Object> rtnList = new ArrayList<>();

		Iterator ite = map.keySet().iterator();
		while (ite.hasNext() ) {
			Object key = (Object)ite.next();
			rtnList.add(key);
		}
		return rtnList;
	}

	/**
	 * 指定項目の位置を取得する
	 * @param key
	 * @param list
	 * @return
	 */
	@Override
	public Integer getListPoint(String key, List<String> list){
		int rtnInt = 0;

		for(Integer i = 0; i < list.size(); i++){
			String koumoku = list.get(i);
			if(key.equals(koumoku)){
				return i;
			}
		}
		return rtnInt;
	}

	/**
	 * リストの要素をトークンで連結して文字列で返す
	 * @param list
	 * @param token
	 * @param mode    要素を " で囲むかの設定
	 * @return
	 */
	@Override
	public String getStringUnitesByToken(List<String> list, String token, Boolean mode){

		if(list == null || list.size() == 0){
			return null;
		}

		StringBuffer sb = new StringBuffer();

		for(Integer i = 0; i < list.size(); i++){
			if(mode == true){
				sb.append("\"" + list.get(i) + "\""+ token);
			}else{
				sb.append(list.get(i) + token);
			}
		}

		sb.delete(sb.length()-1, sb.length());

		return sb.toString();
	}

	/**
	 * Mapの値をリストで返す
	 * @param map
	 * @return
	 */
	@Override
	public List<String> getValueList(Map<String, String> map){
    	List<String> rtnList = new ArrayList<> ();

		Iterator<String> ite = map.keySet().iterator();

		while (ite.hasNext() ) {
			String key   = ite.next();
			String value = map.get(key);
			rtnList.add(value);
		}

    	return rtnList;
    }

	/**
	* Listの内容を System.out に出力する
	*/
	@Override
	public void listPrintln(List<String> l){
		for(int i=0;i<l.size(); i++){
			String s = l.get(i);
			System.out.println(s);
		}
	}

	@Override
	public void mapPrintln(Map<String, Object> map){
        map.forEach((mapKey, value) -> {
			System.out.println( "key   = " + mapKey );
			System.out.println( "value = " + value);
        });
	}

	/**
	 * Mapの内容をリストで返す
	 * @param map
	 * @return
	 */
	@Override
	public List<Object> mapToList(Map<String, Object> map){
		List<Object> rtnList = new ArrayList<>();

		Iterator ite = map.keySet().iterator();
		while (ite.hasNext() ) {
			Object key = (Object)ite.next();
			rtnList.add(map.get(key));
		}
		return rtnList;
	}

	/**
	 * Mapの内容（キーと値を）をタブで区切ったリストで返す
	 * @param map
	 * @return
	 */
	@Override
	public List<String> mapToTabList(Map<String, String> map){
		List<String> rtnList = new ArrayList<>();

		Iterator ite = map.keySet().iterator();
		while (ite.hasNext() ) {
			Object key = (Object)ite.next();
			String value = map.get(key);
			rtnList.add(key + "\t" + value);
		}
		return rtnList;
	}

	/**
	 * Mapのキーに先頭から指定文字を含む場合、Mapより削除して返す
	 * @param map
	 * @param deleteKey
	 * @return
	 */
	@Override
	public Map<String, Object> removeStartWith(Map<String, Object> map, String deleteKey){

		Map<String, Object> rtnMap = new HashMap<>();

		Iterator ite = map.keySet().iterator();
		while (ite.hasNext() ) {
			String key = (String)ite.next();

			if(! key.startsWith(deleteKey)){
				Object obj = map.get(key);
				rtnMap.put(key, obj);
			}
		}
		return rtnMap;
	}

	/**
	 * リストの要素（文字列）で重複している要素を削除して返す
	 * @param inList
	 * @return
	 */
	@Override
	public List<String> senbetuList(List<String> inList){
		List<String> rtnList = new ArrayList<>();
		Map<String, String> map = new HashMap<>();

		 for(Integer i = 0; i < inList.size(); i++){
			 String key = inList.get(i);

			if(map.get(key) == null){
				map.put(key, null);
				rtnList.add(key);
			}
		 }

		 return rtnList;
	}

	/**
	 * 文字列リストを昇順ソートする
	 * @param _list
	 * @return
	 */
	@Override
	public List<String> sort(List<String> _list){

		Set<String> treeSet = new TreeSet<>();

		treeSet.addAll(_list);

		Object array[] = treeSet.toArray();

		String[] strArray = Arrays.asList(array).toArray(new String[array.length]);

		return changeStringList(strArray);
	}

	/**
	 * 文字列配列をSetに変換する
	 * @param data
	 * @return
	 */
	@Override
	public Set<String> toSet(String []data){
		Set<String> rtnSet = new HashSet<>();
		for(Integer i = 0; i < data.length; i++){
			rtnSet.add(data[i]);
		}
		return rtnSet;
	}
}
