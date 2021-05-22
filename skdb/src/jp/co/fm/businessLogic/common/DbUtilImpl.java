package jp.co.fm.businessLogic.common;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import jp.co.fm.businessLogic.table.DbToolMapper;


public class DbUtilImpl implements DbUtil {

    @Override
	public SqlSession getNewSqlSession(){
    	String systemPath = FileUtil.getInstance().getSystemPath();
    	InputStream in = null;
		try {
			in = new FileInputStream(systemPath + "/workspace/skdb/src/jp/co/fm/data/mybatis-config.xml");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(in);
        SqlSession session = factory.openSession();
        session.getConfiguration().addMapper(DbToolMapper.class);
        return session;
	}

    /**
	 * テーブルオブジェクトの項目に値を設定して返す
	 * @param _object
	 * @param _map
	 * @return
	 */
	@Override
	public Object mekeObject(Object _object, Map<String, Object> _map){

		Field []fields = _object.getClass().getDeclaredFields();

		Object obj = null;

		for(int i =0; i < fields.length; i++){

			String fieldName = fields[i].getName();

			fieldName = StringUtil.getInstance().changeCamelItem2UnderItem(fieldName);

			obj = _map.get(fieldName);

			if(obj != null){
				try {
					fields[i].setAccessible(true);
					fields[i].set(_object, obj);
				} catch (IllegalArgumentException | IllegalAccessException e) {
					e.printStackTrace();
				}
			}
		}

		return _object;
	}

	/**
	 * 文字列をハッシュ化して返す（MySQLの関数を利用する)
	 * @param moji
	 * @return
	 */
	@Override
	public String sha512(String moji){
		Map<String, Object> m = null;

		SqlSession sqlSession = getNewSqlSession();

		try {
	        DbToolMapper db = sqlSession.getMapper(DbToolMapper.class);

	        String sql = "SELECT SHA2('" + moji + "', 512) AS " + Const.HASH_DATA;

	        List<Map<String, Object>> rset = db.select(sql);

	        m = rset.get(0);
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			sqlSession.close();
		}

        return (String) m.get(Const.HASH_DATA);
	}

	/**
	 *
	 * @param sqlSession
	 * @param inObject
	 * @param primaryKey
	 * @param sortKey
	 * @return
	 */
	@Override
	public List<Object> select(SqlSession sqlSession, Object inObject, String []primaryKey, String sortKey) {
    	String sql = getSelectSql(inObject, primaryKey) + " " + sortKey;

        DbToolMapper db = sqlSession.getMapper(DbToolMapper.class);

        List<Map<String, Object>> rset = db.select(sql);

        if(rset.isEmpty()){
        	return null;
        }

        List<Object> rtnList = new ArrayList<>();

        try {
            for(Map<String, Object> map: rset) {
                Object rtnObject = mekeObject(inObject.getClass().newInstance(), map);
                rtnList.add(rtnObject);
            }
        }catch(Exception e) {
        	e.printStackTrace();
        }

        return rtnList;
	}

	/**
	 *
	 * @param object
	 * @return
	 */
	@Override
	public List<Object> selectNotNullMember(Object object){

		List<Object> objList = null;

		SqlSession sqlSession = getNewSqlSession();
		try {
			objList = selectNotNullMember(sqlSession, object);
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			sqlSession.close();
		}
		return objList;
	}

	/**
	 *
	 * @param object
	 * @return
	 */
	@Override
	public String getSelectSqlNotNullMember(Object object){
        StringBuffer sb = new StringBuffer();
        sb.append("select * from ");
        sb.append(object.getClass().getSimpleName());
        sb.append(getWhereNotNullMember(object));
        String sql = sb.toString();
        return sql;
	}

	/**
	 *
	 * @param sqlSession
	 * @param object
	 * @param sortKey
	 * @return
	 */
	@Override
	public List<Object> selectNotNullMember(SqlSession sqlSession, Object object, String sortKey){
        String sql = getSelectSqlNotNullMember(object) + " " + sortKey;

        return select(sqlSession, object, sql);
	}

	/**
	 *
	 * @param sqlSession
	 * @param object
	 * @param sql
	 * @return
	 */
	@Override
	public List<Object> select(SqlSession sqlSession, Object object, String sql){

        DbToolMapper db = sqlSession.getMapper(DbToolMapper.class);

        List<Map<String, Object>> rtnList = db.select(sql);

        List<Object> objList = new ArrayList<>();

        try {
            for(int cnt = 0; cnt < rtnList.size(); cnt ++){
            	Map map = rtnList.get(cnt);
                Object ob = mekeObject(object.getClass().newInstance(), map);
                objList.add(ob);
            }
        }catch(Exception e) {

        }

        return objList;
	}

	/**
	 *
	 * @param sqlSession
	 * @param object
	 * @return
	 */
    @Override
	public List<Object> selectNotNullMember(SqlSession sqlSession, Object object){
        return selectNotNullMember(sqlSession, object, "");
    }

	/**
	 * オブジェクトを検索する(最初の１件）
	 * @param sqlSession
	 * @param inObject
	 * @param sql
	 * @return
	 */
	@Override
	public Object selectFirstOneRec(SqlSession sqlSession, Object inObject, String sql) {
        DbToolMapper db = sqlSession.getMapper(DbToolMapper.class);

        List<Map<String, Object>> rset = db.select(sql);

        if(rset.isEmpty()){
        	return null;
        }

        Map<String, Object> m = rset.get(0);

		return mekeObject(inObject, m);
	}

	@Override
	public Object selectFirstOneRec(Object inObject, String sql) {
		SqlSession sqlSession = getNewSqlSession();
		Object obj = selectFirstOneRec(sqlSession, inObject, sql);
		sqlSession.close();
		return obj;
	}

	/**
	 *
	 * @param sql
	 * @return
	 */
	@Override
	public Long selectCount(String sql) {
		SqlSession sqlSession = getNewSqlSession();
		Long count = selectCount(sqlSession, sql);
		sqlSession.close();
		return count;
	}

	/**
	 * レコード件数取得
	 * @param sqlSession
	 * @param inObject
	 * @param sql
	 * @return
	 */
	@Override
	public Long selectCount(SqlSession sqlSession, String sql) {

        DbToolMapper db = sqlSession.getMapper(DbToolMapper.class);

        List<Map<String, Object>> rset = db.select(sql);

        if(rset.isEmpty()){
        	return null;
        }

        Map<String, Object> m = rset.get(0);

        Long count = (Long) m.get("count");

		return count;
	}

	/**
	 * 検索SQL文取得
	 * @param object
	 * @param itemName
	 * @return
	 */
    @Override
	public String getSelectSql(Object object, String []itemName){

        Object obj2 = JsonUtil.getInstance().objectCopy(object);

        String whereKu = getWhereMember(obj2, itemName);

        String tableName = getTableName(object);

        String selectSql = "select * from " + tableName + whereKu;

        return selectSql;
    }

    /**
     *
     * @param object
     * @param camenArray
     * @return
     */
    @Override
    public String[] changeCamelArrayToSnakeArray(String []camenArray){
    	String []rtnArray = new String [camenArray.length];

    	StringUtil su = StringUtil.getInstance();

    	for(int i = 0; i < camenArray.length ; i++) {
    		rtnArray[i] = su.changeCamelItem2UnderItem(camenArray[i]);
    	}

    	return rtnArray;
    }

    /**
     * 検索SQL文取得(for Update)
     * @param object
     * @param itemName
     * @return
     */
    @Override
	public String getSelectForUpdateSql(Object object, String []itemName){
    	String sql = getSelectSql(object, itemName);
    	return sql + " for update";
    }

    @Override
	public String getSelectSql(Object object){
        Object obj2 = JsonUtil.getInstance().objectCopy(object);

        String whereKu = getWhereNotNullMember(obj2);

        String tableName = getTableName(object);

        String selectSql = "select * from " + tableName + whereKu;

        return selectSql;
    }

    /**
     * 件数検索SQL文取得
     * @param object
     * @param itemName
     * @return
     */
    @Override
	public String getSelectCountSql(Object object, String []itemName){

        Object obj2 = JsonUtil.getInstance().objectCopy(object);

        String whereKu = getWhereMember(obj2, itemName);

        String tableName = getTableName(object);

        String selectSql = "select count(*) from " + tableName + whereKu;

        return selectSql;
    }

    /**
     * 削除SQL取得
     * @param object
     * @param primaryKey
     * @return
     */
    @Override
	public String getDeleteSqlNotNullMember(Object object){

        Object obj2 = JsonUtil.getInstance().objectCopy(object);

        String whereKu = getWhereNotNullMember(obj2);

        String tableName = getTableName(object);

        String deleteSql = "delete from " + tableName + whereKu;

        return deleteSql;
    }

    @Override
	public String getDeleteSql(String []primaryKey, Object object){
        Object obj2 = JsonUtil.getInstance().objectCopy(object);

        String whereKu = getWhereMember(obj2, primaryKey);

        String tableName = getTableName(object);

        String deleteSql = "delete from " + tableName + whereKu;

        return deleteSql;
    }

    /**
     *
     * @param sqlSession
     * @param object
     * @throws Exception
     */
    @Override
	public void delete(SqlSession sqlSession, Object object) throws Exception {
        String tableName = getTableName(object);
    	String whereKu = getWhereNotNullMember(object);
        String sql = "delete from " + tableName + whereKu;
        sqlSession.getMapper(DbToolMapper.class).delete(sql);
    }

    /**
     *
     * @param sqlSession
     * @param object
     * @return
     * @throws Exception
     */
    @Override
	public Integer insert(SqlSession sqlSession, Object object) throws Exception {
        String sql = getInsertSqlNotNullMember(object);
        return sqlSession.getMapper(DbToolMapper.class).insert(sql);
    }

    /**
     *
     * @param object
     * @param primaryKey
     * @return
     */
    @Override
	public Integer upsert(Object object, String []primaryKey) throws Exception {
    	Integer rtnInt = null;

		SqlSession sqlSession = getNewSqlSession();

		try {
			rtnInt = upsert(sqlSession, object, primaryKey);
			sqlSession.commit();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			sqlSession.close();
		}
    	return rtnInt;
    }

    /**
     * 有れば更新無ければ追加
     * @param sqlSession
     * @param object
     * @param primaryKey
     * @return
     */
    @Override
	public Integer upsert(SqlSession sqlSession, Object object, String []primaryKey) throws Exception{
    	Integer rtnInt = 0;

    	String sql = getSelectCountSql(object, primaryKey);
    	Long count = selectCount(sqlSession, sql);

    	if(count > 0) {
    		sql = getUpdateSql(primaryKey, object);
    		rtnInt = sqlSession.getMapper(DbToolMapper.class).update(sql);
    	} else {
    		rtnInt = insert(sqlSession, object);
    	}
    	return rtnInt;
    }

    /**
     *
     * @param primaryKey
     * @param inObj
     * @return
     */
    @Override
	public String getUpdateSql(String []primaryKey, Object inObj){
    	Object obj = BeanUtil.getInstance().copyBean(inObj);

    	String whereKu = getWhereMember(obj, primaryKey);

    	obj = BeanUtil.getInstance().beanSetNull(obj, primaryKey);

    	String updateSql = getUpdateSqlNotNullMember(obj) + whereKu;

    	return updateSql;
    }

    /**
     *
     * @param obj
     * @return
     */
	@Override
	public String getWhereNotNullMemberPK(Object obj){
		String methodName = "getId";

		Boolean bool = ReflectionUtil.getInstance().isExistMethodName(obj, methodName);

		if(! bool){
			return "";
		}

		Class[] argType = {};
        Object[] args   ={};
		Object objPK = null;
		try {
			objPK = ReflectionUtil.getInstance().invoke(obj, methodName, argType, args);
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException
				| SecurityException e) {
			e.printStackTrace();
		}
		return getWhereNotNullMember(objPK);
	}

	/**
	 * SQLのwhere句を取得する
	 * @param obj
	 * @param itemName
	 * @return
	 */
	@Override
	public String getWhereMember(Object obj, String []itemName){
		List<String> koumokuList = getKoumokuList(obj, itemName);
		return getWhereKu(koumokuList);
	}

	/**
	 *
	 * @param obj
	 * @return
	 */
	@Override
	public String getWhereNotNullMember(Object obj){
		List<String> list = getItemValueList(obj);
		return getWhereNotNullMember(list);
	}

	@Override
	public String getInsertSqlNotNullMember(Object obj){
		List<String> list = getItemValueList(obj);
		return getInsertSqlNotNullMember(obj, list);
	}

	/**
	 * 更新SQL文取得（オブジェクト項目がnullでない）
	 * @param object
	 * @return
	 */
	private String getUpdateSqlNotNullMember(Object object){
		String tableName = getTableName(object);

		List<String> koumokuList = getItemValueList(object);

		List<String> notNullKoumokuList = getNotNullKoumokuList(koumokuList);

		StringBuilder sb = new StringBuilder();
		sb.append(" update ");
		sb.append(tableName);
		sb.append(" set ");
		StringUtil su = StringUtil.getInstance();
		for (String st: notNullKoumokuList){
			String []data = st.split(Const.TAB);
			String koumokuName = su.changeCamelItem2UnderItem(data[0]);
			sb.append(koumokuName);
			sb.append("=");
			sb.append(data[1]);
			sb.append(",");
		}
		sb.delete(sb.length() - 1, sb.length());

		return sb.toString();
	}

	/**
	 * 追加SQL文作成
	 * @param object
	 * @param list
	 * @return
	 */
	private String getInsertSqlNotNullMember(Object object, List<String> list){

		String tableName = getTableName(object);

		StringBuilder sb = new StringBuilder();
		sb.append(" insert into ");
		sb.append(tableName);
		sb.append(" (");
		StringUtil su = StringUtil.getInstance();
		for (String st: list){
			String []data = st.split(Const.TAB);
			if(! data[1].equals("null")){
				String koumokuName = su.changeCamelItem2UnderItem(data[0]);
				sb.append(koumokuName + ",");
			}
		}
		sb.delete(sb.length() - 1, sb.length());
		sb.append(") VALUES (");

		for (String st: list){
			String []data = st.split(Const.TAB);
			if(! data[1].equals("null")){
				sb.append(data[1] + ",");
			}
		}
		sb.delete(sb.length() - 1, sb.length());
		sb.append(")");
		return sb.toString();
	}

	/**
	 * テーブル名を取得する
	 * "T9980"   ⇒ "T_9980"
	 * "T__9980" ⇒ "T_9980"
	 * @param obj
	 * @return
	 */
	@Override
	public String getTableName(Object obj){
		String tableName = obj.getClass().getSimpleName();
		tableName = tableName.replaceAll("T", "T_");
		tableName = tableName.replaceAll("T__", "T_");
		return tableName;
	}

	/**
	 * 指定キーの項目情報(項目名 + TAB + 値の文字列)をリストで返す
	 * @param obj
	 * @param itemName 項目名
	 * @return
	 */
	@Override
	public List<String> getKoumokuList(Object obj, String []itemName){
		List<String> rtnList = new ArrayList<>();

		Set<String> set = CommonUtil.getInstance().toSet(itemName);

		List<String> koumokuList = getItemValueList(obj);

		for(String data: koumokuList) {
			String array[] = data.split(Const.TAB);

			if(set.contains(array[0])) {
				rtnList.add(data);
			}
		}
		return rtnList;
	}

	/**
	 *
	 * @param inList
	 * @return
	 */
	@Override
	public List<String> getNotNullKoumokuList(List<String> inList){
		List<String> rtnList = new ArrayList<>();

		for(String data: inList) {
			String array[] = data.split(Const.TAB);

			if(! "null".equals(array[1])) {
				rtnList.add(data);
			}
		}
		return rtnList;
	}

	/**
	 * 指定キー以外の項目情報(項目名 + TAB + 値の文字列)をリストで返す
	 * @param obj
	 * @param key
	 * @return
	 */
	@Override
	public List<String> getOtherKoumokuList(Object obj, String []key){
		List<String> rtnList = new ArrayList<>();

		Set<String> set = CommonUtil.getInstance().toSet(key);

		List<String> koumokuList = getItemValueList(obj);

		for(String data: koumokuList) {
			String array[] = data.split(Const.TAB);

			if(! set.contains(array[0])) {
				rtnList.add(data);
			}
		}
		return rtnList;
	}

	/**
	 * 項目リスト((項目名+ TAB + 値)の文字列)取得
	 * @param obj
	 * @return
	 */
	@Override
	public List<String> getItemValueList(Object obj){

		 List<String> rtnList = new ArrayList<>();

		Class<? extends Object> className = obj.getClass();

		Field []fields = className.getDeclaredFields();

		StringUtil su = StringUtil.getInstance();
		ReflectionUtil ru = ReflectionUtil.getInstance();
		try {
			for (Field field: fields){
				String name = field.getName();

				Class[] argType3 = {};
	            Object[] args3  ={};
	            String nn = "get" + su.headUpperCase(name);
				Object obj2 = ru.invoke(obj, nn, argType3, args3);

				if(obj2 == null){
					rtnList.add(name + Const.TAB + "null");
				}else{
					String simpleName = obj2.getClass().getSimpleName();

					String data = obj2.toString();
					String dm = "";

					if ("String".equals(simpleName)){
						dm = "'";
					}else if ("Date".equals(simpleName)){
						dm = "'";
						data = data.replaceAll("-", "/");
					}else if ("Timestamp".equals(simpleName)){
						dm = "'";
						data = data.replace(".0", "");
						data = data.replaceAll("-", "/");
					}else if ("Double".equals(simpleName)){
						data = data.replace(".0", "");
					}
					rtnList.add(name + Const.TAB + dm + data + dm);
				}
			}

		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
		}
		return rtnList;
	}

	/**
	 * SQLのwhere句を取得する
	 * @param list
	 * @return
	 */
	@Override
	public String getWhereKu(List<String> list){
		StringBuilder sb = new StringBuilder();
		sb.append(" where ");
		StringUtil su = StringUtil.getInstance();

		for (String st: list){
			String []data = st.split(Const.TAB);
			String koumokuName = su.changeCamelItem2UnderItem(data[0]);
			sb.append(koumokuName + "=");
			sb.append(data[1] + " and ");
		}
		sb.delete(sb.length() - 5, sb.length());
		return sb.toString();
	}

	/**
	 * SQLのwhere句を取得する(項目内容がnull以外)
	 * @param list
	 * @return
	 */
	@Override
	public String getWhereNotNullMember(List<String> list){
		StringBuilder sb = new StringBuilder();
		sb.append(" where ");
		StringUtil su = StringUtil.getInstance();

		for (String st: list){
			String []data = st.split(Const.TAB);
			if(! data[1].equals("null")){
				String koumokuName = su.changeCamelItem2UnderItem(data[0]);
				sb.append(koumokuName + "=");
				sb.append(data[1] + " and ");
			}
		}
		sb.delete(sb.length() - 5, sb.length());
		return sb.toString();
	}

	private String getSetSql(List<String> list){
		StringBuilder sb = new StringBuilder();
		sb.append(" set ");
		StringUtil su = StringUtil.getInstance();

		for (String st: list){
			String []data = st.split(Const.TAB);
			String koumokuName = su.changeCamelItem2UnderItem(data[0]);
			sb.append(koumokuName + "=");
			sb.append(data[1] + ",");
		}
		sb.delete(sb.length() - 1, sb.length());
		sb.append(" ");
		return sb.toString();
	}

	/**
	 * リストよりNull情報を含む
	 * @param list
	 * @return
	 */
	private List<String> deleteNullData(List<String> list){
		List<String> rtnList = new ArrayList<>();

		for(String st: list) {
			String []data = st.split(Const.TAB);
			if(! data[1].equals("null")){
				rtnList.add(st);
			}
		}
		return rtnList;
	}

	/**
	 *
	 * @param list
	 * @return
	 */
	private String getSetSqlNotNullMember2(List<String> list){
		StringBuilder sb = new StringBuilder();
		sb.append(" set ");
		StringUtil su = StringUtil.getInstance();

		for (String st: list){
			String []data = st.split(Const.TAB);
			if(! data[1].equals("null")){
				String koumokuName = su.changeCamelItem2UnderItem(data[0]);
				sb.append(koumokuName + "=");
				sb.append(data[1] + ",");
			}
		}
		sb.delete(sb.length() - 1, sb.length());
		sb.append(" ");
		return sb.toString();
	}

	/**
	 * オブジェクトの内容をコンソールに出力する
	 * @param _object
	 */
	@Override
	public void print(Object _object){
		Field []fields = _object.getClass().getDeclaredFields();
		ReflectionUtil ru = ReflectionUtil.getInstance();
		for(int i =0; i < fields.length; i++){
			String fieldName = fields[i].getName();
			Object obj = ru.getValueOfField(_object, fieldName);
			System.out.println(fieldName +" : " + obj);
		}
	}

	/**
	 *
	 * @param _object
	 */
	@Override
	public void print2(Object _object){
		String jsonString = JsonUtil.getInstance().makeObjectToJsonString(_object);
		System.out.println(jsonString);
	}
}
