package workFlow.common;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import jp.co.fm.businessLogic.common.DateUtil;
import jp.co.fm.businessLogic.system.SystemService;

public class WorkFlowCommonImpl implements WorkFlowCommon {

	/**
	 * 新規に業務を取得する
	 * @param businessId 業務ID
	 * @return
	 */
	@Override
	public Map<String, String> getBusinessNew(String businessId) {
		Map<String, String> map = new HashMap<>();

		return map;
	}

	/**
	 * 業務を保存する
	 *
	 * @param businessMap
	 * @return
	 */
	@Override
	public void saveBusiness(Map<String, String> businessMap) {

	}

	/**
	 * 申請する
	 * @param applicationMap
	 */
	@Override
	public void apply(Map<String, String> applicationMap) {


		//トラッキングを記録する
		writeTracking(applicationMap);
	}

	/**
	 * 承認する
	 * @param applicationMap 申請書マップ
	 * @return
	 */
	@Override
	public  void approve(Map<String, Object> applicationMap) {


		//トラッキングを記録する
		writeTracking(applicationMap);
	}

	/**
	 * 差し戻す
	 * @param applicationMap
	 * @return
	 */
	@Override
	public Map<String, Object> sendBack(Map<String, Object> applicationMap) {

		//トラッキングを記録する
		writeTracking(applicationMap);

		return applicationMap;
	}



	/*
	 *
	 */
	@Override
	public Map<String, List<String>> getBumonUserMap() {

		Map<String, List<String>> rtnMap = new HashMap<>();



		return rtnMap;
	}

	/**
	 *
	 * @param stringList
	 * @return
	 */
	@Override
	public String getKayString(List<String> stringList) {
		StringBuffer rtnSb = new StringBuffer();

		for(String info: stringList) {
			rtnSb.append(info);
			rtnSb.append("\t");
		}

		rtnSb.delete(rtnSb.length() - 1, rtnSb.length());

		return rtnSb.toString();
	}

	/**
	 * トラッキングを取得する
	 * @param businessId
	 * @return
	 */
	@Override
	public Map<String, String> getTrackingMap(String businessId){
		Map<String, String> map = null;


		return map;
	}

	@Override
	public void makeTrackingMap(String businessId){
		Map<String, String> map = null;



	}

	/**
	 * 経路データを取得する
	 * @param businessId 案件ID
	 * @param timeStamp  日時(yyyymmddmi)
	 * @return
	 */
	@Override
	public Map<String, String> getRouteMap(String businessId, String timeStamp){
		Map<String, String> map = null;


		return map;
	}

	/**
	 * トラッキングを記録する
	 * @param applicationMap
	 * @return
	 */
	@Override
	public int writeTracking(Map applicationMap){


		return 0;
	}

	/**
	 * 交通費申請書（案件ID:00001_001)
	 * （設計図）
	 * @return
	 */
	@Override
	public Map<String, Object> getJob_00001_001(){

		//交通費申請書
		String gyomuId	= "00001_001";
		String name		= "交通費申請書";

		//デプロイ日時
		String deployTimeSt = DateUtil.getInstance().getComDateTime(4);

		//経路マップ作成
		Map<String, Object> routeMap = new TreeMap<>();

		routeMap.put("01", "担当１");
		routeMap.put("02", "担当２");
		routeMap.put("03", "担当３");
		routeMap.put("04", "担当４");
		routeMap.put("05", "担当５");
		routeMap.put("06", "担当６");

		Map<String, Object> rtnMap = new HashMap<>();
		rtnMap.put("route", routeMap);
		rtnMap.put("id", gyomuId);
		rtnMap.put("name", name);
		rtnMap.put("deployTimeSt", deployTimeSt);

		return rtnMap;
	}


//	/**
//	 * 案件情報Map取得
//	 * @param jobId 案件ID
//	 * @return
//	 */
//	public static Map<String, Object> getMap1002(String jobId) {
//		String key1 = "1002"; //ワークフロー設計図
//		String key2 = "0";    //削除フラグ
//		String key3 = jobId;
//		return SystemCommon.getMap_T_0000(key1, key2, key3);
//	}


	/**
	 * 案件定義情報（設計図）Map取得
	 * @param jobId 案件ID
	 * @return
	 */
	@Override
	public Map<String, Object> getMap1001(String jobId) {
		String key1 = "1001"; //ワークフロー設計図
		String key2 = "0";    //削除フラグ
		String key3 = jobId;
		return SystemService.getInstance().getMap_T_0001(key1, key2, key3);
	}

}
