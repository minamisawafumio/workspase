package jp.co.fm.businessLogic.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import jp.co.fm.businessLogic.table.T_1010;


public interface MangaDb {

	public SqlSession getSqlSession();

	public void closeSqlSession();

	public Map<String, Object> getPics(SqlSession sqlSession, Map<String, Object> inMap);

	/**
	 * マンガ番号タイトルマップ取得
	 * @return
	 */
	Map<String, Integer> getNumTittleMap();

	/**
	 * マンガタイトルマップ取得
	 * @return
	 */
	Map<Integer, String> getTittleMap();

	Map<String, Integer> getTittleNameMap();

	/**
	 *
	 * @param key
	 * @return
	 */
	String getWhereKu(String key, String ijyouKensuuKey);

	/**
	 *
	 * @param key
	 * @param ijyouKensuuKey
	 * @return
	 */
	String getSelectSql(String key, String ijyouKensuuKey);

	/**
	 *
	 * @param key
	 * @param ijyouKensuuKey
	 * @return
	 */
	String getSelectCountSql(String key, String ijyouKensuuKey);

	/**
	* マンガタイトルのリストを取得する(要素は、マンガ件数 + タブ + マンガタイトル)
	* @return
	*/
	List<String> getTittleList(Map<String, String> mangaFrontMap);


	T_1010 getMangaRec(SqlSession sqlSession, String bookNo);

	/**
	 *
	 * @param bookNo
	 * @return
	 */
	T_1010 getMangaRec(String bookNo);

	/**
	 *
	 * @param bookNo
	 * @return
	 */
	Long countMangaPageSuu(T_1010 t_1010);

	/**
	 * 画像の件数を取得する
	 * @param bookNo
	 * @return
	 */
	Long count_T_0001(String bookNo);

	Long count_T_0001(SqlSession sqlSession, String bookNo);

	public MangaDb mangaDb = new MangaDbImpl();

	public static MangaDb getInstacce() {
		return mangaDb;
	}
}