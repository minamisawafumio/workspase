package manga.test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import jp.co.fm.businessLogic.common.BeanUtil;
import jp.co.fm.businessLogic.common.DbUtil;
import jp.co.fm.businessLogic.common.FileUtil;
import jp.co.fm.businessLogic.common.StringUtil;
import jp.co.fm.businessLogic.service.MangaDb;
import jp.co.fm.businessLogic.system.SystemConst;
import jp.co.fm.businessLogic.system.SystemDb;
import jp.co.fm.businessLogic.system.SystemInfo;
import jp.co.fm.businessLogic.table.DbToolMapper;
import jp.co.fm.businessLogic.table.T_0001;
import jp.co.fm.businessLogic.table.T_1010;
import manga.common.def.MangaSystemCommon;

public class TestManga2 {

	private DbUtil du = DbUtil.getInstance();

	private BeanUtil bu = BeanUtil.getInstance();

    private SystemInfo si = SystemInfo.getInstance();

    private FileUtil fu = FileUtil.getInstance();


	@Test
	/**
	 * マンガデータ作成(タイトル指定実行）※事前にSystemCommon#makeTittleListを設定すること
	 */
	public void makeMangaPage111() {
		TestManga testManga = new TestManga();
		testManga.makeMangaPage();
	}

	@Test
	public void updateMangaSearchKey111() {
		TestManga testManga = new TestManga();
		testManga.updateMangaSearchKey2();
	}

	@Test
	public void upsertMangaDataNoSitei999() {
		TestManga testManga = new TestManga();

		//開始、終了の番号を指定する
		int startNo = 116;
		int endNo = 171;

		testManga.mangaDataNoSitei(startNo, endNo);
	}

	@Test
	/**
	 * マンガデータ作成(タイトル指定実行）※事前にSystemCommon#makeTittleListを設定すること
	 */
	public void makeMangaPage() {

		Map<String, Integer> tittleMap = MangaDb.getInstacce().getTittleNameMap();

		String title = "爆乳後輩に射精管理された上に寝取られマゾにされた件";

		Integer num = tittleMap.get(title);

		String path = "C:/南沢/999_その他/manga/" + title +  "/";

		String numSt = StringUtil.getInstance().changeFormat("0000", num);

		//マンガデータ作成
		makeMangaData(path, "1", numSt);

		//マンガタイトルデータ作成((レコード指定）マンガデータを作成後に実行)
		makeTitleDate(num);
	}

	@Test
	/**
	 * 漫画検索キー登録(レコード指定)
	 */
	public void updateMangaSearchKey2() {
		DbUtil du = DbUtil.getInstance();

		Map<Integer, String> tittleMap = MangaDb.getInstacce().getTittleMap();

		//作成するマンガの番号を指定する（例: 10=近所の叔母さん）
		Integer i1 = tittleMap.size() - 1;//
 		  i1 = 558;

 		SqlSession sqlSession = du.getNewSqlSession();

		List<Object[]> list = MangaSystemCommon.getInstance().makeTittleList2();

		Map<String, Object> map = new HashMap<String, Object>();

		map.put("corpCd", "01");
		map.put("delFlg", "0");
		map.put("recCd"	, "014");	//マンガデータのコード
		map.put("recKbn", "4");		//明細データ

		String[] KEY_T_1010 = {"corp_cd", "del_flg", "rec_cd", "rec_kbn", "item01"};

		for(Integer i = 0; i < list.size(); i++) {
			Object []data = (Object [])list.get(i);

	   		if(! i.equals(i1)) {
	   			continue;
	   		}

	   		String titleKey  = (String) data[0];//(String) mangaTitleKeyList.get(i);
	   		String searchKey = (String) data[1];//(String) mangaSearchKeyList.get(i);

	   		String key3 = StringUtil.getInstance().changeFormat("0000", i);

			map.put("item01", key3);	//ブックNo
			T_1010 t_1010 = (T_1010) bu.makeBean(new T_1010(), map);
			//画像データより、各マンガの１ページ目を取得（マンガIDを取得する為）


	    	String sql = du.getSelectSql(t_1010, KEY_T_1010);

	    	t_1010 = (T_1010) du.selectFirstOneRec(sqlSession, t_1010, sql);

//			t_1010 = (T_1010)du.selectFirstOneRec(t_1010, KEY_T_1010);
			t_1010.setItem16(titleKey + " "+ searchKey);

	          sql = du.getUpdateSql(SystemConst.PM_KEY_T_1010, t_1010);
	          sqlSession.getMapper(DbToolMapper.class).update(sql);


//			du.update(sqlSession, t_1010, SystemConst.PM_KEY_T_1010);
	   	}

		sqlSession.commit();

		sqlSession.close();
	}

