package jp.co.fm.businessLogic.common;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public interface JsonUtil {

	Object makeJsonStringToObject(String stringObject, Class inClass);

	/**
	 *
	 * @param object
	 * @param stringObject
	 * @return
	 */
	Object makeJsonStringToObject(Object object, String stringObject);


	JsonNode makeJsonStringToJsonNode(ObjectMapper om, String jsonSt);

	/**
	 *
	 * @param beanObject
	 * @return
	 */
	String makeObjectToJsonString(Object beanObject);

	/**
	 * オブジェクトをコピーする
	 * @param copyMoto
	 * @return
	 * @throws JsonProcessingException
	 */
	Object objectCopy(Object copyMoto);

	/**
	 * JSONからMapを作成する
	 * "{"name":"mkyong", "age":"29"}"
	 * @param jsonSt
	 * @return
	 */
	Map<String, String> makeMapFromJson(String jsonSt);

	Map<String, String> makeMapFromJson(ObjectMapper mapper, String jsonSt);

	/**
	 * JSONからList<String[]>を作成する
	 * "{"name":"mkyong", "age":"29"}"
	 * @param jsonSt
	 * @return
	 */
	List<String[]> makeListFromJson(String jsonSt);

	/**
	 *
	 * @param dto
	 * @param json
	 * @return
	 */
	<T> T parse(Class<T> dto, String json);

	/**
	 * JSONの整形
	 * @param dto
	 * @param json
	 * @return
	 */
	String format(String jsonSt, Object object);

	/**
	 *
	 * @param info
	 * @param object
	 */
	void print(String info, Object object);

	/**
	 *
	 * @param jsonSt
	 * @param object
	 */
	void print(Object object);

	JsonUtil jsonUtil = new JsonUtilImpl();

	public static JsonUtil getInstance() {
		return jsonUtil;
	}
}