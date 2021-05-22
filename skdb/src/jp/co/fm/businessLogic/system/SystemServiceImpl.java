package jp.co.fm.businessLogic.system;

import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.bind.DatatypeConverter;

import org.apache.ibatis.session.SqlSession;
import org.springframework.ui.Model;

import com.mongodb.util.JSON;

import jp.co.fm.businessLogic.common.BeanUtil;
import jp.co.fm.businessLogic.common.CommonUtil;
import jp.co.fm.businessLogic.common.Const;
import jp.co.fm.businessLogic.common.DbUtil;
import jp.co.fm.businessLogic.common.JsonUtil;
import jp.co.fm.businessLogic.common.ReflectionUtil;
import jp.co.fm.businessLogic.common.StringUtil;
import jp.co.fm.businessLogic.table.T_0001;
import jp.co.fm.presentation.form.ERROR001Form;
import manga.common.MangaConst;

class SystemServiceImpl implements SystemService {

    private Map<String, Object> instanceMap = new HashMap<String, Object>();

	/**
	 * 指定文字列のSystemCommonクラスのstaticメソッドを実行
	 * @param request
	 * @return
	 */
	@Override
	public String syori(HttpServletRequest request) {

		String jsonData = "";

		Map<String, Object> map = getGamenMap2(request);
		String className	= (String) map.get("className");
		String methodName	= (String) map.get("methodName");


		try {
			Class[] argType = {HttpServletRequest.class};
	        Object[] args  ={request};
	        //実行クラスのインスタンスがMap内より取得する
	        Object clsInst = instanceMap.get(Const.SERVICE_PACKAGE_NAME + className);
	        //実行クラスのインスタンスがMap内に無い場合は、新たに作成する
	        if (clsInst == null) {
	        	 clsInst = ReflectionUtil.getInstance().create(Const.SERVICE_PACKAGE_NAME + className);
	        	 instanceMap.put(Const.SERVICE_PACKAGE_NAME + className, clsInst);
	        }

	        jsonData = (String)ReflectionUtil.getInstance().invoke(clsInst, methodName, argType, args);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return jsonData;
	}

	/**
	 *
	 * @param request
	 * @return
	 */
	@Override
	public boolean isLogin(HttpServletRequest request) {
		HttpSession httpSession = request.getSession();

		String userId = (String) httpSession.getAttribute(Const.USER_ID);
       	//ログインしていない場合
		if(userId == null){
			return false;
		}
		return true;
	}

	/**
	 * クロスオリジンを回避（なぜ？うまく動かない）
	 * @param request
	 * @param response
	 */
	@Override
	public void avoidCrossOrigin(HttpServletRequest request, HttpServletResponse response) {
		//レスポンスヘッダを付ける（つけないとホスト名が変わる(localhose⇒直ID)とChromeでエラーになる）
		response.setHeader("Access-Control-Allow-Origin", "*");
//		response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
		response.setHeader("Access-Control-Allow-Methods", "POST, PUT, GET, DELETE, OPTIONS");
		response.setHeader("Access-Control-Allow-Headers", request.getHeader("Access-Control-Request-Headers"));
		response.setHeader("Access-Control-Max-Age", "-1");
	}

	/**
	 *
	 * @param inMap
	 * @return
	 */
	@Override
	public List<String> makeMap2List(Map<String, String> inMap) {
		List<String> trnList = new ArrayList<String>();

        Iterator ite = inMap.keySet().iterator();
        while (ite.hasNext() ) {
            String key = (String)ite.next();
            trnList.add(key + "~" + inMap.get(key));
        }
        return trnList;
	}

	/**
	 *
	 * @param request
	 * @return
	 */
	@Override
	public Map<String, Object> getGamenMap2(HttpServletRequest request) {

		String jsonString = request.getParameter(Const.REQUEST_KEY);

		String info = jsonString.substring(0, 3);

		if(info.equals("WTJ")) {

			String json = getJson(request, Const.REQUEST_KEY);

			Map<String, List<String>> listMap = getGamenListA(json);

			Map<String, Object> map = new HashMap<> ();

			Set<String> set = listMap.keySet();

			for(String key : set) {
				StringBuffer sb = new StringBuffer();

				List<String> list = listMap.get(key);

				for(String data: list) {
					sb.append(data);
				}

				map.put(key, sb.toString());
			}

			return map;
		} else {
			String json = new String(DatatypeConverter.parseHexBinary(jsonString));

			Map map = (Map) JSON.parse(json);

			return map;
		}
	}

	@Override
	public Map<String, Object> makeMap(List<Object> list) {
		Map<String, Object> rtnMap = new HashMap<>();

		for(int i = 0; i < list.size(); i++) {
			String key = (String) list.get(i);

			Object value = list.get(i + 1);

			if(value instanceof ArrayList) {
				Map<String, Object> map2 = makeMap((List) value);
				rtnMap.put(key, map2);
			}else {
				rtnMap.put(key, value);
			}
			i++;
		}
		return rtnMap;
	}

	/**
	 *
	 * @return
	 */
	@Override
	public String getNotLoginImg() {
		String imgString = null;

		//エラー画像（ログインしなおしてください）取得
		T_0001 t_0001 =  SystemDb.getInstance().selectT_0001("1", "0001", "1");

		if (t_0001 != null) {
			try {
				imgString = t_0001.getValue();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}

		return imgString;
	}

	/**
	 * 初期画面に戻る
	 * @param model
	 * @param request
	 * @return
	 */
	@Override
	public String goMenu(Model model, HttpServletRequest request) {

       	HttpSession httpSession = request.getSession();

       	ERROR001Form form = new ERROR001Form();

    	httpSession.setAttribute("eRROR001Form", form);

        model.addAttribute("ERROR001Form", form);

        return "ERROR001";
    }

	/**
	 *
	 * @param request
	 * @param key
	 * @return
	 */
	@Override
	public String getJson(HttpServletRequest request, String key) {
		String jsonString = request.getParameter(key);
		String json = StringUtil.getInstance().decodeStBase64(jsonString);
		return json;
	}

	/**
	 *
	 * @param request
	 * @param key
	 * @return
	 */
	@Override
	public Map<String, String> getDecodeMap(HttpServletRequest request, String key) {
		String json = getJson(request, key);
		json = StringUtil.getInstance().decode(json);
		return JsonUtil.getInstance().parse(Map.class, json);
	}

    /**
     * マンガタイトルのリストを取得する
     * @return
     */
    @Override
	public List<String> getTittleList() {
    	//タイトル-----------------------------------------------------------------
    	List<String> list = (List<String>) SystemInfo.getInstance().getValue(MangaConst.MANGA_TITLE_LIST);
    	return list;
    }

    /**
     *
     * @param arraySt
     * @return
     */
	@Override
	public Map<String, List<String>> getGamenListA(String arraySt){
		Map<String, List<String>> listMap = new HashMap<>();

		String decoded = new String(Base64.getUrlDecoder().decode(arraySt));

		//文字列を「タブ(\t)+バックスラッシュ(\b)+タブ(\t)」で区切り、配列にセットする
		String[] array09 = decoded.split("%2509%2508%2509");

		Map<String, String> map = (Map) SystemInfo.getInstance().getValue(Const.CODE_MAP_MANGA);

		for(int i = 0; i < array09.length; i++) {
			//文字列を「タブ(\t)+キャリッジリターン(\r)+タブ(\t)」で区切り、配列にセットする
			String[] keyValue = array09[i].split("%2509%250D%2509");

			String key   = keyValue[0];

			if(keyValue.length <= 1) {
				continue;
			}

			String value = keyValue[1];

			//文字列を「タブ(\t)+ラインフィード(\n)+タブ(\t)」で区切り、配列にセットする
			String[] array08 = value.split("%2509%250A%2509");

			List<String> stList = new ArrayList<>();

			for(int j = 0; j < array08.length; j++) {

				StringBuffer sb = new StringBuffer();

				String mojiretu = array08[j];

				String []datas = mojiretu.split("%");

				if(datas.length > 0) {
					sb.append(datas[0]);
				}

				for(int k = 1; k < datas.length; k++) {
					String data = datas[k];

					String moji = "";
					String moji3 = "   ";
					String moji4 = "    ";
					String moji2 = data.substring(0, 2);

					if(data.length() >= 3) {
						moji3 = data.substring(0, 3);
					}
					if(data.length() >= 4) {
						moji4 = data.substring(0, 4);
					}

					//タブ区切りの場合
					if("2509".equals(moji4)) {
						sb.append((char)Integer.parseInt("09", 16));
						if(data.length() > 4) {
							sb.append(data.substring(4, data.length()));
						}
					//Mapに登録されている４文字コードの場合
					}else if(map.get(moji4) != null) {
						sb.append(map.get(moji4));
						if(data.length() > 4) {
							sb.append(data.substring(4, data.length()));
						}
					//Mapに登録されている２文字コードの場合
					}else if(map.get(moji2) != null) {
						sb.append(map.get(moji2));
						if(data.length() > 2) {
							sb.append(data.substring(2, data.length()));
						}
					//ユニコードの場合
					}else if("25u".equals(moji3)) {
						moji = data.substring(3,7);
						sb.append((char)Integer.parseInt(moji, 16));
						if(data.length() > 7) {
							sb.append(data.substring(7, data.length()));
						}
					}else {
						if (StringUtil.getInstance().isNumeric(data)) {
							sb.append((char)Integer.parseInt(data, 16));
						}
					}
				}
				stList.add(sb.toString());
			}

			listMap.put(key, stList);
		}

		return listMap;
	}

	/**
	 *
	 * @param key1
	 * @param key2
	 * @param key3
	 * @return
	 */
	@Override
	public Map<String, Object> getMap_T_0001(String key1, String key2, String key3) {

		T_0001 t_0001 =  SystemDb.getInstance().selectT_0001(key1, key2, key3);

		if(t_0001 == null ) {
			return new HashMap<>();
		}

		String value = t_0001.getValue();

		if(value == null){
			return new HashMap<>();
		}

		Map<String, Object> map = (Map<String, Object>) JsonUtil.getInstance().makeJsonStringToObject(new HashMap<String, Object>(), value);

		return map;
	}

	/**
	 *
	 * @param userId
	 * @param json
	 * @return
	 */
	@Override
	public Integer upsert_T_0001(String key1, String key2, String key3, Map iMap) {
		String json = JsonUtil.getInstance().makeObjectToJsonString(iMap);

		Map<String, Object> map = new HashMap<>();

		map.put("key1"	, key1); //
		map.put("key2"	, key2); //削除フラグ
		map.put("key3"	, key3); //ユーザID
		map.put("value"	, json); //データ

		T_0001 t_0001 = (T_0001) BeanUtil.getInstance().makeBean(new T_0001(), map);

		Integer rtnInt = null;

        SqlSession sqlSession = DbUtil.getInstance().getNewSqlSession();

		try {
			rtnInt = DbUtil.getInstance().upsert(sqlSession, t_0001, SystemConst.PM_KEY_T_0001);
			sqlSession.commit();
		}catch(Exception e) {
			sqlSession.rollback();
		}finally {
			sqlSession.close();
		}
		return rtnInt;
	}

	/**
	 *
	 * @param map
	 * @return
	 */
	@Override
	public String makeJsonData(Map<String, Object> map) {
		String jsonStringMap = JsonUtil.getInstance().makeObjectToJsonString(map);
		String methodName	= (String) map.get("methodName");

		String jsonData = CommonUtil.getInstance().makeJsonSt(
			"methodName", "ret" + StringUtil.getInstance().headUpperCase(methodName),
			Const.JSON_STRING_MAP	, jsonStringMap
		);
		return jsonData;
	}

    /**
     * 引数を配列に変換
     * @param i1
     * @param videoTitle
     * @return
     */
    @Override
	public Object[] makeArray(String i1, String i2) {
    	Object[] array = {i1, i2};
    	return array;
    }

    /**
     * 引数を配列に変換
     * @param i1
     * @param videoTitle
     * @return
     */
    @Override
	public Object[] makeArray(String i1, String i2, String i3) {
    	Object[] array = {i1, i2, i3};
    	return array;
    }

	/**
	 * セッション存在チェック
	 * @param request
	 * @return
	 */
	@Override
	public String isExistSession(HttpServletRequest request) {

		HttpSession httpSession = request.getSession();

		Map<String, Object> map = getGamenMap2(request);

		String userId = (String) httpSession.getAttribute(Const.USER_ID);

		if (userId == null) {
			map.put("result", "NotExist");
		} else {
			map.put("result", "Exist");
		}

		String jsonData = makeJsonData(map);

		return jsonData;
	}
}