	@Test
	/**
	 * 漫画検索キー登録(全件)
	 */
	public void updateMangaSearchKey() {
		DbUtil du = DbUtil.getInstance();

		SqlSession sqlSession = du.getNewSqlSession();

		List<Object[]> list = MangaSystemCommon.getInstance().makeTittleList2();

		Map<String, Object> map = new HashMap<String, Object>();

		map.put("corpCd", "01");
		map.put("delFlg", "0");
		map.put("recCd"	, "014");	//マンガデータのコード
		map.put("recKbn", "4");		//明細データ

		String[] KEY_T_1010 = {"corp_cd", "del_flg", "rec_cd", "rec_kbn", "item01"};


		for(Integer i = 0; i < list.size(); i++) {
			String []data = (String [])list.get(i);

	   		String titleKey  = data[0];
	   		String searchKey = data[1];

	   		String key3 = StringUtil.getInstance().changeFormat("0000", i);

			map.put("item01", key3);	//ブックNo
			T_1010 t_1010 = (T_1010) bu.makeBean(new T_1010(), map);
			//画像データより、各マンガの１ページ目を取得（マンガIDを取得する為）

	    	String sql = du.getSelectSql(t_1010, KEY_T_1010);

	    	t_1010 = (T_1010) du.selectFirstOneRec(sqlSession, t_1010, sql);


//			t_1010 = (T_1010)du.selectFirstOneRec(t_1010, KEY_T_1010);
			t_1010.setItem16(titleKey + " "+ searchKey);


	          sql = du.getUpdateSql(SystemConst.PM_KEY_T_1010, t_1010);
	          sqlSession.getMapper(DbToolMapper.class).update(sql);

//			du.update(sqlSession, t_1010, SystemConst.PM_KEY_T_1010);
	   	}

		sqlSession.commit();

		sqlSession.close();
	}


	@Test
	/**
	 * マンガタイトルデータ作成
	 */
	public void testMakeTitleDate() {
		//作成するマンガの番号を指定する（例: 10=近所の叔母さん）
		Integer num = 318;//

		//マンガタイトルデータ作成((レコード指定）マンガデータを作成後に実行)
		makeTitleDate(num);
	}

	@Test
	/**
	 * マンガデータ作成(全て)
	 */
	public void testUpsertManga() {

		Map<Integer, String> tittleMap = MangaDb.getInstacce().getTittleMap();

		for(Integer key = 0; key < tittleMap.size(); key++) {
			String numSt = StringUtil.getInstance().changeFormat("0000", key);
			String title = tittleMap.get(key);
			String path = "C:/南沢/999 その他/manga/" + title +  "/";
			makeMangaData(path, "1", numSt);
		}
	}

	@Test
	/**
	 *
	 */
	public void testUpdate_T1010() throws Exception {

		SqlSession sqlSession = du.getNewSqlSession();

		Map<String, Object> map = new HashMap<String, Object>();

		map.put("corpCd", "01");
		map.put("delFlg", "0");
		map.put("recCd"	, "014");	//マンガデータのコード
		map.put("recKbn", "4");		//明細データ
		map.put("item01", "0005");	//ブックNo

		T_1010 t_1010 = (T_1010) BeanUtil.getInstance().makeBean(new T_1010(), map);

		String[] KEY_T_1010 = {"corp_cd", "del_flg", "rec_cd", "rec_kbn", "item01"};

		//画像データより、各マンガの１ページ目を取得（マンガIDを取得する為）


    	String sql = du.getSelectSql(t_1010, KEY_T_1010);

    	t_1010 = (T_1010) du.selectFirstOneRec(sqlSession, t_1010, sql);

//		t_1010 = (T_1010)du.selectFirstOneRec(t_1010, KEY_T_1010);

		t_1010.setItem16("巨乳 女子校生 JK ");


        sql = du.getUpdateSql(SystemConst.PM_KEY_T_1010, t_1010);
        sqlSession.getMapper(DbToolMapper.class).update(sql);

//		du.update(sqlSession, t_1010, SystemConst.PM_KEY_T_1010);

		sqlSession.commit();

		sqlSession.close();
	}

