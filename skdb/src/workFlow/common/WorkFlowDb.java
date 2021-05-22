package workFlow.common;

import org.apache.ibatis.session.SqlSession;

import jp.co.fm.businessLogic.table.T_1010;

public interface WorkFlowDb {

	/**
	 *
	 * @param sqlSession
	 * @param corpCd
	 * @param delFlg
	 * @param startYmd
	 * @param item01
	 * @return
	 */
	T_1010 selectMaxT_1010(SqlSession sqlSession, String corpCd, String delFlg, String recCd, String startYmd,
			String item01);

	/**
	 * 採番データ(T_1010(汎用テーブル))を取得する
	 *
	 * @param titleId
	 * @param titleName
	 * @return
	 */
	T_1010 getNextT_1010(String titleId, String titleName);

	public final WorkFlowDb workFlowDb = new WorkFlowDbImpl();

	public static WorkFlowDb getInstance() {
		return workFlowDb;
	}
}