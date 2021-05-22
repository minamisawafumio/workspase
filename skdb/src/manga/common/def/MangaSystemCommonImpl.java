package manga.common.def;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoCollection;

import jp.co.fm.businessLogic.common.DbUtilMongo;
import jp.co.fm.businessLogic.common.FileUtil;
import jp.co.fm.businessLogic.common.JsonUtil;
import jp.co.fm.businessLogic.system.SystemDb;
import jp.co.fm.businessLogic.system.SystemService;
import jp.co.fm.businessLogic.table.T_0001;

class MangaSystemCommonImpl implements MangaSystemCommon {

	/**
	 * マンガタイトルのマップを取得する
	 * @return
	 */
	@Override
	public Map<Integer, String> makeTittleMap() {
		Map<Integer, String> map = new HashMap<>();

		List<Object[]> list = makeTittleList2();

		for(Integer i = 0; i < list.size(); i++) {
			Object []data = (Object [])list.get(i);

			map.put(i, (String) data[0]);
		}
		return map;
	}

	/**
	 *
	 * @param serchKey
	 * @return
	 */
	@Override
	public Map<Integer, String> getTittleMap(String serchKey) {
		Map<Integer, String> map = new HashMap<>();
		List<String> tittleList;

		tittleList = new ArrayList<>();

		map.put(new Integer(100), "あああああああ");

		return map;
	}

	/**
	 *
	 * @param userId
	 * @return
	 */
	@Override
	public T_0001 select_T_0001(String userId) {

		String key1 = "0002";
		String key2 = "0";
		String key3 = userId;

		T_0001 t_0001 = SystemDb.getInstance().selectT_0001(key1, key2, key3);

		return t_0001;
	}

	@Override
	public List<Object> get_T0000_from_mongoDB(MongoCollection coll, String key1, String key2, String key3) {
		BasicDBObject query = new BasicDBObject();

		query.put("key1", key1);
		query.put("key2", key2);
		query.put("key3", key3);

		List<Object> list = DbUtilMongo.getInstance().getDataList(coll, query);

		return list;
	}

	/**
	 * マンガユーザ情報Map取得
	 * @param userId
	 * @return
	 */
	@Override
	public Map<String, Object> getMap(String userId) {
		String key1 = "0002";
		String key2 = "0";
		String key3 = userId;
		return SystemService.getInstance().getMap_T_0001(key1, key2, key3);
	}

    @Override
	public List<Object[]> makeTittleList2() {
       	List<Object[]> keyList = new ArrayList<>();

       	keyList  = getTittleList(keyList);

       	SystemService ss = SystemService.getInstance();

       	return keyList;
    }

    @Override
	public List<Object[]> getTittleList(List<Object[]> keyList) {

		FileUtil fui = FileUtil.getInstance();

		String path  =  fui.getSystemPath();

		String pathFileName = path + "workspace/skdb/WebContent/WEB-INF/temp/mangaData.json";

		String st = null;
		try {
			st = fui.file2string2(pathFileName);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		JsonUtil jul = JsonUtil.getInstance();

		List<String[]>list = jul.makeListFromJson(st);

		keyList.addAll(list);


		return keyList;
    }



}
