package manga.test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import jp.co.fm.businessLogic.common.BeanUtil;
import jp.co.fm.businessLogic.common.DbUtil;
import jp.co.fm.businessLogic.common.FileUtil;
import jp.co.fm.businessLogic.common.StringUtil;
import jp.co.fm.businessLogic.service.MangaDb;
import jp.co.fm.businessLogic.system.SystemConst;
import jp.co.fm.businessLogic.system.SystemDb;
import jp.co.fm.businessLogic.table.DbToolMapper;
import jp.co.fm.businessLogic.table.T_0001;
import jp.co.fm.businessLogic.table.T_1010;
import manga.common.def.MangaSystemCommon;

public class TestManga {
	private SystemDb sd   = SystemDb.getInstance();
	private DbUtil du     = DbUtil.getInstance();
	private BeanUtil bu   = BeanUtil.getInstance();
    private FileUtil fu   = FileUtil.getInstance();
    private StringUtil su = StringUtil.getInstance();

    public Integer mangaNo;
    public static String MANGA_PATH = "C:/南沢/999_その他/manga/";

    public String mangaTitle = "出戻り姫とニート王子";



	@Test
	/**
	 * マンガデータ作成(タイトル指定実行）※事前にSystemCommon#makeTittleListを設定すること
	 */
	public void makeMangaPage() {

		Integer num = getMangaPageNo(mangaTitle);

		String path = MANGA_PATH + mangaTitle +  "/";

		//マンガデータ作成
		makeMangaData(path, num);

		//マンガタイトルデータ作成((レコード指定）マンガデータを作成後に実行)
		makeTitleData(num);

		System.out.println(mangaTitle);
	}

	@Test
	/**
	 * 漫画検索キー登録(レコード指定)
	 */
	public void updateMangaSearchKey2() {

		Integer num = getMangaPageNo(mangaTitle);

        SqlSession sqlSession = du.getNewSqlSession();

		List<Object[]> list = MangaSystemCommon.getInstance().makeTittleList2();

		Map<String, Object> map = new HashMap<>();

		map.put("corpCd", "01");
		map.put("delFlg", "0");
		map.put("recCd"	, "014");	//マンガデータのコード
		map.put("recKbn", "4");		//明細データ

		String[] KEY_T_1010 = {"corp_cd", "del_flg", "rec_cd", "rec_kbn", "item01"};

		//指定漫画の画像データの件数を取得する
		Map<String, Object> t_0001map = new HashMap<>();
		String mangaNo = su.changeFormat("000000", num);
		t_0001map.put("key2", mangaNo);
		String[] KEY_T_0001 = {"key2"};
		T_0001 t_0001 = (T_0001) bu.makeBean(new T_0001(), t_0001map);
		String selectCountSql = du.getSelectCountSql(t_0001, KEY_T_0001);
		Long count = du.selectCount(sqlSession, selectCountSql);

		for(Integer i = 0; i < list.size(); i++) {
			Object []data = (Object [])list.get(i);

	   		if(! i.equals(num)) {
	   			continue;
	   		}

	   		String titleKey  = (String) data[0];//(String) mangaTitleKeyList.get(i);
	   		String searchKey = (String) data[1];//(String) mangaSearchKeyList.get(i);

	   		String item01 = su.changeFormat("000000", i);

			map.put("item01", item01);	//ブックNo
			T_1010 t_1010 = (T_1010) bu.makeBean(new T_1010(), map);
			//画像データより、各マンガの１ページ目を取得（マンガIDを取得する為）
	    	String sql = du.getSelectSql(t_1010, KEY_T_1010);

	    	t_1010 = (T_1010) du.selectFirstOneRec(sqlSession, t_1010, sql);
			t_1010.setItem16(titleKey + " "+ searchKey);
			t_1010.setItem02(count.toString());
			sql = du.getUpdateSql(SystemConst.PM_KEY_T_1010, t_1010);
			sqlSession.getMapper(DbToolMapper.class).update(sql);
	   	}

		sqlSession.commit();

		sqlSession.close();

		System.out.println(mangaTitle);
	}

