package jp.co.fm.businessLogic.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import jp.co.fm.businessLogic.common.BeanUtil;
import jp.co.fm.businessLogic.common.DbUtil;
import jp.co.fm.businessLogic.common.StringUtil;
import jp.co.fm.businessLogic.system.SystemConst;
import jp.co.fm.businessLogic.system.SystemDb;
import jp.co.fm.businessLogic.table.T_0001;
import jp.co.fm.businessLogic.table.T_1010;
import manga.common.def.MangaSystemCommon;

public class MangaDbImpl implements MangaDb {

	//当クラスは、SQLセッションを持つ（漫画のページングで使用している）
	private SqlSession sqlSession;

    @Override
	public SqlSession getSqlSession(){
		if (sqlSession == null) {
			sqlSession = DbUtil.getInstance().getNewSqlSession();
		}
		return sqlSession;
	}

    @Override
	public void closeSqlSession(){
		if (sqlSession != null) {
			sqlSession.close();
			System.out.println("closeSqlSession OK 1");
		}
		System.out.println("closeSqlSession OK 2");
	}

	//画像データ取得（７ページ分）
	//文字列 "000000" の3つ目の"0"が現在ページ。左端の "0"は + 3ページ。右端の"0"は - 3ページ
	//"1"はページデータが無い事の意味で、DBより取得する。"1"は、ページデータ有りの意味で、DBよりデータを取得しない。
	@Override
	public Map<String, Object> getPics(SqlSession sqlSession, Map<String, Object> inMap) {

		int pageNoInt = Integer.parseInt((String)inMap.get("picCount")) -
				          Integer.parseInt((String)inMap.get("pageNo"));

		String pageInfo = (String) inMap.get("needPageInfo");

		System.out.println(pageInfo);

		String bookNo = StringUtil.getInstance().changeFormat("000000", inMap.get("bookNo").toString());

		SystemDb sd = SystemDb.getInstance();

		for (Integer i = 1; i < 8 ; i++) {
			if("1".equals(pageInfo.substring(i - 1, i))) {
				inMap.put(i.toString(), sd.selectLargeData(sqlSession, "1", bookNo, Integer.toString(pageNoInt + 5 - i)));
			} else {
				inMap.put(i.toString(),"");
			}
		}


		return inMap;
	}

	/**
	 * マンガタイトルマップ取得
	 * @return
	 */
	@Override
	public Map<String, Integer> getNumTittleMap() {
	   	Map<String, Integer> map = new HashMap<>();

		List<Object[]> list = MangaSystemCommon.getInstance().makeTittleList2();

		for(Integer i = 0; i < list.size(); i++) {
			Object []data = (Object [])list.get(i);

			map.put((String) data[0], i);
		}

	   	return map;
	}

	/**
	 * マンガタイトルマップ取得
	 * @return
	 */
	@Override
	public Map<Integer, String> getTittleMap() {
	   	Map<Integer ,String> map = new HashMap<>();

		List<Object[]> list = MangaSystemCommon.getInstance().makeTittleList2();

		for(Integer i = 0; i < list.size(); i++) {
			Object []data = (Object [])list.get(i);

			map.put(i, (String) data[0]);
		}

	   	return map;
	}

	@Override
	public Map<String, Integer> getTittleNameMap() {
	   	Map<String, Integer> map = new HashMap<>();

		List<Object[]> list = MangaSystemCommon.getInstance().makeTittleList2();

		for(Integer i = 0; i < list.size(); i++) {
			Object []data = (Object [])list.get(i);

			map.put((String) data[0], i);
		}

	   	return map;
	}

	/**
	 *
	 * @param key
	 * @return
	 */
	@Override
	public String getWhereKu(String key, String ijyouKensuuKey) {

		String whereAdd = " where corp_cd='01' and del_flg='0' and rec_cd='014' and rec_kbn='4'";

		if(key != null) {
			//全角スペースを半角スペースに変換する
			key = key.replaceAll("　", " ");

			String array[] = key.split(" ");

			for(int i = 0; i < array.length; i++) {
				String key2 = array[i].trim();

				if(key2.length() > 0) {
					whereAdd = whereAdd + " and item16 like '%" + key2 + "%'";
				}
			}
		}

		String kensuuIjyou= "";

		if(ijyouKensuuKey != null && ijyouKensuuKey.length() > 0) {
			ijyouKensuuKey = ijyouKensuuKey.trim();

			kensuuIjyou = " and TO_NUMBER(item02,'999999')>=" + ijyouKensuuKey;
		}

		whereAdd = whereAdd + kensuuIjyou;

		return whereAdd;
	}

