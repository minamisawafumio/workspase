package workFlow.test;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import jp.co.fm.businessLogic.common.BeanUtil;
import jp.co.fm.businessLogic.common.CollectionUtil;
import jp.co.fm.businessLogic.common.DateUtil;
import jp.co.fm.businessLogic.common.DbUtil;
import jp.co.fm.businessLogic.common.JsonUtil;
import jp.co.fm.businessLogic.system.SystemConst;
import jp.co.fm.businessLogic.system.SystemService;
import jp.co.fm.businessLogic.table.T_1010;
import workFlow.common.WorkFlowCommon;
import workFlow.common.WorkFlowDb;
import workFlow.make.A0100_010.A0100_010_maker;

public class WorkFlowTest {

	private DbUtil du = DbUtil.getInstance();

	private BeanUtil bu = BeanUtil.getInstance();

	private SystemService ss = SystemService.getInstance();

	private WorkFlowDb wd = WorkFlowDb.getInstance();

    private JsonUtil ju = JsonUtil.getInstance();

    private DateUtil dt = DateUtil.getInstance();


	@Test
	public void test_0() {

		Map map = A0100_010_maker.getInstance().makeBusinessLogic();

		ju.print(map);

	}


	/**
	 * 該当データが無い場合、タイトルIDのカウンターレコードを作成する、
	 * 有る場合、カウントアップする
	 */
	@Test
	public void test_maxT1010() {

		T_1010 t_1010 = wd.getNextT_1010("00001_001", "あいうえおかきくけこ");

		ju.print(t_1010);
	}

	@Test
	public void test_makeT1010() {

        SqlSession sqlSession = DbUtil.getInstance().getNewSqlSession();

		String comDateTime = dt.getComDateTime(5);

		Map<String, Object> map = new HashMap<String, Object>();
		T_1010 t_1010 = null;
		try {
			map.put("corpCd"	, "01"		 );
			map.put("delFlg"	, "0"		 );
			map.put("recCd"		, "015"		 );
			map.put("startYmd"	, "00010101" );
			map.put("endYmd"	, "99991231" );
			map.put("recKbn"	, "1"		 );
			map.put("item01"	, "1"		 );
			map.put("updtYmdhms", comDateTime);

			t_1010 = (T_1010) bu.makeBean(new T_1010(), map);

			du.upsert(sqlSession, t_1010, SystemConst.PM_KEY_T_1010);

			sqlSession.commit();
		} catch (Exception e) {
			sqlSession.rollback();
			e.printStackTrace();
		} finally {
			sqlSession.close();
		}

		String sss = ju.makeObjectToJsonString(t_1010);

		System.out.println(sss);
	}


	@Test
	/**
	 * ワークフロー設計図をDBに保存する
	 */
	public void test_make_00001_001() {
		Map<String, Object> jobMap = WorkFlowCommon.getInstance().getJob_00001_001();

		String code		= "1001"; //ワークフロー設計図
		String delFlg	= "0";
		String jobId	= "00001_001";

		Integer rtnCd = ss.upsert_T_0001(code, delFlg, jobId, jobMap);
	}

	@Test
	/**
	 * 申請テスト
	 */
	public void test_sinsei() {

		String key1 = "1001"; //ワークフロー設計図
		String key2 = "0";    //削除フラグ
		String key3 = "00001_001";

		Map<String, Object> map1001 = ss.getMap_T_0001(key1, key2, key3);




		String key2_1 = "1002"; //ワークフロー案件
		String key2_3 = "1002"; //案件ID

		Map<String, Object> map1002 = new HashMap<String, Object>();

		Integer rtnCd = ss.upsert_T_0001(key2_1, "0", key2_3, map1002);


		CollectionUtil.getInstance().mapPrintln(map1001);


	}

	private void sinsei() {

	}



}
