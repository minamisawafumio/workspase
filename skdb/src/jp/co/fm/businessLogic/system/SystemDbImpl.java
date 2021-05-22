package jp.co.fm.businessLogic.system;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoCollection;

import jp.co.fm.businessLogic.common.BeanUtil;
import jp.co.fm.businessLogic.common.DbUtil;
import jp.co.fm.businessLogic.common.DbUtilMongo;
import jp.co.fm.businessLogic.table.DbToolMapper;
import jp.co.fm.businessLogic.table.T_0001;

public class SystemDbImpl implements SystemDb {

	/**
	 *
	 * @param key1
	 * @param key2
	 * @param key3
	 * @param value
	 * @return
	 */
	@Override
	public T_0001 makeT_0001(String key1, String key2, String key3, String value) {

		Map<String, Object> map = new HashMap<>();

		map.put("key1"	, key1);
		map.put("key2"	, key2);
		map.put("key3"	, key3);
		map.put("value"	, value);

		T_0001 t_0001 = (T_0001) BeanUtil.getInstance().makeBean(new T_0001(), map);

		return t_0001;
	}

	/**
	 * 汎用データ取得
	 * @param sqlSession
	 * @param key
	 * @return
	 */
   @Override
   public T_0001 selectT_0001(String key1, String key2, String key3) {
		Map<String, Object> map = new HashMap<>();

		map.put("key1"	, key1);
		map.put("key2"	, key2);
		map.put("key3"	, key3);

		T_0001 t_0001 = (T_0001) BeanUtil.getInstance().makeBean(new T_0001(), map);

    	String sql = DbUtil.getInstance().getSelectSql(t_0001, SystemConst.PM_KEY_T_0001);

    	t_0001 = (T_0001) DbUtil.getInstance().selectFirstOneRec(t_0001, sql);

		return t_0001;
   }

   /**
   *
   * @param key1
   * @param key2
   * @param key3
   * @return
   */
  @Override
  public String selectLargeData(String key1, String key2, String key3) {
      SqlSession sqlSession = DbUtil.getInstance().getNewSqlSession();

	   String largeData = selectLargeData(sqlSession, key1, key2, key3);
	   sqlSession.close();
	   return largeData;

  }

   /**
    *
    * @param sqlSession
    * @param key1
    * @param key2
    * @param key3
    * @return
    */
   @Override
   public T_0001 selectT_0001(SqlSession sqlSession, String key1, String key2, String key3) {
		Map<String, Object> map = new HashMap<>();

		map.put("key1"	, key1);
		map.put("key2"	, key2);
		map.put("key3"	, key3);

		T_0001 t_0001 = (T_0001) BeanUtil.getInstance().makeBean(new T_0001(), map);

    	String sql = DbUtil.getInstance().getSelectSql(t_0001, SystemConst.PM_KEY_T_0001);

    	t_0001 = (T_0001) DbUtil.getInstance().selectFirstOneRec(sqlSession, t_0001, sql);

		return t_0001;
   }

   @Override
   public T_0001 selectT_0001(MongoCollection coll, String key1, String key2, String key3) {

	   T_0001 t_0001 = new T_0001();

       BasicDBObject query = new BasicDBObject();

       query.put("key1", key1);
       query.put("key2", key2);
       query.put("key3", key3);

       List<Object> list = DbUtilMongo.getInstance().getDataList(coll, query);

       String data = (String) list.get(0);

       System.out.println(data);

	   return t_0001;
   }

   /**
    *
    * @param sqlSession
    * @param key1
    * @param key2
    * @param key3
    * @return
    */
   @Override
   public String selectLargeData(SqlSession sqlSession, String key1, String key2, String key3) {

	   T_0001 t_0001 = selectT_0001(sqlSession, key1, key2, key3);

	   if(t_0001 == null) {
		   return "";
	   }
	   return t_0001.getValue();
   }

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
	@Override
	public void updateLargeData(SqlSession sqlSession, String key1, String key2, String key3,
																String value, String []primaryKey) {
		T_0001 t_0001 = SystemDb.getInstance().makeT_0001(key1, key2, key3, value);

        String sql = DbUtil.getInstance().getUpdateSql(primaryKey, t_0001);
        sqlSession.getMapper(DbToolMapper.class).update(sql);
	}
}