	/**
	 * マンガデータ作成
	 * @param path		マンガ画像フォルダ
	 * @param sysKbn	システム区分:1=マンガ画像
	 * @param no		ページ番号
	 */
	public void makeMangaData(String path, String sysKbn, String no) {

		SqlSession sqlSession = du.getNewSqlSession();

		try {
			Long noCnt = 1L;

			boolean flg = true;

			while(flg){
				String hhh = StringUtil.getInstance().changeFormat("0000", noCnt);
				String file = hhh.toString() + ".jpg";
				String pathFile = path + file;
				String value = fu.file2string(pathFile);
				if(value == null) {
					flg = false;
				} else {
					upsert_T0001(sqlSession, value, sysKbn, no, noCnt.toString());
					noCnt ++;
				}
			}
		} catch (Exception e) {
			sqlSession.rollback();
			e.printStackTrace();
		} finally {
			sqlSession.close();
		}
	}

	/**
	 *
	 * @param sqlSession
	 * @param pathFile
	 * @param key1 システム区分(1:マンガ画像)
	 * @param key2 マンガ種類
	 * @param key3 ページ
	 * @throws Exception
	 */
	private void upsert_T0001(SqlSession sqlSession, String value, String key1, String key2, String key3) throws Exception {
		DbUtil du = DbUtil.getInstance();

		String []primaryKey = {"key1", "key2", "key3"};

		String sql = "";

		sql = "select count(*) from t_0001 where key1 = '" + key1 + "' and key2 ='" + key2 +
				                                                    "' and key3 ='" + key3 + "'";
		Long count = du.selectCount(sqlSession, sql);

		if (count > 0) {
			SystemDb.getInstance().updateLargeData(sqlSession, key1, key2, key3, value, primaryKey);
		} else {
			T_0001 t_0001 = SystemDb.getInstance().makeT_0001(key1, key2, key3, value);
			du.insert(sqlSession, t_0001);
		}
		sqlSession.commit();
	}

//	@Test
//	public void testInsert2() {
//		DbUtil du = DbUtil.getInstance();
//
//		StartupServlet startupServlet = new StartupServlet();
//
//		try {
//			startupServlet.init();
//		} catch (ServletException e1) {
//			e1.printStackTrace();
//		}
//
//		String imgPath = (String) si.getValue(Const.WEB_INF_REAL_PATH) + "/pic/";
//
//		T_0000 t_0000 = new T_0000();
//		t_0000.setKey1("ffffffff222566");
//		t_0000.setValue("aaaaaaaaaaaaaaaaaaaaaaaaaaaaa2222222222222222222222222222222222222222222222222222aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
//
//        SqlSession sqlSession = du.getSqlSession();
//
//        String []primaryKey = {"key"};
//
//        try {
//    		Integer count = du.delete(sqlSession, t_0000, primaryKey);
//
//            sqlSession.commit();
//        }catch(Exception e) {
//        	e.printStackTrace();
//        	sqlSession.rollback();
//        }
//
//        sqlSession.close();
//	}

	@Test
	public void testMangaCount() {

		Map<String, Object> map = new HashMap<String, Object>();

		map.put("corpCd"	, "01"		);
		map.put("delFlg"	, "0"		);
		map.put("recCd"		, "014"		);
		map.put("startYmd"	, "00010101");
		map.put("endYmd"	, "99991231");
		map.put("item01"	, "0001"	);

		T_1010 t_1010 = (T_1010) bu.makeBean(new T_1010(), map);

		Long suu = MangaDb.getInstacce().countMangaPageSuu(t_1010);

		SqlSession sqlSession = MangaDb.getInstacce().getSqlSession();

		System.out.println(suu);

		String selectSql = "";

		List<Object> objList = du.select(sqlSession, new T_1010(), selectSql);//MangaDb.getInstacce().getMangaList();

		for(Object obj: objList) {
			//DbUtil.print(obj);
			bu.printBean(obj);
		}
	}

