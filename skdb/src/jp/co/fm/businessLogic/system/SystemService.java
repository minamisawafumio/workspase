package jp.co.fm.businessLogic.system;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.Model;

public interface SystemService {

	/**
	 * セッション存在チェック
	 * @param request
	 * @return
	 */
	public String isExistSession(HttpServletRequest request);

	/**
	 * 指定文字列のSystemCommonクラスのstaticメソッドを実行
	 * @param request
	 * @return
	 */
	public String syori(HttpServletRequest request);

	/**
	 *
	 * @param request
	 * @return
	 */
	boolean isLogin(HttpServletRequest request);

	/**
	 * クロスオリジンを回避（なぜ？うまく動かない）
	 * @param request
	 * @param response
	 */
	void avoidCrossOrigin(HttpServletRequest request, HttpServletResponse response);

	/**
	 *
	 * @param inMap
	 * @return
	 */
	public List<String> makeMap2List(Map<String, String> inMap);

	/**
	 *
	 * @param request
	 * @return
	 */
	public Map<String, Object> getGamenMap2(HttpServletRequest request);

	public Map<String, Object> makeMap(List<Object> list);

	/**
	 *
	 * @return
	 */
	public String getNotLoginImg();

	/**
	 * 初期画面に戻る
	 * @param model
	 * @param request
	 * @return
	 */
	public String goMenu(Model model, HttpServletRequest request);

	/**
	 *
	 * @param request
	 * @param key
	 * @return
	 */
	public String getJson(HttpServletRequest request, String key);

	/**
	 *
	 * @param request
	 * @param key
	 * @return
	 */
	public Map<String, String> getDecodeMap(HttpServletRequest request, String key);

	/**
	 * マンガタイトルのリストを取得する
	 * @return
	 */
	public List<String> getTittleList();

	/**
	 *
	 * @param arraySt
	 * @return
	 */
	public Map<String, List<String>> getGamenListA(String arraySt);

	/**
	 *
	 * @param key1
	 * @param key2
	 * @param key3
	 * @return
	 */
	public Map<String, Object> getMap_T_0001(String key1, String key2, String key3);

	/**
	 *
	 * @param userId
	 * @param json
	 * @return
	 */
	public Integer upsert_T_0001(String key1, String key2, String key3, Map iMap);

	/**
	 *
	 * @param map
	 * @return
	 */
	public String makeJsonData(Map<String, Object> map);

	/**
	 * 引数を配列に変換
	 * @param i1
	 * @param videoTitle
	 * @return
	 */
	public Object[] makeArray(String i1, String i2);

	/**
	 * 引数を配列に変換
	 * @param i1
	 * @param videoTitle
	 * @return
	 */
	Object[] makeArray(String i1, String i2, String i3);

	SystemService systemService = new SystemServiceImpl();

	public static SystemService getInstance() {
		return systemService;
	}

}