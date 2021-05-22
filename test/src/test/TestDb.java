package test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.bson.Document;
import org.junit.Test;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import jp.co.fm.businessLogic.common.BeanUtil;
import jp.co.fm.businessLogic.common.Const;
import jp.co.fm.businessLogic.common.DateUtil;
import jp.co.fm.businessLogic.common.DbUtil;
import jp.co.fm.businessLogic.common.FileUtil;
import jp.co.fm.businessLogic.common.JsonUtil;
import jp.co.fm.businessLogic.common.StringUtil;
import jp.co.fm.businessLogic.service.StartupServlet;
import jp.co.fm.businessLogic.system.SystemConst;
import jp.co.fm.businessLogic.system.SystemDb;
import jp.co.fm.businessLogic.system.SystemInfo;
import jp.co.fm.businessLogic.table.DbToolMapper;
import jp.co.fm.businessLogic.table.T_0001;
import jp.co.fm.businessLogic.table.T_0160;
import jp.co.fm.businessLogic.table.T_0161;
import jp.co.fm.businessLogic.table.T_1010;

public class TestDb {

	DbUtil du = DbUtil.getInstance();

	@Test
    public void testMapToJsonToMap2() {

		Map<String, Object> mapAll = new HashMap<String, Object>();

		Map<String, Object> map = new HashMap<String, Object>();

		List<Object> list = new ArrayList<Object>();
		list.add("11aaaaaa");
		list.add("11bbbbbb");
		list.add("11cccccc");
		list.add("11dddddd");
		map.put("11", list);

		list = new ArrayList<Object>();
		list.add("22aああああaaaaa");
		list.add("22bb２３４bbささ 　bb");
		list.add(2345776.456);
		list.add(27656678903.547655);
		map.put("22", list);

		mapAll.put("001", map);

		String js = JsonUtil.getInstance().makeObjectToJsonString(mapAll);

		System.out.println(js);

		Map<String, Object> map2 = (Map<String, Object>) JsonUtil.getInstance().makeJsonStringToObject(js, new HashMap<String, Object>().getClass());

		Map<String, Object> mapA = (Map<String, Object>) map2.get("001");

		List<Object> list3 = (List<Object>) mapA.get("22");

		for(Object data:list3) {
			System.out.println(data);
		}

		BigDecimal jjj = ((BigDecimal)list3.get(2)).multiply((BigDecimal)list3.get(3));
		System.out.println("answer=" + jjj);
	}

	@Test
    public void testMapToJsonToMap() {
		Map<String, List<Object>> map = new HashMap<String, List<Object>>();

		List<Object> list = new ArrayList<Object>();
		list.add("11aaaaaa");
		list.add("11bbbbbb");
		list.add("11cccccc");
		list.add("11dddddd");
		map.put("11", list);

		list = new ArrayList<Object>();
		list.add("22aああああaaaaa");
		list.add("22bb２３４bbささ 　bb");
		list.add(2345776.456);
		list.add(27656678903.547655);
		map.put("22", list);

		String js = JsonUtil.getInstance().makeObjectToJsonString(map);

		System.out.println(js);

		Map<String, List<Object>> map2 = (Map<String, List<Object>>) JsonUtil.getInstance().makeJsonStringToObject(js, new HashMap<String, List<Object>>().getClass());

		List<Object> list3 =  map2.get("22");

		for(Object data:list3) {
			System.out.println(data);
		}

		BigDecimal jjj = ((BigDecimal)list3.get(2)).multiply((BigDecimal)list3.get(3));
		System.out.println("answer=" + jjj);
	}

    @Test
    public void dbSelectBean() {

        SqlSession sqlSession = du.getNewSqlSession();

        DbToolMapper db = sqlSession.getMapper(DbToolMapper.class);

        List<Map<String, Object>> rset = db.select("select * from t_1010 limit 1");

        Map<String, Object> m = rset.get(0);

        T_1010 t_1010 = (T_1010) du.mekeObject(new T_1010(), m);

        BeanUtil.getInstance().printBean(t_1010);

        String sql = du.getSelectSql(t_1010, SystemConst.PM_KEY_T_1010);

        rset = db.select(sql);

        m = rset.get(0);

        t_1010 = (T_1010) du.mekeObject(t_1010, m);

        BeanUtil.getInstance().printBean(t_1010);

        sqlSession.close();
    }


