package jp.co.fm.businessLogic.common;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

public interface DbUtil {

	public SqlSession getNewSqlSession();

	/**
	 * テーブルオブジェクトの項目に値を設定して返す
	 * @param _object
	 * @param _map
	 * @return
	 */
	public Object mekeObject(Object _object, Map<String, Object> _map);

	/**
	 * 文字列をハッシュ化して返す（MySQLの関数を利用する)
	 * @param moji
	 * @return
	 */
	public String sha512(String moji);

	/**
	 *
	 * @param sqlSession
	 * @param inObject
	 * @param primaryKey
	 * @param sortKey
	 * @return
	 */
	public List<Object> select(SqlSession sqlSession, Object inObject, String[] primaryKey, String sortKey);

	/**
	 *
	 * @param object
	 * @return
	 */
	public List<Object> selectNotNullMember(Object object);

	/**
	 *
	 * @param object
	 * @return
	 */
	public String getSelectSqlNotNullMember(Object object);

	/**
	 *
	 * @param sqlSession
	 * @param object
	 * @param sortKey
	 * @return
	 */
	public List<Object> selectNotNullMember(SqlSession sqlSession, Object object, String sortKey);

	/**
	 *
	 * @param sqlSession
	 * @param object
	 * @param sql
	 * @return
	 */
	public List<Object> select(SqlSession sqlSession, Object object, String sql);

	/**
	 *
	 * @param sqlSession
	 * @param object
	 * @return
	 */
	public List<Object> selectNotNullMember(SqlSession sqlSession, Object object);

	/**
	 * オブジェクトを検索する(最初の１件）
	 * @param inObject
	 * @param sql
	 * @return
	 */
	public Object selectFirstOneRec(Object inObject, String sql);

	/**
	 * オブジェクトを検索する(最初の１件）
	 * @param sqlSession
	 * @param inObject
	 * @param sql
	 * @return
	 */
	public Object selectFirstOneRec(SqlSession sqlSession, Object inObject, String sql);

	/**
	 *
	 * @param sql
	 * @return
	 */
	public Long selectCount(String sql);

	/**
	 * レコード件数取得
	 * @param sqlSession
	 * @param inObject
	 * @param sql
	 * @return
	 */
	public Long selectCount(SqlSession sqlSession, String sql);

	/**
	 * 検索SQL文取得
	 * @param object
	 * @param itemName
	 * @return
	 */
	public String getSelectSql(Object object, String[] itemName);


	public String[] changeCamelArrayToSnakeArray(String []camenArray);

	/**
	 * 検索SQL文取得(for Update)
	 * @param object
	 * @param itemName
	 * @return
	 */
	public String getSelectForUpdateSql(Object object, String[] itemName);

	public String getSelectSql(Object object);

	/**
	 * 件数検索SQL文取得
	 * @param object
	 * @param itemName
	 * @return
	 */
	public String getSelectCountSql(Object object, String[] itemName);

	/**
	 * 削除SQL取得
	 * @param object
	 * @param primaryKey
	 * @return
	 */
	public String getDeleteSqlNotNullMember(Object object);

	/**
	 *
	 * @param sqlSession
	 * @param object
	 * @throws Exception
	 */
	public void delete(SqlSession sqlSession, Object object) throws Exception;

	/**
	 *
	 * @param sqlSession
	 * @param object
	 * @return
	 * @throws Exception
	 */
	public Integer insert(SqlSession sqlSession, Object object) throws Exception;

	/**
	 *
	 * @param object
	 * @param primaryKey
	 * @return
	 */
	public Integer upsert(Object object, String[] primaryKey) throws Exception;

	/**
	 * 有れば更新無ければ追加
	 * @param sqlSession
	 * @param object
	 * @param primaryKey
	 * @return
	 */
	public Integer upsert(SqlSession sqlSession, Object object, String[] primaryKey) throws Exception;

	/**
	 *
	 * @param primaryKey
	 * @param inObj
	 * @return
	 */
	public String getUpdateSql(String[] primaryKey, Object inObj);

	public String getDeleteSql(String[] primaryKey, Object inObj);


	/**
	 *
	 * @param obj
	 * @return
	 */
	public String getWhereNotNullMemberPK(Object obj);

	/**
	 * SQLのwhere句を取得する
	 * @param obj
	 * @param itemName
	 * @return
	 */
	public String getWhereMember(Object obj, String[] itemName);

	/**
	 *
	 * @param obj
	 * @return
	 */
	public String getWhereNotNullMember(Object obj);

	public String getInsertSqlNotNullMember(Object obj);

	/**
	 * テーブル名を取得する
	 * "T9980"   ⇒ "T_9980"
	 * "T__9980" ⇒ "T_9980"
	 * @param obj
	 * @return
	 */
	public String getTableName(Object obj);

	/**
	 * 指定キーの項目情報(項目名 + TAB + 値の文字列)をリストで返す
	 * @param obj
	 * @param itemName 項目名
	 * @return
	 */
	public List<String> getKoumokuList(Object obj, String[] itemName);

	/**
	 *
	 * @param inList
	 * @return
	 */
	public List<String> getNotNullKoumokuList(List<String> inList);

	/**
	 * 指定キー以外の項目情報(項目名 + TAB + 値の文字列)をリストで返す
	 * @param obj
	 * @param key
	 * @return
	 */
	public List<String> getOtherKoumokuList(Object obj, String[] key);

	/**
	 * 項目リスト((項目名+ TAB + 値)の文字列)取得
	 * @param obj
	 * @return
	 */
	public List<String> getItemValueList(Object obj);

	/**
	 * SQLのwhere句を取得する
	 * @param list
	 * @return
	 */
	public String getWhereKu(List<String> list);

	/**
	 * SQLのwhere句を取得する(項目内容がnull以外)
	 * @param list
	 * @return
	 */
	public String getWhereNotNullMember(List<String> list);

	/**
	 * オブジェクトの内容をコンソールに出力する
	 * @param _object
	 */
	public void print(Object _object);

	/**
	 *
	 * @param _object
	 */
	public void print2(Object _object);

	public DbUtil du = new DbUtilImpl();

	public static DbUtil getInstance() {
		return du;
	}
}