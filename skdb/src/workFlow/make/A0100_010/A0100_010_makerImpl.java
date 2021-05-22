package workFlow.make.A0100_010;

import java.util.HashMap;
import java.util.Map;

import jp.co.fm.businessLogic.common.DateUtil;

public class A0100_010_makerImpl implements A0100_010_maker {

	@Override
	public Map<String, Object> makeBusinessLogic() {
		String businessId = "A0100_010";

		Map<String, Object> rtnMap		= new HashMap<>();

		Map<String, Object> map;
		//---ルートマップ作成-----------------------------------------------------------
		map	= makeRouteMap();
		rtnMap.put("route", map);

		//---項目マップ作成-------------------------------------------------------------
		map	= makeItemMap();
		rtnMap.put("item", map);

		//-----------------------------------------------------------
		rtnMap.put("makeDateTime", DateUtil.getInstance().getComDateTime(5));

		//-----------------------------------------------------------
		rtnMap.put("businessId", businessId);



		return rtnMap;
	}

	/**
	 * ルートを作成する
	 * @return
	 */
	private Map<String, Object> makeRouteMap() {
		Map<String, Object> trnMap	= new HashMap<>();

		Map<String, Object> detailsMap;
		//------------------------------------------------------------
		detailsMap	= new HashMap<>();
		detailsMap.put("アクティビティ名", "申請者");
		detailsMap.put("画面名", "A0100_010_form_01");
		detailsMap.put("次画面番号", "2");

		trnMap.put("1", detailsMap);

		//------------------------------------------------------------
		detailsMap	= new HashMap<>();
		detailsMap.put("アクティビティ名", "承認者A");
		detailsMap.put("画面名", "A0100_010_form_02");

		trnMap.put("2", detailsMap);

		//------------------------------------------------------------
		detailsMap	= new HashMap<>();
		detailsMap.put("アクティビティ名", "最終承認者");
		detailsMap.put("画面名", "A0100_010_form_03");

		trnMap.put("3", detailsMap);

		//-----------------------------------------------------------

		return trnMap;
	}

	/**
	 * 項目Map作成
	 * @return
	 */
	private Map<String, Object> makeItemMap() {
		Map<String, Object> trnMap	= new HashMap<>();

		Map<String, Object> detailsMap;
		//------------------------------------------------------------
		detailsMap = new HashMap<>();
		detailsMap.put("項目名", "aaaaaaaa");
		detailsMap.put("項目型", "数字");
		trnMap.put("1", detailsMap);

		//------------------------------------------------------------
		detailsMap = new HashMap<>();
		detailsMap.put("項目名", "bbbbbb");
		detailsMap.put("項目型", "文字");

		trnMap.put("2", detailsMap);

		return trnMap;

	}
}