    @Test
    public void dbSelectBean2() {

        SqlSession sqlSession = du.getNewSqlSession();

        DbToolMapper db = sqlSession.getMapper(DbToolMapper.class);

        List<Map<String, Object>> rset = db.select("select * from t_1010 limit 3");


        ObjectMapper mapper = new ObjectMapper();



	        for(int i = 0; i < rset.size(); i++) {
	        	Map<String, Object> m = rset.get(i);

	        	String jsonSt = JsonUtil.getInstance().makeObjectToJsonString(m);

				JsonNode node = JsonUtil.getInstance().makeJsonStringToJsonNode(mapper, jsonSt);

				String jsonSt2 = node.toString();

				if(jsonSt.contentEquals(jsonSt2)) {
					System.out.println("同じ");
				}



	            System.out.println(jsonSt2);

				JsonNode jsonNode3 = JsonUtil.getInstance().makeJsonStringToJsonNode(mapper,jsonSt2);


	            System.out.println(jsonNode3);

	        }



    	System.out.println("------------------------------------------------------");


        Map<String, Object> m = rset.get(0);

        T_1010 t_1010 = (T_1010) DbUtil.du.mekeObject(new T_1010(), m);

        BeanUtil.getInstance().printBean(t_1010);

        String sql = du.getSelectSql(t_1010, SystemConst.PM_KEY_T_1010);

        rset = db.select(sql);

        m = rset.get(0);

        t_1010 = (T_1010) du.mekeObject(t_1010, m);

        BeanUtil.getInstance().printBean(t_1010);

        sqlSession.close();
    }



	@Test
	public void bbbb0002(){

		String comDateTime = DateUtil.getInstance().getComDateTime(5);

		String pswd = "uuuu";

		String id = "5555";

		//パスワードハッシュ化
		pswd = StringUtil.getInstance().encryption(pswd, Const.CODE_SHA_512);

		Map<String, Object> map = new HashMap<>();

		map.put("corpCd"	, Const.CORP_CD);
		map.put("userId"	, "aa32aaa");
		map.put("setPswd"	, pswd);
		map.put("makeYmdhms", comDateTime);
		map.put("updtYmdhms", comDateTime);
		map.put("delFlg"	, "0");

		T_0160 t_0160 = (T_0160) BeanUtil.getInstance().makeBean(new T_0160(), map);

    	String jjj = JsonUtil.getInstance().makeObjectToJsonString(t_0160);

    	System.out.println(jjj);
	}



	@Test
	public void aaaaa0002(){

    	T_0160 t_0160 = new T_0160();
    	t_0160.setCorpCd("01");
    	t_0160.setUserId("a");
    	t_0160.setDelFlg("0");



    	String sql = DbUtil.getInstance().getSelectSql(t_0160, SystemConst.PM_KEY_T_0160);

    	t_0160 = (T_0160) DbUtil.getInstance().selectFirstOneRec(t_0160, sql);


//    	t_0160 = (T_0160) du.selectFirstOneRec(t_0160, SystemConst.PM_KEY_T_0160);

    	String jjj = JsonUtil.getInstance().makeObjectToJsonString(t_0160);

    	System.out.println(jjj);
    }

//	@Test
//	public void test_003() {
//        MockHttpServletRequest request = new MockHttpServletRequest();
//        MockHttpServletResponse response = new MockHttpServletResponse();
//
//        HttpSession httpSession = request.getSession();
//
//        FT001F01Form fT001F01Form = new FT001F01Form();
//
//        httpSession.setAttribute("fT001F01Form", fT001F01Form);
//
//        // パラメータをセットするなどテストに必要な準備を行う
//        request.setParameter("name", "value");
//
//        FT001F01 servlet = new FT001F01();
//
//        Model model = new ExtendedModelMap();
//        try {
//        	servlet.goto083(model, request);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//
//		System.out.println("111aaaaaaaaaaaaaaaaaaaaaaaaaaa");
//
//	}


	@Test
	public void test_T_1010_postgres_update() {
        SqlSession sqlSession = du.getNewSqlSession();

        DbToolMapper db = sqlSession.getMapper(DbToolMapper.class);

        for (Integer i=2; i < 7280; i++) {
        	System.out.print(i);
    		boolean bool = test_T_1010_postgres_update(sqlSession, db, i);
    		System.out.println(bool);
        }

		sqlSession.close();

	}


