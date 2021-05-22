package jp.co.fm.businessLogic.common;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

class CommonUtilImpl implements CommonUtil {

    /**
     *
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

    /**
     *
     * @param objects
     * @return
     */
    @Override
	public String makeJsonSt(Object ... objects) {
	    Map<String, Object> rtnMap = makeObjectMap(objects);

	    String jsonData = null;
		try {
			jsonData = new ObjectMapper().writeValueAsString(rtnMap);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
	    return jsonData;
    }

    /**
     * Map作成
     * @param objects
     * @return
     */
	@Override
	public Map<String, Object> makeObjectMap(Object ... objects) {

		Map<String, Object> map = new HashMap<>();

		int count = 1;

		String key = null;

        for(Object object: objects) {
			if(count%2 > 0) {
				key = (String) object;
			} else {
				map.put(key, object);
			}
			count ++;
        }

        return map;
    }
}
