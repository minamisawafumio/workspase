package jp.co.fm.businessLogic.system;

import org.apache.ibatis.session.SqlSession;

import com.mongodb.client.MongoCollection;

import jp.co.fm.businessLogic.table.T_0001;

public interface SystemDb {

	/**
	 *
	 * @param key1
	 * @param key2
	 * @param key3
	 * @param value
	 * @return
	 */
	public T_0001 makeT_0001(String key1, String key2, String key3, String value);

	/**
		 * 汎用データ取得
		 * @param sqlSession
		 * @param key
		 * @return
		 */
	public T_0001 selectT_0001(String key1, String key2, String key3);

	/**
	   *
	   * @param key1
	   * @param key2
	   * @param key3
	   * @return
	   */
	public String selectLargeData(String key1, String key2, String key3);

	/**
	    *
	    * @param sqlSession
	    * @param key1
	    * @param key2
	    * @param key3
	    * @return
	    */
	public T_0001 selectT_0001(MongoCollection coll, String key1, String key2, String key3);

	public T_0001 selectT_0001(SqlSession sqlSession, String key1, String key2, String key3);
	/**
	    *
	    * @param sqlSession
	    * @param key1
	    * @param key2
	    * @param key3
	    * @return
	    */
	public String selectLargeData(SqlSession sqlSession, String key1, String key2, String key3);

	/**
	 *
	 * @param sqlSession
	 * @param key1
	 * @param key2
	 * @param key3
	 * @param value
	 * @param primaryKey
	 * @throws Exception
	 */
	public void updateLargeData(SqlSession sqlSession, String key1, String key2, String key3,
			String value, String[] primaryKey);


	public SystemDb systemDb = new SystemDbImpl();

	public static SystemDb getInstance() {
		return systemDb;
	}
}