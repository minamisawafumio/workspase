package manga.common.def;

import java.util.List;
import java.util.Map;

import com.mongodb.client.MongoCollection;

import jp.co.fm.businessLogic.table.T_0001;

public interface MangaSystemCommon {

	/**
	 * マンガタイトルのマップを取得する
	 * @return
	 */
	Map<Integer, String> makeTittleMap();

	/**
	 *
	 * @param serchKey
	 * @return
	 */
	Map<Integer, String> getTittleMap(String serchKey);

	/**
	 *
	 * @param userId
	 * @return
	 */
	T_0001 select_T_0001(String userId);

	List<Object> get_T0000_from_mongoDB(MongoCollection coll, String key1, String key2, String key3);

	/**
	 * マンガユーザ情報Map取得
	 * @param userId
	 * @return
	 */
	Map<String, Object> getMap(String userId);

	List<Object[]> makeTittleList2();

	List<Object[]> getTittleList(List<Object[]> keyList);

	MangaSystemCommon mangaSystemCommon =  new MangaSystemCommonImpl();

	public static MangaSystemCommon getInstance() {
		return mangaSystemCommon;
	}





}