	/**
	 * マンガタイトルデータ全件作成(マンガデータを作成後に実行)
	 */
	@Test
	public void makeTitleDate() {

		SqlSession sqlSession = du.getNewSqlSession();

		Map<String, Object> map = new HashMap<String, Object>();

		map.put("key1"	, "1");//1=マンガ画像
		map.put("key3"	, "1");//ページ画像 1=1ページ目

		T_0001 t_0001 = (T_0001)  bu.makeBean(new T_0001(), map);

		String sortKey = "order by key2";

		//画像データより、各マンガの１ページ目を取得（マンガIDを取得する為）
		List<Object> objList = du.selectNotNullMember(sqlSession, t_0001, sortKey);

		sqlSession.close();

		for(Integer cnt = 0; cnt < objList.size(); cnt ++) {
			makeTitleDate(cnt);
		}
	}

	@Test
	/**
	 * マンガデータ作成(番号指定実行）※事前にSystemCommon#makeTittleListを設定すること
	 */
	public void testFileWidthHeight() {
		Map<Integer, String> tittleMap = MangaDb.getInstacce().getTittleMap();
		//マンガの番号を指定する（例: 10=近所の叔母さん）
//ごめんね、今日デート行けなくなっちゃた  0006
		Integer num = 6; //
		String title = tittleMap.get(num);
		String pathAndfileName = "C:/南沢/999 その他/manga/" + title +  "/001.jpg";

		Integer[]data = fu.getFileWidthHeight(pathAndfileName);

		// 画像サイズを表示
		System.out.println( "横方向のピクセル=" + data[0] );
		System.out.println( "縦方向のピクセル=" + data[1] );
	}

	/**
	 * マンガタイトルデータ作成((レコード指定）
	 * @param num
	 */
	private void makeTitleDate(Integer num) {
		DbUtil du = DbUtil.getInstance();

		Map<Integer, String> tittleMap = MangaSystemCommon.getInstance().makeTittleMap();

		String title = tittleMap.get(num);

		String pathAndfileName = "C:/南沢/999_その他/manga/" + title +  "/0001.jpg";

		//画像の縦横長を取得する
		Integer[]data = fu.getFileWidthHeight(pathAndfileName);

		String numSt = StringUtil.getInstance().changeFormat("000000", num);

		SqlSession sqlSession = du.getNewSqlSession();

		String key1 = "1";
		String key2 = numSt;
		String key3 = "1";

		T_0001 t_0001 = SystemDb.getInstance().makeT_0001(key1, key2, key3, null);

    	String sql = du.getSelectSql(t_0001, SystemConst.PM_KEY_T_0001);

    	t_0001 = (T_0001) du.selectFirstOneRec(sqlSession, t_0001, sql);


//		t_0001 = (T_0001) du.selectFirstOneRec(sqlSession, t_0001, SystemConst.PM_KEY_T_0001);

		Long count = MangaDb.getInstacce().count_T_0001(t_0001.getKey2());

		//T_1010の基本データ
		Map<String, Object> map = new HashMap<>();

		map.put("corpCd"	, "01"				);
		map.put("delFlg"	, "0"				);
		map.put("recCd"		, "014"				);
		map.put("startYmd"	, "00010101"		);
		map.put("endYmd"	, "99991231"		);
		map.put("recKbn"	, "4"				); //レコード区分
		map.put("item01"	, t_0001.getKey2()	); //マンガID
		map.put("item02"	, count.toString()	); //ページ数
		map.put("item03"	, title				); //マンガタイトル
		map.put("item04"	, data[1].toString()); //画像高さ
		map.put("item05"	, data[0].toString()); //画像幅

		T_1010 t_1010 = (T_1010) bu.makeBean(new T_1010(), map);

		//項目名レコード作成
		try {
			DbUtil.getInstance().upsert(sqlSession, t_1010, SystemConst.PM_KEY_T_1010);
			sqlSession.commit();
			System.out.println(t_0001.getKey2() + "  " + count);
		} catch (Exception e) {
			e.printStackTrace();
			sqlSession.rollback();
		} finally {
			sqlSession.close();
		}
	}

	@Test
	/**
	 * テスト
	 * @param num
	 */
	public void test003() {

		String userId = "cc";

		Map<String, Object> map = MangaSystemCommon.getInstance().getMap(userId);

		System.out.println(map.get("111"));
	}
}
