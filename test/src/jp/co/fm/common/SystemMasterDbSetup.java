package jp.co.fm.common;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import jp.co.fm.businessLogic.common.BeanUtil;
import jp.co.fm.businessLogic.common.DbUtil;
import jp.co.fm.businessLogic.table.T_1010;

public class SystemMasterDbSetup {

	private BeanUtil bu =BeanUtil.getInstance();

	private DbUtil du = DbUtil.getInstance();

	@Test
	public void makeT1010() {

        SqlSession sqlSession = DbUtil.getInstance().getNewSqlSession();

		String[] PM_KEY_T_1010 = {"corpCd", "delFlg", "recCd", "startYmd", "item01"};

		Map<String, Object> map = new HashMap<>();

		T_1010 t_1010 = null;

		try {
			map.put("corpCd"	, "01"					);
			map.put("delFlg"	, "0"					);
			map.put("recCd"		, "003"					);
			map.put("startYmd"	, "00010101"			);
			map.put("endYmd"	, "99991231"			);
			map.put("recKbn"	, "1"					);
			map.put("item01"	, "採番コード"			);
			map.put("item02"	, "番号名"				);
			map.put("item03"	, "開始番号"			);
			map.put("item04"	, "終了番号"			);
			map.put("item05"	, "現在番号"			);
			map.put("item06"	, "増加値"				);
			map.put("item07"	, "数値フォーマット"	);
			map.put("item08"	, "増文字列フォーマット");

			t_1010 = (T_1010) bu.makeBean(new T_1010(), map);

			du.upsert(sqlSession, t_1010, PM_KEY_T_1010);

			//-----------------------------------------------------------------------------------
			map.put("recKbn", "2"	   );
			map.put("item01", "String" );
			map.put("item02", "String" );
			map.put("item03", "Integer");
			map.put("item04", "Integer");
			map.put("item05", "Integer");
			map.put("item06", "Integer");
			map.put("item07", "String" );
			map.put("item08", "String" );

			t_1010 = (T_1010) bu.makeBean(new T_1010(), map);

			du.upsert(sqlSession, t_1010, PM_KEY_T_1010);

			//-----------------------------------------------------------------------------------
			map.put("recKbn", "4"		);
			map.put("item01", "01"		);
			map.put("item02", "受注番号");
			map.put("item03", "1"		);
			map.put("item04", "99999999");
			map.put("item05", "30"		);
			map.put("item06", "2"		);
			map.put("item07", "99999999");
			map.put("item08", "00000000");

			t_1010 = (T_1010) bu.makeBean(new T_1010(), map);

			du.upsert(sqlSession, t_1010, PM_KEY_T_1010);

			sqlSession.commit();
		} catch (Exception e) {
			sqlSession.rollback();
			e.printStackTrace();
		} finally {
			sqlSession.close();
		}
	}

}