	public boolean test_T_1010_postgres_update(SqlSession sqlSession, DbToolMapper db, int suu) {

		boolean rtnBool = true;

		StringUtil su = StringUtil.getInstance();

		String suuSt = su.changeFormat("0000", suu);

    	String selectSql = "select * from T_1010 where rec_cd='014' and item01='" + suuSt + "'";

    	try {

        	String []primaryKey = {"corpCd","delFlg","recCd","startYmd","item01"};

            T_1010 t_1010_insert = null;
            T_1010 t_1010_delete = null;

            List<Map<String, Object>> rset = db.select(selectSql);

            String sql = null;
            String suuSt2 = null;
            for(Map<String, Object> map : rset) {

            	t_1010_insert = (T_1010) du.mekeObject(new T_1010(), map);
            	t_1010_delete = (T_1010) du.mekeObject(new T_1010(), map);

            	String item01 = t_1010_insert.getItem01();
            	suuSt2 = su.changeFormat("000000", Integer.parseInt(item01));
            	t_1010_insert.setItem01(suuSt2);
            	sql = du.getInsertSqlNotNullMember(t_1010_insert);
                db.insert(sql);

                sql = du.getDeleteSql(primaryKey, t_1010_delete);
                db.delete(sql);
            	continue;
            }
            sqlSession.commit();
    	}catch(Exception e) {
    		sqlSession.rollback();
    		e.printStackTrace();
    		return false;
    	}

    	return rtnBool;
	}

	@Test
	public void test_T_000_postgres_update() {
        SqlSession sqlSession = du.getNewSqlSession();

        DbToolMapper db = sqlSession.getMapper(DbToolMapper.class);

        for (Integer i=3496; i < 5000; i++) {
        	System.out.print(i);
    		boolean bool = test_T_000_postgres_update(sqlSession, db, i);
    		System.out.println(bool);
        }

		sqlSession.close();

	}

	public boolean test_T_000_postgres_update(SqlSession sqlSession, DbToolMapper db, int suu) {

		boolean rtnBool = true;

		StringUtil su = StringUtil.getInstance();

		String suuSt =su.changeFormat("000000", (long) suu);

    	String selectSql = "select * from T_0001 where key2='" + suuSt + "'";

    	try {

        	String []primaryKey = {"key1","key2", "key3"};

            T_0001 t_0001 = null;

            List<Map<String, Object>> rset = db.select(selectSql);

            String updateSql = null;

            for(Map<String, Object> map : rset) {

            	t_0001 = (T_0001) du.mekeObject(new T_0001(), map);

            	String value = t_0001.getValue();

            	if (su.isExistCRLF(value)) {
            		String deleteValue = su.deleteCRLF(value);
            		t_0001.setValue(deleteValue);
            		updateSql = du.getUpdateSql(primaryKey, t_0001);
                    db.update(updateSql);
            		continue;
            	}else {
            		return false;
            	}
            }
            sqlSession.commit();
    	}catch(Exception e) {
    		sqlSession.rollback();
    		e.printStackTrace();
    		return false;
    	}

    	return rtnBool;
	}

//	@Test
//	public void test_T_000_to_T_001_insert() {
//        SqlSession sqlSession = du.getNewSqlSession();
//
//        DbToolMapper db = sqlSession.getMapper(DbToolMapper.class);
//
//        for (Integer i=6600; i < 7280; i++) {
//        	System.out.print(i);
//    		boolean bool = test_postgres_T_000_to_T_001(sqlSession, db, i);
//    		System.gc();
//    		System.out.println(bool);
//        }
//
//		sqlSession.close();
//	}

//	public boolean test_postgres_T_000_to_T_001(SqlSession sqlSession, DbToolMapper db, int suu) {
//
//		boolean rtnBool = true;
//
//		StringUtil su = StringUtil.getInstance();
//
//		String suuSt =su.changeFormat("0000", suu);
//
//    	String selectSql = "select * from T_0000 where key2='" + suuSt + "'";
//
//        T_0001 t_0001 = null;
//        T_0001 t_0001 = null;
//        String deleteValue = null;
//        String key2St = null;
//        List<Map<String, Object>> rset = db.select(selectSql);
//
//    	try {
//            for(Map<String, Object> map : rset) {
//
//            	t_0000 = (T_0000) du.mekeObject(new T_0000(), map);
//
//            	t_0001 = new T_0001();
//               	t_0001.setKey1(t_0000.getKey1());
//               	key2St = su.changeFormat("000000", t_0000.getKey2());
//               	t_0001.setKey2(key2St);
//               	t_0001.setKey3(t_0000.getKey3());
//               	deleteValue = su.deleteCRLF(t_0000.getValue());
//               	t_0001.setValue(deleteValue);
//    			du.insert(sqlSession, t_0001);
//            }
//            sqlSession.commit();
//    	}catch(Exception e) {
//    		sqlSession.rollback();
//    		e.printStackTrace();
//    		return false;
//    	}
//
//    	return rtnBool;
//	}





