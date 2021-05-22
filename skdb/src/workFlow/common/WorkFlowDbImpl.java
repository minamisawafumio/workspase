package workFlow.common;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import jp.co.fm.businessLogic.common.BeanUtil;
import jp.co.fm.businessLogic.common.DateUtil;
import jp.co.fm.businessLogic.common.DbUtil;
import jp.co.fm.businessLogic.common.StringUtil;
import jp.co.fm.businessLogic.system.SystemConst;
import jp.co.fm.businessLogic.table.DbToolMapper;
import jp.co.fm.businessLogic.table.T_1010;

public class WorkFlowDbImpl implements WorkFlowDb {

	/**
	 *
	 * @param sqlSession
	 * @param corpCd
	 * @param delFlg
	 * @param startYmd
	 * @param item01
	 * @return
	 */
	@Override
	public T_1010 selectMaxT_1010(SqlSession sqlSession, String corpCd, String delFlg, String recCd, String startYmd, String item01) {

		String sql = "SELECT * FROM t_1010 WHERE corp_cd='?' and del_flg='?' and rec_cd='?' and start_ymd='?' and item01='?' and "
					+ "to_number(item03,'999999999') = (SELECT max(to_number(item03,'999999999')) FROM t_1010 "
					+ "where corp_cd='?' and del_flg='?' and rec_cd='?' and start_ymd='?' and item01='?')";

		Object array[] = {corpCd, delFlg, recCd, startYmd, item01, corpCd, delFlg, recCd, startYmd, item01};
		sql =  StringUtil.getInstance().bindMoji(sql, array);

		T_1010 t_1010 = (T_1010) DbUtil.getInstance().selectFirstOneRec(sqlSession, new T_1010(), sql);

		return t_1010;
	}

	/**
	 * 採番データ(T_1010(汎用テーブル))を取得する
	 *
	 * @param titleId
	 * @param titleName
	 * @return
	 */
	@Override
	public T_1010 getNextT_1010(String titleId, String titleName) {
        SqlSession sqlSession = DbUtil.getInstance().getNewSqlSession();

		String comDateTime = DateUtil.getInstance().getComDateTime(5);

		Map<String, Object> map = new HashMap<>();
		map.put("corpCd"	, "01" );		//会社ｺｰﾄﾞ
		map.put("delFlg"	, "0"  );		//削除ﾌﾗｸﾞ
		map.put("recCd"		, "003");		//ﾚｺｰﾄﾞｺｰﾄﾞ
		map.put("startYmd"	, "00010101" );	//開始年月日
		map.put("item01"	, titleId);		//タイトルID（採番コード）

		T_1010 t_1010 = selectMaxT_1010(sqlSession,
											(String) map.get("corpCd")	,
											(String) map.get("delFlg")	,
											(String) map.get("recCd")	,
											(String) map.get("startYmd"),
											(String) map.get("item01"));

		try {
			if(t_1010 == null) {
				//該当レコードが存在しない場合
				map.put("item02"	, titleName);	//タイトル（番号名）
				map.put("item03"	, "0");			//開始番号
				map.put("item04"	, "9999999");	//終了番号
				map.put("item05"	, "0000000");	//現在番号
				map.put("item06"	, "1");			//増加値
				map.put("item07"	, "9999999");	//数値フォーマット
				map.put("item08"	, "0000000");	//増文字列フォーマット
				map.put("endYmd"	, "99991231" );
				map.put("recKbn"	, "4"		 );
				map.put("makeYmdhms", comDateTime);
				map.put("updtYmdhms", comDateTime);

				t_1010 = (T_1010) BeanUtil.getInstance().makeBean(new T_1010(), map);

				DbUtil.getInstance().insert(sqlSession, t_1010);
			}

			String selectForUpdateSql = DbUtil.getInstance().getSelectForUpdateSql(t_1010, SystemConst.PM_KEY_T_1010);

			//該当レコードを取得する
			t_1010 = (T_1010) DbUtil.getInstance().selectFirstOneRec(sqlSession, selectForUpdateSql);

			Integer add   = Integer.parseInt(t_1010.getItem06());
			Integer nowNo = Integer.parseInt(t_1010.getItem05());

			nowNo = nowNo + add;

			String format = t_1010.getItem08();

			//フォーマットを変換する
			String setNo = StringUtil.getInstance().changeFormat(format, nowNo);

			t_1010.setItem05(setNo);
			t_1010.setUpdtYmdhms(comDateTime);

	        String sql = DbUtil.getInstance().getUpdateSql(SystemConst.PM_KEY_T_1010, t_1010);
	        sqlSession.getMapper(DbToolMapper.class).update(sql);

			sqlSession.commit();

		} catch (Exception e) {
			sqlSession.rollback();
			e.printStackTrace();
		} finally {
			sqlSession.close();
		}

		return t_1010;
	}



}
