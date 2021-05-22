package manga.common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import jp.co.fm.businessLogic.common.BeanUtil;
import jp.co.fm.businessLogic.common.DbUtil;
import jp.co.fm.businessLogic.system.SystemConst;
import jp.co.fm.businessLogic.system.SystemDb;
import jp.co.fm.businessLogic.table.T_0001;
import jp.co.fm.businessLogic.table.T_1010;
import manga.common.def.MangaSystemCommon;

public class VideoDbImpl implements VideoDb {

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
		StringBuffer sql = new StringBuffer("select * from t_1010 ");
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
	 *
	 * @param sql
	 * @return
	 */
	@Override
	public List<Object> getMangaList(String sql) {

        SqlSession sqlSession = DbUtil.getInstance().getNewSqlSession();

		List<Object> objList =  DbUtil.getInstance().select(sqlSession, new T_1010(), sql);

		sqlSession.close();

		return objList;
	}

	/**
	 * マンガリスト取得
	 * @return
	 */
	@Override
	public List<Object> getMangaList() {

		Map<String, Object> map = new HashMap<>();

		map.put("corpCd", "01");
		map.put("delFlg", "0");
		map.put("recCd"	, "014");
		map.put("recKbn", "4");

		T_1010 t_1010 = (T_1010) BeanUtil.getInstance().makeBean(new T_1010(), map);

		//画像データより、各マンガの１ページ目を取得（マンガIDを取得する為）
		List<Object> objList = DbUtil.getInstance().selectNotNullMember(t_1010);

		return objList;
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

		while (ite.hasNext() ) {
			map = new HashMap<>();

			String key   = ite.next();
			String value = mangaFrontMap.get(key);
			tittleList.add(value);
		}

	   	return tittleList;
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
		map.put("recCd"	, "014");	//マンガデータのコード
		map.put("recKbn", "4");		//明細データ
		map.put("item01", bookNo);	//ブックNo

		T_1010 t_1010 = (T_1010) BeanUtil.getInstance().makeBean(new T_1010(), map);

		String[] KEY_T_1010 = {"corp_cd", "del_flg", "rec_cd", "rec_kbn", "item01"};
		//画像データより、各マンガの１ページ目を取得（マンガIDを取得する為）

    	t_1010 = (T_1010) DbUtil.getInstance().selectFirstOneRec(t_1010, DbUtil.getInstance().getSelectSql(t_1010, KEY_T_1010));

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

	    	t_1010 = (T_1010) DbUtil.getInstance().selectFirstOneRec(sqlSession, t_1010, sql);

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
		T_0001 t_0001 = SystemDb.getInstance().makeT_0001("1", bookNo, null, null);
		String []primaryKey = {"key1", "key2"};

		SqlSession sqlSession = DbUtil.getInstance().getNewSqlSession();

    	String sql = DbUtil.getInstance().getSelectCountSql(t_0001, primaryKey);
    	Long count = DbUtil.getInstance().selectCount(sqlSession, sql);

		sqlSession.close();

		return count;
	}
}