	/**
	 *
	 * @param key
	 * @param ijyouKensuuKey
	 * @return
	 */
	@Override
	public String getSelectSql(String key, String ijyouKensuuKey) {
		StringBuffer sql = new 	StringBuffer("select * from t_1010 ");
		sql.append(getWhereKu(key, ijyouKensuuKey));
		sql.append(" order by item03");
		return sql.toString();
	}

	/**
	 *
	 * @param key
	 * @param ijyouKensuuKey
	 * @return
	 */
	@Override
	public String getSelectCountSql(String key, String ijyouKensuuKey) {
		String sql = "select count(rec_cd) from t_1010 " + getWhereKu(key, ijyouKensuuKey);
		return sql;
	}

	/**
	* マンガタイトルのリストを取得する(要素は、マンガ件数 + タブ + マンガタイトル)
	* @return
	*/
	@Override
	public List<String> getTittleList(Map<String ,String> mangaFrontMap) {

		//タイトル-----------------------------------------------------------------
		List<String> tittleList = new ArrayList<>();

		Map<String, String> map;

		Iterator<String> ite = mangaFrontMap.keySet().iterator();

		String key   = null;
		String value = null;

		while (ite.hasNext() ) {
			key   = ite.next();
			value = mangaFrontMap.get(key);
			tittleList.add(value);
		}

	   	return tittleList;
	}

	@Override
	public T_1010 getMangaRec(SqlSession sqlSession, String bookNo) {

		Map<String, Object> map = new HashMap<>();

		map.put("corpCd", "01");
		map.put("delFlg", "0");
		map.put("recCd"	, "016");	//ビデオデータのコード
		map.put("recKbn", "4");		//明細データ
		map.put("item01", bookNo);	//ブックNo

		T_1010 t_1010 = (T_1010) BeanUtil.getInstance().makeBean(new T_1010(), map);

		String[] KEY_T_1010 = {"corp_cd", "del_flg", "rec_cd", "rec_kbn", "item01"};
		//画像データより、各マンガの１ページ目を取得（マンガIDを取得する為）

    	String sql = DbUtil.getInstance().getSelectSql(t_1010, KEY_T_1010);

    	t_1010 = (T_1010) DbUtil.getInstance().selectFirstOneRec(sqlSession, t_1010, sql);

		return t_1010;
	}

	/**
	 *
	 * @param bookNo
	 * @return
	 */
	@Override
	public T_1010 getMangaRec(String bookNo) {

		Map<String, Object> map = new HashMap<>();

		map.put("corpCd", "01");
		map.put("delFlg", "0");
		map.put("recCd"	, "016");	//ビデオデータのコード
		map.put("recKbn", "4");		//明細データ
		map.put("item01", bookNo);	//ブックNo

		T_1010 t_1010 = (T_1010) BeanUtil.getInstance().makeBean(new T_1010(), map);

		String[] KEY_T_1010 = {"corp_cd", "del_flg", "rec_cd", "rec_kbn", "item01"};
		//画像データより、各マンガの１ページ目を取得（マンガIDを取得する為）

    	String sql = DbUtil.getInstance().getSelectSql(t_1010, KEY_T_1010);

    	t_1010 = (T_1010) DbUtil.getInstance().selectFirstOneRec(t_1010, sql);

		return t_1010;
	}

	/**
	 *
	 * @param bookNo
	 * @return
	 */
	@Override
	public Long countMangaPageSuu(T_1010 t_1010) {

        SqlSession sqlSession = DbUtil.getInstance().getNewSqlSession();

		String pageSuuSt = null;

		try {
	    	String sql = DbUtil.getInstance().getSelectSql(t_1010, SystemConst.PM_KEY_T_1010);

	    	t_1010 = (T_1010)  DbUtil.getInstance().selectFirstOneRec(sqlSession, t_1010, sql);

			pageSuuSt = t_1010.getItem02();
		}catch(Exception e) {
			e.printStackTrace();
		}finally{
			sqlSession.close();
		}

		return Long.valueOf(pageSuuSt);
	}

	/**
	 * 画像の件数を取得する
	 * @param bookNo
	 * @return
	 */
	@Override
	public Long count_T_0001(String bookNo) {
		SqlSession sqlSession =  DbUtil.getInstance().getNewSqlSession();
		Long count = count_T_0001(sqlSession, bookNo);
		sqlSession.close();
		return count;
	}

	/**
	 * 画像の件数を取得する
	 * @param bookNo
	 * @return
	 */
	@Override
	public Long count_T_0001(SqlSession sqlSession, String bookNo) {
		T_0001 t_0001 = SystemDb.getInstance().makeT_0001("1", bookNo, null, null);
		String []primaryKey = {"key1", "key2"};

    	String sql = DbUtil.getInstance().getSelectCountSql(t_0001, primaryKey);
    	Long count = DbUtil.getInstance().selectCount(sqlSession, sql);

		return count;
	}

}