	@Test
	public void test_T_000_postgres_to_mongoDb() {
        // MongoDBクライントを生成する
        MongoClient client = new MongoClient("localhost", 27017);
        // DBオブジェクトを取得する
        MongoDatabase mongoDb = client.getDatabase("db0003");
        // ユーザーコレクションを取得する
        MongoCollection<Document> users = mongoDb.getCollection("documents");

//    	String sql = "select * from T_0160 where corp_cd='01' and user_id='aa32aaa' and del_flg='0'";
    	String sql = "select * from T_0001 where key2='7033'";

        SqlSession sqlSession = du.getNewSqlSession();
        DbToolMapper db = sqlSession.getMapper(DbToolMapper.class);



    	try {

            T_0001 t_0001 = null;

            List<Map<String, Object>> rset = db.select(sql);

            for(Map<String, Object> map : rset) {

            	t_0001 = (T_0001) du.mekeObject(new T_0001(), map);


                // ユーザーの属性をセット
                Document user = new Document();
                user.append("key1",  t_0001.getKey1());
                user.append("key2",  t_0001.getKey2());
                user.append("key3",  t_0001.getKey3());
                user.append("value", t_0001.getValue());
                // DBにインサート
                users.insertOne(user);
            }
    	}catch(Exception e) {
    		e.printStackTrace();
    	}finally {
            // クライントを閉じる
            client.close();
    		sqlSession.close();
    	}
	}



	@Test
	public void test_002() {

//    	String sql = "select * from T_0160 where corp_cd='01' and user_id='aa32aaa' and del_flg='0'";
    	String sql = "select * from T_0160 where corp_cd='01' and del_flg='0'";

        SqlSession sqlSession = du.getNewSqlSession();

    	try {
            DbToolMapper db = sqlSession.getMapper(DbToolMapper.class);

            T_0160 t_USER = null;

            List<Map<String, Object>> rset = db.select(sql);

            for(Map<String, Object> map : rset) {

            	t_USER = (T_0160) du.mekeObject(new T_0160(), map);

            	du.print(t_USER);

            	du.print2(t_USER);
            }
    	}catch(Exception e) {
    		e.printStackTrace();
    	}finally {
    		sqlSession.close();
    	}
	}


	@Test
	public void test_update() {

		String updtYmdhms = DateUtil.getInstance().getComDateTime(5);

		Map<String, Object> map = new HashMap<>();

		map.put("corpCd"	, "01");
		map.put("delFlg"	, "0");
		map.put("userId"	, "aa32aaa");
		map.put("updtYmdhms", updtYmdhms);

		T_0160 t_0160 = (T_0160) BeanUtil.getInstance().makeBean(new T_0160(), map);

    	String []primaryKey = {"corpCd","userId", "delFlg"};

    	String sql = du.getUpdateSql(primaryKey, t_0160);

    	System.out.println(sql);

        SqlSession sqlSession = du.getNewSqlSession();

        DbToolMapper db = sqlSession.getMapper(DbToolMapper.class);

        db.update(sql);

        sqlSession.commit();

        //--------------------------------------------

        sql = du.getSelectSql(t_0160, primaryKey);

        List<Map<String, Object>> rset = db.select(sql);

        Map<String, Object> m = rset.get(0);

        T_0160 t_USER = (T_0160) du.mekeObject(new T_0160(), m);

        du.print(t_USER);

        du.print2(t_USER);

        sqlSession.close();
	}

