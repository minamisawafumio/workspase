package jp.co.fm.businessLogic.common;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import net.arnx.jsonic.JSON;


class JsonUtilImpl implements JsonUtil {

	@Override
	public Object makeJsonStringToObject(String stringObject, Class inClass) {
		@SuppressWarnings("unchecked")
		Object object = JSON.decode(stringObject, inClass);
		return object;
	}

	@Override
	public JsonNode makeJsonStringToJsonNode(ObjectMapper om, String jsonSt) {
		JsonNode node = null;
		try {
			node = om.readTree(jsonSt);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return node;
	}

	@Override
	public Map<String, String> makeMapFromJson(String jsonSt) {

        ObjectMapper mapper = new ObjectMapper();

        return makeMapFromJson(mapper, jsonSt);
	}

	@Override
	public Map<String, String> makeMapFromJson(ObjectMapper mapper, String jsonSt) {

		Map<String, String> map = new HashMap<String, String>();

         try {
			map = mapper.readValue(jsonSt, new TypeReference<Map<String, String>>(){});
		} catch (IOException e) {
			e.printStackTrace();
		}

        return map;
	}

	@Override
	public List<String[]> makeListFromJson(String jsonSt) {
		List<String[]> rtnList = new ArrayList<String[]>();

        ObjectMapper mapper = new ObjectMapper();

		JsonNode jsonNode = makeJsonStringToJsonNode(mapper, jsonSt);

		Iterator<Entry<String, JsonNode>>sssss = jsonNode.fields();
        while (sssss.hasNext()) {
        	Entry<String, JsonNode> fieldName = sssss.next();
        	String[] data = {fieldName.getKey().toString(), fieldName.getValue().toString()};
        	rtnList.add(data);
        }

        return rtnList;
	}

	/**
	 *
	 * @param object
	 * @param stringObject
	 * @return
	 */
	@Override
	public Object makeJsonStringToObject(Object object, String stringObject) {

		String className = object.getClass().getName();

		try {
			object = ReflectionUtil.getInstance().create(className);
		} catch (Exception e) {
			e.printStackTrace();
		}

		Object rtnObject = makeJsonStringToObject(stringObject, object.getClass());

		return rtnObject;
	}

	/**
	 *
	 * @param beanObject
	 * @return
	 */
	@Override
	public String makeObjectToJsonString(Object beanObject){

		String jsonString = null;

		try {
			jsonString = new ObjectMapper().writeValueAsString(beanObject);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}

		return jsonString;
	}

	/**
	 * オブジェクトをコピーする
	 * @param copyMoto
	 * @return
	 * @throws JsonProcessingException
	 */
	@Override
	public Object objectCopy(Object copyMoto){
		Object rtnObject = null;

    	try {
    		String JsonString = new ObjectMapper().writeValueAsString(copyMoto);
    		rtnObject = makeJsonStringToObject(JsonString, copyMoto.getClass());
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}

    	return rtnObject;
	}

	/**
	 *
	 * @param dto
	 * @param json
	 * @return
	 */
	@Override
	public <T> T parse(Class<T> dto, String json){
        ObjectMapper mapper = new ObjectMapper();
        try{
             return mapper.readValue(json, dto);
        }catch(IOException e){
             return null;
        }
	}

	/**
	 * JSONの整形
	 * @param dto
	 * @param json
	 * @return
	 */
	@Override
	public String format(String jsonSt, Object object){

		ObjectMapper mapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);

		String json = null;

        try {
			json = mapper.writeValueAsString(object);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}

        return json;
	}

	/**
	 *
	 * @param info
	 * @param object
	 */
	@Override
	public void print(String info, Object object){
		System.out.print(info);
		print(object);
	}

	/**
	 *
	 * @param jsonSt
	 * @param object
	 */
	@Override
	public void print(Object object){
		String jsonString = makeObjectToJsonString(object);

		jsonString = format(jsonString, object);

		System.out.println(jsonString);
	}
}