	/**
	 * マンガデータ削除(タイトル名指定）
	 * ※事前にC:\pleiades\pleiades-2020-12-java-win-64bit-jre_20201222\workspace\skdb\WebContent\WEB-INF\temp\mangaData.jsonを設定すること
	 */
	@Test
	public void testDelete() {

		SqlSession sqlSession = du.getNewSqlSession();

		Integer num = getMangaPageNo(mangaTitle);

		String numSt = su.changeFormat("000000", num);

		T_0001 t_0001 = new T_0001();
		t_0001.setKey2(numSt);

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
	public void test3REN() {

		String stArray[] = {
				"母さん専用催眠アプリ",
				"バナナミルクセーキへようこそ",
				"催淫味くらべ",
				"学園の狩猟者 完全版",
				"ハメられインフィニティ",
				"天煌聖姫ヴァーミリオン THE COMIC",
				"いちゃらぶっ!",
				"いいなり！催眠彼女～隷属洗脳・生ハメ性活!!～ Complete版",
				"ももいろスクール ～とろぷる生ハメ授業中e&～",
				"小悪魔☆アラモード",
				"ヒプノブリンク Ver.1.0",
				"ハイブリッド・ガールフレンド 第01巻",
				"かなめDate 上",
				"NTR少女",
				"妻と娘の人間卒業式!! NTRクソビッチ母子爆誕☆",
				"一人の男に支配されている学園～時間停止＆催眠マインドコントロール",
				"女の子バザー",
				"オフサイドガール",
				"み～とほ～る",
				"純愛コラプス",
				"白衣のあなたに恋してる",
				"淫らな夏",
				"年上の彼女",
				"お姉様はショタ嗜好",
				"ミズギズム",
				"スーパークリティカル",
				"君ってドMでしょ",
		};

		for (String title: stArray) {
			mangaTitle = title;
			testDelete();
			makeMangaPage();
			updateMangaSearchKey2();
		}
	}


    /**
     * 漫画番号取得処理
     * @param iMangaName
     * @return
     */
    private Integer getMangaPageNo(String iMangaName) {
		Map<String, Integer> numTittleMap = MangaDb.getInstacce().getNumTittleMap();

		mangaNo = numTittleMap.get(mangaTitle);

		if (mangaNo == null) {
			System.out.println("【" + mangaTitle + "】というタイトルの漫画はありません");
			System.exit(0);
		}
    	return mangaNo;
    }

	@Test
	/**
	 * 漫画検索キー登録(全件)
	 */
	public void updateMangaSearchKey() {

        SqlSession sqlSession = du.getNewSqlSession();

		List<Object[]> list = MangaSystemCommon.getInstance().makeTittleList2();

		Map<String, Object> map = new HashMap<>();

		map.put("corpCd", "01");
		map.put("delFlg", "0");
		map.put("recCd"	, "014");	//マンガデータのコード
		map.put("recKbn", "4");		//明細データ

		String[] KEY_T_1010 = {"corp_cd", "del_flg", "rec_cd", "rec_kbn", "item01"};

		for(Integer i = 0; i < list.size(); i++) {
			String []data = (String [])list.get(i);

	   		String titleKey  = data[0];
	   		String searchKey = data[1];

	   		String key3 = su.changeFormat("0000", i);

			map.put("item01", key3);	//ブックNo
			T_1010 t_1010 = (T_1010) bu.makeBean(new T_1010(), map);
			//画像データより、各マンガの１ページ目を取得（マンガIDを取得する為）

	    	String sql = du.getSelectSql(t_1010, KEY_T_1010);

	    	t_1010 = (T_1010) du.selectFirstOneRec(sqlSession, t_1010, sql);

			t_1010.setItem16(titleKey + " "+ searchKey);

			sql = du.getUpdateSql(SystemConst.PM_KEY_T_1010, t_1010);
			sqlSession.getMapper(DbToolMapper.class).update(sql);
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
		makeTitleData(num);
	}

	@Test
	/**
	 * マンガデータ作成(全て)
	 */
	public void testUpsertManga() {

		Map<Integer, String> tittleMap = MangaDb.getInstacce().getTittleMap();

		for(int key = 0; key < tittleMap.size(); key++) {
			String title = tittleMap.get(key);
			String path = MANGA_PATH + title +  "/";
			makeMangaData(path, key);
		}
	}

	@Test
	/**
	 *
	 */
	public void testUpdate_T1010() throws Exception {

        SqlSession sqlSession = du.getNewSqlSession();

		Map<String, Object> map = new HashMap<>();

		map.put("corpCd", "01");
		map.put("delFlg", "0");
		map.put("recCd"	, "014");	//マンガデータのコード
		map.put("recKbn", "4");		//明細データ
		map.put("item01", "0005");	//ブックNo

		T_1010 t_1010 = (T_1010) bu.makeBean(new T_1010(), map);

		String[] KEY_T_1010 = {"corp_cd", "del_flg", "rec_cd", "rec_kbn", "item01"};

    	String sql = du.getSelectSql(t_1010, KEY_T_1010);
		//画像データより、各マンガの１ページ目を取得（マンガIDを取得する為）
    	t_1010 = (T_1010) du.selectFirstOneRec(sqlSession, t_1010, sql);

		t_1010.setItem16("巨乳 女子校生 JK ");

        sql = du.getUpdateSql(SystemConst.PM_KEY_T_1010, t_1010);
        sqlSession.getMapper(DbToolMapper.class).update(sql);

		sqlSession.commit();

		sqlSession.close();
	}

	/**
	 * マンガデータ作成
	 * @param path		マンガ画像フォルダ
	 * @param no		マンガ番号
	 */
	public void makeMangaData(String path, int no) {

		SqlSession sqlSession = du.getNewSqlSession();

		String numSt = su.changeFormat("000000", no);

		TreeMap<String, Integer> ts = fu.getFileOnlyNameTreeMapNoExtension(path);

		try {
			int startNo = 1;
			int endNo = ts.get(su.changeFormat("0000", ts.size()));;

			for (int i = startNo; i <= endNo; i++) {
				makeMangaData(sqlSession, path, numSt, i);
			}
		} catch (Exception e) {
			sqlSession.rollback();
			e.printStackTrace();
		} finally {
			sqlSession.close();
		}
	}

	@Test
	/**
	 * 漫画データ作成（開始＆終了ページ指定）
	 */
	public void mangaDataNoSitei(int startNo, int endNo) {

		Integer num = getMangaPageNo(mangaTitle);

		String path = MANGA_PATH + mangaTitle +  "/";

		SqlSession sqlSession = du.getNewSqlSession();

		String numSt = su.changeFormat("000000", num);

		try {
			for (int i = startNo; i <= endNo; i++) {
				makeMangaData(sqlSession, path, numSt, i);
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
	 * @param path
	 * @param numSt
	 * @param i
	 * @throws Exception
	 */
	public void makeMangaData(SqlSession sqlSession, String path, String numSt, int i) throws Exception {
		String hhh = su.changeFormat("0000", i);
		String file = hhh.toString() + ".jpg";
		String pathFile = path + file;
		String value = fu.file2string(pathFile);
		String deleteCrlfValue = su.deleteCRLF(value);
		upsert_T0001(sqlSession, deleteCrlfValue, "1", numSt, Integer.toString(i));
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

		String []primaryKey = {"key1", "key2", "key3"};

		String sql = "";

		sql = "select count(*) from t_0001 where key1 = '" + key1 + "' and key2 ='" + key2 +
				                                                    "' and key3 ='" + key3 + "'";
		Long count = du.selectCount(sqlSession, sql);

		if (count > 0) {
			sd.updateLargeData(sqlSession, key1, key2, key3, value, primaryKey);
		} else {
			T_0001 t_0001 = sd.makeT_0001(key1, key2, key3, value);
			du.insert(sqlSession, t_0001);
		}
		sqlSession.commit();
	}

	@Test
	public void testMangaCount() {

		Map<String, Object> map = new HashMap<>();

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

		List<Object> objList =  du.select(sqlSession, new T_1010(), selectSql);

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

		Map<String, Object> map = new HashMap<>();

		map.put("key1"	, "1");//1=マンガ画像
		map.put("key3"	, "1");//ページ画像 1=1ページ目

		T_0001 t_0001 = (T_0001)  bu.makeBean(new T_0001(), map);

		String sortKey = "order by key2";

		//画像データより、各マンガの１ページ目を取得（マンガIDを取得する為）
		List<Object> objList = du.selectNotNullMember(sqlSession, t_0001, sortKey);

		sqlSession.close();

		for(Integer cnt = 0; cnt < objList.size(); cnt ++) {
			makeTitleData(cnt);
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
		String pathAndfileName = MANGA_PATH + title +  "/001.jpg";

		Integer[]data = fu.getFileWidthHeight(pathAndfileName);

		// 画像サイズを表示
		System.out.println( "横方向のピクセル=" + data[0] );
		System.out.println( "縦方向のピクセル=" + data[1] );
	}

	/**
	 * マンガタイトルデータ作成((レコード指定）
	 * @param num
	 */
	private void makeTitleData(int num) {

		Map<Integer, String> tittleMap = MangaSystemCommon.getInstance().makeTittleMap();

		String title = tittleMap.get(num);

		String pathAndfileName = MANGA_PATH + title +  "/0001.jpg";

		//画像の縦横長を取得する
		Integer[]data = fu.getFileWidthHeight(pathAndfileName);

		String numSt = su.changeFormat("000000", num);

		SqlSession sqlSession = du.getNewSqlSession();

		String key1 = "1";
		String key2 = numSt;
		String key3 = "1";

		T_0001 t_0001 = sd.makeT_0001(key1, key2, key3, null);

    	String sql = du.getSelectSql(t_0001, SystemConst.PM_KEY_T_0001);

    	t_0001 = (T_0001) du.selectFirstOneRec(sqlSession, t_0001, sql);

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
			du.upsert(sqlSession, t_1010, SystemConst.PM_KEY_T_1010);
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
	 * マンガリスト取得
	 * @return
	 */
	public List<Object> getMangaList() {

		Map<String, Object> map = new HashMap<>();

		map.put("corpCd", "01");
		map.put("delFlg", "0");
		map.put("recCd"	, "016");
		map.put("recKbn", "4");

		T_1010 t_1010 = (T_1010) bu.makeBean(new T_1010(), map);

		//画像データより、各マンガの１ページ目を取得（マンガIDを取得する為）
		List<Object> objList = du.selectNotNullMember(t_1010);

		return objList;
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
