package manga.common;

import java.util.List;
import java.util.Map;

import jp.co.fm.businessLogic.table.T_1010;

public interface VideoDb {

	/**
	 * マンガタイトルマップ取得
	 * @return
	 */
	public Map<Integer, String> getTittleMap();

	/**
	 *
	 * @param key
	 * @return
	 */
	public String getWhereKu(String key, String ijyouKensuuKey);

	/**
	 *
	 * @param key
	 * @param ijyouKensuuKey
	 * @return
	 */
	public String getSelectSql(String key, String ijyouKensuuKey);

	/**
	 *
	 * @param key
	 * @param ijyouKensuuKey
	 * @return
	 */
	public String getSelectCountSql(String key, String ijyouKensuuKey);

	/**
	 *
	 * @param sql
	 * @return
	 */
	public List<Object> getMangaList(String sql);

	/**
	 * マンガリスト取得
	 * @return
	 */
	public List<Object> getMangaList();

	/**
	* マンガタイトルのリストを取得する(要素は、マンガ件数 + タブ + マンガタイトル)
	* @return
	*/
	public List<String> getTittleList(Map<String, String> mangaFrontMap);

	/**
	 *
	 * @param bookNo
	 * @return
	 */
	public T_1010 getMangaRec(String bookNo);

	/**
	 *
	 * @param bookNo
	 * @return
	 */
	public Long countMangaPageSuu(T_1010 t_1010);

	/**
	 * 画像の件数を取得する
	 * @param bookNo
	 * @return
	 */
	public Long count_T_0001(String bookNo);

	public final VideoDb videoDb = new VideoDbImpl();

	public static VideoDb getInstance() {
		return videoDb;
	}

}