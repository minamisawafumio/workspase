package jp.co.fm.businessLogic.common;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Set;

class BeanUtilImpl implements BeanUtil {

    /**
     * Beanを作成する
     * @param obj
     * @param map
     * @return
     */
    @Override
	public Object makeBean(Object obj, Map<String, Object> map){
        Object rtnObj = null;

        try {
            rtnObj = obj.getClass().newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }

        List<String> list = CollectionUtil.getInstance().getKeyList(map);

        for(String key: list) {
        	Object value = map.get(key);
        	ReflectionUtil.getInstance().setValueToField(rtnObj, key, value);
        }
        return rtnObj;
    }

    /**
     * Beanの指定メンバにNullをセットして返す
     * @param obj
     * @param mambers
     * @return
     */
    @Override
	public Object beanSetNull(Object obj, String []mambers){
        Object rtnObj = copyBean(obj);

        Set<String> mamberSet = CommonUtil.getInstance().toSet(mambers);

        Field []fields = obj.getClass().getDeclaredFields();

        for (Field field: fields){
            String name = field.getName();

            if(mamberSet.contains(name)){
            	ReflectionUtil.getInstance().setValueToField(rtnObj, name, null);
            }
        }
        return rtnObj;
    }

    /**
     * Beanをコピーする
     * @param object1
     * @return
     */
    @Override
	public Object copyBean(Object object1) {

    	String jsonSt = JsonUtil.getInstance().makeObjectToJsonString(object1);

    	Object obj = JsonUtil.getInstance().makeJsonStringToObject(object1, jsonSt);

    	return obj;
    }

    /**
     * オブジェクトをコンソールに出力する
     * @param object1
     */
    @Override
	public void printBean(Object object1) {
    	System.out.println(JsonUtil.getInstance().makeObjectToJsonString(object1));
    }
}