	@Test
	public void test_select() {

		Map<String, Object> map = new HashMap<>();

		map.put("corpCd", "01");
		map.put("delFlg", "0");
		map.put("userId", "a");

		T_0160 t_0160 = (T_0160) BeanUtil.getInstance().makeBean(new T_0160(), map);

    	String []primaryKey = {"corpCd","userId", "delFlg"};

    	String sql = du.getSelectSql(t_0160, primaryKey);

 		SqlSession sqlSession = du.getNewSqlSession();

        DbToolMapper db = sqlSession.getMapper(DbToolMapper.class);

        T_0160 t_USER = null;

        try {
            List<Map<String, Object>> rset = db.select(sql);

            Map<String, Object> m = rset.get(0);



            if(rset.isEmpty()){

            } else {
            	t_USER = (T_0160) du.mekeObject(new T_0160(), m);
            }
        }catch(Exception e) {
        	e.printStackTrace();
        }finally {
        	sqlSession.close();
        }

        du.print(t_USER);

        du.print2(t_USER);
	}

	@Test
	public void test002() {
		T_0001 t_0001 = new T_0001();
		t_0001.setKey1("ffffffff");

		String []primaryKey = {"key"};

		String sql = du.getSelectSql(t_0001, primaryKey);

		System.out.println(sql);
	}

	@Test
	public void test001() {
		T_0001 t_0001 = new T_0001();
		t_0001.setKey1("ffffffff");

		String []primaryKey = {"key"};

		String sql = du.getDeleteSql(primaryKey, t_0001);

		System.out.println(sql);
	}

	@Test
	public void test_T_0001_Insert() {

		String file = "0002.jpg";

		String pathFile = "C:/temp/manga/" + file;

		String key = DateUtil.getInstance().getComDateTime(5);

		SqlSession sqlSession = du.getNewSqlSession();

		try {
			String value = FileUtil.getInstance().file2string(pathFile);
			T_0001 t_0001 = SystemDb.getInstance().makeT_0001(key, "7", "7", value);
			du.insert(sqlSession, t_0001);
			sqlSession.commit();
		} catch (Exception e) {
			sqlSession.rollback();
			e.printStackTrace();
		} finally {
			sqlSession.close();
		}
	}

	@Test
	public void testDelete() {


		SqlSession sqlSession = du.getNewSqlSession();

		T_0001 t_0001 = new T_0001();
		t_0001.setKey2("7");

        try {
    		du.delete(sqlSession, t_0001);
			sqlSession.commit();
		} catch (Exception e) {
			sqlSession.rollback();
			e.printStackTrace();
		} finally {
			sqlSession.close();
		}
	}



	@Test
	public void testSelect() {
		String key3 = "20190429115215655";
		T_0001 t_0001 = SystemDb.getInstance().selectT_0001("1","1" , key3);
		System.out.println(t_0001.getValue());
	}

	@Test
	public void testSelect2() {
		String key3 = "1";
		String value = SystemDb.getInstance().selectLargeData("2","0", "1");
		System.out.println(value);
	}


	@Test
	public void testInsert2() {

		StartupServlet startupServlet = new StartupServlet();

		try {
			startupServlet.init();
		} catch (Exception e1) {
			e1.printStackTrace();
		}

		String imgPath = (String) SystemInfo.getInstance().getValue(Const.WEB_INF_REAL_PATH) + "pic/";

		T_0001 t_0001 = new T_0001();
		t_0001.setKey1("ffffffff222566");
		t_0001.setValue("aaaaaaaaaaaaaaaaaaaaaaaaaaaaa2222222222222222222222222222222222222222222222222222aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");

        SqlSession sqlSession = DbUtil.getInstance().getNewSqlSession();

        String []primaryKey = {"key"};

        try {

        	String sql = DbUtil.getInstance().getDeleteSql(primaryKey, t_0001);

        	Integer count = sqlSession.getMapper(DbToolMapper.class).delete(sql);



//    		Integer count = DbUtil.getInstance().delete(sqlSession, t_0001, primaryKey);

            sqlSession.commit();
        }catch(Exception e) {
        	e.printStackTrace();
        	sqlSession.rollback();
        }

        sqlSession.close();
	}

