package workFlow.common;

import java.util.List;
import java.util.Map;

public interface WorkFlowCommon {

	/**
	 * 新規に業務を取得する
	 * @param businessId 業務ID
	 * @return
	 */
	Map<String, String> getBusinessNew(String businessId);

	/**
	 * 業務を保存する
	 *
	 * @param businessMap
	 * @return
	 */
	void saveBusiness(Map<String, String> businessMap);

	/**
	 * 申請する
	 * @param applicationMap
	 */
	void apply(Map<String, String> applicationMap);

	/**
	 * 承認する
	 * @param applicationMap 申請書マップ
	 * @return
	 */
	void approve(Map<String, Object> applicationMap);

	/**
	 * 差し戻す
	 * @param applicationMap
	 * @return
	 */
	Map<String, Object> sendBack(Map<String, Object> applicationMap);

	/*
	 *
	 */
	Map<String, List<String>> getBumonUserMap();

	/**
	 *
	 * @param stringList
	 * @return
	 */
	String getKayString(List<String> stringList);

	/**
	 * トラッキングを取得する
	 * @param businessId
	 * @return
	 */
	Map<String, String> getTrackingMap(String businessId);

	void makeTrackingMap(String businessId);

	/**
	 * 経路データを取得する
	 * @param businessId 案件ID
	 * @param timeStamp  日時(yyyymmddmi)
	 * @return
	 */
	Map<String, String> getRouteMap(String businessId, String timeStamp);

	/**
	 * トラッキングを記録する
	 * @param applicationMap
	 * @return
	 */
	int writeTracking(Map applicationMap);

	/**
	 * 交通費申請書（案件ID:00001_001)
	 * （設計図）
	 * @return
	 */
	Map<String, Object> getJob_00001_001();

	/**
	 * 案件定義情報（設計図）Map取得
	 * @param jobId 案件ID
	 * @return
	 */
	Map<String, Object> getMap1001(String jobId);

	WorkFlowCommon workFlowCommon = new WorkFlowCommonImpl();

	public static WorkFlowCommon getInstance() {
		return workFlowCommon;
	}
}