	@Test
	public void testSelectCount() {

		Map<String, Object> map = new HashMap<String, Object>();

		map.put("corpCd"	, "01");
		map.put("delFlg"	, "0");
		map.put("userId"	, "aaaaa");

		T_0160 t_0160 = (T_0160) BeanUtil.getInstance().makeBean(new T_0160(), map);

    	String []primaryKey = {"corpCd","userId", "delFlg"};

		SqlSession sqlSession = du.getNewSqlSession();


    	String sql = du.getSelectCountSql(t_0160, primaryKey);
    	Long ll = du.selectCount(sqlSession, sql);
		sqlSession.close();



//    	Long ll = DbUtil.getInstance().selectCount(t_0160, primaryKey);

    	System.out.println(ll);
    }

	@Test
	public void test_getKoumokuList() {

		Map<String, Object> map = new HashMap<String, Object>();

		map.put("corpCd"		, "01");
		map.put("userId"		, "aaaaa");
		map.put("userIdSerialNo", "aaaaa");
		map.put("userKey"		, "aaaaa");
		map.put("delFlg"		, "0");

		T_0161 t_0161 = (T_0161) BeanUtil.getInstance().makeBean(new T_0161(), map);

		String ddd = DbUtil.getInstance().getWhereMember(t_0161, SystemConst.PM_KEY_T_0161);

		List<String> list = DbUtil.getInstance().getItemValueList(t_0161);

    	System.out.println();
	}

	@Test
	public void select_T_0161() {
        SqlSession sqlSession = DbUtil.getInstance().getNewSqlSession();

		String []itemName = {"corpCd","userId","userIdSerialNo","userKey","delFlg"};

		Map<String, Object> map = new HashMap<String, Object>();

		map.put("corpCd"		, "01");
		map.put("userId"		, "uuu");
		map.put("userIdSerialNo", 1);
		map.put("userKey"		, "1");
		map.put("delFlg"		, "0");

		T_0161 t_0161 = (T_0161) BeanUtil.getInstance().makeBean(new T_0161(), map);

    	String sql = du.getSelectSql(t_0161, itemName);

    	t_0161 = (T_0161) du.selectFirstOneRec(sqlSession, t_0161, sql);

    	sqlSession.close();

    	String hhh = JsonUtil.getInstance().makeObjectToJsonString(t_0161);

    	System.out.println(hhh);
	}


	@Test
	public void make_T_0161() {

        SqlSession sqlSession = DbUtil.getInstance().getNewSqlSession();

		Map<String, Object> map = new HashMap<String, Object>();

		map.put("corpCd"		, "01");
		map.put("userId"		, "uuu");
		map.put("userIdSerialNo", 1);
		map.put("userKey"	, "1");
		map.put("delFlg"	, "0");
		map.put("makeYmdhms", "00010101");
		map.put("updtYmdhms", "00010101");

		T_0161 t_0161 = (T_0161) BeanUtil.getInstance().makeBean(new T_0161(), map);

		//レコード作成
		try {
			DbUtil.getInstance().upsert(sqlSession, t_0161, SystemConst.PM_KEY_T_0161);
			sqlSession.commit();
		} catch (Exception e) {
			sqlSession.rollback();
			e.printStackTrace();
		}finally {
			sqlSession.close();
		}
	}


	@Test
	public void test77777() {

        SqlSession sqlSession = DbUtil.getInstance().getNewSqlSession();

        DbToolMapper db = sqlSession.getMapper(DbToolMapper.class);

        List<Map<String, Object>> rset = db.select("select * from t_1010 limit 1");

        Map<String, Object> m = rset.get(0);

        T_1010 t_1010 = (T_1010) DbUtil.getInstance().mekeObject(new T_1010(), m);

        String []primaryKey = {"corp_cd", "rec_cd", "start_ymd", "item01"};

        //PM_KEY_T_1010 = {"corpCd","delFlg","recCd","startYmd","item01"};

//	        String sql = DbUtil.getSelectSql(t_1010, primaryKey);
        String sql = DbUtil.getInstance().getSelectSql(t_1010, SystemConst.PM_KEY_T_1010);

        rset = db.select(sql);

        m = rset.get(0);

        T_1010 t_1010_2 = (T_1010) DbUtil.getInstance().mekeObject(t_1010, m);

        sqlSession.close();

        System.out.println();

	}
}
